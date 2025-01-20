package org.example.lesson13.executor_service;

import java.util.concurrent.*;

public class ExecutorServiceMain {

    public static void main(String[] args) {
        //Tasklari submit etmek icin bir thread pool saglar
        createExecutorServiceWithFactoryMethod();

    }

    private static void createExecutorServiceWithFactoryMethod() {
        //Factory metod ile olusturulabilir
        //birden fazla factory method bulunur
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(getRunnable("Task 1"));
        executorService.execute(getRunnable("Task 2"));
        executorService.execute(getRunnable("Task 3"));

        executorService.shutdown();
    }

    private static void createExecutorServiceWithThreadPoolExecutor() {
        //Executors.newFixedThreadPool dedigimizde de ThreadPoolExecutor concrete sinifindan nesne uretiyor

        int corePoolSize = 10;
        int maxPoolSize = 20;
        long keepAliveTime = 3000;

        //bir task bitince yenisini hemen alir
        //Executors.newFixedThreadPool(10) buna benzer bir yapi kurar core=10, max=10, keepAlive=0 olur

        //Executors.newFixedThreadPool(1) ve Executors.newSingleThreadExecutor() ayni sey

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize, //kac thread ile baslayacak
                maxPoolSize,  //max kac thread e kadar cikabilir
                keepAliveTime, //ekstra uretilen threadler ne kadar yasayacak
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(128) //Tasklarin bulundugu ve okundugu yer
        );

        /*
        //bu sekilde de uretilebilir
        //tasklari ilerde calisacak sekilde plnalamaya olanak saglar

        Executors.newScheduledFixedThreadPool(10) ile benzer bir sey yapilir
        ExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(corePoolSize);
        */
    }

    private static Runnable getRunnable(String message) {
        return () -> {
            String completeMessage = Thread.currentThread().getName() + " with message: " + message;
            System.out.println(completeMessage);
        };
    }
}
