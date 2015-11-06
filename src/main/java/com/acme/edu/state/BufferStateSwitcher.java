package com.acme.edu.state;


import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.BufferPrinter;

/**
 * Позволяет правильно переключаться между состояними буффера:
 * IntBufferState, StringBufferState, DefaultBufferState
 */
public class BufferStateSwitcher {

    private BufferPrinter[] bufferPrinter;

    /**
     * Инициализирует новый переключатель. Принимает на вход экземпляр конкретной
     * реализации интерфейса BufferPrinter. Этой реализацией будут инициализироваться
     * состояния при переключении
     * @param - экземпляр класса, реализующий интерфейс BufferPrinter
     */
    public BufferStateSwitcher(BufferPrinter[] bp) {
        this.bufferPrinter = bp;
    }

    /**
     * Переключает буффер состояний в Int State. При необходимости
     * запрашивается печать предыдущего состояния
     * @param bufferState - предыдущее состояние
     * @return - новое Int состояние
     */
    public BufferState switchToIntState(BufferState bufferState) throws BufferPrinterException {
        BufferState newBufferState = bufferState;
        if(bufferState == null) {
            newBufferState = new IntBufferState(bufferPrinter);
        } else if(bufferState.getState() != States.INT) {
            bufferState.printBuffer();
            newBufferState = new IntBufferState(bufferPrinter);
        }
        return newBufferState;
    }

    /**
     * Переключает буффер состояний в String State. При необходимости
     * запрашивается печать предыдущего состояния
     * @param bufferState - предыдущее состояние
     * @return - новое String состояние
     */
    public BufferState switchToStringState(BufferState bufferState) throws BufferPrinterException {
        BufferState newBufferState = bufferState;
        if(bufferState == null) {
            newBufferState = new StringBufferState(bufferPrinter);
        }else if(bufferState.getState() != States.STRING) {
            bufferState.printBuffer();
            newBufferState = new StringBufferState(bufferPrinter);
        }
        return newBufferState;
    }

    /**
     * Переключает буффер состояний в Default State. При необходимости
     * запрашивается печать предыдущего состояния
     * @param bufferState - предыдущее состояние
     * @return - новое Default состояние
     */
    public BufferState switchToDefaultState(BufferState bufferState) throws BufferPrinterException {
        BufferState newBufferState = bufferState;
        if(bufferState == null) {
            newBufferState = new DefaultBufferState(bufferPrinter);
        } else if (bufferState.getState() != States.DEFAULT) {
            bufferState.printBuffer();
            newBufferState = new DefaultBufferState(bufferPrinter);
        }
        return newBufferState;
    }

}
