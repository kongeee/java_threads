package org.example.lesson8.threadlocal;

public class ThreadLocalLazyInitExample {
    public static void main(String[] args) {
        //bazen threadlocal degeri runtime'da gelebilir bu durumda asagidaki gibi bir yol izlenebilir

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        //main thread in threadlocal i
        String value = threadLocal.get();
        if (value == null) {
            threadLocal.set("Lazily set value");
        }
        System.out.println(threadLocal.get());
    }
}
