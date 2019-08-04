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
        int i = -1;
        boolean serverLife = true;

        try {
            server = new ServerSocket(55555);
            System.out.println("Сервер запущен!");
            server.setSoTimeout(300);

            while (serverLife){
                try {
                    socket = server.accept();
                    clients.add(new ClientHandler(this, socket));
                }catch (SocketTimeoutException e){
                    for (ClientHandler c: clients) {
                        if (c.socket.isClosed()){
                         i = clients.indexOf(c);
                        }
                    }
                    if (i >= 0) {
                        clients.remove(i);
                        i = -1;
                        serverLife = !clients.isEmpty();
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
