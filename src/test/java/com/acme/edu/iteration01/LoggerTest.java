package com.acme.edu.iteration01;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.except.NullInLogException;
import com.acme.edu.print.FilePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore
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
    public void shouldLogInteger() throws IOException, BufferPrinterException {
        //region when
        logger.log(1);
        logger.log(0);
        logger.log(-1);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 0\n");
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException, BufferPrinterException {
        //region when
        logger.log((byte) 1);
        logger.log((byte) 0);
        logger.log((byte) -1);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        //assertSysoutContains("1");
        assertSysoutContains("0");
        //assertSysoutContains("-1");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException, BufferPrinterException {
        //region when
        logger.log(true);
        logger.log(false);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogChar() throws IOException, BufferPrinterException {
        //region when
        logger.log('a');
        logger.log('b');
        logger.close();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

   /* @Test
    public void shouldLogString() throws IOException {
        //region when
        logger.log("test string 1");
        logger.log("other str");
        logger.close();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }*/

    @Test
    public void shouldLogReference() throws IOException, NullInLogException, BufferPrinterException {
        //region when
        logger.log(new Object());
        logger.close();
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }


}