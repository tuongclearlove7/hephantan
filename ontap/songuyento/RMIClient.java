package org.example.ontap.songuyento;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrimeChecker primeChecker = (PrimeChecker) registry.lookup("PrimeChecker");

            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                System.out.print("Nhập các số cách nhau bởi dấu ';' (hoặc gõ 'stop' để dừng): ");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("stop")) {
                    break;
                }
                String response = primeChecker.checkPrimes(input);
                System.out.println("Server: Các số nguyên tố là: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
