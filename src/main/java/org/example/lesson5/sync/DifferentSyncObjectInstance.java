package org.example.lesson5.sync;


public class DifferentSyncObjectInstance {
    public static void main(String[] args) {
        //Eger sync blogu icine this verilirse baska threadler baska metodlara erismeye calissalar dahi bunu yapamazlar. Cunku instance a lock koyulur
        //Eger sync blogu icine instance icindeki bir field verilirse sadece o fielda erisim kisitlanir
        //Ornek vermek gerekirse A sinifinin icinde x ve y instance fieldlari olsun
        //ilk sync bloguna this.x ikinci sync bloguna this.y dersek bir thread ilk metodla calisirken diger bir thread ikinci metodla calisabilir
        //Ama her ikisine de this verseydik ilk metodun isi bitip this uzerindeki lock kalkana kadar ikinci thread diger metoda erisemezdi



        //Bu kodda func2 ve func1 neredeyse ayni anda calisir. 2 saniye gecer ve neredeyse ayni zamanda biterler. Cunku 2 sync blogunun damonitor objleri farkli
        //Eger sync bloguna this.count ve this.test degil de sadece this yazsaydik. func1 start yazacakti 2 saniye bekledikten sonra func1 finish diyip func2 ye baslayip 2 saniye gecirip func2 finish diyecekti
        TestInstance test = new TestInstance();
        Thread th1 = new Thread(()-> {
            try {
                test.func1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread1");


        Thread th2 = new Thread(()-> {
            try {
                test.func2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread2");


        th1.start();
        th2.start();

    }
}

class TestInstance{
    private Integer count = 333;
    private Integer test = 0;

    public void func1() throws InterruptedException {
        synchronized (this.count) {
            System.out.println(Thread.currentThread().getName() + " func1 start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " func1 finish");
        }

    }

    public void func2() throws InterruptedException {
        synchronized (this.test) {
            System.out.println(Thread.currentThread().getName() + " func2 start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " func2 finish");
        }

    }
}