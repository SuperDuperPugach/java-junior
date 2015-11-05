package com.acme.edu.state;

import com.acme.edu.print.BufferPrinter;

/**
 * Реализация буффера состояния по умолчанию
 */
public class DefaultBufferState extends BufferState {
    private String buffer;

    /**
     * Конструктор, инициализируеший buffer printer конкретным экземпляром класса,
     * реализующего интерфейс BufferPrinter
     * @param bufferPrinter - экземпляр класса, реализующий интерфейс BufferPrinter
     */
    public DefaultBufferState(BufferPrinter bufferPrinter) {
        super(bufferPrinter);
    }

    /**
     * Возращает краткое имя текущего состояния
     * @return - краткое имя состояния
     */
    @Override
    public States getState() {
        return States.DEFAULT;
    }

    /**
     * Принимает сообщение в свой буффер и по необходимости обрабатываеть его,
     * учитывая предыдущее состояние и его буффер.
     * @param message - сообщение для хранения в буффере
     * @param format - предполагаемый формат вывода из буффера
     */
    @Override
    public void pushMessageToBuffer(String message, String format) {
        if(buffer != null)
            printBuffer();
        this.format = format;
        this.buffer = message;
    }

    /**
     * Печать из буффера согласно конкретной реализации BufferPrint, которая
     * используется для данного состояния
     */
    @Override
    public void printBuffer() {
        bufferPrinter.print(buffer, format);
        this.buffer = null;
        this.format = null;
    }
}
