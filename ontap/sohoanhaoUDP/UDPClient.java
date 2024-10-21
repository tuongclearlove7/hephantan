package org.example.ontapkiemtracuoiki.sohoanhaoUDP;

import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];
            Scanner scanner = new Scanner(System.in);

            String input;
            while (true) {
                System.out.print("Nhập dãy số nguyên (cách nhau bằng dấu ';') hoặc 'stop' để dừng: ");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    sendBuffer = input.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 1234);
                    socket.send(sendPacket);
                    break;
                }

                // Gửi dữ liệu tới Server
                sendBuffer = input.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 1234);
                socket.send(sendPacket);

                // Nhận phản hồi từ Server
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

