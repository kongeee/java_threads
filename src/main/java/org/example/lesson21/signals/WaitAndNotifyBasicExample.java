package org.example.lesson21.signals;

public class WaitAndNotifyBasicExample {
    public static void main(String[] args) {
        /*
            ayni senkronized objesi uzerinde bir thread belirli bir islem yaptiktan sonra wait diyerek bekler
            wait dendiniginde kilit kalkar ve th1 beklemeye baslar (kilit kalkmazsa th2 giremez buraya)
            th2 gelir ve isini ypar
            th2 notify metodunu cagirir
            wait olan th1 uyanir ve islerini yapmaya devam eder

            sync blogu zorunludur!
            wait notify ve notifyAll Object sinifindan gelir

            bir kez wait dendi mi notify veya notifyAll diyene kadar thread uyanmaz (bazi istisnalar haric)

            th1 wait oncesi --bunu yazdiktan sonra bekler
            th2 notif oncesi -- bu yazildiktan sonra notify cagirilir artir h1 devam edebilir
            th2 notif sonrasi
            th1 wait sonrasi
            !!!!th2 den notfiy kaldirilirsa program sonlanmaz
            !!!!th2 sync blogunu terk ettikten sonra th1 calisir. Cunku th2 lockluyor cikarken lock birakiyor notify dediginde lock kalkmiyor bitince kalkiyor
            !!!!th2 bittiktens onra th1 waitten cikar yani th1 tekrar kilit koymus olur nesneye

            notify sadece o nesne uzerinde bekleyen herhangi bir threadi uyandirir
            notifyAll tum waitinleri uyandirir ama uyanan thread kilit koydugundan sirayla nesneye erisirler
         */

        Object monitorObject = new Object();

        Thread waitngThread = new Thread( () -> {
            synchronized (monitorObject) {
                try {
                    System.out.println("th1 wait oncesi");
                    monitorObject.wait();
                    System.out.println("th1 wait sonrasi");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread notifyingThread = new Thread( () -> {
           synchronized (monitorObject) {
               System.out.println("th2 notif oncesi");
               monitorObject.notify();
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               System.out.println("th2 notif sonrasi");
           }
        });

        waitngThread.start();
        notifyingThread.start();
    }
}
