package org.example.lesson5.sync;

import java.util.function.Consumer;

public class SynchronizedLambda {
    private static Object object = null;

    public static synchronized void setObject(Object obj) {
        object = obj;
    }

    public static void consumeObject(Consumer consumer) {
        consumer.accept(object);
    }

    public static void main(String[] args) {
        consumeObject( (obj) -> {
            synchronized (SynchronizedLambda.class){ //setObject ile ayni monitor objecte sahipler baska thread set e erismek isterse bloklanir
                System.out.println(object);
            }
        });

        consumeObject( (obj) -> {
            synchronized (String.class){ //setObject ile farkli monitor objecte sahipler baska thread set e erismek isterse bloklanmaz
                System.out.println(object);
            }
        });

        /*
        consumeObject( (obj) -> {
            synchronized (this){ //lambda expressionda this olmaz
                System.out.println(object);
            }
        });
         */



    }
}
