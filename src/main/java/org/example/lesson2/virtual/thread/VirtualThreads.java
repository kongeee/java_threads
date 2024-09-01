package org.example.lesson2.virtual.thread;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreads {

    public static void main(String[] args) {
        //virtual threadler java 19 ile birlikte gelmistir
        //OS Thread > Platform Thread > Virtual Thread, os thread platformu calistirir platform virtuali.
        //Her bir platform thread yasam suresi boyunca bir Os threadi bloklar Os thread maliyetlidir
        //Bir virtual thread platform threadde calisir ve isi bitince baska bir virtual thread ayni platform threade alinir. Boylece cok fazla platform thread dloayisiyla os thread kullanilmamis olur
        // Platform thread hazir oldugunda virtuali calistirir
        //virtual threadler lightweighttir
        //Genellikle I/O islemleri icin kullanilirlar
        //virtual thread bloklayici bir i/o islemi yaptiginda askiya alinir (veri cekme vs)

        Runnable runnable = () -> {
            long result = 1;
            for(int i = 1 ; i < 100_000 ; i++){
                result += i;
            }
            System.out.println(result);
        };

        long startMillis = System.currentTimeMillis();

        List<Thread> threadList = new ArrayList<>();
        int threadCounter = 100_000;

        for(int i = 0; i < threadCounter ; i++) {
            Thread th = Thread.ofVirtual().unstarted(runnable); // new Thread(runnable) -> platform thread 6 saniye
                                                                // Thread.ofVirtual().unstarted(runnable) -> virtual thread 1 saniye
            th.start();

            threadList.add(th);
        }

        try {
            for(int i = 0; i < threadCounter ; i++) {
                threadList.get(i).join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endMillis = System.currentTimeMillis();

        System.out.println(endMillis - startMillis);


    }
}
