package org.example.lesson21.signals;

public class SignalCarrier {
    /*
        Senkronizayon ve sinyal yonetimini burada halledip disariya daha anlasilir ve souyut bir yapi sunuyor
     */

    public void doWait() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " calling wait()");
            this.wait();
            System.out.println(Thread.currentThread().getName() + " exited wait()");
        }
    }

    public void doNotify(){
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " calling notify()");
            this.notify();
            System.out.println(Thread.currentThread().getName() + " exited notify()");
        }
    }

    public void doNotifyAll() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " calling notifyAll()");
            this.notifyAll();
            System.out.println(Thread.currentThread().getName() + " exited notifyAll()");
        }
    }
}