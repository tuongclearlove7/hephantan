package org.example.ontap.chatTCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(1234);

        System.out.println("server is running ..... ");

        Socket socket=serverSocket.accept();
        System.out.println(" Client connected ! ");

        BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output=new PrintWriter(socket.getOutputStream(),true);

        BufferedReader console=new BufferedReader(new InputStreamReader(System.in));
        String clientMessage, serverMessage;

        while(true){
            clientMessage=input.readLine();
            if(clientMessage.equalsIgnoreCase("quit")){
                break;
            }
            System.out.println("Client : "+clientMessage);
            System.out.print("You: ");
            serverMessage=console.readLine();
            output.println(serverMessage);
            if(serverMessage.equalsIgnoreCase("quit")){
                break;
            }
        }
        socket.close();
        serverSocket.close();

    }
}
