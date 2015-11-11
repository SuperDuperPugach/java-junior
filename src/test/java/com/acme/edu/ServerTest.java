package com.acme.edu;


import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.ServerPrinter;
import com.acme.edu.serv.LogServer;
import com.acme.edu.serv.LogServerException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class ServerTest {
    private LogServer server;
    private ServerPrinter serverPrinter;
    private ServerPrinter serverPrinter2;
    private File actual;
    private File expected;
    @Before
    public void setUpSystemOut() throws IOException, BufferPrinterException {
        server = new LogServer(4747);
        server.accept();
        serverPrinter = new ServerPrinter(4747);
        serverPrinter2 = new ServerPrinter(4747);

    }

    @Test
    public void shouldWriteToFileOnServer() throws BufferPrinterException, LogServerException, IOException {

        synchronized (server) {
            actual = new File("log.dat");
            expected = new File("expected.txt");
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(expected, false)));
            for(int i = 0; i < 200; i++) {
                writer.println("primitive: " + (i+1));
            }

            writer.close();
            for(int i = 0; i < 200; i+=2) {
                serverPrinter.print(Integer.toString(i+1), "primitive: %s");
                serverPrinter2.print(Integer.toString(i+2), "primitive: %s");
            }
            serverPrinter2.close();
            serverPrinter.close();


            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            server.closeServer();
            List<String> expexctedList = FileUtils.readLines(expected);
            List<String> actualList = FileUtils.readLines(actual);
            Collections.sort(expexctedList);
            Collections.sort(actualList);

            Assert.assertEquals(expexctedList, actualList);

            if(actual.exists()) actual.delete();
            if(expected.exists()) expected.delete();
        }

    }

}
