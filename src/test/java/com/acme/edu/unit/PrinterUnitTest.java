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
    private File actual1;
    private File actual2;
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
        String filename1 = UUID.randomUUID().toString();
        actual1 = new File(filename1);
        bufferPrinter = new FilePrinter(filename1);
        //region when
        bufferPrinter.print("155", "primitive: %s");
        bufferPrinter.close();

        //endregion
        //region then
        junitx.framework.FileAssert.assertEquals(expected, actual1);
        if(actual1.exists()) {
            actual1.delete();
        }
        //endregion

    }

    @Test
    public void ShouldPrintInFileConstructorWithTwoArg() throws  BufferPrinterException {
        String filename2 = UUID.randomUUID().toString();
        actual2 = new File(filename2);
        bufferPrinter = new FilePrinter(filename2, "UTF-8");

        //region when
        bufferPrinter.print("155", "primitive: %s");
        bufferPrinter.close();
        //endregion

        //region then
        junitx.framework.FileAssert.assertEquals(expected, actual2);
        if(actual2.exists()) {
            actual2.delete();
        }
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
    //endregion

    //region ServerPrinter

    //endregion

}
