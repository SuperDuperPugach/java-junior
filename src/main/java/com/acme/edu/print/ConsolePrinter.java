package com.acme.edu.print;

import com.acme.edu.except.BufferPrinterException;

/**
 * Реализация абстрактного класса BufferPrinter с выводом в консоль
 */
public class ConsolePrinter implements BufferPrinter {
    /**
     * Реализация абстрактного метода с выводом в консоль
     * @param buffer - что печатать
     * @param format - шаблон вывода
     */
    @Override
    public void print(String buffer, String format) throws BufferPrinterException{
        System.out.println(String.format(format, buffer));
    }
}
