package com.acme.edu;

public class Logger {
    private static final String PRIMITIVE_FORMAT   = "primitive: %s";
    private static final String CHAR_FORMAT        = "char: %s";
    private static final String STRING_FORMAT      = "string: %s";
    private static final String REFERENCE_FORMAT   = "reference: %s";

    private static int        summ = 0;     //хранит сумму
    private static boolean isPrint = false; //флаг, что нужно сбросить
    private static boolean isSmt   = false; // флаг, что в сумме что-то есть для сброса
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа int. В случае последоваельно вызванных методов,
     * выводит единожды сумму значений аргументов всех вызовов
     * @param message - параметр типа int
     */
    public static void log(int message) {
        if (Logger.isPrint && Logger.isSmt) {
            print(PRIMITIVE_FORMAT, Integer.toString(message));
            Logger.summ = 0;
            return;
        }
        summ += message;
        if(!isSmt)
            isSmt = true;
    }
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа char
     * @param message -  параметр типа char
     */
    public static void log(char message) {
        close();
        print(CHAR_FORMAT, Character.toString(message));
    }

    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа boolean
     * @param message - параметр типа boolean
     */
    public static void log(boolean message) {
        close();
        print(PRIMITIVE_FORMAT, Boolean.toString(message));
    }

    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа String
     * @param message - параметр типа String
     */
    public static void log(String message) {
        close();
        print(STRING_FORMAT, message);
    }

    /**
     * Выводит в консоль метод toString() объекта,
     * передаваемого в качестве параметра
     * @param message - параметр типа Object
     */
    public static void log(Object message) {
        close();
        print(REFERENCE_FORMAT, message.toString());
    }

    /**
     * Необходимо вызвать по завершению вызовов методов log()
     */
    public static void close() {
        Logger.isPrint = true;
        log(Logger.summ);
        //обнуляем флаги
        Logger.isPrint = false;
        Logger.isSmt   = false;
    }
    /**
     *
     * @param format - шаблон строки для вывода
     * @param arg    - аргумент шаблона
     */
    private static void print(String format, String arg) {
        System.out.println(String.format(format, arg));
    }
}
