package org.example.lesson9.racecondition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CheckThenActExample {
    public static void main(String[] args) {
        Map<String, String> sharedMap = new ConcurrentHashMap<>();

        Thread thread1 = new Thread(getRunnable(sharedMap));
        Thread thread2 = new Thread(getRunnable(sharedMap));

        thread1.start();
        thread2.start();

    }

    private static Runnable getRunnable(Map<String, String> sharedMap) {
        return () -> {
            for (int i=0 ; i<1_000_000 ; i++) {
                if (sharedMap.containsKey("key")) {
                    String val = sharedMap.remove("key"); //silinen degeri doner, key yoksa null doner

                    //normalde bu durumla karsilasmayi beklemeyiz. Ama  2 thread de ilk if e girerse biri remove yapacak digeri ise olmayan key i remove
                    //etmeye caliscagi icin null donecek val
                    //sync bloguna alinarak cozulur
                    if (val == null) {
                        System.out.println("Iteration: " + i + " key was null!");
                    }
                } else {
                    sharedMap.put("key", "value");
                }
            }
        };
    }
}
