package org.example.lesson5.sync;

public class DifferentSyncStatic {
    public static void main(String[] args) {

        //Eger sync blogu icine TestStatic.class verilirse baska threadler baska metodlara erismeye calissalar dahi bunu yapamazlar. Cunku classa a lock koyulur
        //Eger sync blogu icine class icindeki bir static field verilirse sadece o fielda erisim kisitlanir
        //Ornek vermek gerekirse A sinifinin icinde x ve y static fieldlari olsun
        //ilk sync bloguna x ikinci sync bloguna y dersek bir thread ilk metodla calisirken diger bir thread ikinci metodla calisabilir
        //Ama her ikisine de TestStatic.class verseydik ilk metodun isi bitip TestStatic.class uzerindeki lock kalkana kadar ikinci thread diger metoda erisemezdi



        //Bu kodda func2 ve func1 neredeyse ayni anda calisir. 2 saniye gecer ve neredeyse ayni zamanda biterler. Cunku 2 sync blogunun da monitor objleri farkli
        //Eger sync bloguna count ve test degil de sadece TestStatic.class yazsaydik. func1 start yazacakti 2 saniye bekledikten sonra func1 finish diyip func2 ye baslayip 2 saniye gecirip func2 finish diyecekti

        //Eger static olanlara TestStatic.class digerine de this verseydik 1 ve 3 (veya 2 ve 3) ayni anda calisirdi.

        TestStatic test = new TestStatic();
        Thread th1 = new Thread(()-> {
            try {
                TestStatic.func1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread1");


        Thread th2 = new Thread(()-> {
            try {
                TestStatic.func2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread2");

        Thread th3 = new Thread(()-> {
            try {
                test.func3();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread2");


        th1.start();
        th2.start();
        th3.start();
    }

}

class TestStatic{
    private static Integer count = 333;
    private static Integer test = 0;

    private Object obj = new Object();

    public static void func1() throws InterruptedException {
        synchronized (count) {
            System.out.println(Thread.currentThread().getName() + " func1 start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " func1 finish");
        }

    }

    public static void func2() throws InterruptedException {
        synchronized (test) {
            System.out.println(Thread.currentThread().getName() + " func2 start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " func2 finish");
        }

    }

    public void func3() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " func3 start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " func3 finish");
        }

    }
}
