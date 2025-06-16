package org.example.lesson17.compare_and_swap;

public class ProblematicLock implements MyLock{

    private volatile boolean locked = false;

    public void lock() {
        //locked birden fazla thread tarafindan ayni anda true olabilir boyle olunca bekleme dongusunden cikarlar ama yalnizca 1 thread kilidi elinde tutabilmeli
        //burada check-then-act problemi var once kotrol etmeli sonra herekete gecmeli
        while (locked) {
            //busy wait - surekli kontrol eder aktiftir, uyumaz
        }

        locked = true;
    }

    public void unlock() {
        locked = false;
    }
}
