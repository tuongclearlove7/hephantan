package org.example.ontapkiemtracuoiki.finbonaci_RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfacefinbonaci extends Remote {
    int[] generateFibonacci(int a, int b, int k) throws RemoteException;
    int findPosition(int[] fibonacciArray, int p) throws RemoteException;
}
