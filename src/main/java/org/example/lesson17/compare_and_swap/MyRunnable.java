package org.example.lesson17.compare_and_swap;

public class MyRunnable implements Runnable {

    private final MyLock lock;
    private Integer value;

    public MyRunnable(MyLock lock, Integer value) {
        this.lock = lock;
        this.value = value;
    }

    @Override
    public void run() {
        lock.lock();

        value++;

        System.out.println(value);

        lock.unlock();
    }
}
