package org.example.ontapkiemtracuoiki.tinhtongRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SumService extends Remote {
    String calculateSum(int n) throws RemoteException;
}
