package com.acme.edu;

public class Logger {
    private static final String PRIMITIVE_FORMAT = "primitive: %s";
    private static final String CHAR_FORMAT      = "char: %s";
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа int
     * @param message - параметр типа int
     */
    public static void log(int message) {
        print(PRIMITIVE_FORMAT, Integer.toString(message));
    }
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа char
     * @param message -  параметр типа char
     */
    public static void log(char message) {
        print(CHAR_FORMAT, Character.toString(message));
    }
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа boolean
     * @param message - параметр типа boolean
     */
    public static void log(boolean message) {
        print(PRIMITIVE_FORMAT, Boolean.toString(message));
    }

    private static void print(String format, String arg) {
        System.out.println(String.format(format, arg));
    }

}
