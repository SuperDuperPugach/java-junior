package com.acme.edu.iteration02;

import com.acme.edu.LoggerProcedure;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore
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



    //TODO: implement LoggerProcedure solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        LoggerProcedure.log("str 1");
        LoggerProcedure.log(1);
        LoggerProcedure.log(2);
        LoggerProcedure.log("str 2");
        LoggerProcedure.log(0);
        LoggerProcedure.close();
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
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        LoggerProcedure.log("str 1");
        LoggerProcedure.log(10);
        LoggerProcedure.log(Integer.MAX_VALUE);
        LoggerProcedure.log("str 2");
        LoggerProcedure.log(0);
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1\n" +
            "primitive: 10\n" +
            "primitive: " + Integer.MAX_VALUE + "\n" +
            "string: str 2\n" +
            "primitive: 0\n"
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        LoggerProcedure.log("str 1");
        LoggerProcedure.log((byte) 10);
        LoggerProcedure.log((byte) Byte.MAX_VALUE);
        LoggerProcedure.log("str 2");
        LoggerProcedure.log(0);
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1\n" +
                        "primitive: 10\n" +
                        "primitive: " +
                        Byte.MAX_VALUE + "\n" +
                        "string: str 2\n" +
                        "primitive: 0\n"
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        LoggerProcedure.log("str 1");
        LoggerProcedure.log("str 2");
        LoggerProcedure.log("str 2");
        LoggerProcedure.log(0);
        LoggerProcedure.log("str 2");
        LoggerProcedure.log("str 3");
        LoggerProcedure.log("str 3");
        LoggerProcedure.log("str 3");
        LoggerProcedure.close();
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