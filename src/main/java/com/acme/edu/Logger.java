package com.acme.edu;

public class Logger {
    private static final String PRIMITIVE_FORMAT     = "primitive: %s";
    private static final String CHAR_FORMAT          = "char: %s";
    private static final String STRING_FORMAT        = "string: %s";
    private static final String REFERENCE_FORMAT     = "reference: %s";
    private static final String STRING_FORMAT_REPEAT = "string: %s (x%s)";

    private static int        summ = 0;     //хранит сумму
    private static boolean isPrint = false; //флаг, что нужно сбросить
    private static boolean isSmt   = false; // флаг, что в сумме что-то есть для сброса

    private static String prevStr = ""; //хранит предыдущую строку для сравнения
    private static int strCount = 0;   // счетчик количества повторяемых строк
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа int. В случае последоваельно вызванных методов,
     * выводит единожды сумму значений аргументов всех вызовов
     * @param message - параметр типа int
     */
    public static void log(int message) {
        closeStr();
        if (Logger.isPrint && Logger.isSmt) {
            print(PRIMITIVE_FORMAT, Integer.toString(message));
            Logger.summ = 0;
            return;
        }
        if(isIntOverFlow(message)) {
            closeInt();
        }
        Logger.summ += message;
        if(!Logger.isSmt)
            Logger.isSmt = true;
    }

    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа byte.
     * @param message - параметр типа byte
     */
    public static void log(byte message) {
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

    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа String
     * @param message - параметр типа String
     */
    /*public static void log(String message) {
        closeInt();
        if(!message.equals(Logger.prevStr)) {
            if(Logger.strCount > 1) {
                print(STRING_FORMAT_REPEAT, Logger.prevStr,
                        Integer.toString(Logger.strCount));
                Logger.strCount = 1;
                Logger.prevStr = message;
            }
            else {
                print(STRING_FORMAT, Logger.prevStr);
                Logger.strCount = 1;
                Logger.prevStr = message;
            }
            return;
        }
        Logger.strCount++;

    }*/

    /**
     * Выводит в консоль метод toString() объекта,
     * передаваемого в качестве параметра
     * @param message - параметр типа Object
     */
    public static void log(Object message) {
        print(REFERENCE_FORMAT, message.toString());
    }

    /**
     * Необходимо вызвать явно по завершению вызовов методов log()
     * Вызываеся неявно в log(String) методe, т.к. он неявно завершает
     * последовательность вводимых int
     */
    public static void close() {
        closeInt();
        closeStr();
    }
    private static void closeInt() {
        print(PRIMITIVE_FORMAT, Integer.toString(Logger.summ));
        //обнуляем флаги
        Logger.isPrint = false;
        Logger.isSmt   = false;
    }
    private static void closeStr() {
        if(Logger.strCount == 0)
            return;
        if()
    }

    /**
     *
     * @param format - шаблон строки для вывода
     * @param arg    - аргумент шаблона
     */
    private static void print(String format, String... arg) {
        System.out.println(String.format(format, arg));
    }

    /**
     * метод проверяет выход за границы int
     * @param message
     * @return возвращает true, если имеем выход за границы int
     */
    private static boolean isIntOverFlow(int message) {
        return (((long)Logger.summ + (long)message) > Integer.MAX_VALUE) ||
                (((long)Logger.summ + (long)message)) < Integer.MIN_VALUE;
    }
}
