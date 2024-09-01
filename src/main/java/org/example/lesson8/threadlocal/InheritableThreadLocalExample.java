package org.example.lesson8.threadlocal;

public class InheritableThreadLocalExample {
    public static void main(String[] args) {
        //Threadlocal her thread icin ayri demistik
        //InheritableThreadLocal de ise child threadler parentleri ile ayni veriyi kullanabilirler. Bu veriler child a aktarilir
        //Bagimsiz thrreadler yine bu verileri goremez th1 ve th2 gibi

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            System.out.println("===== THREAD 1 =====");
            threadLocal.set("Thread1 threadLocal variable");
            inheritableThreadLocal.set("Thread1 inheritableThreadLocal variable");

            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());

            Thread childThread = new Thread(() -> {
                System.out.println("===== THREAD 1 CHILD =====");

                System.out.println(threadLocal.get()); //null cunku bunun threadlocal i set edilmedi ayri
                System.out.println(inheritableThreadLocal.get()); // yukaridaki deger cunku parenti ile ayni yeri kullaniyor
            });

            childThread.start();
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("===== THREAD 2 =====");
            threadLocal.set("Thread2 threadLocal variable");
            inheritableThreadLocal.set("Thread2 inheritableThreadLocal variable");

            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
        });
        thread2.start();
    }
}
