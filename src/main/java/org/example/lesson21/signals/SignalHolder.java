package org.example.lesson21.signals;

public class SignalHolder {

    /*
        Burada notify bosa giderse uyari veriliyor
        ama birden fazla wait varsa bu da ise yaramaz en garanti yontem SignalCounter
     */

    private boolean isSignalRaised  = false;
    private boolean isThreadWaiting = false;

    public void doWait() throws InterruptedException {
        synchronized (this) {
            if (isSignalRaised) {
                System.out.println(Thread.currentThread().getName() + " signal was already raised");
                isSignalRaised = false;
                return;
            }
            System.out.println(Thread.currentThread().getName() + " calling wait()");
            isThreadWaiting = true;
            this.wait();
            isThreadWaiting = false;
            System.out.println(Thread.currentThread().getName() + " exited wait()");
        }
    }

    public void doNotify(){
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " calling notify()");
            if (!isThreadWaiting){
                isSignalRaised = true;
            }
            this.notify();
            System.out.println(Thread.currentThread().getName() + " exited notify()");
        }
    }
}
