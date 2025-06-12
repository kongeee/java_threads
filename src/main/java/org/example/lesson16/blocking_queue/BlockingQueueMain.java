package org.example.lesson16.blocking_queue;

import java.util.concurrent.*;

public class BlockingQueueMain {
    public static void main(String[] args) throws InterruptedException {
        //Buffer gorevi gorur.
        //threadler kuyruga ekleme yaprken, diger threadler de kuyruktan elemanlari sirayla ceker.

        //Bircok cesidi vardir:
        /*
            ArrayBlockingQueue;
            LinkedBlockingQueue;
            DelayQueue;
            LinkedBlockingDeque;
            LinkedTransferQueue;
            PriorityBlockingQueue;
            SynchronousQueue;
         */

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        //queue ya veri ekleme (enqueue)
        queue.put("str1");
        queue.put("str2");

        //queue dan veri cekme (dequeue)
        System.out.println(queue.take()); //kuyrugun ilk elemaninin ceker (ilk eklenen)
        System.out.println(queue.take()); //kuyrugun ikinci elemaninin ceker


        //buradaki ornekte tek bir thread uzerunden queue ya ekleme ve queue dan okuma yapilmistir normalde bu islemler icin farkli threadler kullanilir.
    }
}
