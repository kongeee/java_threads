package org.example.lesson8.threadlocal;

public class ThreadLocalInitValueExample {
    public static void main(String[] args) {
        /*
            ThreadLocal e initila deger verebiliriz.
            ThreadLocal her thread icin ayri oldugundan new ile verilen init deger de her thread icin farkli olur
            th1 in init objsii farkli th2 nin init objsi farkli adrestedir

         */

        //1.yontem
        ThreadLocal<Object> threadLocal1 = new ThreadLocal<>() {
            @Override
            protected Object initialValue() {
                return new Object();
            }
        };

        ThreadLocal<Object> threadLocal2 = ThreadLocal.withInitial(Object::new);

        Thread thread1 = new Thread(() -> {
            System.out.println("th1 threadLocal1 : " + threadLocal1.get());
            System.out.println("th1 threadLocal2 : " + threadLocal2.get());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("th2 threadLocal1 : " + threadLocal1.get());
            System.out.println("th2 threadLocal2 : " + threadLocal2.get());
        });

        Thread thread3 = new Thread(() -> {
            threadLocal1.set(new Object());
            System.out.println("th3 threadLocal1 before remove: " + threadLocal1.get());

            threadLocal1.remove(); //Burada default deger initValue olur iki sout'tan farkli sonuc gelir
            System.out.println("th3 threadLocal1 after remove: " + threadLocal1.get());
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
