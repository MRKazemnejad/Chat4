package server;

import server.ClientHandler;
import server.ClientsList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    public static List<ClientsList> list = new ArrayList<>();
    public static Map<String,String> clientsName=new HashMap<>();
    public static Socket socket = null;
    static final int PORT = 15000;
    public static String userName;
    public static String ip;
    public static Socket connection = null;


    public void ServerConn() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);

        } catch (IOException e) {

            e.printStackTrace();

        }
        while (true) {

            try {

                socket = serverSocket.accept();

            } catch (IOException e) {

                System.out.println("I/O error: " + e);
            }
            //************************************************

            //***********************************************

            // new thread for a client
            new ClientHandler(socket).start();
        }
    }
}