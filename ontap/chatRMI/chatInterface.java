package org.example.ontap.chatRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface chatInterface extends Remote {
    void sendMessage(String message) throws RemoteException;
    String receiveMessage() throws RemoteException;
}