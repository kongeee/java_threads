package org.example.lesson13.executor_service;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceShutdown {
    public static void main(String[] args) {

        //shutDownMethods();
        taskCancelling();


    }

    private static void shutDownMethods() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //submit edilen tum tasklar bitince service i kapatir. (Tum tasklar tamamlanan kadar main thread burada bloklanir)
        executorService.shutdown();

        //aninda sonlandirir. (hicbir zaman baslamayan tasklari doner. yarida biraktiklarini donmez)
        List<Runnable> runnables = executorService.shutdownNow();

        try {
            //Tum tasklari verilen sure icinde islemeye calisir. eger isleyemezse false doner
            boolean isTerminated = executorService.awaitTermination(3000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void taskCancelling() {
        //Henuz islenmeye baslanmamais bir taski su sekilde cancel edebiliriz.

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> future = executorService.submit(getCallable("Task 1.1"));

        System.out.println("isDone: " + future.isDone());//buyuk ihtimalle false donecek

        boolean mayInterrupt = true;

        boolean wasCancelledByMe = future.cancel(mayInterrupt); //boolean doner. cancel islemi basarili mi degil mi onu bildirir

        System.out.println("Was canncelled? : " + wasCancelledByMe);


        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        } catch (CancellationException ce) {
            System.out.println("cannot call future,get() since task was cancelled");
        }

        System.out.println("isDone: " + future.isDone());//true (bitmedi ama tamamlandi)
        System.out.println("isCancelled: " + future.isCancelled());

        executorService.shutdown();


    }


    private static Callable<String> getCallable(String message) {
        return () -> "%s with message: %s".formatted(Thread.currentThread().getName(), message);
    }
}
