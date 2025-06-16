package org.example.lesson17.compare_and_swap;

public class CompareAndSwapMain {
    public static void main(String[] args) {

        /*
            Karşılaştırma ve Takas (CAS), modern çok çekirdekli sistemlerde yüksek verim gerektiren eşzamanlı veri yapıları ve algoritmaları oluşturmak
            için güçlü ve kilitsiz bir mekanizma sunar.
            OS seviyesinde engelleme maliyetlerinden kaçınarak, iş parçacıklarının daha hızlı tepki vermesini sağlar, ancak bu,
            ekişme durumlarında CPU kullanımının artmasıyla (meşgul bekleme) sonuçlanabilir.
            AtomicBoolean ve AtomicLong gibi Java'nın atomik sınıfları, geliştiricilere bu güçlü primitifi kullanma imkanı sunar.

         */

        ProblematicLock problematicLock = new ProblematicLock();
        CompareAndSwapLock compareAndSwapLock = new CompareAndSwapLock();

        Integer value = 0;

        Runnable myRunnable = new MyRunnable(
                compareAndSwapLock, //burayi degistererek kontrol saglayabilirsin
                value
        );

        Thread th1 = new Thread(myRunnable);
        Thread th2 = new Thread(myRunnable);
        Thread th3 = new Thread(myRunnable);
        Thread th4 = new Thread(myRunnable);
        Thread th5 = new Thread(myRunnable);

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
    }
}
