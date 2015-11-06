package com.acme.edu.print;

import com.acme.edu.except.IllegalFormatPrinterException;

import java.util.IllegalFormatException;

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
    public void print(String buffer, String format) throws IllegalFormatPrinterException{
        try {
            System.out.println(String.format(format, buffer));
        } catch (IllegalFormatException e) {
            throw new IllegalFormatPrinterException();
        }
    }

    /**
     * ??Пустышка??
     */
    @Override
    public void close() {
        return;
    }
}
