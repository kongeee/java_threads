package org.example.lesson14.deadlock;

public class RunnableSync implements Runnable{

    private Object obj1;
    private Object obj2;

    public RunnableSync(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " attempting to lock obj");
        synchronized (obj1) {
            System.out.println(threadName + " locked obj");

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException ignore) {
            }

            System.out.println(threadName + " attempting to lock obj");
            synchronized (obj2) {
                System.out.println(threadName + " locked obj");
            }
            System.out.println(threadName + " unlocked second object");

        }
        System.out.println(threadName + " unlocked first object");
    }
}
