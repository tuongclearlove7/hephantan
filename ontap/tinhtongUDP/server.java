package org.example.ontapkiemtracuoiki.tinhtongUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class server {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(12345)) {
            System.out.println("UDP Server đang chờ dữ liệu...");

            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                if (message.equalsIgnoreCase("quit")) {
                    System.out.println("Client đã ngắt kết nối.");
                    break;
                }

                int n = Integer.parseInt(message);
                String response;
                if (n >= 50 && n <= 100) {
                    int sum = (n * (n + 1)) / 2;
                    response = "Tổng S = 1 + 2 + ... + " + n + " = " + sum;
                } else {
                    response = "Lỗi: n phải nằm trong khoảng (50, 100).";
                }

                sendBuffer = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
