package com.acme.edu;


public class Logger {
    // format strings for print()
    private static final String PRIMITIVE_FORMAT     = "primitive: %s";
    private static final String CHAR_FORMAT          = "char: %s";
    private static final String STRING_FORMAT        = "string: %s";
    private static final String REFERENCE_FORMAT     = "reference: %s";
    private static final String STRING_FORMAT_REPEAT = "string: %s (x%s)";
    private static final String ARRAY_FORMAT         = "primitives array: %s";
    private static final String MATRIX_FORMAT        = "primitives matrix: %s";
    private static final String MULTI_MATRIX_FORMAT  = "primitives multimatrix: %s";

    private static int        summ                   = 0;     //хранит сумму
    private static boolean isSumm                    = false; // флаг, что в сумме что-то есть для сброса

    private static String prevStr                    = ""; //хранит предыдущую строку для сравнения
    private static int strCount                      = 0;   // счетчик количества повторяемых строк





    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа int. В случае последоваельно вызванных методов,
     * выводит единожды сумму значений аргументов всех вызовов
     * @param message - параметр типа int
     */
    public static void log(int message) {
        Logger.closeStr();
        if(isIntOverFlow(message)) {
            Logger.closeInt();
        }
        Logger.summ += message;
        if(!Logger.isSumm)
            Logger.isSumm = true;
    }

    /**
     * Выводит в консоль элементы передаваемого массива
     * в формате {a1,a2,...}
     * @param message - массив
     */
    public static void log(int... message) {
        Logger.print(ARRAY_FORMAT, Logger.arrayToString(message));
    }

    /**
     * Выводит в консоль элементы передаваемого массива
     * в формате {{a11, a12, ...}, ...}
     * @param message - массив [][]
     */
    public static void log(int[][] message) {
        Logger.print(MATRIX_FORMAT, Logger.matrixToString(message));
    }

    /**
     *
     * @param message
     */
    /*public static void log(int[][][][] message) {

    }*/
    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа byte.
     * @param message - параметр типа byte
     */
    public static void log(byte message) {
        Logger.closeStr();
        Logger.print(PRIMITIVE_FORMAT, Integer.toString(message));
    }

    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа char
     * @param message -  параметр типа char
     */
    public static void log(char message) {
        Logger.print(CHAR_FORMAT, Character.toString(message));
    }

    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа boolean
     * @param message - параметр типа boolean
     */
    public static void log(boolean message) {
        Logger.print(PRIMITIVE_FORMAT, Boolean.toString(message));
    }

    /**
     * Выводит в консоль передаваемое в качестве параметра
     * значение переменной типа String. При последовательном введении повторяющихся сток -
     * выводит один раз с указанием в скобках числа вызовов
     * @param message - параметр типа String
     */
    public static void log(String message) {
        Logger.closeInt();
        if(message.equals(Logger.prevStr)) {
            Logger.strCount++;
        }
        else if(Logger.strCount != 0) {
            Logger.closeStr();
            Logger.strFirstSet(message);
        } else {
            Logger.strFirstSet(message);
        }
    }

    /**
     * Выводит в консоль множество передаваемых строк.
     * При последовательном введении повторяющихся сток -
     * выводит один раз с указанием в скобках числа вызовов
     * @param message массив строк
     */
    public static void log(String ... message) {
        for(String s : message)
            Logger.log(s);
    }

    /**
     * Выводит в консоль метод toString() объекта,
     * передаваемого в качестве параметра
     * @param message - параметр типа Object
     */
    public static void log(Object message) {
        Logger.print(REFERENCE_FORMAT, message.toString());
    }






    /**
     * Необходимо вызвать явно по завершению вызовов методов log()
     */
    public static void close() {
        Logger.closeInt();
        Logger.closeStr();
    }

    /**
     *вызывается для сброса в консоль суммы int
     */
    private static void closeInt() {
       if(Logger.isSumm) {
           print(PRIMITIVE_FORMAT, Integer.toString(Logger.summ));
           Logger.isSumm = false;
           Logger.summ = 0;
       }
    }

    /**
     *вызывается для сброса в консоль string(с числом повторений)
     */
    private static void closeStr() {
        if(Logger.strCount == 0) //нечего выводить
            return;
        if(Logger.strCount == 1) {
            print(STRING_FORMAT, Logger.prevStr);
            strReset();
        } else {
            print(STRING_FORMAT_REPEAT, Logger.prevStr, Integer.toString(Logger.strCount));
            strReset();
        }
    }

    /**
     * обнуляет счетчик числа string
     */
    private static void strReset() {
        Logger.strCount = 0;
        Logger.prevStr = "";
    }

    /**
     * устанавливает strCount= 1 и prevStr = message
     * @param message
     */
    private static void strFirstSet(String message) {
        Logger.strCount = 1;
        Logger.prevStr = message;
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
        return ((long)Logger.summ + (long)message) > Integer.MAX_VALUE ||
                ((long)Logger.summ + (long)message) < Integer.MIN_VALUE;
    }

    /**
     * функция, возвращающая строку с элементами передаваемого массива
     * в формате {a1,a2,...}
     * @param array - массив
     * @return
     */
    private static String arrayToString(int[] array) {
        String strArray = "{"+array[0];
        for (int i = 1; i < array.length; i++) {
            strArray +=", " + Integer.toString(array[i]);
        }
        strArray+="}";
        return strArray;
    }

    /**
     * функция, возвращающая строку с элементами передаваемого двумерного
     * массива в формате {{a11, a12, ...}, ...}
     * @param matrix
     * @return
     */
    private static String matrixToString(int[][] matrix) {
        String strMatrix = "{\n";
        for(int[] m : matrix ) {
            strMatrix += arrayToString(m) + "\n";
        }
        strMatrix +="}";
        return strMatrix;
    }
}
