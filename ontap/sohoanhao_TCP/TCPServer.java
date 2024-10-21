package org.example.ontapkiemtracuoiki.sohoanhao_TCP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server TCP đang chạy...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client đã kết nối.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("stop")) {
                    System.out.println("Client đã yêu cầu dừng.");
                    break;
                }

                // Xử lý chuỗi số nguyên từ Client
                String[] numbersStr = inputLine.split(";");
                List<Integer> numbers = new ArrayList<>();

                // Chuyển đổi chuỗi thành số nguyên
                for (String numStr : numbersStr) {
                    try {
                        numbers.add(Integer.parseInt(numStr.trim()));
                    } catch (NumberFormatException e) {
                        out.println("Dữ liệu không hợp lệ, vui lòng nhập lại.");
                        continue;
                    }
                }

                StringBuilder result = new StringBuilder("Số hoàn thiện: ");
                boolean foundPerfectNumber = false;

                // Kiểm tra số hoàn thiện
                for (int i = 0; i < numbers.size(); i++) {
                    int number = numbers.get(i);
                    if (isPerfectNumber(number)) {
                        result.append(number).append(" (vị trí: ").append(i + 1).append("); ");
                        foundPerfectNumber = true;
                    }
                }

                if (!foundPerfectNumber) {
                    result.append("Không có số hoàn thiện nào trong dãy.");
                }

                out.println(result.toString());
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPerfectNumber(int number) {
        if (number <= 1) return false;
        int sum = 0;

        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
}
