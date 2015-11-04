package com.acme.edu.unit;


import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.print.BufferPrinter;
import com.acme.edu.print.ConsolePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PrinterUnitTest implements SysoutCaptureAndAssertionAbility {
    private BufferPrinter bufferPrinter;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        bufferPrinter = new ConsolePrinter();
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void ShouldPrintInConsole() {
        //region when
        bufferPrinter.print("155", "primitive: %s");
        //endregion

        //region then
        assertSysoutEquals("primitive: 155\n");
        //endregion

    }
}
