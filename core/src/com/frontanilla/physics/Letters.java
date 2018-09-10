package com.frontanilla.physics;

public class Letters {

    public static double[][] A() {
        double[][] A = new double[4][8];

        Utils.putDot(A, 0, 0, 0);
        Utils.putDot(A, 1, 2, 0);
        Utils.putDot(A, 2, 3, 4);
        Utils.putDot(A, 3, 4, 4);
        Utils.putDot(A, 4, 5, 0);
        Utils.putDot(A, 5, 7, 0);
        Utils.putDot(A, 6, 5, 9);
        Utils.putDot(A, 7, 2, 9);
        fill2D(A);

        return A;
    }

    public static double[][] B() {
        double[][] B = new double[4][7];

        Utils.putDot(B, 0, 0, 0);
        Utils.putDot(B, 1, 3, 0);
        Utils.putDot(B, 2, 5, 2);
        Utils.putDot(B, 3, 3, 4.5);
        Utils.putDot(B, 4, 5, 7);
        Utils.putDot(B, 5, 3, 9);
        Utils.putDot(B, 6, 0, 9);
        fill2D(B);

        return B;
    }

    public static double[][] F() {
        double[][] F = new double[4][10];

        Utils.putDot(F, 0, 0, 0);
        Utils.putDot(F, 1, 2, 0);
        Utils.putDot(F, 2, 2, 3);
        Utils.putDot(F, 3, 4, 3);
        Utils.putDot(F, 4, 4, 5);
        Utils.putDot(F, 5, 2, 5);
        Utils.putDot(F, 6, 2, 7);
        Utils.putDot(F, 7, 6, 7);
        Utils.putDot(F, 8, 6, 9);
        Utils.putDot(F, 9, 0, 9);
        fill2D(F);

        return F;
    }

    public static double[][] V() {
        double[][] V = new double[4][7];

        Utils.putDot(V, 0, 0, 9);
        Utils.putDot(V, 1, 2, 0);
        Utils.putDot(V, 2, 4, 0);
        Utils.putDot(V, 3, 6, 9);
        Utils.putDot(V, 4, 4, 9);
        Utils.putDot(V, 5, 3, 3);
        Utils.putDot(V, 6, 2, 9);
        fill2D(V);

        return V;
    }

    private static void fill2D(double[][] matrix) {
        // Puts 0 in all z values and 1 in all Position/Direction values
        for (int i = 2; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = i - 2;
            }
        }
    }
}
