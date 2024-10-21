package org.example.ontap.chatTCP;

import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",1234);
        BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output=new PrintWriter(socket.getOutputStream(),true);

        BufferedReader console=new BufferedReader(new InputStreamReader(System.in));
        String clientMessage, serverMessage;

        while(true){
            System.out.println("You : ");
            clientMessage=console.readLine();
            output.println(clientMessage);
            if(clientMessage.equalsIgnoreCase("quit")){
                break;
            }
            serverMessage=input.readLine();
            System.out.print("Server: "+serverMessage);
            if(serverMessage.equalsIgnoreCase("quit")){
                break;
            }
        }
        socket.close();


    }
}
