package org.example.lesson16.blocking_queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class EnqueueMethodsMain {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //put metodu : Eger queue doluysa bir yer bosalana kadar veriyi koymak icin bekler
        try {
            blockingQueue.put("1");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //add metodu : Queue da yer yoksa IllegalStateException firlatir
        try {
            blockingQueue.add("2");
        } catch (IllegalStateException e) {
            //queue da yer yok
        }

        //Queue da yer varsa ekler ve ture doner, yer yoksa false doner
        boolean wasEnqueued = blockingQueue.offer("3");


        //Belirtilen sure kadar queue ya eklemeyi dener. Eger ekleyebilirsa true, ekleyemezsse false doner
        try {
            boolean wasEnqueued2 = blockingQueue.offer("4", 1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {

        }


        Collection<String> destination = new ArrayList<>();

        //queuedaki verileri collectiona aktarir ve aktarilan veriler queue dan silinir (enqueue)
        //kac eleman eklendiyse onun bilgisini doner
        //queue da hic eleman yoksa bos liste aktarilir destinationa ve 0 doner
        int numberOfElements = blockingQueue.drainTo(destination);

        //ilk n kadar eleman aktarilir
        //queue da talep edilenden daha az eleman varsa (orn 1 eleman varsa) olanlari koyar listeye
        int numberOfElements2 = blockingQueue.drainTo(destination, 2);




        //kuyrugun ilk sirasindaki elemani doner ama bu elemani kuyruktan cikarmaz. kuyruk bossa null doner
        String element3 = blockingQueue.peek();

        //kuyrugun ilk sirasindaki elemani doner ama bu elemani kuyruktan cikarmaz. kuyruk bossa NoSuchElementException firlatir
        try {
            String element4 = blockingQueue.element();
        } catch (NoSuchElementException e) {

        }


    }
}
