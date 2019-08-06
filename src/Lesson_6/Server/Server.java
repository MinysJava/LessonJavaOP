package Lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public void privateMsg(String msg, String nick) {       // метод приватного сообщения
            String[] tokens = msg.split(" ");        // разбиваем сообщения на слова
            for (ClientHandler o: clients) {
                if (o.nick.equals(tokens[1])){              // ищем сопадение по нику
                    String messege = "";
                   for (int i = 2; i < tokens.length; i++){     // склеиваем сообщение для отправки без служебной информации
                       messege = messege + tokens[i];
                   }
                    o.sendMsg(nick + ": " + messege);       // отправляем сообщение
                    break;
                }
            }
    }

    public boolean clientOnline(String nick){       // проверка онлайн пользователь или нет
        boolean result = false;
        for (ClientHandler o: clients) {
            if (o.nick.equals(nick)) {
                result = true;
                break;
            }
        }
        return result;
    }

}

