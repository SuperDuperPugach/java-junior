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

public class PrinterUnitTest implements SysoutCaptureAndAssertionAbility {
    private BufferPrinter bufferPrinter;
    File expected;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
       /* expected = new File("expected.txt");

        PrintWriter toFile =new PrintWriter( new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(expected), "UTF-8")));
        toFile.println("primitive: 155");
        toFile.flush();
        toFile.close();
        System.out.println(expected.getAbsolutePath());*/

        PrintWriter writer = new PrintWriter("expected.txt", "UTF-8");
        writer.println("primitive: 155");
        writer.close();

        expected = new File("expected.txt");

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
        bufferPrinter = new FilePrinter("actual.txt", "UTF-8");
        //region when
        bufferPrinter.print("155", "primitive: %s");
        bufferPrinter.close();

        //endregion
        //region then
        File actual = new File("actual.txt");
        junitx.framework.FileAssert.assertEquals(expected, actual);
        if(actual.exists()) {
            actual.delete();
        }
        //endregion

    }

    @Test
    public void ShouldPrintInFileConstructorWithTwoArg() throws  BufferPrinterException {
        bufferPrinter = new FilePrinter("actual.txt", "UTF-8");
        //region when
        bufferPrinter.print("155", "primitive: %s");
        bufferPrinter.close();

        //endregion
        //region then
        File actual = new File("actual.txt");
        junitx.framework.FileAssert.assertEquals(expected, actual);
        if(actual.exists()) {
            actual.delete();
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
