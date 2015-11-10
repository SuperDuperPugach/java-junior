package com.acme.edu.unit;


import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.BufferPrinter;
import com.acme.edu.print.ConsolePrinter;
import com.acme.edu.print.FilePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.UUID;

public class PrinterUnitTest implements SysoutCaptureAndAssertionAbility {
    private BufferPrinter bufferPrinter;
    private File expected;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        expected = new File(UUID.randomUUID().toString());
        PrintWriter writer = new PrintWriter(expected, "UTF-8");
        writer.println("primitive: 155");
        writer.close();

        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        if(expected.exists()) expected.delete();
        resetOut();
    }
    //endregion

    //region ConsolePrinter
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

    @Test(expected = BufferPrinterException.class)
    public void shouldCatchExceptionIfWrongFormatinConsolePrinter() throws BufferPrinterException {
        bufferPrinter = new ConsolePrinter();
        //region when
        bufferPrinter.print("d", "%s%s%a");
        //endregion
    }
    //endregion

    //region FilePrinter
    @Test
    public void ShouldPrintInFileConstructorWithOneArg() throws  BufferPrinterException {
        String filename = UUID.randomUUID().toString();
        File actual = new File(filename);
        bufferPrinter = new FilePrinter(filename);
        //region when
        bufferPrinter.print("155", "primitive: %s");
        bufferPrinter.close();

        //endregion
        //region then
        junitx.framework.FileAssert.assertEquals(expected, actual);
        if(actual.exists()) {
            actual.delete();
        }
        //endregion

    }

    @Test
    public void ShouldPrintInFileConstructorWithTwoArg() throws  BufferPrinterException {
        String filename = UUID.randomUUID().toString();
        File actual = new File(filename);
        bufferPrinter = new FilePrinter(filename, "UTF-8");

        //region when
        bufferPrinter.print("155", "primitive: %s");
        bufferPrinter.close();
        //endregion

        //region then
        junitx.framework.FileAssert.assertEquals(expected, actual);
        if(actual.exists()) {
            actual.delete();
        }
        //endregion

    }

    @Test(expected = BufferPrinterException.class)
    public void shouldCatchExceptionIfNullObject() throws BufferPrinterException {
        String filename = "";
        File actual = new File(filename);
        bufferPrinter = new FilePrinter("");
        if(actual.exists()) {
            actual.delete();
        }
    }

    @Test(expected = BufferPrinterException.class)
    public void shouldThrowExceptionWnenWrongEncodingName() throws BufferPrinterException {
        String filename = UUID.randomUUID().toString();
        File actual = new File(filename);
        bufferPrinter = new FilePrinter(filename, "sdl;fk");
        if(actual.exists()) {
            actual.delete();
        }
    }

    //endregion

    //region ServerPrinter

    //endregion

}
