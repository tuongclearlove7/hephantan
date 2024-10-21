package org.example.ontapkiemtracuoiki.phuongtrinhbachai_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class QuadraticEquationUDPServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(1234)) {
            System.out.println("Server UDP đang chạy...");

            byte[] receiveData = new byte[1024];
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String received = new String(receivePacket.getData(), 0, receivePacket.getLength());

                if (received.equalsIgnoreCase("stop")) {
                    System.out.println("Server đã nhận lệnh dừng.");
                    break;
                }

                String[] coefficientsStr = received.split(";");
                String response;

                if (coefficientsStr.length == 3) {
                    try {
                        double a = Double.parseDouble(coefficientsStr[0].trim());
                        double b = Double.parseDouble(coefficientsStr[1].trim());
                        double c = Double.parseDouble(coefficientsStr[2].trim());
                        response = solveQuadraticEquation(a, b, c);
                    } catch (NumberFormatException e) {
                        response = "Vui lòng nhập đúng định dạng a; b; c với a, b, c là số thực.";
                    }
                } else {
                    response = "Vui lòng nhập đúng định dạng a; b; c.";
                }

                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                socket.send(sendPacket);
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

