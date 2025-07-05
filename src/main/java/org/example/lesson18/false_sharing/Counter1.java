package org.example.lesson18.false_sharing;

//buraya da anotason uygulanabilir
//@jdk.internal.vm.annotation.Contended
public class Counter1 {
    //bir degisiklik oldugunda dogrudan ram e yazsin diye volatile yaptik
    //boylece kendi cache blogunu invalidate edebilecek
    public volatile long count1 = 0;

    // bu anotasyonla padding verilir. yukarıdaki değişkenle aynı cache blogunda olmasi engellenir
    @jdk.internal.vm.annotation.Contended
    public volatile long count2 = 0;
}
