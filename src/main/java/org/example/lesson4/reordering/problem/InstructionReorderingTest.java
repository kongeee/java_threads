package org.example.lesson4.reordering.problem;

public class InstructionReorderingTest {
    /*
    Java compiler bazen birbirini etkilemeyecek kodların sıralarını değiştirebilir. Bunu da paralel çalıştırma için yapapr.
    reordering1 resmini incele


    Bu ornekte ise threadA daki satırlar reorder olursa ve arada da threadB çalışırsa a ve b nin değeri sonuçta 1 olması beklenirsek her ikisi de 0 kalabilir.
    Bunu bircok kez denemek gerekebilir sık gerçekleşen bir durum değil ama nadiren de olsa gerçekleşiyor

    !!!Test fail olana akdar çalıştırmak için -> Edit Configuration (sag ustte calistirin yaninda) -> Build and Run -> Modify Options -> Tests -> Repeat -> Until failure
            Explanation
        The results we expect are

        x = 0, y = 1: threadA runs to completion before threadB starts.
        x = 1, y = 0: threadB runs to completion before threadA starts.
        x = 1, y = 1: their instructions are interleaved.
        No one can expect x = 0, y = 0, which may happen as the test results showed.


        The actions in each thread have no dataflow dependence on each other,
        and accordingly can be executed out of order.
         (Even if they are executed in order, the timing by which caches are flushed to main memory can make it appear,
          from the perspective of threadB, that the assignments in threadA occurred in the opposite order.)
     */
    static int x, y, a, b;

    @org.junit.jupiter.api.BeforeEach
    public void init() {
        x = y = a = b = 0;
    }

    @org.junit.jupiter.api.Test
    public void test() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            a = 1; // can be reordered this one and
            x = b; // this one
        });
        Thread threadB = new Thread(() -> {
            b = 1;
            y = a;
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        org.junit.jupiter.api.Assertions.assertFalse(x == 0 && y == 0);
    }

}