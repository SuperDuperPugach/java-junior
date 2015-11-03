package com.acme.edu;

import com.acme.edu.print.BufferPrinter;
import com.acme.edu.print.ConsolePrinter;
import com.acme.edu.state.BufferState;
import com.acme.edu.state.DefaultBufferState;
import com.acme.edu.state.IntBufferState;
import com.acme.edu.state.StringBufferState;

public class Logger {
    // format strings for print()
    private static final String PRIMITIVE_FORMAT     = "primitive: %s";
    private static final String CHAR_FORMAT          = "char: %s";
    private static final String STRING_FORMAT        = "string: %s";
    private static final String REFERENCE_FORMAT     = "reference: %s";
    private static final String STRING_FORMAT_REPEAT = "string: %s (x%s)";
    private static final String ARRAY_FORMAT         = "primitives array: %s";
    private static final String MATRIX_FORMAT        = "primitives matrix: %s";
    private static final String MULTI_MATRIX_FORMAT  = "primitives multimatrix: %s";



    private BufferPrinter bufferPrinter = new ConsolePrinter();
    private BufferState bufferState;

    public void log(int message) {
        if(bufferState == null) {
            bufferState = new IntBufferState(bufferPrinter);
        } else if(bufferState.getState() != BufferState.State.INT) {
            bufferState.printBuffer();
            bufferState = new IntBufferState(bufferPrinter);
        }
        bufferState.pushMessageToBuffer(Integer.toString(message), PRIMITIVE_FORMAT);
    }

    public void log(String message) {
        if(bufferState == null) {
            bufferState = new StringBufferState(bufferPrinter);
        }else if(bufferState.getState() != BufferState.State.STRING) {
            bufferState.printBuffer();
            bufferState = new StringBufferState(bufferPrinter);
        }
        bufferState.pushMessageToBuffer(message, STRING_FORMAT);
    }

    public void log(char message) {
        if(bufferState == null) {
            bufferState = new DefaultBufferState(bufferPrinter);
        } else if (bufferState.getState() != BufferState.State.DEFAULT) {
            bufferState.printBuffer();
            bufferState = new DefaultBufferState(bufferPrinter);
        }
        bufferState.pushMessageToBuffer(Character.toString(message), CHAR_FORMAT);
    }

    public void close() {
        bufferState.printBuffer();
    }
}
