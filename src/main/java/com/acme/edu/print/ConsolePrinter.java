package com.acme.edu.print;

/**
 * Реализация абстрактного класса BufferPrinter с выводом в консоль
 */
public class ConsolePrinter extends BufferPrinter {
    /**
     * Реализация абстрактного метода с выводом в консоль
     * @param buffer - что печатать
     * @param format - шаблон вывода
     */
    @Override
    public void print(String buffer, String format) {
        System.out.println(String.format(format, buffer));
    }
}
