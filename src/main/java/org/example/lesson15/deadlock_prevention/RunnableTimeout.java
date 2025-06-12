package org.example.lesson15.deadlock_prevention;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class RunnableTimeout implements Runnable {
    private final Lock lock1;
    private final Lock lock2;

    public RunnableTimeout(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        while(true) {
            int failureCount = 0;
            while (!areBothLocked()) {
                failureCount++;
                System.err.println(threadName + " failed to lock both locks. Waiting a bit after [" + failureCount + "] tries");
                sleep((long)(100.0 * Math.random()));

            }
            if (failureCount > 0) {
                System.out.println(threadName + " locks both locks successfully after [" + failureCount + "] tries");
            }

            lock2.unlock();
            lock1.unlock();
        }
    }

    private boolean areBothLocked() {
        try {
            boolean lock1Succeeded = lock1.tryLock(1000, TimeUnit.MILLISECONDS);
            if (!lock1Succeeded) {
                return false;
            }
        } catch (InterruptedException e) {
            return false;
        }

        try {
            boolean lock2Succeeded = lock2.tryLock(1000, TimeUnit.MILLISECONDS);
            if (!lock2Succeeded) {
                lock1.unlock(); //kilitlenmis tum kilitleri ac
                return false;
            }
        } catch (InterruptedException e) {
            return false;
        }

        return true;
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {

        }
    }
}
