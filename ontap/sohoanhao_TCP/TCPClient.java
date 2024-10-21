package org.example.ontapkiemtracuoiki.sohoanhao_TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String input;
            while (true) {
                System.out.print("Nhập dãy số nguyên (cách nhau bằng dấu ';') hoặc 'stop' để dừng: ");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    out.println(input);
                    break;
                }

                // Gửi dữ liệu tới Server
                out.println(input);

                // Nhận phản hồi từ Server
                String response = in.readLine();
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

