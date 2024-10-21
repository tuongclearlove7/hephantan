package org.example.ontapkiemtracuoiki.finbonaci_UDP;

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
                System.out.print("Nhập a;b;k;p hoặc 'stop' để dừng: ");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    sendBuffer = input.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 1234);
                    socket.send(sendPacket);
                    break;
                }


                sendBuffer = input.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 1234);
                socket.send(sendPacket);


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


