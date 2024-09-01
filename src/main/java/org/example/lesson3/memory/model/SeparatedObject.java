package org.example.lesson3.memory.model;

public class SeparatedObject {
    public static void main(String[] args) {

        /*
        //MyRunnable icindeki count fieldi classa ait olmasina ragmen threadler icin shared degildir cunku runnable1 ve runnable 2 farkli instanceler
        //Bu yuzden kod calistirildiginda ikisi de 100000 degerini gosterecek

        MyRunnable runnable1 = new MyRunnable();
        MyRunnable runnable2 = new MyRunnable();

        Thread thread1 = new Thread(runnable1, "Thread1");
        Thread thread2 = new Thread(runnable2, "Thread2");

        thread1.start();
        thread2.start();

         */

        /*

        //local objelerin adresleri farklidir cunku local degiskenler her thread icin ayri olusturulur
        //instance objelerin adresleri aynidir cunku bir tane nesne urattik ve onu parametre olarak gectik iki thread de ayni objeye erisiyor

        Object obj = new Object();

        MyRunnable runnable1 = new MyRunnable(obj);
        MyRunnable runnable2 = new MyRunnable(obj);

        Thread thread1 = new Thread(runnable1, "Thread1");
        Thread thread2 = new Thread(runnable2, "Thread2");

        thread1.start();
        thread2.start();

        */

    }
}
