package org.example.lesson6.volatilekeyword;

public class VolatileNotEnoughExample {
    private volatile int count = 0;

    public boolean inc() {

        /*
        count un 9 oldugunu dusunelim. n tane thread bu bloga girebilir. hepsi de count u ramden okur degeri 9. hepsi de count++ yi calistirabilir.
        Bu durumda yalnzica 10 kez girilmesine izin verilen bir yere 10 dan fazla giris oldu. (sync blogu ile cozulebilir)

        ++ atomic bir operasyon degildir. ()
        //read (kendi memorlerine okurlar)
        //inc
        //write (main memorye)
        bunu 2 thread ayni anda yapabilir. 0 degerini ayni anda ikisi okur ve ikisi ayni anda yazarsa count 2 olmasi gerekirken 1 kalir (syn blogu veya atomicint ile cozulebilir)
         */

        if(count == 10) {
            return false;
        }
        count++;
        return true;
    }
}
