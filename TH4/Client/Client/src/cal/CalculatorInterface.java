/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 *
 * @author Desktop
 */
public interface CalculatorInterface extends Remote{
	public int countPrimes(int[] arr) throws RemoteException;
	public List<Integer> getPrimes(int[] arr) throws RemoteException;
}
