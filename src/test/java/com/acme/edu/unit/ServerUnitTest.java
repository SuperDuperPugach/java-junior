package com.acme.edu.unit;


import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.ServerPrinter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ServerUnitTest {
    //private LogServer fileServer;
    private ServerPrinter serverPrinter;
    private ServerPrinter serverPrinter2;
    @Before
    public void setUpSystemOut() throws IOException, BufferPrinterException {
        serverPrinter = new ServerPrinter(4747);
        serverPrinter2 = new ServerPrinter(4747);

    }

    @Test @Ignore
    public void shouldWriteToFileOnServer() throws BufferPrinterException, IOException {
        /*for(int i = 0; i < serverPrinters.length; i++) {
            serverPrinters[i].print(Integer.toString(i), "primitive: %s");
            serverPrinters[i].close();
       }*/
        serverPrinter.print(Integer.toString(16), "primitive: %s");
        serverPrinter.close();
        serverPrinter2.print(Integer.toString(10), "primitive: %s");
        serverPrinter2.close();
    }

}
