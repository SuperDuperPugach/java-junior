package com.acme.edu.state;

import com.acme.edu.print.BufferPrinter;

/**
 * Created by pugach on 03/11/15.
 */
public class DefaultBufferState extends BufferState {
    private String buffer;
    public DefaultBufferState(BufferPrinter bufferPrinter) {
        super(bufferPrinter);
    }

    @Override
    public State getState() {
        return State.DEFAULT;
    }

    @Override
    public void pushMessageToBuffer(String message, String format) {
        if(buffer != null)
            printBuffer();
        this.format = format;
        this.buffer = message;
    }

    @Override
    public void printBuffer() {
        bufferPrinter.print(buffer, format);
        this.buffer = null;
        this.format = null;
    }
}
