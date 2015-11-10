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
    private static final int BUFFER_SIZE = 50;

    private Socket client;
    private BufferedReader fromClient;
    private static BufferedWriter toFile;
    private List<String> buffer; //буффер получаемых сообщений

    /**
     * Конструктор, принимающий в качестве параметра буферный поток записи в файл и
     * экземпляр соединения с клиентом
     * @param client - соединение с клиентом
     * @param bw - буферный поток записи в файл
     */
    public FileServerPrinter(Socket client, BufferedWriter bw) {
        this.client = client;
        this.toFile = bw;
        buffer = new LinkedList<>();
    }

    /**
     * Метод, в которм устанавливаются потоки соединения с клиентом и производится
     * запись информации от клинета в файл при накоплении достаточного количества сообщений.
     * По окончании записи, закрывается соединение клиента
     * При возниконовении ошибки записи в файл
     * клиенту передается оповещение
     *
     * Данный метод переопределяет метод интерфейса Runnable
     */
    @Override
    public void run() {
        try {
            writeToFile();
            close();
        } catch (LogServerException e) {
            //TODO нельзя здесь пробросить ошибку дальше
        }
    }


    private void writeToFile() throws LogServerException {
        try {
            fromClient = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            throw new LogServerException("Can't connect to client");
        }
        //ловим ошибку записи логов
        try {
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

            //пробуем открыть выходной поток для оповещение клиента об ошибке
            try (BufferedWriter toClient = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream()))) {
                toClient.write(WRITE_ERROR + System.lineSeparator());
            } catch (IOException e1) {
                throw new LogServerException("Can't connect to client");
            }
        }
    }

    private void close() throws LogServerException {
        try {
            if(client != null) client.close();
        } catch (IOException e) {
            throw new LogServerException("Can't close client connection");
        }
    }

    //сортирует буффер по ворнингам и пишет его в файл.
    private void writeBufferToFile(BufferedWriter toFile) {
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
        synchronized (toFile){
            for (String s : buffer) {
                try {
                    toFile.write(s + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("dima6");
                    e.printStackTrace();
                }
            }
            try {
                toFile.flush();
            } catch (IOException e) {
                System.out.println("dima7");
                e.printStackTrace();
            }
        }
        buffer.clear();
    }
}
