package org.example.lesson17.compare_and_swap;

import java.util.concurrent.atomic.AtomicLong;

public class OptimisticLocking {
    private AtomicLong counter = new AtomicLong();

    /*

        inc metoduna bir thread girdiğinde mevcut değeri çeker
        mevcut değer bir artıralarak yeni değer belirlenir
        eğer value, o an başka bir thread tarafından değiştilmemişse değer düncellemesi gerçekleşir

        Ama diyelim thread1 value yu aldı değeri 5, newVal 6 olur.
        thread2 de 5 ken çekti değeri 6 yaptı. thread1 den önce
        thread1 counter değerinin 5 olmasını bekliyor ama counter değeri 6 dolayısıyla compareAndSet false donuyor ve tekrar değeri öekip 1 artırıyor
        tekrar bakıyor counter ile değeri aynı mı diye aynı olana kadar bunu yapmaya devam ediyor
        aynı olduğunda değeri update ediyor ve işi bitiyor.

        burada theadler boşa kilitlenmiyor da iş yapılana kadar sürekli denemeler yapıyor



        İyimser kilitleme, birden fazla iş parçacığının aynı anda kritik bölüme girmesine izin verildiği anlamına gelir.
        Ancak, aynı anda birden fazla iş parçacığı kritik bölüm içinde yürütülse bile,
         yalnızca bir iş parçacığının kritik bölümde yürütmenin sonucunu "commit" etmesine (değişiklikleri uygulamasına) izin verilir.


     */
    public void inc() {
        boolean incSuccessful = false;

        while (!incSuccessful) {
            long value = counter.get();
            long newVal = value + 1;

            incSuccessful = counter.compareAndSet(value, newVal);
        }
    }

    public long getCount() {
        return counter.get();
    }
}
