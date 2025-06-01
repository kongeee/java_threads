package org.example.lesson14.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {

    public static void main(String[] args) {
        //Bu durumun oluşması için en az 2 thread gerekiyor.

        //Thread1 lock1 i kilitlesin ve Thread2 de lock2 yi kilitlesin.
        //Thread1 lock2 yi ve Thread2 de lock1 i kilitlemek istesin.
        //Thread1 lock2 yi kilitleyemez çünkü Thread2 lock2 yi kilitlemiş durumda ve lock1 in lock ının kalkmasını bekliyor
        //Bu durumda 2 thread de birbirini sonsuza dek beklediğinden bir dead lock oluşur.
        //Thread1 lock 1 i unlock etmeden lock2 yi çağırmak istemektedir. Thread2 de lock2 yi unlock etmeden lock1 i kilitlemeye çalışıyor

        //basic bir deadlock
//        Lock lock1 = new ReentrantLock();
//        Lock lock2 = new ReentrantLock();
//
//        Runnable1 runnable1 = new Runnable1(lock1, lock2);
//        Runnable2 runnable2 = new Runnable2(lock1, lock2);
//
//        Thread thread1 = new Thread(runnable1);
//        Thread thread2 = new Thread(runnable2);
//
//        thread1.start();
//        thread2.start();


        //lock, sync bloklarinda da olabilir. çünkü bir obje sync bloguna girdiğinde ona başka kimse erişecemez üzerine bir lock konur
        Object obj1 = new Object();
        Object obj2 = new Object();

        RunnableSync runnableSync1 = new RunnableSync(obj1, obj2);
        RunnableSync runnableSync2 = new RunnableSync(obj2, obj1);

        Thread thread1 = new Thread(runnableSync1);
        Thread thread2 = new Thread(runnableSync2);

        thread1.start();
        thread2.start();

        //her iki durumda da lock1 ve lock2 sirali olursa - her 2 thread icin de - deadlock ortadan kalkar
    }
}
