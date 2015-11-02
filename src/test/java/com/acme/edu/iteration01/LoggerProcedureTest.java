package com.acme.edu.iteration01;

import com.acme.edu.LoggerProcedure;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerProcedureTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
    resetOut();
    }
    //endregion

    @Test
    public void shouldLogInteger() throws IOException {
        //region when
        LoggerProcedure.log(1);
        LoggerProcedure.log(0);
        LoggerProcedure.log(-1);
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 0\n");
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        LoggerProcedure.log((byte) 1);
        LoggerProcedure.log((byte) 0);
        LoggerProcedure.log((byte) -1);
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        //assertSysoutContains("1");
        assertSysoutContains("0");
        //assertSysoutContains("-1");
        //endregion
    }


    //TODO: implement LoggerProcedure solution to match specification as tests

    @Test
    public void shouldLogChar() throws IOException {
        //region when
        LoggerProcedure.log('a');
        LoggerProcedure.log('b');
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException {
        //region when
        LoggerProcedure.log("test string 1");
        LoggerProcedure.log("other str");
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        LoggerProcedure.log(true);
        LoggerProcedure.log(false);
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException {
        //region when
        LoggerProcedure.log(new Object());
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }


}