package com.acme.edu.serv;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервер, записывающий принимаемые сообщения в лог файл
 */
public class FileServer {
    private static final String END_LOG = "END_LOG";
    private static final String ERROR_LOG = "Can't write logs to file on server!";
    private static final String FILE_NAME = "log.dat";
    private static final int BUFFER_SIZE = 50;

    private ServerSocket serverSocket;
    private Socket client;
    private BufferedReader fromClient;
    private List<String> buffer; //буффер получаемых сообщений

   // private int count; //счетчик вызовов метода принт
    /**
     * Конструктор, принимающий в качестве параметра номер порта. Сервер помещается в
     * режим ожидания соединения с клиентом
     * @param port - номер порта
     * @throws IOException - бросает, если невозможно создать сервер или установить соединение
     * с клиентом
     */
    public FileServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        client = serverSocket.accept();
        buffer = new ArrayList<>();
       // count = 1;
    }

    /**
     * Метод, в которм сервер устанавливает потоки соединения с клиентом и производится
     * запись информации от клинета в файл при накоплении достаточного количества сообщений.
     * При возниконовении ошибки записи в файл
     * клиенту передается оповещение
     * @throws IOException - бросается, если невозможно установить входной или выходной поток с клиентом
     */
    public void writeToFile() throws IOException {
        fromClient = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
            //ловим ошибку записи логов
        try (BufferedWriter toFile = new BufferedWriter(
                new FileWriter(FILE_NAME, true))) {
            String readline;
            //бесконечный цикл, выход из которого осуществляется приемом от клиента
            // сообщения о выходе
            while (true) {
                readline = fromClient.readLine();
                if (readline.equals(END_LOG)) {
                    writeBufferToFile(toFile);
                    break;
                }
                buffer.add(readline);
                if(buffer.size() == BUFFER_SIZE) {
                    writeBufferToFile(toFile);
                }
            }
        } catch (IOException e) {
            //здесь нужно передать клиенту сообщение об ошибке записи логов в файл
            //пробуем открыть выходной поток
            try (BufferedWriter toClient = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream()))) {
                toClient.write(ERROR_LOG + System.lineSeparator());
            }
        }
    }

    private void writeBufferToFile(BufferedWriter toFile) throws IOException {
        for(String s : buffer) {
            toFile.write(s + System.lineSeparator());
        }
        toFile.flush();
        buffer.clear();
    }


    /**
     * Необходимо вызвать для закрытия записи в файл и закрытия сервера
     * @throws IOException
     */
    public void closeServer() throws IOException {
        if(client != null) client.close(); //так же закрывает входной и выходной потоки
        if(serverSocket != null) serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        FileServer fileServer = new FileServer(4747);
        fileServer.writeToFile();
        fileServer.closeServer();
    }
}
