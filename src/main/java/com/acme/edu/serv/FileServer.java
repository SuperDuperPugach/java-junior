package com.acme.edu.serv;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сервер, записывающий принимаемые сообщения в лог файл
 */
public class FileServer {
    private ServerSocket serverSocket;
    private Socket client;
    private BufferedReader fromClient;
    private PrintWriter toFile;
    private final String filename= "log.dat";
    private int count; //счетчик вызовов метода принт
    /**
     * Конструктор, принимающий в качестве параметра номер порта
     * @param port - номер порта
     * @throws IOException
     */
    public FileServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        count = 0;
    }

    /**
     * Метод, после вызова которого сервер ожидает соединения. После установки соединения
     * начинается запись принимаемых сообщений в файл
     * @throws IOException
     */
    public void accept() throws IOException {
        client = serverSocket.accept();
        fromClient = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
        //ловим ошибку записи логов
        try {
            toFile = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(filename, true)), false);
            String readline;
            while ((readline = fromClient.readLine()) != null) {
                this.print(readline);
            }
        } catch (IOException e) {
            //здесь нужно передать клиенту сообщение об ошибкезаписи логов
        }
    }

    /**
     * Необходимо вызвать для закрытия записи в файл и закрытия сервера
     * @throws IOException
     */
    public void closeServer() throws IOException {
        toFile.close();
        serverSocket.close();
    }

    private void print(String readline) {
        count++;
        toFile.println(readline);
        if((count % 50) == 0)  toFile.flush();
    }

    public static void main(String[] args) throws IOException {
        FileServer fileServer = new FileServer(4747);
        fileServer.accept();
        fileServer.closeServer();
    }
}
