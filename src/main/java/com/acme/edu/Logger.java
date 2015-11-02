package com.acme.edu;

/**
 * Created by pugach on 02/11/15.
 */
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


    private BufferState bufferState;
    private BufferPrinter bufferPrinter = new ConsolePrinter();

    public void log(int message) {
        if(bufferState.getState() != BufferState.State.INT) {
            bufferState.printBuffer();
            bufferState = new IntBufferState(bufferPrinter);
        }
        bufferState.pushMessageToBuffer(Integer.toString(message), PRIMITIVE_FORMAT);
    }

    public void log(String message) {
        if(bufferState.getState() != BufferState.State.STRING) {
            bufferState.printBuffer();
            bufferState = new StringBufferState(bufferPrinter);
        }
        bufferState.pushMessageToBuffer(message, STRING_FORMAT);
    }
}
