package org.example.ontapkiemtracuoiki.userPasswordRMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements AuthService {
    private static final String DEFAULT_USER = "CS420";
    private static final String DEFAULT_PASSWORD = "123";
    private int attemptCount = 0;

    public RMIServer() throws RemoteException {
        super();
    }

    @Override
    public String authenticate(String user, String password) throws RemoteException {
        if (attemptCount >= 3) {
            return "Bạn đã nhập sai 3 lần, bạn đã hết quyền truy cập vào hệ thống.";
        }

        if (DEFAULT_USER.equals(user) && DEFAULT_PASSWORD.equals(password)) {
            return "Bạn đã truy cập thành công";
        } else {
            attemptCount++;
            return "User hoặc Password của bạn không đúng, yêu cầu nhập lại";
        }
    }

    public static void main(String[] args) {
        try {
            // Khởi tạo registry trên cổng 1099
            LocateRegistry.createRegistry(1099);

            // Tạo đối tượng server và đăng ký nó với RMI
            AuthService server = new RMIServer();
            Naming.rebind("rmi://localhost:1099/AuthService", server);

            System.out.println("Server RMI đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

