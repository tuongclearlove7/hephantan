package org.example.ontapkiemtracuoiki.serverDoubleRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaceserverDouble extends Remote {
    int add(int a, int b) throws RemoteException;
    int computeDifference(int c, int d) throws RemoteException;
}
