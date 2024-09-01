package org.example.lesson3.memory.model;

public class ThreadMemoryAccessInfo {
    public static void main(String[] args) {
        //Her thread kendi thread stackine sahiptir ve sadece kendi thread stacklerine erisebilirler baska threadlerinke erisemezler.
        //Heap'e tum threadler erisebilir
        //Threadlerin calistirdigi metodlarda bulunan lokal degiskenler shared variable olamaz cunku bu degiskenler(veya referanslar) ayri thread stackte tutulur
        //Runnable sinnifindan implement eden sinif icindeki degiskenler (lokal olmayan) shared variable olabilir

    }
}

