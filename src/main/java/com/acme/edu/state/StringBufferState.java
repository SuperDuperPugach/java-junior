package com.acme.edu.state;

import com.acme.edu.print.BufferPrinter;

public class StringBufferState extends BufferState {
    private String buffer;
    private int count;

    /**
     * Конструктор, инициализируеший buffer printer конкретным экземпляром класса,
     * реализующего абстрактный класс BufferPrinter
     * @param bufferPrinter - экземпляр класса, реализующий абстрактный класс BufferPrinter
     */
    public StringBufferState(BufferPrinter bufferPrinter) {
        super(bufferPrinter);
    }

    /**
     * Возращает краткое имя текущего состояния
     * @return - краткое имя состояния
     */
    @Override
    public States getState() {
        return States.STRING;
    }

    /**
     * Принимает сообщение в свой буффер и по необходимости обрабатываеть его,
     * учитывая предыдущее состояние и его буффер. При последовательном добавлении
     * в буффер одинаковы строк, хранит их число для корректного вывода
     * @param message - сообщение для хранения в буффере
     * @param format - предполагаемый формат вывода из буффера
     */
    @Override
    public void pushMessageToBuffer(String message, String format) {
        if(buffer == null) {
            this.buffer = message;
            this.count = 1;
        } else if(!this.buffer.equals(message)) {
            printBuffer();
            this.buffer = message;
            this.count = 1;
        } else this.count++;
        this.format = format;
    }

    /**
     * Печать из буффера согласно конкретной реализации BufferPrint, которая
     * используется для данного состояния
     */
    @Override
    public void printBuffer() {
        if (count > 1)
            buffer += " (x" + count + ")";
        bufferPrinter.print(buffer, format);
        this.buffer = null;
        this.count = 0;
    }
}
