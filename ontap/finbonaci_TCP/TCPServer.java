package org.example.ontap.finbonaci_TCP;

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


                String[] data = inputLine.split(";");
                if (data.length != 4) {
                    out.println("Dữ liệu không hợp lệ, vui lòng nhập lại.");
                    continue;
                }

                try {
                    int a = Integer.parseInt(data[0]);
                    int b = Integer.parseInt(data[1]);
                    int k = Integer.parseInt(data[2]);
                    int p = Integer.parseInt(data[3]);

                    if (k <= 2) {
                        out.println("k phải lớn hơn 2, vui lòng nhập lại.");
                        continue;
                    }

                    List<Integer> fibonacci = generateFibonacci(a, b, k);
                    System.out.println("Dãy Fibonacci: " + fibonacci);


                    int position = fibonacci.indexOf(p) + 1;
                    String result = (position > 0)
                            ? "Số " + p + " tồn tại ở vị trí thứ " + position
                            : "Số " + p + " không tồn tại trong dãy.";

                    out.println(result);
                } catch (NumberFormatException e) {
                    out.println("Vui lòng nhập các số nguyên hợp lệ.");
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> generateFibonacci(int a, int b, int k) {
        List<Integer> sequence = new ArrayList<>();
        sequence.add(a);
        sequence.add(b);
        for (int i = 2; i < k; i++) {
            sequence.add(sequence.get(i - 1) + sequence.get(i - 2));
        }
        return sequence;
    }
}
