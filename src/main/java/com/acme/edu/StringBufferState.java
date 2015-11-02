package com.acme.edu;

public class StringBufferState extends BufferState {
    private String buffer;
    private int count;
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
        if(buffer == null) {
            this.buffer = message;
            this.count = 1;
        } else if(!this.buffer.equals(message)) {
            printBuffer();
            this.buffer = message;
            this.count = 1;
        } else this.count++;
    }

    @Override
    void printBuffer() {
        if (count > 1)
            buffer += " (x" + count + ")";
        bufferPrinter.print(buffer, format);
    }
}
