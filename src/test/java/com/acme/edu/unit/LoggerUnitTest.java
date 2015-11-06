package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.except.NullInLogException;
import com.acme.edu.print.BufferPrinter;
import com.acme.edu.state.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class LoggerUnitTest implements SysoutCaptureAndAssertionAbility {
    private Logger loggerForException;
    private Logger loggerForLog;
    private BufferStateSwitcher stubSwitcher;
    private BufferState mockState;
    private BufferPrinter mockPrinter;
    //region given
    @Before
    public void setUpSystemOut() throws IOException, BufferPrinterException {
        loggerForException = new Logger();
        mockState = mock(BufferState.class);
        mockPrinter = mock(BufferPrinter.class);

        stubSwitcher = mock(BufferStateSwitcher.class);
        when(stubSwitcher.switchToIntState(anyObject())).thenReturn(new IntBufferState(new BufferPrinter[]{mockPrinter}));
        when(stubSwitcher.switchToStringState(anyObject())).thenReturn(new StringBufferState(new BufferPrinter[]{mockPrinter}));
        when(stubSwitcher.switchToDefaultState(anyObject())).thenReturn(new DefaultBufferState(new BufferPrinter[]{mockPrinter}));

        loggerForLog = new Logger(stubSwitcher);
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    //region log() methods
    @Test
    public void shouldCallSwitchtoIntStateMethodWhenCallLogInt() throws BufferPrinterException {
        loggerForLog.log(1);
        loggerForLog.log(2);
        loggerForLog.log(2);

        verify(stubSwitcher, times(3)).switchToIntState(anyObject());
    }

    @Test
    public void shouldCallSwitchtoIntStateMethodWhenCallLogArrayInt() throws BufferPrinterException {
        loggerForLog.log(1, 2);

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoIntStateMethodWhenCallLogMassive2dInt() throws BufferPrinterException {
        loggerForLog.log(new int[][] {{1,2}});

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoIntStateMethodWhenCallLogMassive4dInt() throws BufferPrinterException {
        loggerForLog.log(new int[][][][]{{{{0}}}});

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoStringStateMethodWhenCallLogString() throws NullInLogException, BufferPrinterException {
        loggerForLog.log("str");
        loggerForLog.log("str");

        verify(stubSwitcher, times(2)).switchToStringState(anyObject());
    }

    @Test
    public void shouldCallSwitchtoStringStateMethodWhenCallLogVarargString() throws NullInLogException, BufferPrinterException {
        loggerForLog.log("str1", "str2");

        verify(stubSwitcher, times(2)).switchToStringState(any());
    }
    @Test
    public void shouldCallSwitchtoDefaultStateMethodWhenCallLogChar() throws BufferPrinterException {
        loggerForLog.log('c');

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoDefaultStateMethodWhenCallLogBoll() throws BufferPrinterException {
        loggerForLog.log(true);

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoDefaultStateMethodWhenCallLogObject() throws NullInLogException, BufferPrinterException {
        loggerForLog.log(new Object());

        verify(stubSwitcher).switchToDefaultState(any());
    }
    //endregion

    //region exception
    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullString() throws NullInLogException, BufferPrinterException {

        loggerForException.log((String) null);
    }

    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullVarargString() throws NullInLogException, BufferPrinterException {

        loggerForException.log((String) null, (String) null, (String) null);
    }



    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullObject() throws NullInLogException, BufferPrinterException {

        loggerForException.log((Integer) null);
    }
    //endregion
}
