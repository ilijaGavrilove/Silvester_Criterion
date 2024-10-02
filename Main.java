public class Main {
    public static void main(String[] args) {
        SquareMatrix matrix = new SquareMatrix(3);
        double[][] outputMatrix = matrix.create();

        for(int i = 0; i < matrix.getSize(); ++i){
            for(int j = 0; j < matrix.getSize(); ++j){
                System.out.println(outputMatrix[i][j]);
            }
        }

        System.out.println(SquareMatrix.defineForm(new double[][]{{1,2},{2,4}}));
    }
}
