package org.example.ontapkiemtracuoiki.sohoanhaoRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PerfectNumberService extends Remote {
    String checkPerfectNumbers(List<Integer> numbers) throws RemoteException;
}

