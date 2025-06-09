import java.util.*;
import java.util.concurrent.*;
import java.text.SimpleDateFormat;

public class TH2 {
    static final int SIZE = 200; 
    static final Queue<Integer> A = new LinkedList<>();
    static final Semaphore mutex = new Semaphore(1); 
    static final Semaphore empty = new Semaphore(0); 
    static final Semaphore full = new Semaphore(1); 
    static final Random rand = new Random();
    static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        int k = 2;
        int h = 2;

        for (int i = 1; i <= k; i++) {
            int id = i;
            new Thread(() -> producer(id)).start();
        }

        for (int i = 1; i <= h; i++) {
            int id = i;
            new Thread(() -> consumer(id)).start();
        }
    }
    
//sinh
    static void producer(int id) {
        while (true) {
            try {
                Thread.sleep(rand.nextInt(1000)); 
                int value = rand.nextInt(1000); 

                empty.acquire(); 
                
                mutex.acquire();

                A.add(value);
                String time = sdf.format(new Date());
                System.out.println("P" + id + ": " + value + " - " + time);

                mutex.release();
                
                full.release(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
//xử lý
    static void consumer(int id) {
        while (true) {
            try {
                Thread.sleep(rand.nextInt(1500)); 

                full.acquire(); 
             
                mutex.acquire(); 

                int value = A.poll(); 
                String time = sdf.format(new Date());
                int result = value * 2;
                System.out.println("C" + id + ": " + value + " - " + result + " - " + time);

                mutex.release();
                empty.release(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
                
            }
        }
    }
}