package org.example.ontapkiemtracuoiki.serverDoubleUDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class server1 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(1234);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData()).trim();
            System.out.println("server 1 : " + receivedMessage);

            String[] values = receivedMessage.split(";");
            int a = Integer.parseInt(values[0]);
            int b = Integer.parseInt(values[1]);

            int sum = a + b;

            sendData = String.valueOf(sum).getBytes();

            InetAddress clientAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, port);
            socket.send(sendPacket);

            System.out.println("server 1 gui " + sum);
        }
    }
}
