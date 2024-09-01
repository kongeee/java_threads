package org.example.lessson11.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue;
    private List<PoolThreadRunnable> runnables = new ArrayList<>();
    private boolean isStopped;

    public ThreadPool(int noOfThreads, int maxNoOfTasks) {
        taskQueue = new ArrayBlockingQueue<>(maxNoOfTasks);

        for (int i = 0; i < noOfThreads; i++) {
            PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);

            runnables.add(poolThreadRunnable);
        }

        for (PoolThreadRunnable poolThreadRunnable : runnables) {
            new Thread(poolThreadRunnable).start();
        }
    }

    public synchronized void execute(Runnable task) {
        if (isStopped) {
            throw new IllegalArgumentException("ThreadPool is stopped!");
        }
        taskQueue.offer(task);
    }

    public synchronized void stop() {
        isStopped = true;
        for (PoolThreadRunnable poolThreadRunnable : runnables) {
            poolThreadRunnable.doStop();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
