package com.acme.edu.state;


import com.acme.edu.print.BufferPrinter;

public class BufferStateFactory {
   // private IntBufferState intBufferState;
   // private StringBufferState stringBufferState;
   // private DefaultBufferState defaultBufferState;
    private BufferPrinter bufferPrinter;

    /**
     *
     * @param bp
     */
    public BufferStateFactory(BufferPrinter bp) {
        this.bufferPrinter = bp;
    }
    public BufferState switchToIntState(BufferState bufferState) {
        if(bufferState == null) {
            bufferState = new IntBufferState(bufferPrinter);
        } else if(bufferState.getState() != BufferState.State.INT) {
            bufferState.printBuffer();
            bufferState = new IntBufferState(bufferPrinter);
        }
        return bufferState;
    }
    public BufferState switchToStringState(BufferState bufferState) {
        if(bufferState == null) {
            bufferState = new StringBufferState(bufferPrinter);
        }else if(bufferState.getState() != BufferState.State.STRING) {
            bufferState.printBuffer();
            bufferState = new StringBufferState(bufferPrinter);
        }
        return bufferState;
    }
    public BufferState switchToDefaultState(BufferState bufferState) {
        if(bufferState == null) {
            bufferState = new DefaultBufferState(bufferPrinter);
        } else if (bufferState.getState() != BufferState.State.DEFAULT) {
            bufferState.printBuffer();
            bufferState = new DefaultBufferState(bufferPrinter);
        }
        return bufferState;
    }

}
