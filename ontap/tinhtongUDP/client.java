package org.example.ontapkiemtracuoiki.tinhtongUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");

            while (true) {
                System.out.print("Nhập số nguyên n (hoặc 'quit' để dừng): ");
                String message = scanner.nextLine();
                byte[] sendBuffer = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 12345);
                clientSocket.send(sendPacket);

                if (message.equalsIgnoreCase("quit")) {
                    break;
                }

                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
