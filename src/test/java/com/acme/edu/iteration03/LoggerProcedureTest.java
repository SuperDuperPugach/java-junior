package com.acme.edu.iteration03;

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


    //TODO: implement LoggerProcedure solution to match specification as tests

    @Test
    public void shouldLogIntegersArray() throws IOException {
        //region when
        LoggerProcedure.log(new int[]{-1, 0, 1});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives array: {-1, 0, 1}\n"
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when
        LoggerProcedure.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives matrix: {\n" +
                "{-1, 0, 1}\n" +
                "{1, 2, 3}\n" +
                "{-1, -2, -3}\n" +
            "}\n"
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
        //region when
        LoggerProcedure.log(new int[][][][]{{{{0}}}});

        //endregion

        //region then
        assertSysoutEquals(
            "primitives multimatrix: {\n" +
                "{\n" + "{\n" + "{" +
                    "0" +
                "}\n" + "}\n" + "}\n" +
            "}\n"
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when
        LoggerProcedure.log("str1", "string 2", "str 3");
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("string: str1\nstring: string 2\nstring: str 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        LoggerProcedure.log(-1, 0, 1, 3);
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        LoggerProcedure.log(1);
        LoggerProcedure.log("str");
        LoggerProcedure.log(Integer.MAX_VALUE - 10);
        LoggerProcedure.log(11);
        LoggerProcedure.close();
        //endregion

        //region then
        assertSysoutContains("1");
        assertSysoutContains("str");
        assertSysoutContains(Integer.toString(Integer.MAX_VALUE - 10));
        assertSysoutContains("11");
        //endregion
    }


}