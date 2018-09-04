package com.frontanilla.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Utils {

    public static void putDot(double[][] matrix, int index, double x, double y) {
        matrix[0][index] = x;
        matrix[1][index] = y;
    }

    public static double[][] copy(double[][] matrix) {
        double[][] copy = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, matrix[i].length);
        }
        return copy;
    }

    public static Vector2 centroid(double[][] matrix) {
        Vector2 centroid = new Vector2();
        for (int i = 0; i < matrix.length - 2; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) {
                    centroid.x += matrix[i][j];
                }
                if (i == 1) {
                    centroid.y += matrix[i][j];
                }
            }
        }
        centroid.x /= matrix[0].length;
        centroid.y /= matrix[0].length;
        return centroid;
    }

    public static double[][] add(double[][] matrix, Vector2 position) {
        for (int i = 0; i < matrix.length - 2; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) {
                    matrix[i][j] += position.x;
                }
                if (i == 1) {
                    matrix[i][j] += position.y;
                }
            }
        }
        return matrix;
    }

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
