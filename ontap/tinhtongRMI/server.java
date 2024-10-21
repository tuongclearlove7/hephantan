package org.example.ontapkiemtracuoiki.tinhtongRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class server  extends UnicastRemoteObject implements SumService{
    public server() throws RemoteException{
        super();
    }

    @Override
    public String calculateSum(int n) throws RemoteException {
        if (n > 50 && n < 100) {
            int sum = (n * (n + 1)) / 2;
            return "Tổng S = 1 + 2 + ... + " + n + " = " + sum;
        } else {
            return "Lỗi: n phải nằm trong khoảng (50, 100).";
        }
    }

    public static void main(String[] args) {
        try {
            server server = new server();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("SumService", server);
            System.out.println("RMI Server đã khởi động...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
