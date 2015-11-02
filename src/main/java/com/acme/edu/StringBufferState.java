package com.acme.edu;

/**
 * Created by pugach on 02/11/15.
 */
public class StringBufferState extends BufferState {
    String buffer;
    public StringBufferState(BufferPrinter bufferPrinter) {
        super(bufferPrinter);
    }

    @Override
    State getState() {
        return State.STRING;
    }

    @Override
    void pushMessageToBuffer(String message, String format) {
        buffer = message;
        this.format = format;
    }

    @Override
    void printBuffer() {

    }
}
