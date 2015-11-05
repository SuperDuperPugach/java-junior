package com.acme.edu.print;

import com.acme.edu.except.BufferPrinterException;

/**
 * Инерфейс, который необходимо реализовать, чтобы определить собственную
 * реализацию вывода класса Logger
 */
public interface BufferPrinter {
    /**
     * Метод, который необходимо определить в реализуемом классе
     * @param buffer - что печатать
     * @param format - шаблон вывода
     */
    public abstract void print(String buffer, String format) throws BufferPrinterException;
}
