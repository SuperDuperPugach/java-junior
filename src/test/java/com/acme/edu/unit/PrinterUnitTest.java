package com.acme.edu.unit;


import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.BufferPrinter;
import com.acme.edu.print.ConsolePrinter;
import com.acme.edu.print.FilePrinter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PrinterUnitTest implements SysoutCaptureAndAssertionAbility {
    private BufferPrinter bufferPrinter;
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
    public void ShouldPrintInConsole() throws BufferPrinterException {
        bufferPrinter = new ConsolePrinter();
        //region when
        bufferPrinter.print("155", "primitive: %s");

        //endregion

        //region then
        assertSysoutEquals("primitive: 155\n");
        //endregion
    }

    @Test
    public void ShouldPrintIFile() throws  BufferPrinterException {
        bufferPrinter = new FilePrinter("actual.txt");
        //region when
        bufferPrinter.print("155", "primitive: %s");
        bufferPrinter.close();

        //endregion
        junitx.framework.FileAssert.assertEquals(new File("expected.txt"), new File("actual.txt"));
        //region then
        //endregion

    }

    @Test(expected = BufferPrinterException.class)
    public void shouldCatchExceptionIfNullObject() throws BufferPrinterException {
        bufferPrinter = new FilePrinter("");
    }

    @Test(expected = BufferPrinterException.class)
    public void shouldThrowExceptionWnenWrongEncodingName() throws BufferPrinterException {
        bufferPrinter = new FilePrinter("test", "sdl;fk");
    }
}
