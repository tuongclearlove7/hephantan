package org.example.ontapkiemtracuoiki.userPasswordTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private static final String DEFAULT_USER = "CS420";
    private static final String DEFAULT_PASSWORD = "123";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is running...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    int attempts = 0;
                    boolean authenticated = false;

                    while (attempts < 3) {
                        String credentials = in.readLine();

                        if (credentials.equalsIgnoreCase("stop")) {
                            System.out.println("Client đã ngắt kết nối.");
                            break;
                        }

                        String[] parts = credentials.split(";");
                        if (parts.length == 2) {
                            String user = parts[0];
                            String password = parts[1];

                            if (user.equals(DEFAULT_USER) && password.equals(DEFAULT_PASSWORD)) {
                                out.println("Bạn đã truy cập thành công");
                                authenticated = true;
                                break;
                            } else {
                                attempts++;
                                out.println("User hoặc Password của bạn không đúng, yêu cầu nhập lại");
                            }
                        } else {
                            out.println("Định dạng không hợp lệ, yêu cầu nhập lại.");
                        }
                    }

                    if (!authenticated && attempts >= 3) {
                        out.println("Bạn đã nhập sai 3 lần, bạn đã hết quyền truy cập vào hệ thống.");
                        System.out.println("Client bị ngắt do nhập sai 3 lần.");
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
