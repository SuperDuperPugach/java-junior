package com.acme.edu.unit;

import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.BufferPrinter;
import com.acme.edu.state.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class BufferStateSwitcherTest {
    private BufferPrinter mockPrinter;
    //region given
    @Before
    public void initLogger() {
        mockPrinter = mock(BufferPrinter.class);
    }
    //endregion
    //test should call printer
    @Test
    public void shouldCallBufferPrinterPrintWhenChangeDefaultToStringState() throws BufferPrinterException {
        BufferState stubState = mock(DefaultBufferState.class);
        when(stubState.getState()).thenReturn(States.DEFAULT);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(new BufferPrinter[]{mockPrinter});
        bufferStateSwitcher.switchToStringState(stubState);

        verify(stubState, times(1)).printBuffer();

    }

    @Test
    public void shouldCallBufferPrinterPrintWhenChangeIntToDefaultState() throws BufferPrinterException {
        BufferState stubState = mock(IntBufferState.class);
        when(stubState.getState()).thenReturn(States.INT);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(new BufferPrinter[]{mockPrinter});
        bufferStateSwitcher.switchToStringState(stubState);

        verify(stubState, times(1)).printBuffer();

    }

    //test should not call printer
    @Test
    public void shouldNotCallBufferPrinterPrintWhenChangeStringToStringState() throws BufferPrinterException {
        BufferState stubState = mock(StringBufferState.class);
        when(stubState.getState()).thenReturn(States.STRING);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(new BufferPrinter[]{mockPrinter});
        bufferStateSwitcher.switchToStringState(stubState);

        verify(stubState, times(0)).printBuffer();

    }
    // тесты на возвращаемый тип
    @Test
    public void shouldreturnDefaultStateWhenSwitchToDefaultFromNullState() throws BufferPrinterException {
        BufferState mockState = null;
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(new BufferPrinter[]{mockPrinter});
        BufferState newMockState = bufferStateSwitcher.switchToDefaultState(mockState);

        assertTrue(newMockState instanceof DefaultBufferState);
    }


    @Test
    public void shouldreturnIntStateWhenSwitchToIntFromAnotherState() throws BufferPrinterException {
        BufferState stubState = mock(StringBufferState.class);
        when(stubState.getState()).thenReturn(States.STRING);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(new BufferPrinter[]{mockPrinter});
        BufferState newMockState = bufferStateSwitcher.switchToIntState(stubState);

        assertTrue(newMockState instanceof IntBufferState);
    }
}
