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
    private static final String END = "END_LOG";
    private Socket socket;
    private BufferedWriter toServer;

    /**
     * Коснтруктор, принимающий в качетсве параметра имя порта сервера
     * (в данном случаее сервер локальный)
     * @param port - номер порта локального сервера
     * @throws IOException
     * @throws BufferPrinterException
     */
    public ServerPrinter(int port) throws  BufferPrinterException {
        try {
            socket = new Socket(InetAddress.getByName(null), port);
            initPrintWriter();
        } catch (IOException e) {
            throw new BufferPrinterException("can't connect to server");
        }
    }

    /**
     * Передает сообщение на сервер, для записи в файл
     * @param buffer - что печатать
     * @param format - шаблон вывода
     * @throws BufferPrinterException
     */
    @Override
    public void print(String buffer, String format) throws BufferPrinterException{
        try {
            if (toServer != null) {
                toServer.write(String.format(format, buffer) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new BufferPrinterException("Can't write to server(ServerPrinter)");
        }
    }
    /**
     * Метод, который закрывает поток отпавления логов на серевер. Необходимо
     * вызвать после окончания вывода логов.
     */
    @Override
    public void close() throws BufferPrinterException {
        try {
            if(toServer != null)  {
                toServer.write(END);
                toServer.flush();
                toServer.close();
            }
        } catch (IOException e) {
            throw new BufferPrinterException();
        }
    }

    private void initPrintWriter() throws IOException {
        toServer = new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
    }
}
