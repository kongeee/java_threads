package org.example.lesson15.deadlock_prevention;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockReorderingMain {
    public static void main(String[] args) {
        //locklar ayni sirayla gonderilirse deadlock olusmaz

        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        //Asagidaki gibi yapilirsa deadlock olur
//        Runnable runnable1 = new MyRunnable(lock1, lock2);
//        Runnable runnable2 = new MyRunnable(lock2, lock1);

        //ayni sira olursa problem yok
        Runnable runnable1 = new MyRunnable(lock1, lock2);
        Runnable runnable2 = new MyRunnable(lock1, lock2);

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
    }
}
