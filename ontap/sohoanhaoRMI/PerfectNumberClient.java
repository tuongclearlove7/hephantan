package org.example.ontap.sohoanhaoRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerfectNumberClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PerfectNumberService service = (PerfectNumberService) registry.lookup("PerfectNumberService");
            Scanner scanner = new Scanner(System.in);

            String input;
            while (true) {
                System.out.print("Nhập dãy số nguyên (cách nhau bằng dấu ';') hoặc 'stop' để dừng: ");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    break;
                }

                String[] numbersStr = input.split(";");
                List<Integer> numbers = new ArrayList<>();

                // Chuyển đổi chuỗi thành số nguyên
                for (String numStr : numbersStr) {
                    try {
                        numbers.add(Integer.parseInt(numStr.trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Dữ liệu không hợp lệ, vui lòng nhập lại.");
                        break;
                    }
                }

                // Gọi phương thức từ Server
                String result = service.checkPerfectNumbers(numbers);
                System.out.println("Server: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

