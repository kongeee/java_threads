package org.example.lesson15.deadlock_prevention;

import java.util.concurrent.locks.Lock;

public class MyRunnable implements Runnable{

    private final Lock lock1;
    private final Lock lock2;

    public MyRunnable(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " tries to lock first LOCK");
        lock1.lock();
        System.out.println(threadName + " locks first LOCK");

        sleep(2000L);

        System.out.println(threadName + " tries to lock second LOCK");
        lock2.lock();
        System.out.println(threadName + " locks second LOCK");

        System.out.println("unlocking second LOCK");
        lock2.unlock();

        System.out.println("unlocking first LOCK");
        lock1.unlock();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {

        }
    }
}
