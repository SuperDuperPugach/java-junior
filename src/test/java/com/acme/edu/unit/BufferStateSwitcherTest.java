package com.acme.edu.unit;

import com.acme.edu.print.BufferPrinter;
import com.acme.edu.state.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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
    public void shouldCallBufferPrinterPrintWhenChangeDefaultToStringState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState stubState = mock(DefaultBufferState.class);
        when(stubState.getState()).thenReturn(States.DEFAULT);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        bufferStateSwitcher.switchToStringState(stubState);

        verify(stubState, times(1)).printBuffer();

    }

    //
    @Test @Ignore
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
    public void shouldreturnDefaultStateWhenSwitchToDefaultFromAnotherState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(IntBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToDefaultState(mockState);

        assertTrue(newMockState instanceof DefaultBufferState);
    }

    @Test @Ignore
    public void shouldreturnStringStateWhenSwitchToStringFromAnotherState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(IntBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToStringState(mockState);

        assertTrue(newMockState instanceof StringBufferState);
    }

    @Test @Ignore
    public void shouldreturnIntStateWhenSwitchToIntFromAnotherState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(StringBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToIntState(mockState);

        assertTrue(newMockState instanceof IntBufferState);
    }
}
