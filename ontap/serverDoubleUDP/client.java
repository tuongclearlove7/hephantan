package org.example.ontap.serverDoubleUDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
public class client {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress server1Address = InetAddress.getByName("localhost");
        InetAddress server2Address = InetAddress.getByName("localhost");

        Scanner scanner = new Scanner(System.in);
        String inputLine;

        while (true) {
            System.out.println("Nhập các giá trị a;b;c;d (hoặc 'stop' để dừng): ");
            inputLine = scanner.nextLine();
            if (inputLine.equals("stop")) {
                break;
            }
            String[] values = inputLine.split(";");
            int a = Integer.parseInt(values[0]);
            int b = Integer.parseInt(values[1]);
            int c = Integer.parseInt(values[2]);
            int d = Integer.parseInt(values[3]);

            String message1 = a + ";" + b;
            byte[] sendData1 = message1.getBytes();
            DatagramPacket sendPacket1 = new DatagramPacket(sendData1, sendData1.length, server1Address, 1234);
            socket.send(sendPacket1);


            byte[] receiveData1 = new byte[1024];
            DatagramPacket receivePacket1 = new DatagramPacket(receiveData1, receiveData1.length);
            socket.receive(receivePacket1);
            String resultFromServer1 = new String(receivePacket1.getData()).trim();
            System.out.println("Kết quả nhận từ Server 1 (a + b): " + resultFromServer1);
            int sumAB = Integer.parseInt(resultFromServer1);


            String message2 = c + ";" + d;
            byte[] sendData2 = message2.getBytes();
            DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, server2Address, 2345);
            socket.send(sendPacket2);


            byte[] receiveData2 = new byte[1024];
            DatagramPacket receivePacket2 = new DatagramPacket(receiveData2, receiveData2.length);
            socket.receive(receivePacket2);
            String resultFromServer2 = new String(receivePacket2.getData()).trim();
            System.out.println("Kết quả nhận từ Server 2 (3c - 2d): " + resultFromServer2);
            int diffCD = Integer.parseInt(resultFromServer2);


            int S = 25 * sumAB - 6 * diffCD;
            System.out.println("Kết quả S: " + S);
        }
        socket.close();
    }
}
