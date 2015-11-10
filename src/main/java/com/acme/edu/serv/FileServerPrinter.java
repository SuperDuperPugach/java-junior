package com.acme.edu.serv;


import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class FileServerPrinter implements Runnable {
    private static final String END_LOG = "END_LOG";
    private static final String WRITE_ERROR = "Can't write logs to file on server!";
    private static final String FILE_NAME = "log.dat";
    private static final int BUFFER_SIZE = 50;

    private Socket client;
    private BufferedReader fromClient;
    private List<String> buffer; //буффер получаемых сообщений

    public FileServerPrinter(Socket client) {
        this.client = client;
        buffer = new LinkedList<>();
    }

    /**
     * Метод, в которм устанавливаются потоки соединения с клиентом и производится
     * запись информации от клинета в файл при накоплении достаточного количества сообщений.
     * При возниконовении ошибки записи в файл
     * клиенту передается оповещение
     * @throws IOException - бросается, если невозможно установить входной или выходной поток с клиентом
     */
    private void writeToFile() throws IOException {
        fromClient = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        //внутренний класс, необходимый для сортировки List

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
                toClient.write(WRITE_ERROR + System.lineSeparator());
            }
        }
    }

    private void close() throws IOException {
        if(client != null) client.close();
    }

    @Override
    public void run() {
        try {
            writeToFile();
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeBufferToFile(BufferedWriter toFile) throws IOException {
        Collections.sort(buffer, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                boolean contains1 = o1.contains("ERROR");
                boolean contains2 = o2.contains("ERROR");
                if (contains1 && !contains2) return -1;
                if (!contains1 && contains2) return 1;
                return 0;
            }
        });
        for(String s : buffer) {
            toFile.write(s + System.lineSeparator());
        }
        toFile.flush();
        buffer.clear();
    }
}
