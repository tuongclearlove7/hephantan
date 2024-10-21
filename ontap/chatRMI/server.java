package org.example.ontap.chatRMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class server extends UnicastRemoteObject implements chatInterface {
    private String message="";

    public server() throws RemoteException{
        super();
    }
    @Override
    public void sendMessage(String message) throws RemoteException {
        this.message=message;
    }

    @Override
    public String receiveMessage() throws RemoteException {
        return message;
    }

    public static void main(String[] args){
        try{
            server se=new server();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("chatservice",se);
            System.out.println("Server is running ....");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

