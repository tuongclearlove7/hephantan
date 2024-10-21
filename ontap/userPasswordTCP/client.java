package org.example.ontapkiemtracuoiki.userPasswordTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            int attempts = 0;

            while (true) {
                System.out.print("Nhập User và Password (dạng User;Password) hoặc 'stop' để dừng: ");
                String input = scanner.nextLine();
                out.println(input);

                if (input.equalsIgnoreCase("stop")) {
                    break;
                }
                String response = in.readLine();
                System.out.println("Server: " + response);

                if (response.equals("Bạn đã truy cập thành công") || response.equals("Bạn đã nhập sai 3 lần, bạn đã hết quyền truy cập vào hệ thống.")) {
                    break;
                }
                attempts++;
                if (attempts >= 3) {
                    System.out.println("Bạn đã nhập sai 3 lần, kết thúc phiên làm việc.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
