package com.acme.edu.state;

import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.BufferPrinter;

/**
 * Реализация буффера состояния String
 */
public class StringBufferState extends BufferState {
    private String buffer;
    private int count;

    /**
     * Конструктор, инициализируеший buffer printer конкретным экземпляром класса,
     * реализующего интерфейс BufferPrinter
     * @param bufferPrinter - экземпляр класса, реализующий интерфейс BufferPrinter
     */
    public StringBufferState(BufferPrinter[] bufferPrinter) {
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
    public void pushMessageToBuffer(String message, String format) throws BufferPrinterException {
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
    public void printBuffer() throws BufferPrinterException {
        if (count > 1) {
            buffer += " (x" + count + ")";
        }
        for (BufferPrinter bp : bufferPrinter) {
            try {
                if (bp != null) bp.print(buffer, format);
            } finally {
                continue;
            }
        }
        this.buffer = null;
        this.count = 0;
    }
}
