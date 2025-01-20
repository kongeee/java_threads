package org.example.lesson13.executor_service;

import java.util.concurrent.*;

public class ExecutorServiceSubmit {
    public static void main(String[] args) {
        //submit metodu execute gibi calisir ama execute void donerken submit Future nesnesi doner.
        //Future nesnesi taskin statusu hakkinda bilgi verir
        //submit, runnable ve callable alabilir,
        //runnable deger donmezken, callable deger doner

        //Future ve Task su sekilde baglanir:
        //  Task, thread pool' a submit edilir
        //  thread pool, bir future olusturur ve task ile baglar
        //  Future yaratilir yaratilmaz hemen main thread e doner (daha task baslamamisken)
        //  task bitince future nesnesinin durumunu update eder. boylede main thread taskin durumunu gorebilir



        //runRunnableExample();
        runCallableExample();


    }

    private static void runRunnableExample() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(getRunnable("Runnable Task"));

        System.out.println(future.isDone()); //false donecek cunku submitten hemen sonra burasi calisiyor ve muhtemelen task bitmemis olacak

        try {
            future.get(); //task bitene kadar bekler. Bir seye atamadik cunku runnable void doner
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(future.isDone()); //yukarida task bitene kadar bekledigi icin true doner

        executorService.shutdown();
    }

    private static void runCallableExample() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(getCallable("Callable Task"));

        System.out.println(future.isDone()); //false donecek cunku submitten hemen sonra burasi calisiyor ve muhtemelen task bitmemis olacak

        try {
            String callableMessage = (String) future.get(); //task bitene kadar bekler. Callable bir deger doner
            System.out.println(callableMessage);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(future.isDone()); //yukarida task bitene kadar bekledigi icin true doner

        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> {
            String completeMessage = Thread.currentThread().getName() + " with message: " + message;
            System.out.println(completeMessage);
        };
    }

    private static Callable getCallable(String message) {
        return () -> {
            String completeMessage = Thread.currentThread().getName() + " with message: " + message;
            return completeMessage;
        };
    }
}
