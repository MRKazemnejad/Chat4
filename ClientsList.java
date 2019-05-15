package server;

import java.net.Socket;

public class ClientsList {
    private  String userName;
    private  String ip;
    private  Socket connection=null;
    public static int counter=1;

    public ClientsList(String username, String ip,Socket con) {
        this.userName = username;
        this.ip = ip;
        this.connection=con;
    }

    public String getUserName() {
        return this.userName;
    }

    public  String getIp() {
        return this.ip;
    }

    public  Socket getConnection() {
        return this.connection;
    }

    public static int getCounter() {
        return counter;
    }

    public  void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static void setConnection(Socket connection) {
        connection = connection;
    }

    public static void setCounter(int counter) {
        ClientsList.counter = counter;
    }
}
