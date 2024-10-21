package org.example.ontapkiemtracuoiki.userPasswordUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            Scanner scanner = new Scanner(System.in);
            int dem = 0;

            while (true) {
                System.out.print("Nhập User và Password (dạng User;Password) hoặc 'stop' để dừng: ");
                String input = scanner.nextLine();

                sendData = input.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);
                socket.send(sendPacket);

                if (input.equalsIgnoreCase("stop")) {
                    break;
                }

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + response);

                if (response.equals("Bạn đã truy cập thành công") || response.equals("Bạn đã nhập sai 3 lần, bạn đã hết quyền truy cập vào hệ thống.")) {
                    break;
                }

                dem++;
                if (dem >= 3) {
                    System.out.println("Bạn đã nhập sai 3 lần, kết thúc phiên làm việc.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
