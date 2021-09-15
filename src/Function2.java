import java.util.Arrays;

public class Function2 extends Thread{

    private Data data;

    public Function2(Data data, int newPriority) {
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
        System.out.println("F2 started");
        //F2: MG = TRANS(MK) * (MH * MF)

        int[][] MK = data.getMatrixFilledWithValue(1);
        int[][] MH = data.getMatrixFilledWithValue(1);
        int[][] MF = data.getMatrixFilledWithValue(1);


        try {
            int[][] result = data.F2(MK, MH, MF);
            sleep(1000);
            System.out.println("Result of F2: ");
            for (int[] row : result) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("F2 has finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
