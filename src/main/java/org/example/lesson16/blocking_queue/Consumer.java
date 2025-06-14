package org.example.lesson16.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = queue.take();
                System.out.println("Consumed: " + data + " by [" + Thread.currentThread().getName() + "]");
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted");
            }
        }
    }
}
