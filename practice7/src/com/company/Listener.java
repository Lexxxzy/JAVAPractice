package com.company;

import java.io.*;
import java.net.Socket;

import static com.company.Server.PASSWD;

class Listener extends Thread {


    private Socket socket; // сокет, через который сервер общается с клиентом
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет

    public Listener(Socket socket) throws IOException
    {
        this.socket = socket;
        // если потоку ввода/вывода приведут к генерированию исключения, оно проброситься дальше
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start(); // вызываем run()
    }
    @Override
    public void run()
    {
        String word;

        try {
            if(!in.readLine().equals(PASSWD))
            {
                //если пароль введенный клиентом неверный закрываем соединение
                out.write("Неверный пароль!" + "\n");
                out.flush();
                socket.close();
                in.close();
                out.close();
                throw new Exception(); //принудительно выкидываем Exception, чтобы попасть в блок catch
            }
            else
            {
                //Если все верно - впускаем клиента на сервер
                out.write("Добро пожаловать на сервер! Если хотите выйти - напишите -exit" +"\n");
                out.flush();
            }
            while (true)
            {
                if(this.socket.isClosed())
                {
                    break;
                }
                word = in.readLine();

                if(word.equals("-exit")) break;

                for (Listener client : Server.serverList)
                {
                    if(client.socket != this.socket) client.send(word); // отослать  сообщение клиента всем остальным
                }
            }
        } catch (Exception ignored)
        {
            System.out.println("Клиент "+socket.getInetAddress().getHostName() + " отключен!");
        }
    }

    private void send(String msg)
    {
        try
        {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }

}
