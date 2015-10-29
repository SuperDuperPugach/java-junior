package com.acme.edu;

public class Logger {
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа int
     * @param message - параметр типа int
     */
    public static void log(int message) {
        System.out.println("primitive: " + message);
    }
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа char
     * @param message -  параметр типа char
     */
    public static void log(char message) {
        System.out.println("char: " + message);
    }
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа boolean
     * @param message - параметр типа boolean
     */
    public static void log(boolean message) {
        System.out.println("primitive: " + message);
    }

}
