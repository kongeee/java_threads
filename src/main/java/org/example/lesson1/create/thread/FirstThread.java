package org.example.lesson1.create.thread;

public class FirstThread {
    public static void main(String[] args) {

        //https://jenkov.com/tutorials/java-concurrency/index.html

        //Thread olusturma, isim verme, birden fazla thread ile calisma, threadi bekletme,
        // threadi durdurma, deamon thread (main ile sonlanan), thread join (threadlerin birini beklemesi), project loom -> lightweight concurrency in java

        //Javada threadlerle calismak icin birden fazla yol vardir

        /*

        //1 - Thread sinifini extend bir sinif
        MyThread myThread1 = new MyThread();
        myThread1.start(); // bir thread start metodu ile bir kez baslar 2. kez bunu yazarsan IllegalThreadStateException firlatir

         */

        /*

        //2 - Runnable interfaceini impleme eden bir sinif (recommended)
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        */

        /*

        //3 - Runnable anonim class

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable running");
                System.out.println("Runnable finished");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        */

        /*
        //4 - Runnable lambda expression (functional interface oldugu icin)

        Runnable runnable = () -> {
            System.out.println("Lambda Runnable running");
            System.out.println("Lambda Runnable  finished");
        };

        Thread thread = new Thread(runnable);
        thread.start();

         */
        // ------------------------------------------------------------------------------------

         /*
        //Threadlere isim verilebilir, main metodu da bir threaddir ve ismi main dir

        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " running");
        };

        Thread thread = new Thread(runnable, "myFirstThread");
        thread.start();

        */

        /*
         //Birden fazla threadle calismak icin

        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " running");
        };

        Thread thread1 = new Thread(runnable, "Thread1");
        thread1.start();
        Thread thread2 = new Thread(runnable, "Thread2");
        thread2.start();
        */

        /*
        //Thread.sleep(millis) ile threadi bekletebiliriz
        Runnable runnable = () -> {
            System.out.println("Thread running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread finished");
        };

        Thread thread = new Thread(runnable);
        thread.start();
         */


        /*
        //threadin calismasini durdurma
        StoppableRunnable stoppableRunnable = new StoppableRunnable();
        Thread thread = new Thread(stoppableRunnable);
        thread.start();

        try {
            Thread.sleep(5000); //main thread icin
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Requesting stop");
        stoppableRunnable.requestStop();
        System.out.println("Stop requested");

        */


        /*
        //Javada threadler mainthread sonlansa dahi calismaya devam eder. Eger bir threadi main thread bitince sonlandirmak istersek setDeamon metodunu kullaniriz
        Runnable runnable = () -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread running");
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(true); // bunu yazmasaydik while dan dolayi sonsuza kadar calisacakti ama thread deamon oldugu icin main bitince biticek
        thread.start();

        try {
            Thread.sleep(3100); // main thread icin
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        */

        /*
        //thread join

        Runnable runnable = () -> {
            for(int i = 0 ; i<5 ; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread running");
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(true); // normalde main thread bitince bitmesi lazim
        thread.start();

        try {
            thread.join(); // main thread bunun bitmesini bekler
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        */

    }


}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread running");
        System.out.println("MyThread finished");
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("MyRunnable running");
        System.out.println("MyRunnable finished");
    }
}

class StoppableRunnable implements Runnable{

    private boolean stopRequested = false;

    public synchronized void requestStop() { //synchronized ile ayni anda yalnizca 1 threadin eristigi garanti edilir
        this.stopRequested = true;
    }

    public synchronized boolean isStopRequested() {
        return this.stopRequested;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        System.out.println("StoppableRunnable running");
        while (!isStopRequested()) {
            sleep(1000);
            System.out.println("...");
        }
        System.out.println("StoppableRunnable finished");
    }
}
