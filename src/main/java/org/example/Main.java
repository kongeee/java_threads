package org.example;

public class Main {
    public static void main(String[] args) {
        Test test = null;
        System.out.println(test.getCount());

    }
}
class Test {
    private static int count = 55;
    public static int getCount(){
        return count;
    }
}