package org.example.ontapkiemtracuoiki.userPasswordRMI;

import java.rmi.Naming;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // Kết nối đến AuthService trên RMI Server
            AuthService authService = (AuthService) Naming.lookup("rmi://localhost:1099/AuthService");

            Scanner scanner = new Scanner(System.in);
            int attempts = 0;

            while (attempts < 3) {
                System.out.print("Nhập User và Password (dạng User;Password) hoặc 'stop' để dừng: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    System.out.println("Kết thúc kết nối.");
                    break;
                }

                String[] credentials = input.split(";");
                if (credentials.length == 2) {
                    String user = credentials[0];
                    String password = credentials[1];

                    String response = authService.authenticate(user, password);
                    System.out.println("Server: " + response);

                    if (response.equals("Bạn đã truy cập thành công") || response.equals("Bạn đã nhập sai 3 lần, bạn đã hết quyền truy cập vào hệ thống.")) {
                        break;
                    }
                } else {
                    System.out.println("Định dạng không hợp lệ, yêu cầu nhập lại.");
                }

                attempts++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}