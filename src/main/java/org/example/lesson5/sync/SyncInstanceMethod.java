package org.example.lesson5.sync;

public class SyncInstanceMethod {

    //synchronized bloklara ayni anda yalnizca bir thread erisebilir. Yani bu metod locklanir
    //Instance metoda synchronized verilebilecegi gibi metod icerisinde ayri bir blok olarakta tanimlanabilir.
    //instance metoda synchronized demek ile metodu acip icerisine synchronized blogu yazip this vermek ile ayni seydir. Yani sync instance metodlar this i sync eder
    //sync blogu na () icinde verilen degere monitor object denir ve bu primitive tip olamaz!
    //Eger iki tane SyncExchanger nesnesi varsa 2 thread ayni anda 2 farkli nesnenin metodlarini erisebilir. sync sadece ayni nesne uzerinde etkilidir.
    //Kisaca sync blogundaki monitor object onemlidir
    //String literalleri monitor object olarak kullanma! Cunku jvm bu degiskenler icin ayni adresi de atayabilir farkli adresi de

    //metodlara erisim siralari yine bilinemez yani thread1 calisti bitti araya thread2 girebilir. sync sadece ayni anda erisimi kapatir
    /*
    output:
    null ->  thread2 once erisip okumus
    null ->  thread2 once erisip okumus
    null ->  thread2 once erisip okumus
    31   ->  thread1 31 kere artirdiktan sonra thread2 bu degeri okumus
    85   ->  thread1 85 kere artirdiktan sonra thread2 bu degeri okumus
    99   ->  thread1 isini bitirdikten sonra thread2 bu degeri okumus
    99   ->  thread1 isini bitirdikten sonra thread2 bu degeri okumus
    .
    .
    .
     */

    public static void main(String[] args) {
        SyncExchanger syncExchanger = new SyncExchanger();

        Thread thread1 = new Thread(()->{
            for(int i = 0 ; i < 100 ; i++) {
                syncExchanger.setObject("" + i);
            }
        });

        Thread thread2 = new Thread(()->{
            for(int i = 0 ; i < 100 ; i++) {
                System.out.println(syncExchanger.getObject());
            }
        });

        thread1.start();
        thread2.start();
    }



}

class SyncExchanger{
    protected Object object = null;

    public synchronized Object getObject() {
        return object;
    }

    public synchronized void setObject(Object object) {
        this.object = object;
    }

    public synchronized Object getObj() {
        synchronized (this) {
            return object;
        }

    }

    public synchronized void setObj(Object object) {
        synchronized (this) {
            this.object = object;
        }

    }
}
