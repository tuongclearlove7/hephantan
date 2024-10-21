package org.example.ontapkiemtracuoiki.tinhtongRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                SumService service = (SumService) registry.lookup("SumService");

                while (true) {
                    System.out.print("Nhập số nguyên n (hoặc 'quit' để dừng): ");
                    String input = scanner.nextLine();

                    if (input.equalsIgnoreCase("quit")) {
                        break;
                    }

                    int n = Integer.parseInt(input);
                    String result = service.calculateSum(n);
                    System.out.println(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            scanner.close();
        }

}
