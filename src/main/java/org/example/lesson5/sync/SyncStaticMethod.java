package org.example.lesson5.sync;

public class SyncStaticMethod {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for(int i = 0 ; i < 100 ; i++){
                StaticSyncExchanger.setObject("" + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int i = 0 ; i < 100 ; i++){
                System.out.println(StaticSyncExchanger.getObject());
            }
        });


        thread1.start();
        thread2.start();
    }

}

class StaticSyncExchanger{
    protected static Object object = null;

    public static synchronized Object getObject() {
        return object;
    }

    public static synchronized void setObject(Object o) {
        object = o;
    }

    public static synchronized Object getObj() {
        synchronized (StaticSyncExchanger.class) {
            return object;
        }

    }

    public static synchronized void setObj(Object o) {
        synchronized (StaticSyncExchanger.class) {
            object = o;
        }

    }
}

