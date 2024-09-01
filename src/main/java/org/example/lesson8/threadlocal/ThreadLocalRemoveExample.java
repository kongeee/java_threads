package org.example.lesson8.threadlocal;

public class ThreadLocalRemoveExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            threadLocal.set("Thread 1 local variable");

            String value = threadLocal.get();
            System.out.println(value);

            threadLocal.remove(); //default deger null oldugundan siliince th1 icin threadlocalde null degeri olur
            value = threadLocal.get();
            System.out.println(value);
        });


        Thread thread2 = new Thread(() -> {
            threadLocal.set("Thread 2 local variable");

            String value = threadLocal.get(); //Bu th1 den etkilenmez kendi localinde calisir
            System.out.println(value);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            threadLocal.remove();
            value = threadLocal.get();
            System.out.println(value);
        });

        thread1.start();
        thread2.start();
    }
}
