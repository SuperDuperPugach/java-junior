package com.acme.edu.serv;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    private ServerSocket serverSocket;
    private Socket client;
    private BufferedReader fromClient;
    private PrintWriter toFile;

    public FileServer(int port) throws IOException {

        serverSocket = new ServerSocket(port);
    }

    public void accept() throws IOException {
        client = serverSocket.accept();
        fromClient = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        toFile = new PrintWriter(
                    new BufferedWriter(
                        new FileWriter("log.txt")));
        String readline;
        while((readline = fromClient.readLine()) != null) {
            toFile.println(readline);
        }
    }
}
