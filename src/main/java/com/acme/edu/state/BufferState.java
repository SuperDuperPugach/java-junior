package com.acme.edu.state;

import com.acme.edu.print.BufferPrinter;

/**
 * Created by pugach on 02/11/15.
 */
public abstract class BufferState {
    //String buffer; //буффер
    String format; //формат
    BufferPrinter bufferPrinter; //ссылка для хранения определенной реализации вывода сообщения
    //конструктор
    BufferState(BufferPrinter bufferPrinter) {
        this.bufferPrinter = bufferPrinter;
    }
    //для реализации возвращения типа текущего объекта
    public enum State {
        INT, STRING, DEFAULT
    }
    public abstract  State getState(); //возвращает тип текущего объекта(необходим для логгера)
    public abstract void pushMessageToBuffer(String message, String format); //будет использоваться в логгере для передачи месседжа

    public abstract void printBuffer(); // печать буфера

}
