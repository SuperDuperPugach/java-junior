package com.acme.edu.except;

/**
 * Бросается, когда вызывается метод print()
 * интерфейса BufferPrinter
 */
public class BufferPrinterException extends Exception {
    /**
     * Конструктор по умолчанию
     */
    public BufferPrinterException() {
        super();
    }

    /**
     * Конструктор, принимающий в качестве параметра комментарий к ошибке
     * @param message - комментарий
     */
    public BufferPrinterException(String message) {
        super(message);
    }
}
