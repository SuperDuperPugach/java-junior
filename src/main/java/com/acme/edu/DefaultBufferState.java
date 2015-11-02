package com.acme.edu;

/**
 * Created by pugach on 03/11/15.
 */
public class DefaultBufferState extends BufferState {
    private String buffer;
    public DefaultBufferState(BufferPrinter bufferPrinter) {
        super(bufferPrinter);
    }

    @Override
    State getState() {
        return State.DEFAULT;
    }

    @Override
    void pushMessageToBuffer(String message, String format) {
        if(buffer != null)
            printBuffer();
        this.format = format;
        this.buffer = message;
    }

    @Override
    void printBuffer() {
        bufferPrinter.print(buffer, format);
        this.buffer = null;
        this.format = null;
    }
}
