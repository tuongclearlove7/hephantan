package org.example.ontapkiemtracuoiki.phuongtrinhbachai_RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class QuadraticEquationClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            QuadraticEquationService service = (QuadraticEquationService) registry.lookup("QuadraticEquationService");
            Scanner scanner = new Scanner(System.in);

            String input;
            while (true) {
                System.out.print("Nhập các giá trị a, b, c (cách nhau bằng dấu ';') hoặc 'stop' để dừng: ");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    break;
                }

                String[] coefficientsStr = input.split(";");
                if (coefficientsStr.length != 3) {
                    System.out.println("Vui lòng nhập đúng định dạng a; b; c.");
                    continue;
                }

                try {
                    double a = Double.parseDouble(coefficientsStr[0].trim());
                    double b = Double.parseDouble(coefficientsStr[1].trim());
                    double c = Double.parseDouble(coefficientsStr[2].trim());

                    // Gọi phương thức giải phương trình từ Server
                    String result = service.solveQuadraticEquation(a, b, c);
                    System.out.println("Server: " + result);
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu không hợp lệ, vui lòng nhập lại.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

