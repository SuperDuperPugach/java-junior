package com.acme.edu;

import com.acme.edu.print.BufferPrinter;
import com.acme.edu.print.ConsolePrinter;
import com.acme.edu.state.*;

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



    private BufferPrinter bufferPrinter;
    private BufferState bufferState;
    private BufferStateFactory bufferStateFactory;

    public Logger() {
        bufferPrinter = new ConsolePrinter();
        bufferStateFactory = new BufferStateFactory(bufferPrinter);
    }

    public void log(int message) {
        bufferState = bufferStateFactory.switchToIntState(bufferState);
        bufferState.pushMessageToBuffer(Integer.toString(message), PRIMITIVE_FORMAT);
    }

    public void log(String message) {
        bufferState = bufferStateFactory.switchToStringState(bufferState);
        bufferState.pushMessageToBuffer(message, STRING_FORMAT);
    }

    public void log(char message) {
        bufferState = bufferStateFactory.switchToDefaultState(bufferState);
        bufferState.pushMessageToBuffer(Character.toString(message), CHAR_FORMAT);
    }

    public void close() {
        bufferState.printBuffer();
    }
}
