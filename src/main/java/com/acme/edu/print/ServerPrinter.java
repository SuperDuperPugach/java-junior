package com.acme.edu.print;


import com.acme.edu.except.BufferPrinterException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Реализация интерфейса BufferPrinter с передачей данных на сервер
 * и последующей записью в файл(на сервере)
 */
public class ServerPrinter implements BufferPrinter {
    private int count; //счетчик вызовов метода принт
    private Socket socket;
    private PrintWriter toServer;

    /**
     * Коснтруктор, принимающий в качетсве параметра имя порта сервера
     * (в данном случаее сервер локальный)
     * @param port - номер порта локального сервера
     * @throws IOException
     * @throws BufferPrinterException
     */
    public ServerPrinter(int port) throws  BufferPrinterException {
        System.out.println("shouldWriteToFileOnServer()");
        try {
            socket = new Socket(InetAddress.getByName(null), port);
            initPrintWriter();
        } catch (IOException e) {
            throw new BufferPrinterException("can't conncet to server");
        }
        count = 0;
    }

    /**
     * Передает сообщение на сервер, для записи в файл
     * @param buffer - что печатать
     * @param format - шаблон вывода
     * @throws BufferPrinterException
     */
    @Override
    public void print(String buffer, String format) throws BufferPrinterException{
        count++;
        if(toServer != null) {
            toServer.println(String.format(format, buffer));
        }
        if((count % 50) == 0) toServer.flush();
    }
    /**
     * Метод, который закрывает поток отпавления логов на серевер. Необходимо
     * вызвать после окончания вывода логов.
     */
    @Override
    public void close() {
        toServer.close();
    }

    private void initPrintWriter() throws IOException {
        toServer = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), false);
    }
}
