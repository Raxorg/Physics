package com.frontanilla.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Figure {

    private Texture pixel;
    private double[] xRow, yRow;
    private Color color;
    private double[][] matrix;

    public Figure(double[][] matrix, Color color) {
        this.matrix = matrix;
        this.color = color;
        pixel = new Texture("pixel.png");
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        calculateRows();
        batch.begin();
        renderPixels(batch);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderLines(shapeRenderer);
        shapeRenderer.end();
    }

    private void renderPixels(SpriteBatch batch) {
        batch.setColor(color);
        for (int column = 0; column < matrix[0].length; column++) {
            batch.draw(pixel,
                    (float) xRow[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_X,
                    (float) yRow[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_Y,
                    Constants.PIXEL_SIZE,
                    Constants.PIXEL_SIZE);
        }
    }

    private void renderLines(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        float[] poly = new float[matrix[0].length * 2];
        for (int column = 0; column < matrix[0].length; column++) {
            poly[column * 2] = (float) (xRow[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_X);
            poly[column * 2 + 1] = (float) (yRow[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_Y);
        }
        shapeRenderer.polygon(poly);
    }

    private void calculateRows() {
        xRow = new double[matrix[0].length];
        yRow = new double[matrix[0].length];
        for (int column = 0; column < matrix[0].length; column++) {
            xRow[column] = matrix[0][column];
            yRow[column] = matrix[1][column];
        }
    }

    public Vector2 centroid() {
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

    public double[][] add(Vector2 position) {
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

    public double[][] subtract(Vector2 position) {
        return add(position.cpy().scl(-1));
    }

    public double[][] transform(double[][] transformationMatrix) {

        int aRows = transformationMatrix.length;
        int aColumns = transformationMatrix[0].length;
        int bRows = matrix.length;
        int bColumns = matrix[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("a:Rows: " + aColumns + " did not match b:Columns " + bRows + ".");
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    matrix[i][j] = transformationMatrix[i][k] * matrix[k][j];
                }
            }
        }

        return matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
