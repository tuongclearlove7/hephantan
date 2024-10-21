package org.example.ontap.songuyento;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 6789)) {
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            String input;

            while (true) {
                System.out.print("Nhập các số cách nhau bởi dấu ';' (hoặc gõ 'stop' để dừng): ");
                input = scanner.nextLine();
                outToServer.writeBytes(input + "\n");

                if (input.equalsIgnoreCase("stop")) {
                    break;
                }
                String response = inFromServer.readLine();
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
