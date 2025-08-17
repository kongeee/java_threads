package org.example.lesson21.signals;

public class SignalCounter {
    // kac sinyal gonderildi
    // ve kac thread bekliyor bilgilerini tutar

    private int signals = 0;
    private int threadsWaiting = 0;

    public void doNotify() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " calling notify");
            if (threadsWaiting == 0) {
                signals++;
            }
            this.notify();
            System.out.println(Thread.currentThread().getName() + " exited notify");
        }
    }

    public void doWait() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " calling wait");
            if (signals > 0) {
                System.out.println(Thread.currentThread().getName() + " signal was already raised");
                signals--;
                return;
            }
            threadsWaiting++;
            this.wait();
            threadsWaiting--;
            System.out.println(Thread.currentThread().getName() + " exited wait");
        }
    }
}
