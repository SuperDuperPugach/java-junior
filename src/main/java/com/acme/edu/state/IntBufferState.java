package com.acme.edu.state;

import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.BufferPrinter;

/**
 * Реализация буффера состояния типа int
 */
public class IntBufferState extends BufferState {
    private int buffer;

    /**
     * Конструктор, инициализируеший buffer printer конкретным экземпляром класса,
     * реализующего интерфейс BufferPrinter
     * @param bufferPrinter - экземпляр класса, реализующий интерфейс BufferPrinter
     */
    public IntBufferState(BufferPrinter[] bufferPrinter) {
        super(bufferPrinter);
        buffer = 0;
    }

    /**
     * Возращает краткое имя текущего состояния
     * @return - краткое имя состояния
     */
    @Override
    public States getState() {
        return States.INT;
    }

    /**
     * Принимает сообщение в свой буффер и по необходимости обрабатываеть его,
     * учитывая предыдущее состояние и его буффер. При последовательном вводе int,
     * суммирует эти значения и хранит в буффере.
     * @param message - сообщение для хранения в буффере
     * @param format - предполагаемый формат вывода из буффера
     */
    @Override
    public void pushMessageToBuffer(String message, String format) throws BufferPrinterException {
        if (isIntOverFlow(Integer.parseInt(message))) {
            printBuffer();
            buffer = 0;
        }
        buffer += Integer.parseInt(message); //суммируем с буфером
        this.format = format;
    }

    /**
     * Печать из буффера согласно конкретной реализации BufferPrint, которая
     * используется для данного состояния
     */
    @Override
    public void printBuffer() throws BufferPrinterException {
        for (BufferPrinter bp : bufferPrinter) {
            bp.print(Integer.toString(buffer), format);
        }
        this.buffer = 0;
    }

    private  boolean isIntOverFlow(int message) {
        return ((long) buffer + (long)message) > Integer.MAX_VALUE ||
                ((long) buffer + (long)message) < Integer.MIN_VALUE;
    }
}
