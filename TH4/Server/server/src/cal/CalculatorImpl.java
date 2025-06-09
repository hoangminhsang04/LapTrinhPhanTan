//lớp triển khai
package cal;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Random;

public class CalculatorImpl extends UnicastRemoteObject implements CalculatorInterface {

    public CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public int[] generateNumbers(int n) throws RemoteException {
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100); 
        }
        return arr;
    }

    @Override
    public int sum(int[] arr) throws RemoteException {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }
}
