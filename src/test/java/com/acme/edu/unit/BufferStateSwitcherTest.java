package com.acme.edu.unit;

import com.acme.edu.print.BufferPrinter;
import com.acme.edu.state.BufferState;
import com.acme.edu.state.BufferStateSwitcher;
import com.acme.edu.state.IntBufferState;
import com.acme.edu.state.StringBufferState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by pugach on 04/11/15.
 */
public class BufferStateSwitcherTest {
    //region given
    @Before
    public void initLogger() {
    }
    //endregion

    @Test
    public void shouldCallBufferPrinterPrintWhenChangeIntToStringState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(IntBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToStringState(mockState);

        verify(mockState, times(1)).printBuffer();

    }

    @Test
    public void shouldCallBufferPrinterPrintWhenChangeIntToDefaultState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(IntBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToDefaultState(mockState);

        verify(mockState, times(1)).printBuffer();

    }

    @Test
    public void shouldCallBufferPrinterPrintWhenChangeStringToDefaultState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(StringBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToDefaultState(mockState);

        verify(mockState, times(1)).printBuffer();

    }
}
