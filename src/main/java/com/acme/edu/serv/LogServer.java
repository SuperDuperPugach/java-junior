package com.acme.edu.serv;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Сервер, записывающий принимаемые сообщения в лог файл
 */
public class LogServer {
    private static final String FILE_NAME = "log.dat";

    private ServerSocket serverSocket;
    private Socket client;
    private ExecutorService pool;
    private ExecutorService poolMain;
    private BufferedWriter toFile;
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
        poolMain = Executors.newFixedThreadPool(2);
        toFile = new BufferedWriter(
                new FileWriter(FILE_NAME, true));
    }

    /**
     * Переводит сервер в состояние ожидания подключения клиентов
     * @throws IOException
     */
    public void accept() {
        poolMain.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        client = serverSocket.accept();
                        pool.execute(new FileServerPrinter(client, toFile));
                    } catch (IOException e) {
                        //TODO решить что делать с ошибкой
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * Необходимо вызвать для закрытия закрытия сервера
     * @throws IOException
     */
    public void closeServer() throws LogServerException {
        pool.shutdown(); //ждем выполнения всех потоков
        if(toFile != null) {
            try {
                toFile.close(); //закрываем файл
            } catch (IOException e) {
                throw new LogServerException("can't close log file");
            }
        }
        if(serverSocket != null) {
            try {
                serverSocket.close(); //закрываем сервер
            } catch (IOException e) {
                throw new LogServerException("can't close server socket");
            }
        }
    }
}
