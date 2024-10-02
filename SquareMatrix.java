import java.util.Scanner;

public class SquareMatrix {

    private static int size = 0;

    private static double calcDeterminant(double[][] matrix){
        double det = 0;
        // Базовый случай для 1x1 матрицы
        if (size == 1) {
            return matrix[0][0];
        }

        // Базовый случай для 2x2 матрицы
        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        for (int i = 0; i < size; i++) {
            det += Math.pow(-1, i) * matrix[0][i] * calcDeterminant(minor(matrix, 0, i));
        }

        return det;
    }

    private static double[][] minor(double[][] matrix, int row, int col) {
        double[][] minor = new double[size - 1][size - 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != row && j != col) {
                    minor[i < row ? i : i - 1][j < col ? j : j - 1] = matrix[i][j];
                }
            }
        }
        return minor;
    }

    public SquareMatrix(int size){this.size = size;}

    public int getSize() {return size;}

    public double[][] create(){
        double[][] result = new double[size][size];
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < size; ++i){
            for(int j = i; j < size; ++j){
                System.out.println("Введите элемент матрицы " + Integer.toString(i+1) + ' ' + Integer.toString(j+1) + ":");
                result[i][j] = scanner.nextDouble();
                result[j][i] = result[i][j];
            }
        }
        return result;
    }

    public static FormTypes defineForm(double[][] matrix){
        //for(int i = 0; i < size; ++i){

        //}
        return FormTypes.NONE;
    }
}
