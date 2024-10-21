package org.example.ontap.songuyento;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket udpSocket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                System.out.print("Nhập các số cách nhau bởi dấu ';' (hoặc gõ 'stop' để dừng): ");
                input = scanner.nextLine();
                byte[] sendBuffer = input.getBytes();
                InetAddress serverAddress = InetAddress.getByName("localhost");
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 9876);
                udpSocket.send(sendPacket);

                if (input.equalsIgnoreCase("stop")) {
                    break;
                }
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                udpSocket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
