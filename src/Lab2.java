import java.util.Scanner;

/**
 * Parallel and distributed computing.
 * Labwork 2. Java.
 *
 * Andrii Sliusarenko
 * IO-91
 * 16.09.2021
 *
 * F1: E = A + C * (MA * ME) + B
 * F2: MG = TRANS(MK) * (MH * MF)
 * F3: S = (O + P) * TRANS(MR * MT)
 */

public class Lab2 extends Thread{
    public static void main(String[] args) {
        new Lab2().start();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the N - size of vectors and matrices: ");
        int N = scanner.nextInt();
        System.out.println("Program has started!\n");

        Data data = new Data(N);
        Function1 f1 = new Function1(data, Thread.MIN_PRIORITY);
        Function2 f2 = new Function2(data, Thread.NORM_PRIORITY);
        Function3 f3 = new Function3(data, Thread.MAX_PRIORITY);

        f1.start();
        f2.start();
        f3.start();

        try {
            f1.join();
            f2.join();
            f3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nProgram has finished!");
    }
}
