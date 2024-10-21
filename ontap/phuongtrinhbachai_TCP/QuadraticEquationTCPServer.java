package org.example.ontapkiemtracuoiki.phuongtrinhbachai_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class QuadraticEquationTCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server TCP đang chạy...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.equalsIgnoreCase("stop")) {
                            break;
                        }

                        String[] coefficientsStr = inputLine.split(";");
                        if (coefficientsStr.length == 3) {
                            try {
                                double a = Double.parseDouble(coefficientsStr[0].trim());
                                double b = Double.parseDouble(coefficientsStr[1].trim());
                                double c = Double.parseDouble(coefficientsStr[2].trim());
                                String result = solveQuadraticEquation(a, b, c);
                                out.println(result);
                            } catch (NumberFormatException e) {
                                out.println("Vui lòng nhập đúng định dạng a; b; c với a, b, c là số thực.");
                            }
                        } else {
                            out.println("Vui lòng nhập đúng định dạng a; b; c.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String solveQuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            return "Hệ số a không được bằng 0. Đây không phải là phương trình bậc hai.";
        }

        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Phương trình có hai nghiệm phân biệt: x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Phương trình có nghiệm kép: x = " + x;
        } else {
            return "Phương trình vô nghiệm.";
        }
    }
}


