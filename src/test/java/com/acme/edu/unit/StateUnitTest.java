package com.acme.edu.unit;

import com.acme.edu.print.BufferPrinter;
import com.acme.edu.state.BufferState;
import com.acme.edu.state.DefaultBufferState;
import com.acme.edu.state.IntBufferState;
import com.acme.edu.state.StringBufferState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class StateUnitTest {
    private BufferState bufferState;
    //private BufferPrinter mock;

    //region given
    @Before
    public void initLogger() {
       // BufferPrinter mock = mock(BufferPrinter.class);
    }
    //endregion

    @Test
    public void shouldCallBufferPrinterPrintIfSomethingInBufferInIntBufferState() {
        BufferPrinter mock = mock(BufferPrinter.class);
        BufferState sut = new IntBufferState(mock);
        sut.pushMessageToBuffer("5", "primitive: %s");
        sut.pushMessageToBuffer("10", "primitive: %s");
        sut.printBuffer();

        verify(mock, times(1)).print("15", "primitive: %s");

    }

    @Test
    public void shouldCallBufferPrinterPrintIfSomethingInBufferInStringBufferState() {
        BufferPrinter mock = mock(BufferPrinter.class);
        BufferState sut = new StringBufferState(mock);
        sut.pushMessageToBuffer("str 1", "string: %s");
        sut.pushMessageToBuffer("str 1", "string: %s");
        sut.pushMessageToBuffer("str 1", "string: %s");
        sut.pushMessageToBuffer("str 2", "string: %s");
        sut.printBuffer();

        verify(mock, times(1)).print("str 1 (x3)", "string: %s");
        verify(mock, times(1)).print("str 2", "string: %s");

    }

    @Test
    public void shouldCallBufferPrinterPrintIfSomethingInBuffer() {
        BufferPrinter mock = mock(BufferPrinter.class);
        BufferState sut = new StringBufferState(mock);
        sut.pushMessageToBuffer("str 1", "string: %s");
        sut.pushMessageToBuffer("str 1", "string: %s");
        sut.printBuffer();
        sut = new IntBufferState(mock);
        sut.pushMessageToBuffer("5", "primitive: %s");
        sut.pushMessageToBuffer("7", "primitive: %s");
        sut.printBuffer();

        verify(mock, times(1)).print("str 1 (x2)", "string: %s");
        verify(mock, times(1)).print("12", "primitive: %s");

    }

    @Test
    public void shouldCallBufferPrinterIfStackOverFlowInIntBufferState() {
        BufferPrinter mock = mock(BufferPrinter.class);
        BufferState sut = new IntBufferState(mock);
        sut.pushMessageToBuffer(Integer.toString(Integer.MAX_VALUE - 10), "primitive: %s");
        sut.pushMessageToBuffer(Integer.toString(100), "primitive: %s");

        verify(mock, times(1)).print(Integer.toString(Integer.MAX_VALUE - 10), "primitive: %s");
    }

    @Test
    public void shouldCallBufferPrinterIfSomethingInBufferInDefaultBufferState() {
        BufferPrinter mock = mock(BufferPrinter.class);
        BufferState sut = new DefaultBufferState(mock);
        sut.pushMessageToBuffer("d", "char: %s");
        sut.pushMessageToBuffer("{1, 2}", "array: %s");
        sut.pushMessageToBuffer("k", "char: %s");

        verify(mock, times(1)).print("d", "char: %s");
        verify(mock, times(1)).print("{1, 2}", "array: %s");
    }

}
