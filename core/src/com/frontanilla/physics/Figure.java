package com.frontanilla.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Figure {

    private Texture pixel;
    private double[][] data;
    private double[] xRow, yRow;
    private Color color;

    public Figure(double[][] data, Color color) {
        this.data = data;
        this.color = color;
        pixel = new Texture("pixel.png");
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        calculateRows();
        renderPixels(batch);
        renderLines(shapeRenderer);
    }

    private void renderPixels(SpriteBatch batch) {
        batch.setColor(color);
        for (int column = 0; column < data[0].length; column++) {
            batch.draw(pixel,
                    (float) xRow[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_X,
                    (float) yRow[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_Y,
                    Constants.PIXEL_SIZE,
                    Constants.PIXEL_SIZE);
        }
    }

    private void renderLines(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        float[] poly = new float[data[0].length * 2];
        for (int column = 0; column < data[0].length; column++) {
            poly[column * 2] = (float) (xRow[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_X);
            poly[column * 2 + 1] = (float) (yRow[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_Y);
        }
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.polygon(poly);
    }

    private void calculateRows() {
        xRow = new double[data[0].length];
        yRow = new double[data[0].length];
        for (int column = 0; column < data[0].length; column++) {
            xRow[column] = data[0][column];
            yRow[column] = data[1][column];
        }
    }

    public double[][] getData() {
        return data;
    }
}
