package org.example.ontap.chatRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        try {
            chatInterface chat = (chatInterface) Naming.lookup("rmi://localhost/chatservice");
            Scanner sc = new Scanner(System.in);
            String message;

            while (true) {
                System.out.print("You :");
                message = sc.nextLine();
                chat.sendMessage(message);
                if (message.equalsIgnoreCase("quit")) break;

                System.out.println("server : " + chat.receiveMessage());
                if (chat.receiveMessage().equalsIgnoreCase("quit")) break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

