package org.example.ontapkiemtracuoiki.userPasswordRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthService extends Remote {
    String authenticate(String user, String password) throws RemoteException;
}
