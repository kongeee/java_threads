package org.example.lesson20.congestion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MultiBlockingQueueExample {
    public static void main(String[] args) {

        /*
            Thread-2 consumer finished 333333
            Thread-1 consumer finished 333333
            Thread-0 consumer finished 333334

            Burada ise daha adil bir dağılım var. Çünkü her biri kendi queue suna sahip ve bloklanmıyor
            producer da basit bir mod işlemi ile queue lara dağıtıyor yükleri
            Aynı zamanda burada iş çalma da yapıalabilir. (work stealing) - fork join polda da bu kullanilir
            Örneğin thread1 in işi bitti ama th2 de daha çook iş var ordan iş çalıp kendisi yapabilir

         */

        int objectsToProduce = 1_000_000;

        BlockingQueue<String>[] blockingQueues = new BlockingQueue[3];

        for (int i = 0; i < blockingQueues.length; i++) {
            blockingQueues[i] = new ArrayBlockingQueue(objectsToProduce);
        }

        ConsumerRunnable[] consumerRunnables = new ConsumerRunnable[3];

        synchronized (MultiBlockingQueueExample.class) {
            for (int i = 0; i < consumerRunnables.length; i++) {
                consumerRunnables[i] = new ConsumerRunnable(blockingQueues[i]);
                Thread consumingThread = new Thread(consumerRunnables[i]);
                consumingThread.start();
            }
        }

        Thread producingThread = new Thread(() -> {
            for (int i = 0; i < objectsToProduce; i++) {
                try {
                    blockingQueues[i%blockingQueues.length].put("" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("All objects produced");
            System.out.println("produced " + objectsToProduce);

            synchronized (MultiBlockingQueueExample.class) {
                for (ConsumerRunnable consumerRunnable : consumerRunnables) {
                    consumerRunnable.stop();
                }
            }
        });

        producingThread.start();
    }
}
