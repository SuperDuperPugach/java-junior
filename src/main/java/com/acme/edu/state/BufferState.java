package com.acme.edu.state;

import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.BufferPrinter;

/**
 * Абстрактный класс состояний буффера
 */
public abstract class BufferState {
    protected final String ERROR_FORMAT = "ERROR: %s";
    protected final String ERROR_MESSAGE = "wrong output format";

    protected String format;
    protected BufferPrinter[] bufferPrinter;

    /**
     * Конструктор, инициализируеший buffer printer конкретным экземпляром класса,
     * реализующего интерфейс BufferPrinter
     * @param bufferPrinter - экземпляр класса, реализующий абстрактный класс BufferPrinter
     */
    public BufferState(BufferPrinter[] bufferPrinter) {
        this.bufferPrinter = bufferPrinter;
    }

    /**
     * Абстрактный метод, который должен возвращать краткое имя состояния из States
     * @return - краткое имя состояния
     */
    public abstract States getState();

    /**
     * Абстрактный метод, в котором реализуется свое поведение, характерное
     * для каждого состояния, при помещении сообщения в буффер состояния
     * @param message
     * @param format
     */
    public abstract void pushMessageToBuffer(String message, String format) throws BufferPrinterException;

    /**
     * Абстрактный метод, в котором должно реализовываться обращение к текущему bufferPrintr
     * для печати буффера
     */
    public abstract void printBuffer() throws BufferPrinterException; // печать буфера

}
