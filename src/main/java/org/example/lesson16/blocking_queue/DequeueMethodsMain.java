package org.example.lesson16.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class DequeueMethodsMain {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);


        //queue da bir veri varsa ceker yoksa veri gelene kadar bekler dinlemeye devam eder
        try {
            String element = blockingQueue.take();
        } catch (InterruptedException e) {

        }


        //icerde elemean varsa alir yoksa null doner bekleme yapmaz poll calisinca varsa var yoksa null
        String element2 = blockingQueue.poll();



        //hicbir element yoksa timeout suresi boyunca queue yu dinler eger yine eleman gelmezse null doner
        try {
            String element3 = blockingQueue.poll(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {

        }


        //veri cekmez icerdeki veriyi siler. icerde veriyi silebilirse true, veri yoksa silemediyse false doner.
        //remove birden fazla elemanla eslesiyorsa (n tane 1 olabilir queue da) kuyrugun basindaki ilk elemani siler
        boolean wasRemoved = blockingQueue.remove("1");
    }
}
