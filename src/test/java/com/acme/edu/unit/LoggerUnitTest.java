package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.print.ConsolePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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


    @Test
    public void shouldPint() {

    }
}
