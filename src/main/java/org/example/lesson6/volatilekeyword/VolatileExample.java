package org.example.lesson6.volatilekeyword;

public class VolatileExample {
    //volatile keyword u bize happsend before garantisi verir.
    //volatile ram'e dogrudan yaz demektir. Bir volatile variable Ram e yazilirken instance in diger variable lari da dogrudan Ram e yazilir.
    //VolExchanger sinifinda set metodunda first ve second degerlerine atama yapiliyor ancak hemen ram e yazilmayabilirler. Ama third volatile oldugundan o yazilirken digerleri de aninda Ram e yazilir
    //ayni sey get metodu icin de gecerlidir. third dogrudan ram den okunurken aslinda tum instance degerleri Ram den okunur.

    //Ama jvm burada reordering yapabilir. Eger set metodunda yazma sirasi second third first olursa visibility problem olusabilir
    //cunku second a deger atandi third e atandi, third volatile oldugundan tum degerler ram e dogrudan yazildi ve first in onceki degeri Ram e yazilmis oldu
    //ayni sey get icin de gecerli first second third diye get olsaydi first ve second un Ram deki en guncel degeri okumasi garanti olmazdi

}

class VolExchanger{
    private int first = 0;
    private int second = 0;
    private volatile int third = 0;

    public void setValues(VolValues values) {
        first = values.getFirst();
        second = values.getSecond();

        third = values.getThird();
    }

    public void getValues(VolValues values) {
        values.setThird(third);

        values.setFirst(first);
        values.setSecond(second);

    }

}

class VolValues{
    private int first;
    private int second;
    private int third;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }
}
