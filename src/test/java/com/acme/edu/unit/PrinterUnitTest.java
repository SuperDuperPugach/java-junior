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

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        bufferPrinter = new FilePrinter("actual.txt");
        //region when
        bufferPrinter.print("155", "primitive: %s");
        bufferPrinter.close();

        //endregion
        //region then
        File actual = new File("actual.txt");
        junitx.framework.FileAssert.assertEquals(new File("expected.txt"), actual);
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
        junitx.framework.FileAssert.assertEquals(new File("expected.txt"), actual);
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
