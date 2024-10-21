package org.example.ontap.songuyento;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket udpSocket = new DatagramSocket(9876)) {
            System.out.println("UDP Server đang chạy...");

            while (true) {
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                udpSocket.receive(receivePacket);

                String clientInput = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Nhận từ client: " + clientInput);
                if (clientInput.equalsIgnoreCase("stop")) {
                    System.out.println("Client đã dừng.");
                    break;
                }

                String[] numbers = clientInput.split(";");
                StringBuilder primeNumbers = new StringBuilder();

                for (String numStr : numbers) {
                    int number = Integer.parseInt(numStr.trim());
                    if (isPrime(number)) {
                        primeNumbers.append(number).append(" ");
                    }
                }

                String response = "Các số nguyên tố là: " + primeNumbers.toString().trim();
                byte[] sendBuffer = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                udpSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

