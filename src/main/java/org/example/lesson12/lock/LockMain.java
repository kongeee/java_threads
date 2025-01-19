package org.example.lesson12.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockMain {
    public static void main(String[] args) {
        //critical section yaratmak icin kullanilirlar.
        //lock ile belirlenen critical section a yalnizca tek bir thread girebilir
        //sync bloklarina benzerdir CounterSync ve CounterLock siniflarini incele.
        //lock ve unlock metodu genelde try finally icinde kullanilir. lock edildikten sonra diger threadler kilitlenmesin diye
        //  hata alinsa bile lock un belli bir yerde kaldirilmasi gerek. Veya getCounter daki gibi return den once unlock gerek.

        //ReentrantLock, bir thread tekrar tekrar lock kullanabilir demek, ornegin thread x, a metounda lock kullandi
        //  sonra a metodu byi cagirdi orda da lock kullanabilir yani 2 kere kitler. Acarken de 2 kez acmasi gerek.

        //lock layan thread o sectionu tekrar lock layabiliyorsa buna re-entrant denir, re-entrant olmayan lock lar reentrance lockout
        // problemine sebep olabilir.

        //ReentrantLock(true) diyere lock u fair hale getirebiliriz,
        //  ornegin th1 locklamis olsun th2 locklamayi deniyor ama th1 tarafindan locklu sonra th3 locklamayi deniyor ama yine locklu
        //  th1 in isi bittikten sonra th2 mi th3 mu olacagi normalde belirsiz ama fair true yaparak ilk kim geldiyse onun locku almasi saglanir.
        // fair olmayan locklarda bekleyen threadler hic locklayamayabilir ve starvation olur.

        //sync sadece bir metodu kapsayabilir ama lock yapisinda bir metodda kilitlenip baska bir metodda kilit acilabilir
        //visibility ve happens before guarantee sunar (sync gibi)
        //sync blocklar her zaman reentrant tir. Lock lar olmak zorunda degil. custom bi lock yaparak bunu ortadan kaldirabilriz
        //sync blocklar her zaman fair degildir

        //lockInterruptibly() -> Thread interrupt olmadiysa calisir, yoksa InterruptedException verir.
        //tryLock() -> eger locklayabiliyorsa true locklayamiyorsa false doner. (fair true olsa bile dikkate almaz!!!)
        //  timeout verilebilir belirlenen sure icerisinde locklarsa true locklayamazsa false (bu sayede fair yapilabilir)
        //holdCount() -> thread kac kere locklamis
        //queueLength -> lock un acilmasiniu bekleyen thread sayisi
        //hasQueueThisThread(Thread thread) -> verilen thread beklemede mi
        //hasQueuedThreads() -> ehrhangi bir thread beklemede mi
        //isFair() -> fair mi
        //isLocked() -> kilitli mi
        //isHeldByCurrentThread() -> mevcut thread tarafindan mi locklandi?
    }
}
//---------------------------------------------------------------------------------
class CounterSync{
    private int counter = 0;

    public synchronized void increment() {
        this.counter++;
    }

    public synchronized int getCounter() {
        return this.counter;
    }
}
//---------------------------------------------------------------------------------
class CounterLock{
    private int counter = 0;

    private Lock lock = new ReentrantLock();


    public void increment() {
        try {
            lock.lock();
            this.counter++;
        }finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        try {
            lock.lock();
            return this.counter;
        }finally {
            lock.unlock();
        }
    }
}