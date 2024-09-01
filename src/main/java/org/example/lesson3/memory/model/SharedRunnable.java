package org.example.lesson3.memory.model;

public class SharedRunnable {
    public static void main(String[] args) {
        /*

        //Iki thread de ayni runnable nesnesini kullaniyor. Bu yuzden count shared variable olur.
        //Burada race condition olusur. Cunku thread1 ve thread2 ayni anda memoryden register'a veri okuyup 1 artirip yazabilirler. Normalde 2 artmasi gerekirken 1 artar
        //Her iki thread de ayni count alanina eristiginden sonucun 200000 olmasi beklenir (th1 100000, th2 100000 artiracak) ama sonuc soyle olabilir :
        //Thread2 : 124844
        //Thread1 : 135269
        //veya
        //Thread1 : 120470
        //Thread2 : 124528
        //her calistirmada farkli

        MyRunnable runnable = new MyRunnable();

        Thread thread1 = new Thread(runnable, "Thread1");
        Thread thread2 = new Thread(runnable, "Thread2");

        thread1.start();
        thread2.start();

         */


        /*
        //local objelerin adresleri farklidir cunku local degiskenler her thread icin ayri olusturulur
        //instance objelerin adresleri aynidir cunku bir tane nesne urattik ve onu parametre olarak gectik iki thread de ayni objeye erisiyor


        Object obj = new Object();

        MyRunnable runnable = new MyRunnable(obj);

        Thread thread1 = new Thread(runnable, "Thread1");
        Thread thread2 = new Thread(runnable, "Thread2");

        thread1.start();
        thread2.start();

         */
    }
}
