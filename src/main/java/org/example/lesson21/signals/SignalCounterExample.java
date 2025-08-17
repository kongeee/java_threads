package org.example.lesson21.signals;

public class SignalCounterExample {
    public static void main(String[] args) {
        SignalCounter signalCounter = new SignalCounter();

        Thread waiter = new Thread( () -> {
            try {
                signalCounter.doWait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread notifier = new Thread( () -> {
            signalCounter.doNotify();
        });
        //yerleri ters
        notifier.start();
        waiter.start();

    }
}
