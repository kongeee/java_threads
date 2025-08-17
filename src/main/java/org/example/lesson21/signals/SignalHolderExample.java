package org.example.lesson21.signals;

public class SignalHolderExample {
    public static void main(String[] args) {
        SignalHolder signalHolder = new SignalHolder();

        Thread waiter = new Thread( () -> {
            try {
                signalHolder.doWait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread notifier = new Thread( () -> {
            signalHolder.doNotify();
        });

        //once notify gelmesine ragmen calisiyor
        notifier.start();
        waiter.start();

    }

}
