import java.util.Arrays;

public class Function1 extends Thread{
    private Data data;

    public Function1(Data data, int newPriority) {
        this.data = data;
        setPriority(newPriority);
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public void run(){
        System.out.println("F1 started");
        //F1: E = A + C * (MA * ME) + B

        int[] A = data.getVectorFilledWithValue(1);
        int[] C = data.getVectorFilledWithValue(1);
        int[][] MA = data.getMatrixFilledWithValue(1);
        int[][] ME = data.getMatrixFilledWithValue(1);
        int[] B = data.getVectorFilledWithValue(1);

        try {
            int[] result = data.F1(A, C, MA, ME, B);
            sleep(1000);
            System.out.println("Result of F1:" + Arrays.toString(result) +
                    "\nF1 has finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
