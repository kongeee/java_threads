package org.example.lesson12.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {

    //once calculate metodu locklaniyor daha unlcok edilmemisken add veya sub cagiriliyor onlarada da lock var

    public static class Calculation{
        public static final int UNSPECIFIED = -1;
        public static final int ADDITION = 0;
        public static final int SUBTRACTION = 1;

        int type = UNSPECIFIED;
        public double value;

        public Calculation(int type, double value) {
            this.type = type;
            this.value = value;
        }
    }

    private double result = 0.0d;

    Lock lock = new ReentrantLock();

    public void add(double value) {
        try {
            lock.lock(); //second lock
            result += value;
        } finally {
            lock.unlock();
        }
    }

    public void subtract(double value) {
        try {
            lock.lock(); //second lock
            result -= value;
        } finally {
            lock.unlock();
        }
    }

    public void calcuate(Calculation ...calculations) {
        try {
            lock.lock(); //first lock

            for (Calculation calculation : calculations) {
                switch (calculation.type) {
                    case Calculation.ADDITION : add(calculation.value); break;
                    case Calculation.SUBTRACTION: subtract(calculation.value);break;
                }

            }
        } finally {
            lock.unlock();
        }
    }
}
