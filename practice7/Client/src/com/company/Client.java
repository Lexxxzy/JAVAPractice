package com.company;
import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {

                clientSocket = new Socket("localhost", 8080); //запрашиваем у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //читаем соообщения с сервера
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));//писать на сервер
                int i = 0;

                System.out.println("Добро пожаловать! Введите пароль сервера: ");
                String pass = reader.readLine();
                out.write(pass+"\n");
                out.flush();
                System.out.println(in.readLine());

                while(true)
                {
                    String word = reader.readLine(); // ждём пока клиент что-нибудь не напишет в консоль
                    if(word.equals("-exit")) break; //если клиент набирает команду -exit
                    out.write(word + "\n"); // отправляем сообщение на сервер
                    out.flush(); //очищаем стрим
                    String serverWord = in.readLine(); // ждём, что скажет сервер
                    System.out.println(serverWord); // получив - выводим на экран
                }
            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}