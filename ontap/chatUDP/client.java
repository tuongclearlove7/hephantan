package org.example.ontap.chatUDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket=new DatagramSocket();
        InetAddress serverAddress=InetAddress.getByName("localhost");

        byte[] sendData;
        byte[] receiveData=new byte[1024];
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("you : ");
            String message=sc.nextLine();
            sendData=message.getBytes();

            DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,serverAddress,2345);
            clientSocket.send(sendPacket);
            if(message.equalsIgnoreCase("quit")){
                break;
            }
             DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);
            clientSocket.receive(receivePacket);
            String reply=new String(receivePacket.getData(),0,receivePacket.getLength());
            System.out.println("Server: "+reply);
            if(reply.equalsIgnoreCase("quit")){
                break;
            }

        }
        clientSocket.close();
    }
}
