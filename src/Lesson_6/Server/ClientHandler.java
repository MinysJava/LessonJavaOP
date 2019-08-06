package Lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Server server;
    public String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (newNick != null) {
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(ClientHandler.this);
                                    break;
                                } else {
                                    sendMsg("Неверный логин/пароль!");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            System.out.println("Client " + str);
                            if (str.startsWith("/w")){              // провряем начало строки на ключ /w
                                String[] part = str.split(" ");
                                if (server.clientOnline(part[1])){        // проверяем онлайн клиент или нет
                                    server.privateMsg(str, nick);           // если да то отправляем приватное сообщение
                                } else {
                                    sendMsg("Пользователь не в сети.");
                                }
                            }else {
                                server.broadcastMsg(nick + ": " + str);   // если нет ключа /w то отправляем сообщение всем
                            }
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                        }
                    } catch (IOException e) {
                            e.printStackTrace();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void privateMsg(String str) {
//        String[] tokens = str.split(" ");
//
//    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
