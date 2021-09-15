import java.util.Arrays;

public class Function3 extends Thread{
    private Data data;

    public Function3(Data data, int newPriority) {
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
        System.out.println("F3 started");
        //F3: S = (O + P) * TRANS(MR * MT)

        int[] O = data.getVectorFilledWithValue(1);
        int[] P = data.getVectorFilledWithValue(1);
        int[][] MR = data.getMatrixFilledWithValue(1);
        int[][] MT = data.getMatrixFilledWithValue(1);


        try {
            int[] result = data.F3(O, P, MR, MT);
            sleep(1000);
            System.out.println("Result of F3:" + Arrays.toString(result) +
                    "\nF3 has finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
