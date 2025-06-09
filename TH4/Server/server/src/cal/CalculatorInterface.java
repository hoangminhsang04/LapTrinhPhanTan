//giao diện từ xa
package cal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Desktop
 */
public interface CalculatorInterface extends Remote {
    public int[] generateNumbers(int n) throws RemoteException;
    public int sum(int[] arr) throws RemoteException;
}