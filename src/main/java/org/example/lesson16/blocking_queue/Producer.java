package org.example.lesson16.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //her 1 saniyede bir milisaniye cinsinden zamani queue ya ekler

        while (true) {
            long timeMillis = System.currentTimeMillis();

            try {
                queue.put("" + timeMillis);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted");
            }
            sleep(1000L);
        }
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            //ignore
        }
    }
}
