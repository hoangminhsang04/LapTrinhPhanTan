import java.io.*;
import java.net.*;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("172.20.10.3", 12345);

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            int n = 100;
            int[] arr = new int[n];
            Random rand = new Random();
            System.out.println("Generated array:");
            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt(100); 
                System.out.print(arr[i] + " ");
            }

            dos.writeInt(n);
            for (int x : arr) {
                dos.writeInt(x);
            }

            int result = dis.readInt();
            System.out.println("\nResult from server: " + result);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
