package org.example.ontapkiemtracuoiki.phuongtrinhbachai_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class QuadraticEquationTCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            String input;
            while (true) {
                System.out.print("Nhập các giá trị a, b, c (cách nhau bằng dấu ';') hoặc 'stop' để dừng: ");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    out.println(input); // Gửi "stop" cho server
                    break;
                }

                out.println(input);  // Gửi dữ liệu tới server
                String response = in.readLine();  // Nhận phản hồi từ server
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


