package org.example.ontapkiemtracuoiki.sohoanhaoUDP;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(1234)) {
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            System.out.println("Server UDP đang chạy...");

            while (true) {
                // Nhận dữ liệu từ Client
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String input = new String(receivePacket.getData(), 0, receivePacket.getLength());
                if (input.equalsIgnoreCase("stop")) {
                    System.out.println("Client đã yêu cầu dừng.");
                    break;
                }

                String[] numbersStr = input.split(";");
                List<Integer> numbers = new ArrayList<>();
                StringBuilder result = new StringBuilder("Số hoàn thiện: ");
                boolean foundPerfectNumber = false;

                // Chuyển đổi chuỗi thành số nguyên
                for (String numStr : numbersStr) {
                    try {
                        numbers.add(Integer.parseInt(numStr.trim()));
                    } catch (NumberFormatException e) {
                        sendResponse(socket, receivePacket, "Dữ liệu không hợp lệ, vui lòng nhập lại.");
                        continue;
                    }
                }

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

                // Gửi kết quả về Client
                sendResponse(socket, receivePacket, result.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendResponse(DatagramSocket socket, DatagramPacket receivePacket, String message) throws Exception {
        byte[] sendBuffer = message.getBytes();
        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
        socket.send(sendPacket);
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

