package com.acme.edu;

import com.acme.edu.except.NullInLogException;
import com.acme.edu.print.BufferPrinter;
import com.acme.edu.print.ConsolePrinter;
import com.acme.edu.state.BufferState;
import com.acme.edu.state.BufferStateSwitcher;

import java.util.ArrayList;

public class Logger {
    // format strings for print()
    private static final String PRIMITIVE_FORMAT     = "primitive: %s";
    private static final String CHAR_FORMAT          = "char: %s";
    private static final String STRING_FORMAT        = "string: %s";
    private static final String REFERENCE_FORMAT     = "reference: %s";
    private static final String ARRAY_FORMAT         = "primitives array: %s";
    private static final String MATRIX_FORMAT        = "primitives matrix: %s";
    private static final String MULTI_MATRIX_FORMAT  = "primitives multimatrix: %s";


    private ArrayList<BufferPrinter> listBufferPrinter;
    private ArrayList<BufferState> listBufferState;
    private ArrayList<BufferStateSwitcher> listBufferStateSwitcher;

    /**
     * Конструктор по умолчанию. Реализует вывод в консоль
     */
    public Logger() {
        listBufferPrinter = new ArrayList<BufferPrinter>();
        listBufferState = new ArrayList<BufferState>();
        listBufferStateSwitcher = new ArrayList<BufferStateSwitcher>();
        listBufferPrinter.add(new ConsolePrinter());
        listBufferStateSwitcher.add(new BufferStateSwitcher(listBufferPrinter.get(0)));
        listBufferState.add(null);
    }

    /**
     *
     */
    public Logger(BufferPrinter... bp) {
        listBufferPrinter = new ArrayList<BufferPrinter>();
        listBufferState = new ArrayList<BufferState>();
        listBufferStateSwitcher = new ArrayList<BufferStateSwitcher>();
        for(BufferPrinter bufferPrinter : bp) {
            listBufferPrinter.add(bufferPrinter);
            listBufferStateSwitcher.add(new BufferStateSwitcher(bufferPrinter));
            listBufferState.add(null);
        }
    }

    /**
     * Конструктор, принимающий в качестве параметра экземпляр переключателя состояний
     * (Основная задача - возможность провести юнит тесты класса Logger)
     * @param bss - переключатель состояний типа BufferStateSwitcher
     */
    public Logger(BufferStateSwitcher bss) {
        listBufferPrinter = new ArrayList<BufferPrinter>();
        listBufferState = new ArrayList<BufferState>();
        listBufferStateSwitcher = new ArrayList<BufferStateSwitcher>();
        listBufferStateSwitcher.add(bss);
        listBufferState.add(null);
    }

    /**
     * Выводит (в консоль по умолчанию) передаваемое в качестве параметра
     * значение переменной типа int. В случае последоваельно вызванных методов,
     * выводит единожды сумму значений аргументов всех вызовов
     * @param message - то, что следует вывести
     */
    public void log(int message) {
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.set(i,listBufferStateSwitcher.get(i).switchToIntState(listBufferState.get(i)));
            listBufferState.get(i).pushMessageToBuffer(Integer.toString(message), PRIMITIVE_FORMAT);
        }
    }

    /**
     * Выводит (в консоль по умолчанию) элементы передаваемого массива
     * в формате {a1,a2,...}
     * @param message - массив, который следует вывести
     */
    public void log(int... message) {
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.set(i,listBufferStateSwitcher.get(i).switchToDefaultState(listBufferState.get(i)));
            listBufferState.get(i).pushMessageToBuffer(arrayToString(message), ARRAY_FORMAT);
        }
    }

    /**
     * Выводит (в консоль по умолчанию) элементы передаваемого массива
     * в формате {{a11, a12, ...}, ...}
     * @param message - массив [][], который следует вывести
     */
    public void log(int[][] message) {
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.set(i,listBufferStateSwitcher.get(i).switchToDefaultState(listBufferState.get(i)));
            listBufferState.get(i).pushMessageToBuffer(matrixToString(message), MATRIX_FORMAT);
        }
    }

    /**
     * Выводит (в консоль по умолчанию) элементы 4х мерного передаваемого массива
     * @param message - массив, который следует вывести
     */
    public void log(int[][][][] message) {
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.set(i,listBufferStateSwitcher.get(i).switchToDefaultState(listBufferState.get(i)));
            listBufferState.get(i).pushMessageToBuffer(dimFourMatrixToString(message), MULTI_MATRIX_FORMAT);
        }
    }

    /**
     * Выводит (в консоль по умолчанию) передаваемое в качестве параметра
     * значение переменной типа boolean
     * @param message - то, что следует вывести
     */
    public void log(boolean message) {
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.set(i,listBufferStateSwitcher.get(i).switchToDefaultState(listBufferState.get(i)));
            listBufferState.get(i).pushMessageToBuffer(Boolean.toString(message), PRIMITIVE_FORMAT);
        }
    }

    /**
     * Выводит (в консоль по умолчанию) передаваемое в качестве параметра
     * значение переменной типа String. При последовательном введении повторяющихся сток -
     * выводит один раз с указанием в скобках числа вызовов
     * @param message - то, что следует вывести
     */
    public void log(String message) throws NullInLogException {
        if(message == null) {
            throw new NullInLogException();
        }
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.set(i,listBufferStateSwitcher.get(i).switchToStringState(listBufferState.get(i)));
            listBufferState.get(i).pushMessageToBuffer(message, STRING_FORMAT);
        }
    }

    /**
     * Выводит (в консоль по умолчанию) множество передаваемых строк.
     * При последовательном введении повторяющихся сток -
     * выводит один раз с указанием в скобках числа вызовов
     * @param message массив строк, который следует вывести
     */
    public void log(String ... message) throws NullInLogException {
        for(String s : message) log(s);
    }

    /**
     * Выводит (в консоль по умолчанию) передаваемое в качестве параметра
     * значение переменной типа char
     * @param message -  то, что следует вывести
     */
    public void log(char message) {
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.set(i,listBufferStateSwitcher.get(i).switchToDefaultState(listBufferState.get(i)));
            listBufferState.get(i).pushMessageToBuffer(Character.toString(message), CHAR_FORMAT);
        }
    }
    /**
     * Выводит (в консоль по умолчанию) метод toString() объекта,
     * передаваемого в качестве параметра
     * @param message - объект, toString() метод которого нужно вывести
     */
    public void log(Object message) {
        if(message == null) {
            throw new NullInLogException();
        }
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.set(i,listBufferStateSwitcher.get(i).switchToDefaultState(listBufferState.get(i)));
            listBufferState.get(i).pushMessageToBuffer("" + message, REFERENCE_FORMAT);
        }
    }
    /**
     * Необходимо вызвать явно по завершению вызовов методов log()
     * для корректного завершения вывода
     */
    public void close() {
        for(int i = 0; i < listBufferStateSwitcher.size(); i++) {
            listBufferState.get(i).printBuffer();
            listBufferPrinter.get(i).close();
        }
    }



    /**
     * функция, возвращающая строку с элементами передаваемого массива
     * в формате {a1,a2,...}
     */
    private String arrayToString(int[] array) {
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
     */
    private  String matrixToString(int[][] matrix) {
        String strMatrix = "{\n";
        for(int[] m : matrix ) {
            strMatrix += arrayToString(m) + "\n";
        }
        strMatrix +="}";
        return strMatrix;
    }

    /**
     * функция, возвращающая строку с элементами передаваемого трехмерного
     * массива
     */
    private  String dimThreeMatrixToString(int[][][] matrix) {
        String strMatrix = "{\n";
        for(int[][] m : matrix) {
            strMatrix += matrixToString(m);
        }
        strMatrix +="\n}";
        return strMatrix;
    }

    /**
     * функция, возвращающая строку с элементами передаваемого четырехмерного
     * массива
     */
    private  String dimFourMatrixToString(int[][][][] matrix) {
        String strMatrix = "{\n";
        for(int[][][] m : matrix) {
            strMatrix += dimThreeMatrixToString(m);
        }
        strMatrix +="\n}";
        return strMatrix;
    }
}
