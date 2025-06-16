package org.example.lesson17.compare_and_swap;

import java.util.concurrent.atomic.AtomicBoolean;

public class CompareAndSwapLock implements MyLock{

    AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        //lock degeri false ise kilit yok demektir
        //expexted value ve lock degeri ayni oldugundan compareAndSet metodu true doner ve lock un degeri true olur (newValue'dan dolayi)
        //expected false iken lock degeri true olsaydi compareAndSet metodu false doner ve while dongusu calisirdi herhangi bire deger atamasi yapilmazdi.

        //problematic in aksine burada race condition olmuyor donanim seviyesinde bir atomiklik saglaniyor
        //bir thread buraya girdiginde digerlerinin giremedigi garanti ediliyor

        //check-then-act burada cozulmustur cunku kontrol var
        while (!lock.compareAndSet(false, true)) {
            //busy wait
        }
    }

    public void unlock () {
        this.lock.set(false);
    }

}
