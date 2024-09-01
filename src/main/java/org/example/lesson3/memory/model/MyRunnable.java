package org.example.lesson3.memory.model;

import java.util.Objects;

public class MyRunnable implements Runnable{

    private int count = 0;
    private Object instanceObject;

    MyRunnable(){}
    MyRunnable(Object obj){this.instanceObject = obj;}

    @Override
    public void run() {
        Object localObject = new Object();
        System.out.println(Thread.currentThread().getName() + ", local object : " + localObject);
        System.out.println(Thread.currentThread().getName() + ", class field object : " + instanceObject);

        for (int i = 0; i < 100_000; i++) {
            this.count++;
        }

        System.out.println(Thread.currentThread().getName() + " : " + count);
    }


}
