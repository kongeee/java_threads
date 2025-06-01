package org.example.lesson14.deadlock;

import java.util.concurrent.locks.Lock;

public class Runnable1 implements Runnable{

    private Lock lock1;
    private Lock lock2;

    public Runnable1(Lock lock1, Lock lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " attempting to lock Lock1");
        lock1.lock();
        System.out.println("Lock1 locked!");

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ignore) {
        }

        System.out.println(threadName + " attempting to lock Lock2");
        lock2.lock();
        System.out.println("Lock2 locked!");

        //kilitleri ac

        System.out.println(threadName + " unlocking Lock1");
        lock1.unlock();
        System.out.println("Lock1 unlocked!");

        System.out.println(threadName + " unlocking Lock2");
        lock2.unlock();
        System.out.println("Lock2 unlocked!");
    }
}
