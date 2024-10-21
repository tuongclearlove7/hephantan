package org.example.ontap.songuyento;

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6789)) {
            System.out.println("Server đang chạy...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Kết nối từ client.");
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());

                String clientInput;
                while ((clientInput = inFromClient.readLine()) != null) {
                    if (clientInput.equalsIgnoreCase("stop")) {
                        System.out.println("Client đã dừng.");
                        break;
                    }
                    String[] numbers = clientInput.split(";");
                    StringBuilder primeNumbers = new StringBuilder();

                    for (String numStr : numbers) {
                        int number = Integer.parseInt(numStr.trim());
                        if (isPrime(number)) {
                            primeNumbers.append(number).append(" ");
                        }
                    }
                    outToClient.writeBytes("Cac so nguyen to la: " + primeNumbers + "\n");
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
