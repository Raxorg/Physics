package com.frontanilla.physics;

import com.badlogic.gdx.math.MathUtils;

public class Utils {

    public static double[][] transformationMatrix(float angle) {
        double[][] transformMatrix = new double[4][4];
        transformMatrix[0][0] = MathUtils.cosDeg(angle);
        transformMatrix[0][1] = -MathUtils.sinDeg(angle);
        transformMatrix[0][2] = 0;
        transformMatrix[0][3] = 0;
        transformMatrix[1][0] = MathUtils.sinDeg(angle);
        transformMatrix[1][1] = MathUtils.cosDeg(angle);
        transformMatrix[1][2] = 0;
        transformMatrix[1][3] = 0;
        transformMatrix[2][0] = 0;
        transformMatrix[2][1] = 0;
        transformMatrix[2][2] = 1;
        transformMatrix[2][3] = 0;
        transformMatrix[3][0] = 0;
        transformMatrix[3][1] = 0;
        transformMatrix[3][2] = 0;
        transformMatrix[3][3] = 1;
        return transformMatrix;
    }

    public static double[][] multiply(double[][] a, double[][] b) {

        int aRows = a.length;
        int aColumns = a[0].length;
        int bRows = b.length;
        int bColumns = b[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("a:Rows: " + aColumns + " did not match b:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return C;
    }

    public static void showMatrix(double[][] matrix) {
        for (double[] vector : matrix) {
            for (double value : vector) System.out.print(value + " ");
            System.out.println();
        }
    }

}
