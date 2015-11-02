package com.acme.edu;

public class StringBufferState extends BufferState {
    String buffer = "";
    public StringBufferState(BufferPrinter bufferPrinter) {
        super(bufferPrinter);
    }

    @Override
    State getState() {
        return State.STRING;
    }

    @Override
    void pushMessageToBuffer(String message, String format) {
        this.format = format;
        if(!this.buffer.equals(message))
            printBuffer();
        this.buffer = message;

    }

    @Override
    void printBuffer() {
        bufferPrinter.print(buffer, format);
    }
}
