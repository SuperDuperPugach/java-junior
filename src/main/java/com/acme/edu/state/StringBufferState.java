package com.acme.edu.state;

import com.acme.edu.print.BufferPrinter;

public class StringBufferState extends BufferState {
    private String buffer;
    private int count;
    public StringBufferState(BufferPrinter bufferPrinter) {
        super(bufferPrinter);
    }

    @Override
    public State getState() {
        return State.STRING;
    }

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

    @Override
    public void printBuffer() {
        if (count > 1)
            buffer += " (x" + count + ")";
        bufferPrinter.print(buffer, format);
        this.buffer = null;
        this.format = null;
        this.count = 0;
    }
}
