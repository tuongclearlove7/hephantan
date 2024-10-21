package org.example.ontap.finbonaci_RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.example.ontapkiemtracuoiki.finbonaci_RMI.interfacefinbonaci;

public class server extends UnicastRemoteObject implements interfacefinbonaci {

    public server() throws RemoteException {
        super();
    }
    @Override
    public int[] generateFibonacci(int a, int b, int k) throws RemoteException {
        int[] fibonacci = new int[k];
        fibonacci[0] = a;
        fibonacci[1] = b;
        for (int i = 2; i < k; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    @Override
    public int findPosition(int[] fibonacciArray, int p) throws RemoteException {
        for (int i = 0; i < fibonacciArray.length; i++) {
            if (fibonacciArray[i] == p) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            server server = new server();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("FibonacciService", server);
            System.out.println("Fibonacci Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
