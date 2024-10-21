package org.example.ontap.serverDoubleUDP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class server2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(2345);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData()).trim();
            System.out.println("server 2 : " + receivedMessage);

            String[] values = receivedMessage.split(";");
            int c = Integer.parseInt(values[0]);
            int d = Integer.parseInt(values[1]);

            int diff = 3 * c - 2 * d;
            sendData = String.valueOf(diff).getBytes();

            InetAddress clientAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, port);
            socket.send(sendPacket);

            System.out.println("server 2 gui " + diff);
        }
    }
}
