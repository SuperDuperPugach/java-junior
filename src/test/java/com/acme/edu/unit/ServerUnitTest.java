package com.acme.edu.unit;


import com.acme.edu.except.BufferPrinterException;
import com.acme.edu.print.ServerPrinter;
import com.acme.edu.serv.FileServer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ServerUnitTest {
    //private FileServer fileServer;
    private ServerPrinter serverPrinter;
    @Before
    public void setUpSystemOut() throws IOException, BufferPrinterException {
        //fileServer = new FileServer(4747);
        //fileServer.accept();
        Runtime runtime = Runtime.getRuntime();
        //runtime.exec("touch dina");
        runtime.exec("java com.acme.edu.serv.FileServer", null, new File("target/classes/"));
        //runtime.exec("java /target/classes/com.acme.edu.serv.FileServer");
        serverPrinter = new ServerPrinter(4747);
    }
    //проверяет запись логов на сервере(необходим запущенный сервер)
    @Test @Ignore
    public void shouldWriteToFileOnServer() throws BufferPrinterException, IOException {
        serverPrinter.print("1", "primitive: %s");
        serverPrinter.print("1", "primitive: %s");
        serverPrinter.print("1", "primitive: %s");
        serverPrinter.print("true", "primitive: %s");
        serverPrinter.print("false", "primitive: %s");
        serverPrinter.print("true", "primitive: %s");
        serverPrinter.close();
    }

}
