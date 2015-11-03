package com.acme.edu.state;

import com.acme.edu.print.BufferPrinter;

/**
 * Created by pugach on 02/11/15.
 */
public class IntBufferState extends BufferState {
    int buffer = 0;
    public IntBufferState(BufferPrinter bufferPrinter) {
        super(bufferPrinter);
    }

    @Override
    public State getState() {
        return State.INT;
    }

    @Override
    public void pushMessageToBuffer(String message, String format) {
        buffer += Integer.parseInt(message); //суммируем с буфером
        this.format = format;
    }

    @Override
    public void printBuffer() {
        bufferPrinter.print(Integer.toString(buffer), format);
        this.buffer = 0;
        this.format = null;
    }
}
