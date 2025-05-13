package TH1;
import java.util.*;
import java.time.LocalTime;

public class TH1 {
    static int N; 
    static int K;   
    static int[] A;
    static int[] ketqua;

    static class TimMaxThread extends Thread {
        int id, start, end;

        public TimMaxThread(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {            
            int max = A[start];
            for (int i = start + 1; i < end; i++) {
                if (A[i] > max) {
                    max = A[i];
                }
            }
            ketqua[id] = max;
            System.out.println("T" + (id + 1) + ": " + max + " - " + LocalTime.now());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số phần tử N: ");
        N = sc.nextInt();
        System.out.print("Nhập số luồng K: ");
        K = sc.nextInt();

        A = new int[N];
        ketqua = new int[K];

        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            A[i] = rand.nextInt(100);  
        }
        A[N-1]=1000;
        

        System.out.println("Mảng A: " + Arrays.toString(A));

        TimMaxThread[] threads = new TimMaxThread[K];
        
        int phanDoan = N / K;
        for (int i = 0; i < K; i++) {
            int start = i * phanDoan;
            int end = (i == K - 1) ? N : start + phanDoan;
            threads[i] = new TimMaxThread(i, start, end);
            threads[i].start();
        }

        for (int i = 0; i < K; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int maxCuoiCung = ketqua[0];
        for (int i = 1; i < K; i++) {
            if (ketqua[i] > maxCuoiCung) {
                maxCuoiCung = ketqua[i];
            }
        }

        System.out.println(">> Max cuối cùng: " + maxCuoiCung);
        sc.close();
    }
}
