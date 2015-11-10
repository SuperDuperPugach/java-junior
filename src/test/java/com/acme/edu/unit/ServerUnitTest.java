package com.acme.edu.unit;


import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.ServerPrinter;
import com.acme.edu.serv.LogServer;
import com.acme.edu.serv.LogServerException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ServerUnitTest {
    private LogServer server;
    private ServerPrinter serverPrinter;
    private ServerPrinter serverPrinter2;
    @Before
    public void setUpSystemOut() throws IOException, BufferPrinterException {
        server = new LogServer(4747);
        server.accept();
        serverPrinter = new ServerPrinter(4747);
        serverPrinter2 = new ServerPrinter(4747);

    }

    @Test @Ignore
    public void shouldWriteToFileOnServer() throws BufferPrinterException, LogServerException {

        serverPrinter2.print(Integer.toString(3), "primitive: %s");
        serverPrinter.print(Integer.toString(16), "primitive: %s");
        serverPrinter.print(Integer.toString(14), "primitive: %s");
        serverPrinter2.print(Integer.toString(6), "primitive: %s");
        serverPrinter2.print(Integer.toString(8), "primitive: %s");
        serverPrinter.print(Integer.toString(13), "primitive: %s");
        serverPrinter2.close();
        serverPrinter.close();


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.closeServer();

    }

}
