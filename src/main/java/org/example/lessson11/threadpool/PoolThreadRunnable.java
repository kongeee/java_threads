package org.example.lessson11.threadpool;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable{

    //taski alir calistirir ve sonraki taska gecer

    private Thread thread;
    private BlockingQueue<Runnable> taskQueue;
    private boolean isStopped;

    public PoolThreadRunnable(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped()){
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            }catch (Exception e){
                //log etc
            }

        }

    }

    public synchronized void doStop(){
        isStopped = true;
        thread.interrupt();

    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}
