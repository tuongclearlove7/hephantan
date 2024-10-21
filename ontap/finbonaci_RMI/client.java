package org.example.ontap.finbonaci_RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            interfacefinbonaci service = (interfacefinbonaci) registry.lookup("FibonacciService");

            while (true) {
                System.out.print("Nhập 1;2;3;4; (hoặc gõ 'stop' để dừng): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    break;
                }

                String[] values = input.split(";");
                int a = Integer.parseInt(values[0]);
                int b = Integer.parseInt(values[1]);
                int k = Integer.parseInt(values[2]);
                int p = Integer.parseInt(values[3]);

                int[] fibonacciArray = service.generateFibonacci(a, b, k);
                int position = service.findPosition(fibonacciArray, p);

                if (position != -1) {
                    System.out.println("Số " + p + " tồn tại trong dãy ở vị trí thứ " + position);
                } else {
                    System.out.println("Số " + p + " không tồn tại trong dãy Fibonacci.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
