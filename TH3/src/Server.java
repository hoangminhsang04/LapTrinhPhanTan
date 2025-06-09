import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            int n = dis.readInt();
            int[] arr = new int[n];
            System.out.println("Received array:");
            for (int i = 0; i < n; i++) {
                arr[i] = dis.readInt();
                System.out.print(arr[i] + " ");
            }

            int sum = 0;
            for (int x : arr) sum += x;

            dos.writeInt(sum);
            System.out.println("\nResult sent back to client: " + sum);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
