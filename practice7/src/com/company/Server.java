package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static final int PORT = 8080;
    protected static final String PASSWD = "cats";
    public static LinkedList<Listener> serverList = new LinkedList<>(); // список всех нитей

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен! Ждем подключений...");
            while (true)
            {
                // Блокируется до возникновения нового соединения:
                Socket socket = server.accept();
                try
                {
                    System.out.println("Новое подключение! "+ socket.getInetAddress().getHostName());
                    serverList.add(new Listener(socket)); // добавить новое соединенние в список
                } catch (IOException e)
                {
                    // Если завершится неудачей, закрывается сокет
                    socket.close();
                }
            }
        }

    }

}