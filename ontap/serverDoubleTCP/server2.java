package org.example.ontap.serverDoubleTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server2 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12346)) {
            System.out.println("Server 2 đang chờ kết nối...");

            Socket socket = serverSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                if (message.equalsIgnoreCase("quit")) {
                    System.out.println("Client đã ngắt kết nối.");
                    break;
                }

                String[] parts = message.split(";");
                int c = Integer.parseInt(parts[0]);
                int d = Integer.parseInt(parts[1]);
                int result = 3 * c - 2 * d;

                output.println(result);  // Gửi kết quả về Client
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
