package server;

import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class ClientHandler extends Thread {
    protected Socket socket;
    private Scanner in;
    private Formatter out;
    private String clientname;
    private String clientStatus;


    public ClientHandler(Socket clientSocket) {
        this.socket = clientSocket;
    }


    public void run() {
        try {
            in = new Scanner(socket.getInputStream());
            out = new Formatter(socket.getOutputStream());
        } catch (IOException e) {

            return;
        }
        String line = "";
        while (true) {
            try {

                if (in.hasNextLine()) {
                    line = in.nextLine();
                } else {
                    line = "left the chat.";
                    //in.close();

                }
                   //getting new client name
                if (line.contains("user:")) {
                    Server.userName = line;
                    String test = line.replace("user:", "");
                    this.clientname = test;
                    // System.out.println(test);
                }
                //getting new ip
                if (line.contains("IP:")) {
                    Server.ip = line;
                    Server.connection = socket;
                    Server.list.add(new ClientsList(Server.userName, Server.ip, Server.connection));
                    Server.clientsName.put(Server.userName, Server.ip);
                    //sending connected clients name to all clients
                    if (!Server.list.isEmpty()) {
                        for (int i = 0; i < Server.list.size(); i++) {
                            String data = Server.list.get(i).getUserName();
                            out.format(data + "\n");
                            out.flush();
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                } else {
                    // boolean check = false;
                    for (int j = 0; j < Server.list.size(); j++) {
                        String name = Server.list.get(j).getUserName();
                        Socket d = Server.list.get(j).getConnection();
                        String newName = name.replace("user:", "");
                        newName = newName + ":";
                        if (line.contains(newName)) {
                            try {
                                out = new Formatter(d.getOutputStream());
                                out.format(line + "\n");
                                out.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }

                }
                //************************************************************


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "one client left group", "dd", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                //return;
            }
        }
    }
}