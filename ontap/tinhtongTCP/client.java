package org.example.ontapkiemtracuoiki.tinhtongTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 12345)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while (true) {
                System.out.print("Nhập số nguyên n (hoặc 'quit' để dừng): ");
                message = scanner.nextLine();

                output.println(message);
                if (message.equalsIgnoreCase("quit")) {
                    break;
                }

                String response = input.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
