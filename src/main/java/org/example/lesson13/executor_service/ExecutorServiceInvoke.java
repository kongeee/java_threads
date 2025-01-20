package org.example.lesson13.executor_service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceInvoke {
    public static void main(String[] args) {

        //runInvokeAnyExample();
        runInvokeAllExample();

    }

    private static void runInvokeAnyExample() {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(getCallable("Task 1"));
        callableList.add(getCallable("Task 2"));
        callableList.add(getCallable("Task 3"));

        try {
            //sadece callable list alir. timeoutlu versionu da var
            //herhangi biri tamamlandiginde geriye don. Sadece biri tamamlandiginden onun degerini al. digerlerini de iptal et
            String result = executorService.invokeAny(callableList);
            System.out.println("Result is : " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

    }

    private static void runInvokeAllExample() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(getCallable("Task 1"));
        callableList.add(getCallable("Task 2"));
        callableList.add(getCallable("Task 3"));

        try {
            //sadece callable list alir. timeoutlu versionu da var
            //Hepsi tamamlandiginde bitir.
            List<Future<String>> result = executorService.invokeAll(callableList);
            for (Future<String> future : result){
                System.out.println("Result is : " + future.get());
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }

    private static Callable<String> getCallable(String message) {
        return () -> "%s with message: %s".formatted(Thread.currentThread().getName(), message);
    }
}
