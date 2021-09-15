import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Data {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] inputVectorManually() {
        int[] vector = new int[n];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < vector.length; i++) {
            vector[i] = sc.nextInt();
        }
        return vector;
    }

    public int[] getVectorUsingRandom(int upperBound) {
        int[] vector = new int[n];
        Random random = new Random();
        for (int i = 0; i < vector.length; i++) {
            vector[i] = random.nextInt(upperBound);
        }
        return vector;
    }


    public int[] getVectorFilledWithValue(int value) {
        int[] vector = new int[n];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = value;
        }
        return vector;
    }

    public int[] getVectorFromFile(File file) throws IOException {
        var br = new BufferedReader(new FileReader(file));
        List<Integer> vector = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] arrFromLine = line.replaceAll(" ", "").split(",");
            for (String str : arrFromLine) {
                vector.add(Integer.parseInt(str));
            }
        }
        if (vector.size() != n) {
            throw new IllegalArgumentException("File must contain " + n + " values!");
        }
        return listToArray(vector);
    }

    public int[] listToArray(List<Integer> list) {
        int[] resultVector = new int[n];
        for (int i = 0; i < resultVector.length; i++) {
            resultVector[i] = list.get(i);
        }
        return resultVector;
    }

    public int[][] inputMatrixManually() {
        int[][] matrix = new int[n][n];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }

    public int[][] getMatrixUsingRandom() {
        int[][] matrix = new int[n][n];
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(60);
            }
        }
        return matrix;
    }

    public int[][] getMatrixFilledWithValue(int value) {
        int[][] matrix = new int[n][n];
        for (int[] ints : matrix) {
            Arrays.fill(ints, value);
        }
        return matrix;
    }

    public int[][] getMatrixFromFile(File file) throws IOException {
        var br = new BufferedReader(new FileReader(file));
        List<List<Integer>> listMatrix = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] arrFromLine = line.replaceAll(" ", "").split(",");
            List<Integer> listIn = new ArrayList<>();
            for (String str : arrFromLine) {
                listIn.add(Integer.parseInt(str));
            }
            listMatrix.add(listIn);
        }
        if (listMatrix.size() != n) {
            throw new IllegalArgumentException("File must contain " + n + "x" + n + " values!");
        }
        return listMatrixToMatrix(listMatrix);
    }

    public int[][] listMatrixToMatrix(List<List<Integer>> listMatrix) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = listMatrix.get(i).get(j);
            }
        }
        return matrix;
    }


    private int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        if (firstMatrix.length != n || secondMatrix.length != n) {//For matrix multiplication, the number of columns in the first matrix must be equal to the number of rows in the second matrix.
            return null;
        }
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return result;
    }

    private int[] vectorMultiplyByMatrix(int[] vector, int[][] matrix) {
        //For multiplication, the number of columns in the first matrix must be equal to the number of rows in the second matrix
        if (vector.length != n || matrix.length != n) {
            return null;

        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += vector[j] * matrix[i][j];
            }
        }
        return result;
    }

    private int[] addVectors(int[] firstVector, int[] secondVector) {
        //For addition, length of vectors must be equals
        if (firstVector.length != n || secondVector.length != n) {
            return null;
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = firstVector[i] + secondVector[i];
        }
        return result;
    }

    private int[][] transposeMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    public int[] F1(int[] a, int[] c, int[][] ma, int[][] me, int[] b) {
        return addVectors(Objects.requireNonNull(addVectors(a, vectorMultiplyByMatrix(c, multiplyMatrices(ma, me)))), b);
    }

    public int[][] F2(int[][] mk, int[][] mh, int[][] mf) {
        return multiplyMatrices(transposeMatrix(mk), multiplyMatrices(mh, mf));
    }

    public int[] F3(int[] o, int[] p, int[][] mr, int[][] mt) {
        return vectorMultiplyByMatrix(
                Objects.requireNonNull(addVectors(o, p)),
                transposeMatrix(Objects.requireNonNull(multiplyMatrices(mr, mt))));
    }
}

