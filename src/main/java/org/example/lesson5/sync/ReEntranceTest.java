package org.example.lesson5.sync;

public class ReEntranceTest {

    public static void main(String[] args) {
        //inc ve incAndGet metodu sync olunca
        //once th1 calisirsa : Thread1 inc method 2sn Thread2 inc method 2sn Thread2 incAndGet method 2sn
        //once th1 calisirsa : Thread2 inc method 2sn Thread2 incAndGet method 2sn Thread1 inc method 2sn

        //inc() kendisi degil de icine sync koyup count u monitor yaparsak
        //Thread1 inc method Thread2 inc method 2sn Thread2 incAndGet method 2sn
        //Cunku inc count u kitliyor sadece ama incAndGet this i monitor olarak kabul ediyor



        MyClass myClass = new MyClass();

        Thread th1 = new Thread(()->{
            myClass.inc();
        }, "Thread1");

        Thread th2 = new Thread(()->{
            System.out.println(myClass.incAndGet());
        }, "Thread2");

        th1.start();
        th2.start();


    }

}

class MyClass{
    private Integer count = 255;

    public  void inc(){
        synchronized(this.count) {
            count++;

            System.out.println(Thread.currentThread().getName() + " inc method");

        }



    }

    public synchronized Integer incAndGet(){

        inc();
        System.out.println(Thread.currentThread().getName() + " incAndGet method");

        return count;


    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
