package com.acme.edu.unit;

import com.acme.edu.print.BufferPrinter;
import com.acme.edu.state.BufferState;
import com.acme.edu.state.IntBufferState;
import com.acme.edu.state.StringBufferState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StateUnitTest {
    private BufferState bufferState;

    //region given
    @Before
    public void initLogger() {

    }
    //endregion

    @Test
    public void shouldCallBufferPrinterPrintIfSomethingInBufferInIntBufferState() {
        BufferPrinter mock = mock(BufferPrinter.class);
        BufferState sut = new IntBufferState(mock);
        sut.pushMessageToBuffer("5", "primitive: %s");
        sut.printBuffer();

        verify(mock).print("5", "primitive: %s");

    }

    @Test
    public void shouldCallBufferPrinterPrintIfSomethingInBufferInStringBufferState() {
        BufferPrinter mock = mock(BufferPrinter.class);
        BufferState sut = new StringBufferState(mock);
        sut.pushMessageToBuffer("str 1", "string: %s");
        sut.printBuffer();

        verify(mock).print("str 1", "string: %s");

    }
}
