package Lab1.Exercise2;

public class Main {
    public static void main(String[] args) {
        int[][] matrix1 = new int[][] {
            { 2, 3, 1 },
            { 7, 1, 6 },
            { 9, 2, 4 }
        };
        int[][] matrix2 = new int[][] {
            { 8, 5, 3 },
            { 3, 9, 2 },
            { 2, 7, 3 }
        };

        // Sum of the two matrices:
        System.out.println("Sum of the two matrices is: ");
        int[][] sum = new int[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Product of the two matrices:
        System.out.println("Product of the two matrices is: ");
        int[][] product = new int[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                product[i][j] = 0;
                for (int k = 0; k < matrix1.length; k++) {
                    product[i][j] += matrix1[i][k] * matrix2[k][j];
                }
                System.out.print(product[i][j] + " ");
            }
            System.out.println();
        }
    }
}
