import java.util.Arrays;

public class SquareMatrix {

    private int size = 0;
    private final double[][] matrix;


    public SquareMatrix getMinor(int row, int col) {
        double[][] resultMinor = new double[size - 1][size - 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != row && j != col) {
                    resultMinor[i < row ? i : i - 1][j < col ? j : j - 1] = matrix[i][j];
                }
            }
        }
        return new SquareMatrix(size - 1, resultMinor);
    }

    public double calcDeterminant(){
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
            det += Math.pow(-1, i) * matrix[0][i] * getMinor(0, i).calcDeterminant();
        }

        return det;
    }
    public SquareMatrix getMainMinor(int degree) {
        double[][] resultMatrix = new double[degree][degree];
        for ( int i = 0 ; i < degree ; i++ ) {
            for ( int j = 0 ; j < degree ; j++ ) {
                resultMatrix[i][j] = matrix[i][j];
            }
        }
        return new SquareMatrix(degree, resultMatrix);
    }

    public SquareMatrix(int size, double[][] matrix) {
        this.size = size;
        this.matrix = matrix;
    }

    public int getSize() {return size;}

    public double[][] getMatrix() {return matrix;}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for ( int i = 0 ; i < size ; i++) {
            builder.append(Arrays.toString(matrix[i]));
            builder.append("\n");
        }
        return builder.toString();
    }

    public FormTypes defineForm(){
        int[] mainMinorSigns = new int[size];
        int tmp = -1;
        for(int i = 0; i < size; ++i){
            if(getMainMinor(i+1).calcDeterminant() != 0) {
                mainMinorSigns[i] = getMainMinor(i + 1).calcDeterminant() > 0 ? 1 : -1;
            } else {
                return FormTypes.NONE;
            }
        }


        if(mainMinorSigns[0] == 1){
            for(int i = 0; i < mainMinorSigns.length; ++i){
                if(mainMinorSigns[i] == -1){return FormTypes.NONE;}
            }
            return FormTypes.POSITIVE;
        }

        else{
            for(int i = 0; i < mainMinorSigns.length; ++i){
                tmp *= (int) Math.pow(-1, i);
                if(tmp != mainMinorSigns[i]){return FormTypes.NONE;}
            }
            return FormTypes.NEGATIVE;
        }
    }
}


