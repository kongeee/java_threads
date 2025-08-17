package org.example.lesson21.signals;

public class SignalCarrierExample {

    public static void main(String[] args) {
        SignalCarrier signalCarrier = new SignalCarrier();

        Thread waiter = new Thread( () -> {
            try {
                signalCarrier.doWait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread notifier = new Thread( () -> {
            signalCarrier.doNotify();
        });

        //!!BUNLARIN SIRASI DEGISIRSE NOTIFY SINYALI WAIT TEN ONCE CALISIR VE BOSA GIDER
        //WAIT DE HICBIR NOTIFY GELMEDIGI ICIN KILITLENIR VE PROGRAM KILITLENIR
        //BU DURUMU GIDERMEK ICIN SURAYA BAK : SignalHolder
        waiter.start();
        notifier.start();
    }

}


