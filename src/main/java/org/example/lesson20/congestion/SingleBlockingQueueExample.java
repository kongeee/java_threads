package org.example.lesson20.congestion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SingleBlockingQueueExample {
    public static void main(String[] args) {
        /*
            Thread-1 consumer finished 314295
            Thread-0 consumer finished 330749
            Thread-2 consumer finished 354956

            threadler tek bir queue dan tüketirken aynı anda sadece bir tanesi erişebileceği için diğerleri bloklanır
            Bu bloklanmalardan kaynaklı adeletsiz bir dağılım olur
            Bloklanma demek bir thread in bosa beklemesi anlamina geliyor. Bu da thread in is yapma zamaninin bosa gitmesi demek.

            Congestion : tıkanıklık demek

            Tıkanklık olduğunu anlamanın 2 yolu var (olay jcm de oluyor)

            1. prodfilleme aracı kullanarak boştaki-blocklanmış threadlere bakmak çok bekleyen thread varsa
            2. throughput ölçmek. consumer saysıını kademeli artır iş süresine bak. th ekledikçe süre kısalır bir noktadan sonra süre artıyorsa tıkanıklık vardır aynı kaynak için sırada bekliyor olabilirelr.

         */

        int objectsToProduce = 1_000_000;

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(objectsToProduce);

        ConsumerRunnable[] consumerRunnables = new ConsumerRunnable[3];

        synchronized (SingleBlockingQueueExample.class) {
            for (int i = 0; i < consumerRunnables.length; i++) {
                consumerRunnables[i] = new ConsumerRunnable(blockingQueue);
                Thread consumingThread = new Thread(consumerRunnables[i]);
                consumingThread.start();
            }
        }

        Thread producingThread = new Thread(() -> {
            for (int i = 0; i < objectsToProduce; i++) {
                try {
                    blockingQueue.put("" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("All objects produced");
            System.out.println("produced " + objectsToProduce);

            synchronized (SingleBlockingQueueExample.class) {
                for (ConsumerRunnable consumerRunnable : consumerRunnables) {
                    consumerRunnable.stop();
                }
            }
        });

        producingThread.start();
    }
}
