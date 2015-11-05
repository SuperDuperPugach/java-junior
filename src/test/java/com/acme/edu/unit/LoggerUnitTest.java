package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
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
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        loggerForException = new Logger();
        mockState = mock(BufferState.class);
        stubSwitcher = mock(BufferStateSwitcher.class);
        when(stubSwitcher.switchToIntState(null)).thenReturn(new IntBufferState(null));
        when(stubSwitcher.switchToStringState(null)).thenReturn(new StringBufferState(null));
        when(stubSwitcher.switchToDefaultState(null)).thenReturn(new DefaultBufferState(null));
        loggerForLog = new Logger(stubSwitcher);
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    //endregion

    @Test
    public void shouldCallSwitchtoIntStateMethodWhenCallLogInt() {
        loggerForLog.log(1);
        //loggerForLog.log(2);

        verify(stubSwitcher).switchToIntState(any());
    }

    @Test
    public void shouldCallSwitchtoIntStateMethodWhenCallLogArrayInt() {
        loggerForLog.log(1, 2);

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoIntStateMethodWhenCallLogMassive2dInt() {
        loggerForLog.log(new int[][] {{1,2}});

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoIntStateMethodWhenCallLogMassive4dInt() {
        loggerForLog.log(new int[][][][]{{{{0}}}});

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoStringStateMethodWhenCallLogString() {
        loggerForLog.log("str");

        verify(stubSwitcher).switchToStringState(any());
    }

    /*@Test
    public void shouldCallSwitchtoStringStateMethodWhenCallLogVarargString() {
        loggerForLog.log("str1", "str2", "str3");

        verify(stubSwitcher).switchToStringState(any());
    }*/
    @Test
    public void shouldCallSwitchtoDefaultStateMethodWhenCallLogChar() {
        loggerForLog.log('c');

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoDefaultStateMethodWhenCallLogBoll() {
        loggerForLog.log(true);

        verify(stubSwitcher).switchToDefaultState(any());
    }

    @Test
    public void shouldCallSwitchtoDefaultStateMethodWhenCallLogObject() {
        loggerForLog.log(new Object());

        verify(stubSwitcher).switchToDefaultState(any());
    }

    //region log() methods

    //region exception
    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullString() {

        loggerForException.log((String) null);
    }

    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullVarargString() {

        loggerForException.log((String) null, (String) null, (String) null);
    }

    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullObject() {

        loggerForException.log((Integer) null);
    }
    //endregion
}
