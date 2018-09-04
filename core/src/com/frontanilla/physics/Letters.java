package com.frontanilla.physics;

public class Letters {

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
