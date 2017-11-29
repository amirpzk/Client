package Client;

import java.awt.List;
import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Created by amirpez on 11/11/17.
 */
public class MultithreadedClient extends Thread implements ImpMultithreadedClient {
    private Socket clientSocket;
    private BufferedReader dis;
    private PrintWriter dos;
    private Controller controller;
    private User user;
    private boolean shouldRun = true;
    private boolean loginAccessBoolean;
    private ArrayList<String> allUsers;
    private java.util.List<String> onlineUsers ;
    private ArrayList<String> userRecievedMesseges;
    private String pm = null ;
    private boolean isOnlineListUpdated = true ;

//    private ClientGUI gui;


    public void setPm(String pm) {
        this.pm = pm;
    }

    public MultithreadedClient(Socket clientSocket, Client client) {
        this.clientSocket = clientSocket;
        this.controller = new Controller(this);
        this.allUsers = new ArrayList<>();
        this.onlineUsers = new ArrayList<>();
        this.userRecievedMesseges = new ArrayList<>();
    }

    public String getPm() {
        return pm;
    }

    public java.util.List<String> getOnlineUsers() {
        return onlineUsers;
    }

    public boolean isLoginAccessBoolean() {
        return loginAccessBoolean;
    }

    public void setLoginAccessBoolean(boolean loginAccessBoolean) {
        this.loginAccessBoolean = loginAccessBoolean;
    }

    public boolean isOnlineListUpdated() {
        return isOnlineListUpdated;
    }

    public void setOnlineListUpdated(boolean onlineListUpdated) {
        isOnlineListUpdated = onlineListUpdated;
    }

    @Override
    public void run() {
        try {

            dos = new PrintWriter(clientSocket.getOutputStream());
            dis = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (shouldRun) {
                try {
//                    while (dis.available() == 0) {
//                        try {
//                            Thread.sleep(1);
//                        } catch (Exception e) {
//                            System.out.println("Problem1 , Class MultiThreadedCient , Method >> run() ");
//                        }
//                    }
                } catch (Exception e) {
                    System.out.println("Problem2 , Class MultiThreadedCient , Method >> run() ");
                }
                controller.identifier(getFromServer());
            }
            controller.identifier(getFromServer());

        } catch (Exception e) {
            System.out.println("Problem3 , Class MultiThreadedCient , Method >> run() ");
        }
    }

    public String getFromServer() {
        try {
            return dis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendStringToServer(String text) {
        try {
            dos.write(text);
            dos.flush();
        } catch (Exception e){
            System.out.println("DavoodMouseDare");
        }
    }


    public void setAllUsers(String str) {
        this.allUsers.add(str);
    }

    public void setOnlineUsers(String str){
        this.onlineUsers.add(str);
    }

    public void clearOnlineUsers(){
        this.onlineUsers.clear();
    }


    public java.util.List<String> getAllUsers() {
        return allUsers;
    }


    public void close() {
        try {
            dis.close();
            dos.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void makeUser(String username, String user) {
        User user1 = new User(username, user);
        this.user = user1;
    }

    public User getUser() {
        return user;
    }


    public void pmHandler(String str1 , String str2){
        this.pm = str1 + " : " + str2 ;
    }

    public String pmHandlerChat(String pm, String from) {
        String messege = from + " : " + pm;
        userRecievedMesseges.add(messege);
        return messege;
    }

}


