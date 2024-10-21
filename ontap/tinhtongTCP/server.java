package org.example.ontapkiemtracuoiki.tinhtongTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("TCP Server đang chờ kết nối...");

            Socket socket = serverSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while (true) {
                message = input.readLine();
                if (message == null || message.equalsIgnoreCase("quit")) {
                    System.out.println("Client đã ngắt kết nối.");
                    break;
                }

                int n = Integer.parseInt(message);
                if ((n >= 50) && (n <= 100)) {
                    int sum = (n * (n + 1)) / 2;
                    output.println("Tổng S = 1 + 2 + ... + " + n + " = " + sum);
                } else {
                    output.println("Lỗi: n phải nằm trong khoảng (50, 100).");
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
