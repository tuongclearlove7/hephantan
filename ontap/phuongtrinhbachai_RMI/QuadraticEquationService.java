package org.example.ontapkiemtracuoiki.phuongtrinhbachai_RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuadraticEquationService extends Remote {
    String solveQuadraticEquation(double a, double b, double c) throws RemoteException;
}

