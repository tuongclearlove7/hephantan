package org.example.ontapkiemtracuoiki.finbonaci_UDP;
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

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String input = new String(receivePacket.getData(), 0, receivePacket.getLength());
                if (input.equalsIgnoreCase("stop")) {
                    System.out.println("Client đã yêu cầu dừng.");
                    break;
                }

                String[] data = input.split(";");
                if (data.length != 4) {
                    String errorMessage = "Dữ liệu không hợp lệ, vui lòng nhập lại.";
                    sendResponse(socket, receivePacket, errorMessage);
                    continue;
                }

                try {
                    int a = Integer.parseInt(data[0]);
                    int b = Integer.parseInt(data[1]);
                    int k = Integer.parseInt(data[2]);
                    int p = Integer.parseInt(data[3]);

                    if (k <= 2) {
                        String errorMessage = "k phải lớn hơn 2, vui lòng nhập lại.";
                        sendResponse(socket, receivePacket, errorMessage);
                        continue;
                    }

                    List<Integer> fibonacci = generateFibonacci(a, b, k);
                    System.out.println("Dãy Fibonacci: " + fibonacci);

                    int position = fibonacci.indexOf(p) + 1;
                    String result = (position > 0)
                            ? "Số " + p + " tồn tại ở vị trí thứ " + position
                            : "Số " + p + " không tồn tại trong dãy.";


                    sendResponse(socket, receivePacket, result);
                } catch (NumberFormatException e) {
                    String errorMessage = "Vui lòng nhập các số nguyên hợp lệ.";
                    sendResponse(socket, receivePacket, errorMessage);
                }
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


