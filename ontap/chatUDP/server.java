package org.example.ontap.chatUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class server {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket=new DatagramSocket(2345);
        byte[] receiveData=new byte[1024];
        byte[] sendData=new byte[1024];
        Scanner sc=new Scanner(System.in);

        while (true){
            DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivePacket);
            String message=new String(receivePacket.getData(),0,receivePacket.getLength());

            System.out.println("Client : "+message);
            if(message.equalsIgnoreCase("quit")){
                break;
            }

            InetAddress clientAddress=receivePacket.getAddress();
            int clientPort=receivePacket.getPort();

            System.out.println("You: ");
            String reply=sc.nextLine();
            sendData=reply.getBytes();

            DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,clientAddress,clientPort);
            serverSocket.send(sendPacket);
            if(reply.equalsIgnoreCase("quit")){
                break;
            }

        }
        serverSocket.close();

    }
}
