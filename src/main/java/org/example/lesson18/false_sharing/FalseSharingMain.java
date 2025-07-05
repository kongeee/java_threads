package org.example.lesson18.false_sharing;

import java.util.stream.LongStream;

public class FalseSharingMain {

    public static void main(String[] args) {
        long iterations = 100_000_000;

        Counter1 counter1 = new Counter1();
        //counter1 ile ayni referans verilince count1 ve count2 degerleri muhtemelen ayni blok icinde olduklarindan beraber invalide oluyorlar
        // bu sekilde calisinca 2600 ms suruyor
        Counter1 counter2 = counter1;

        //bu sekilde yaparsak farkli nesneler oldugundan muhtemelen ayni cache blogunda olmayacaklar genelde max 200 ms oluyor
        //bazen ayni blokta oluyorlar sanirim 2200 ms cikiyor
        //Counter1 counter2 = new Counter1();



        Thread thread1 = new Thread(
                () -> {
                    long startTime = System.currentTimeMillis();
                    LongStream.range(0, iterations).forEach((i) -> counter1.count1++);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Total time = " + (endTime-startTime) + " ms (first)");
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    long startTime = System.currentTimeMillis();
                    LongStream.range(0, iterations).forEach((i) -> counter2.count2++);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Total time = " + (endTime-startTime) + " ms (second)");
                }
        );

        thread1.start();
        thread2.start();
    }
}
