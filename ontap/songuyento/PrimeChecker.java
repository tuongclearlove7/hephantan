package org.example.ontap.songuyento;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrimeChecker extends Remote {
    String checkPrimes(String input) throws RemoteException;
}

