package org.example.lesson21.signals;

public class SpuriousWakeupSignalGuard {

    //Sahte uyanma anlamina gelir
    //Waiting bir thread kendisine notify sinyal igelmedi halde cok nadir de olsa uyanabilir
    //jvm denb veya sistemden kaynakli olabilir yanki kontrolumuz disinda

    //buna onlem almak icin wait metodunu while ile cagirmak
    //while icindeyken notify gelemden kendi basina uyanirsa bayrak set edilmediginden tekrar uyku durumuna gecer thread

    final Object monitoringObject = new Object();
    boolean wasSignalled = false; //notify olunca true olucak

    public void doWait() throws InterruptedException {
        synchronized (monitoringObject) {
            while (!wasSignalled) {
                this.wait();
            }
            wasSignalled = false;
        }
    }

    public void doNotify() {
        synchronized (monitoringObject) {
            wasSignalled = true;
            this.notify();
        }
    }


}
