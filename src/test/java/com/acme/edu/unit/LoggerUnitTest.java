package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.except.NullInLogException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerUnitTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        logger = new Logger();
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

//обрабатываем исключительные значения
    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullString() {

        logger.log((String) null);
    }

    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullVarargString() {

        logger.log((String) null, (String) null, (String) null);
    }

    @Test(expected = NullInLogException.class)
    public void shouldCatchExceptionIfNullObject() {

        logger.log((Integer) null);
    }
}
