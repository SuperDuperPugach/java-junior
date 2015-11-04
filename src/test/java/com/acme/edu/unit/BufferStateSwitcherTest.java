package com.acme.edu.unit;

import com.acme.edu.print.BufferPrinter;
import com.acme.edu.state.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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

    @Test @Ignore
    public void shouldCallBufferPrinterPrintWhenChangeDefaultToStringState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(DefaultBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToStringState(mockState);

        verify(mockState, times(1)).printBuffer();

    }

    //
    @Test @Ignore
    public void shouldNotCallBufferPrinterPrintWhenChangeStringToStringState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(StringBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToStringState(mockState);



    }
    //
    @Test
    public void shouldreturnDefaultStateWhenSwitchToDefaultFromAnotherState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(IntBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToDefaultState(mockState);

        assertTrue(newMockState instanceof DefaultBufferState);
    }

    @Test
    public void shouldreturnStringStateWhenSwitchToStringFromAnotherState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(IntBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToStringState(mockState);

        assertTrue(newMockState instanceof StringBufferState);
    }

    @Test
    public void shouldreturnIntStateWhenSwitchToIntFromAnotherState() {
        BufferPrinter mockPrinter = mock(BufferPrinter.class);
        BufferState mockState = mock(StringBufferState.class);
        BufferStateSwitcher bufferStateSwitcher = new BufferStateSwitcher(mockPrinter);
        BufferState newMockState = bufferStateSwitcher.switchToIntState(mockState);

        assertTrue(newMockState instanceof IntBufferState);
    }
}
