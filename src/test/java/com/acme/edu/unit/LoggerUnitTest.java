package com.acme.edu.unit;

import com.acme.edu.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class LoggerUnitTest {
    private Logger logger;

    //region given
    @Before
    public void initLogger() {
        logger = new Logger();
    }
    //endregion

    @Test @Ignore
    public void shouldPint() {
        return;
    }
}
