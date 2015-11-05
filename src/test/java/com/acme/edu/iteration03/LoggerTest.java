package com.acme.edu.iteration03;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
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
    public void shouldLogIntegersArray() throws IOException {
        //region when
        logger.log(new int[]{-1, 0, 1});
        logger.close();
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
        logger.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        logger.close();
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
        logger.log(new int[][][][]{{{{0}}}});
        logger.close();

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
        logger.log("str1", "string 2", "str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutContains("string: str1\nstring: string 2\nstring: str 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        logger.log(-1, 0, 1, 3);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        logger.log(1);
        logger.log("str");
        logger.log(Integer.MAX_VALUE - 10);
        logger.log(11);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("1");
        assertSysoutContains("str");
        assertSysoutContains(Integer.toString(Integer.MAX_VALUE - 10));
        assertSysoutContains("11");
        //endregion
    }
}