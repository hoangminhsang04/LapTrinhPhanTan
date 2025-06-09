/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.List;
/**
 *
 * @author Desktop
 */
public class Client {
    public static void main(String[] args) {
    	try {
            Registry registry = LocateRegistry.getRegistry("10.2.68.168", 1099);
            CalculatorInterface stub = (CalculatorInterface) registry.lookup("Calculator");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Nhập số lượng phần tử của mảng: ");
                int n = scanner.nextInt();
                int[] arr = new int[n];

                for (int i = 0; i < n; i++) {
                    System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
                    arr[i] = scanner.nextInt();
                }
                int primeCount = stub.countPrimes(arr);
                System.out.println("Số lượng số nguyên tố trong mảng: " + primeCount);
                List<Integer> primes = stub.getPrimes(arr);

                if (primes.isEmpty()) {
                    System.out.println("Không có số nguyên tố nào trong mảng.");
                } else {
                    System.out.println("Các số nguyên tố trong mảng là: ");
                    for (int prime : primes) {
                        System.out.print(prime + " ");
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
