package org.example.lesson16.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        //genel kullanimlari asagidaki gibidir

        /*
            1.CPU Kullanımının İyileştirilmesi ve Sunucu Yüklenmesinin Önlenmesi:
                Bu kalıp, işi algılayan iş parçacıkları ile işi yürüten işçi iş parçacıklarını ayırarak, CPU'ların daha verimli kullanılmasını sağlar
                 ve sunucunun gereksiz yere aşırı sayıda iş parçacığıyla yüklenmesini önler.
                 Genelde bir thread işleri alır ve queue ya ekler n thread de bunları okru ve işler.
            2.Ön Plan İş Parçacığı Gecikmesinin Azaltılması:
             Bu kalıp, kullanıcı arayüzü (UI) veya bağlantı kabul eden sunucu gibi ön plandaki iş parçacıklarının yoğun görevleri
              arka plana aktarmasına olanak tanır, böylece bu iş parçacıkları her zaman duyarlı kalır ve kullanıcı etkileşimlerine veya
               yeni bağlantılara anında yanıt verebilir.
            3.Geri Basınç (Back Pressure) Yönetimi:
             Üreticilerin tüketicilerden daha hızlı iş ürettiği durumlarda, kuyruğun dolmasıyla bir geri basınç mekanizması devreye girer;
              bu da üreticilere yavaşlamaları veya durmaları sinyalini vererek sistemin aşırı yüklenmesini engeller.
         */


        //Şu an sırayla işliyor gibi görünebilir önce 1 sonra 2. thread ama iş yükü artarsa örn: 1. taskı 1. thread aldı ve çok uzun o oişi yapana kadar
        //2. thread 5 tane iş bitirebilir

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        Producer producer = new Producer(blockingQueue);
        Consumer consumer1 = new Consumer(blockingQueue);
        Consumer consumer2 = new Consumer(blockingQueue);

        Thread producerThread = new Thread(producer, "Producer Thread");
        Thread consumerThread1 = new Thread(consumer1, "Consumer1 Thread");
        Thread consumerThread2 = new Thread(consumer2, "Consumer2 Thread");

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}
