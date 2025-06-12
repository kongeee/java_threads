package org.example.lesson16.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class OtherMethodsMain {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);

        //kuyruktaki eleman sayisini doner
        int size = blockingQueue.size();


        //kuyruga daha kac tane eleman eklenebilecegini doner
        int remaining = blockingQueue.remainingCapacity();

        //icerde bu eleman varsa true doner (equals metodu ile bakilir)
        boolean containsElement = blockingQueue.contains("1");
    }
}
