package Lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        int i = -1;                     //переменная для хранения индекса элемента который необходимо удалить из Vectorа
        boolean serverLife = true;      // флаг определяющий жизнь сервера

        try {
            server = new ServerSocket(55555);
            System.out.println("Сервер запущен!");
            server.setSoTimeout(300);           // задаем таймаут на чтение

            while (serverLife){
                try {
                    socket = server.accept();
                    clients.add(new ClientHandler(this, socket));
                }catch (SocketTimeoutException e){          // когда чтение прерывается, проверяем список клиентов
                    for (ClientHandler c: clients) {
                        if (c.socket.isClosed()){           //проверяем для каждого клиента в списке закрыт его сокет или нет
                         i = clients.indexOf(c);            // если закрыт определяем его индекс
                        }
                    }
                    if (i >= 0) {
                        clients.remove(i);              // удаляем клиент из списка с определенным индексом
                        i = -1;
                        serverLife = !clients.isEmpty();    // если после первого подключения клиента наш список опустел(стал равен 0) закрываем сокет и сервер
                    }
                }
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
        }
    }

    public void broadCastMsg(String msg){
        for (ClientHandler c: clients) {
            c.sendMsg(msg);
        }
    }
}
