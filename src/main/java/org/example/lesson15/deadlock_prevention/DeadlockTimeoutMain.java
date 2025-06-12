package org.example.lesson15.deadlock_prevention;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockTimeoutMain {
    public static void main(String[] args) {
        //buradaki mantik bir thread bir lock u locklayamazsa surekli denemek yerine bir sure bekler
        //eger locku kilitleyemezse tum kilitlerini acar ve tekrardan kilitlemeye calisir
        //buradaki onemli nokta her thread icin bekleme suresi ayni olamamli bu yuzden random bir zaman bvelirlenir (zamanlar ayni olrsa live lock olur)

        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Runnable runnable1 = new RunnableTimeout(lock1, lock2);
        Runnable runnable2 = new RunnableTimeout(lock2, lock1);

        Thread th1 = new Thread(runnable1);
        Thread th2 = new Thread(runnable2);

        th1.start();
        th2.start();
    }
}
