package org.example.ontap.songuyento;

import org.example.ontap.chatRMI.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements PrimeChecker{


    protected RMIServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            RMIServer server=new RMIServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeChecker", server);
            System.out.println("RMI Server đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String checkPrimes(String input) throws RemoteException {
        StringBuilder primeNumbers = new StringBuilder();
        String[] numbers = input.split(";");

        for (String numStr : numbers) {
            int number = Integer.parseInt(numStr.trim());
            if (isPrime(number)) {
                primeNumbers.append(number).append(" ");
            }
        }

        return primeNumbers.toString().trim();
    }
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
