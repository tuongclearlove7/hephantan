package org.example.ontapkiemtracuoiki.userPasswordUDP;

import java.io.IOException;
import java.net.*;
import java.util.Objects;
import java.util.Scanner;

public class server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(1234);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            String defaultUser = "CS420";
            String defaultPassword = "123";

            int dem = 0;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String received = new String(receivePacket.getData(), 0, receivePacket.getLength());

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String[] credentials = received.split(";");
                if (credentials.length == 2) {
                    String user = credentials[0];
                    String password = credentials[1];

                    if (user.equals(defaultUser) && password.equals(defaultPassword)) {
                        String successMessage = "Bạn đã truy cập thành công";
                        sendData = successMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                        socket.send(sendPacket);
                    } else {
                        dem++;
                        if (dem >= 3) {
                            String blockMessage = "Bạn đã nhập sai 3 lần, bạn đã hết quyền truy cập vào hệ thống.";
                            sendData = blockMessage.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                            socket.send(sendPacket);
                            dem = 0;
                        } else {
                            String errorMessage = "User hoặc Password của bạn không đúng, yêu cầu nhập lại";
                            sendData = errorMessage.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                            socket.send(sendPacket);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
