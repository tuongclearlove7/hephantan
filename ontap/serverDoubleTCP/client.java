package org.example.ontapkiemtracuoiki.serverDoubleTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (
                Socket socket1 = new Socket("localhost", 12345);
                Socket socket2 = new Socket("localhost", 12346);
                PrintWriter output1 = new PrintWriter(socket1.getOutputStream(), true);
                PrintWriter output2 = new PrintWriter(socket2.getOutputStream(), true);
                BufferedReader input1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                BufferedReader input2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        ) {
            while (true) {
                System.out.print("Nhập a;b;c;d (hoặc 'quit' để dừng): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("quit")) {
                    output1.println("quit");
                    output2.println("quit");
                    break;
                }

                String[] parts = input.split(";");
                if (parts.length != 4) {
                    System.out.println("Vui lòng nhập đúng 4 giá trị a;b;c;d.");
                    continue;
                }

                // Gửi dữ liệu đến Server 1 để tính a + b
                output1.println(parts[0] + ";" + parts[1]);
                int sumAB = Integer.parseInt(input1.readLine());

                // Gửi dữ liệu đến Server 2 để tính 3c - 2d
                output2.println(parts[2] + ";" + parts[3]);
                int diffCD = Integer.parseInt(input2.readLine());

                // Tính giá trị S và hiển thị kết quả
                int S = 25 * sumAB - 6 * diffCD;
                System.out.println("Kết quả S = " + S);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
