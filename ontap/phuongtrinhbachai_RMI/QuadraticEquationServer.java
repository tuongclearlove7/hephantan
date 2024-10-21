package org.example.ontapkiemtracuoiki.phuongtrinhbachai_RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class QuadraticEquationServer implements QuadraticEquationService {

    @Override
    public String solveQuadraticEquation(double a, double b, double c) throws RemoteException {
        if (a == 0) {
            return "Hệ số a không được bằng 0. Đây không phải là phương trình bậc hai.";
        }

        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Phương trình có hai nghiệm phân biệt: x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Phương trình có nghiệm kép: x = " + x;
        } else {
            return "Phương trình vô nghiệm.";
        }
    }

    public static void main(String[] args) {
        try {
            QuadraticEquationServer server = new QuadraticEquationServer();
            QuadraticEquationService stub = (QuadraticEquationService) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("QuadraticEquationService", stub);
            System.out.println("Server RMI đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

