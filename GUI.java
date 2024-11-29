import javax.swing.*; // подключаем все средства java Swing
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI implements ActionListener {
    private static int userMatrixSizeInt;
    private static double[][] userMatrix;
    public static void start(){
            JFrame matrixSizeFrame = new JFrame("Silvester Criterion Auto");
            matrixSizeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            matrixSizeFrame.setSize(300, 300); // размеры окна
            matrixSizeFrame.setLocationRelativeTo(null); // окно - в центре экрана

            JTextField matrixSizeInput = new JTextField();
            JTextArea matrixSizeFrameText = new JTextArea("Введите размер квадратной матрицы:");
            matrixSizeFrameText.setEditable(false);
            JButton matrixSizeSendButton = new JButton("Подтвердить");
            matrixSizeSendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String userMatrixSizeText = matrixSizeInput.getText();
                        userMatrixSizeInt = Integer.parseInt(userMatrixSizeText);
                        matrixSizeFrame.dispose();
                        setMatrix();
                    }
                    catch (NumberFormatException err){
                        JOptionPane.showMessageDialog(matrixSizeFrame, "Некорректный ввод");
                    }
                }
            });

            matrixSizeFrame.getContentPane().add(BorderLayout.CENTER, matrixSizeInput);
            matrixSizeFrame.getContentPane().add(BorderLayout.NORTH, matrixSizeFrameText);
            matrixSizeFrame.getContentPane().add(BorderLayout.SOUTH, matrixSizeSendButton);


            matrixSizeFrame.setVisible(true); // Делаем окно видимым
        }

    private static void setMatrix(){
        JFrame setMatrixFrame = new JFrame("Silvester Criterion Auto");
        setMatrixFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMatrixFrame.setSize(700, 300); // размеры окна
        setMatrixFrame.setLocationRelativeTo(null); // окно - в центре экрана

        JTextArea setMatrixText = new JTextArea("Введите квадратную матрицу - каждый новый элемент через пробел," +
                "а каждая новая строка на следующей строчке:");
        setMatrixText.setEditable(false);

        JTextArea setMatrixInput = new JTextArea();

        JButton matrixSendButton = new JButton("Подтвердить");
        matrixSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String[] matrixInputRows = setMatrixInput.getText().split("\n");
                    double[][] inputMatrix = new double[userMatrixSizeInt][userMatrixSizeInt];

                    for(int i = 0; i < userMatrixSizeInt; ++i) {
                        String[] elements = matrixInputRows[i].split(" ");
                        for (int j = 0; j < matrixInputRows.length; ++j) {
                            inputMatrix[i][j] = Double.parseDouble(elements[j]);
                        }
                    }
                    userMatrix = inputMatrix;
                    setMatrixFrame.dispose();
                    result();
                }
                catch(NumberFormatException err1){
                    JOptionPane.showMessageDialog(setMatrixFrame, "Некорректный ввод");
                }
                catch(ArrayIndexOutOfBoundsException err2){
                    JOptionPane.showMessageDialog(setMatrixFrame, "Число строк не совпадает с числом " +
                            "столбцов");
                }
            }
        });

        setMatrixFrame.getContentPane().add(BorderLayout.NORTH, setMatrixText);
        setMatrixFrame.getContentPane().add(BorderLayout.CENTER, setMatrixInput);
        setMatrixFrame.getContentPane().add(BorderLayout.SOUTH, matrixSendButton);

        setMatrixFrame.setVisible(true);
    }

    private static void result(){
        JFrame resultFrame = new JFrame("Silvester Criterion Auto");
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setSize(700, 300); // размеры окна
        resultFrame.setLocationRelativeTo(null); // окно - в центре экрана

        JButton exitButton = new JButton("Выйти");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton repeatButton = new JButton("Повторить");
        repeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultFrame.dispose();
                start();
            }
        });

        SquareMatrix matrix = new SquareMatrix(userMatrixSizeInt, userMatrix);

        JTextArea resultText = new JTextArea("Квадратичная форма матрицы: " + matrix.defineForm().getRusName());
        resultText.setEditable(false);

        resultFrame.getContentPane().add(BorderLayout.CENTER, resultText);
        resultFrame.getContentPane().add(BorderLayout.WEST, repeatButton);
        resultFrame.getContentPane().add(BorderLayout.EAST, exitButton);

        resultFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
