package org.example.lesson9.racecondition;

public class RaceConditionExample {
    public static void main(String[] args) {

        /*
            Birden fazla thread bir veriye ayni anda yazmaya ve okumaya calisirsa race condition olabilir
            Buradaki onemli nokta en az 2 threadin ayni veri uzerinde okuma ve yazma yapmasidir
            2 thread den biri ayni verriye ayzarken digeri okursa  burada rrace condition olmaz


            iki turu vardir
            1 - read modify write
            2 - check then act.

            Burada ilk problemin ornegi gosterilmistir
         */

        //Bu kod calistirildiginda bir threadin sounucunu 2 milyon gormeyi bekleriz ama genelde 2 milyondan kucuk degerler cikar
        //Daha onceki konlarda sebebi verilmisti
        //th1 ve ve th2 bazi zamanalrda veriyi ayni anda okur ve update eder 2 artmasi gerekirken 1 artar sonucta
        //bu sorunu ruzeltmek icin sync blogu kullanilabilir
        Counter counter = new Counter();

        Thread thread1 = new Thread(getRunnable(counter, "Thread1 count: "));
        Thread thread2 = new Thread(getRunnable(counter, "Thread2 count: "));

        thread1.start();
        thread2.start();

    }

    private static Runnable getRunnable(Counter counter, String message) {
        return () -> {
          for (int i = 0 ; i < 1_000_000; i++) {
              counter.incAndGet();
          }
            System.out.println(message + counter.getCounter());
        };
    }


}

class Counter{
    private long counter = 0;

    public long incAndGet(){
        this.counter++;
        return this.counter;
    }

    public long getCounter(){
        return this.counter;
    }
}