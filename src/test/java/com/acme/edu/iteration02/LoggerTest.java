package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.LoggerProcedure;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    Logger logger;

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
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1\n" +
                        "primitive: 3\n" +
                        "string: str 2\n" +
                        "primitive: 0\n"
        );
        //endregion
    }


    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1\n" +
                        "string: str 2 (x2)\n" +
                        "primitive: 0\n" +
                        "string: str 2\n" +
                        "string: str 3 (x3)\n"
        );
        //endregion
    }


}
