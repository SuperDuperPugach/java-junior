package com.acme.edu.unit;

import com.acme.edu.print.BufferPrinter;
import com.acme.edu.state.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class BufferStateSwitcherTest {
    //region given
    @Before
    public void initLogger() {
    }
    //endregion

    @Test
    public void shouldCallBufferPrinterPrintWhenChangeDefaultToStringState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState stubState = mock(DefaultBufferState.class);
        when(stubState.getState()).thenReturn(States.DEFAULT);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        bufferStateSwitcher.switchToStringState(stubState);

        verify(stubState, times(1)).printBuffer();

    }

    //
    @Test
    public void shouldNotCallBufferPrinterPrintWhenChangeStringToStringState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState stubState = mock(StringBufferState.class);
        when(stubState.getState()).thenReturn(States.STRING);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        bufferStateSwitcher.switchToStringState(stubState);

        verify(stubState, times(0)).printBuffer();

    }
    // тесты на возвращаемый тип
    @Test
    public void shouldreturnDefaultStateWhenSwitchToDefaultFromNullState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = null;
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToDefaultState(mockState);

        assertTrue(newMockState instanceof DefaultBufferState);
    }


    @Test
    public void shouldreturnIntStateWhenSwitchToIntFromAnotherState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState stubState = mock(StringBufferState.class);
        when(stubState.getState()).thenReturn(States.STRING);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToIntState(stubState);

        assertTrue(newMockState instanceof IntBufferState);
    }
}
