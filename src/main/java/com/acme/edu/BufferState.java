package com.acme.edu;

/**
 * Created by pugach on 02/11/15.
 */
public abstract class BufferState {
    String buffer; //буффер
    String format; //формат
    BufferPrinter bufferPrinter; //ссылка для хранения определенной реализации вывода сообщения
    //конструктор
    BufferState(BufferPrinter bufferPrinter) {
        this.bufferPrinter = bufferPrinter;
    }
    //для реализации возвращения типа текущего объекта
    enum State {
        INT, STRING, OTHER
    }
    abstract State getState(); //возвращает тип текущего объекта(необходим для логгера)
    abstract void pushMessageToBuffer(String message); //будет использоваться в логгере для передачи месседжа

    void printBuffer() { // печать буфера
        bufferPrinter.print(buffer, format);
    }
}
