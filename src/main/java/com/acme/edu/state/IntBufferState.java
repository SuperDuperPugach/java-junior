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
        if (isIntOverFlow(Integer.parseInt(message))) {
            bufferPrinter.print(Integer.toString(buffer), format);
            buffer = 0;
        }
        buffer += Integer.parseInt(message); //суммируем с буфером
        this.format = format;
    }

    @Override
    public void printBuffer() {
        bufferPrinter.print(Integer.toString(buffer), format);
        this.buffer = 0;
        this.format = null;
    }

    private  boolean isIntOverFlow(int message) {
        return ((long) buffer + (long)message) > Integer.MAX_VALUE ||
                ((long) buffer + (long)message) < Integer.MIN_VALUE;
    }
}
