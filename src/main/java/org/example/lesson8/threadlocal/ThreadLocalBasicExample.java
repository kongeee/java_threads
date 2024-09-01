package org.example.lesson8.threadlocal;

public class ThreadLocalBasicExample {
    public static void main(String[] args) {
        /*
            ThreadLocal sinifi ile her thread icin ayri veriler tutabiliriz.
            ThreadLocal icerisindeki veriler thread'e baglidir. Baska bir thread bu veriyi okuyamaz veya yazamaz.

            !Ileriki konu icin uyari: Threadlocal Task bazinda degil thread bazinda ayrisir
         */
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread thread1 = new Thread(() -> {
           threadLocal.set("Thread 1 local variable");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String value = threadLocal.get();
            System.out.println(value);
        });


        Thread thread2 = new Thread(() -> {
            threadLocal.set("Thread 2 local variable"); //her thread in ayri local i oldugu icin burada yazilan deger usttekine etki etmedi

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String value = threadLocal.get();
            System.out.println(value);
        });

        thread1.start();
        thread2.start();

    }

}
