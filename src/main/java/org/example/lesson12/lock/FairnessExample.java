package org.example.lesson12.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class FairnessExample {
    public static void main(String[] args) {

        //true ya da false olmasi sirali calisacagi anlamina gelmez th6 th3 ten once start i cagirmis olabilir
        //true yaparsak starvationunh onune geceriz lock u cagiranlar sirayla isletilir
        //false olursa lock bosa dusunce hangisi once davranirsa o kilitler
        //bu durumda bir thread hic once davranamayabilir ve surekli bekleyebilir
        Lock lock = new ReentrantLock(false);
        
        Runnable runnable = () -> lockSleepUnlock(lock, 10);

        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");
        Thread thread3 = new Thread(runnable, "Thread 3");
        Thread thread4 = new Thread(runnable, "Thread 4");
        Thread thread5 = new Thread(runnable, "Thread 5");
        Thread thread6 = new Thread(runnable, "Thread 6");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }

    private static void lockSleepUnlock(Lock lock, long millis) {

        try {
            lock.lock();
            System.out.printf("%s holds the lock.\n", Thread.currentThread().getName());
            sleep(millis);
        }finally {
            lock.unlock();
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
