package com.acme.edu.serv;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Сервер, записывающий принимаемые сообщения в лог файл
 */
public class LogServer {

    private ServerSocket serverSocket;
    private Socket client;
    private Executor pool;
    /**
     * Конструктор, принимающий в качестве параметра номер порта. Сервер помещается в
     * режим ожидания соединения с клиентом
     * @param port - номер порта
     * @throws IOException - бросает, если невозможно создать сервер или установить соединение
     * с клиентом
     */
    public LogServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(5);
    }

    /**
     * Переводит сервер в состояние ожидания подключения клиентов
     * @throws IOException
     */
    public void accept() throws IOException {
        while(true) {
            client = serverSocket.accept();
            pool.execute(new FileServerPrinter(client));
        }
    }


    /**
     * Необходимо вызвать для закрытия записи в файл и закрытия сервера
     * @throws IOException
     */
    public void closeServer() throws IOException {
        if(serverSocket != null) serverSocket.close();
    }

    public static void main(String[] args) {
        try {
            LogServer logServer = new LogServer(4747);
            logServer.accept();
            logServer.closeServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
