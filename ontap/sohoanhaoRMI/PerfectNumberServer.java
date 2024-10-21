package org.example.ontapkiemtracuoiki.sohoanhaoRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PerfectNumberServer implements PerfectNumberService {

    @Override
    public String checkPerfectNumbers(List<Integer> numbers) throws RemoteException {
        StringBuilder result = new StringBuilder("Số hoàn thiện: ");
        boolean foundPerfectNumber = false;

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

        return result.toString();
    }

    private boolean isPerfectNumber(int number) {
        if (number <= 1) return false;
        int sum = 0;

        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }

    public static void main(String[] args) {
        try {
            PerfectNumberServer server = new PerfectNumberServer();
            PerfectNumberService stub = (PerfectNumberService) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PerfectNumberService", stub);
            System.out.println("Server RMI đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

