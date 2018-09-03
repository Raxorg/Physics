package com.frontanilla.physics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PhysicsApp extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture pixel;
    private double[][] originalDots, rotatedDots;
    private double[] originalXRow, originalYRow, rotatedXRow, rotatedYRow;
    private float angle = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.gl20.glLineWidth(Constants.AXIS_THICKNESS);
        pixel = new Texture("pixel.png");
        originalDots = new double[4][10];
        originalDots[0][0] = 4;
        originalDots[1][0] = 0;
        originalDots[2][0] = 0;
        originalDots[3][0] = 1;

        originalDots[0][1] = 6;
        originalDots[1][1] = 0;
        originalDots[2][1] = 0;
        originalDots[3][1] = 1;

        originalDots[0][2] = 6;
        originalDots[1][2] = 3;
        originalDots[2][2] = 0;
        originalDots[3][2] = 1;

        originalDots[0][3] = 8;
        originalDots[1][3] = 3;
        originalDots[2][3] = 0;
        originalDots[3][3] = 1;

        originalDots[0][4] = 8;
        originalDots[1][4] = 5;
        originalDots[2][4] = 0;
        originalDots[3][4] = 1;

        originalDots[0][5] = 6;
        originalDots[1][5] = 5;
        originalDots[2][5] = 0;
        originalDots[3][5] = 1;

        originalDots[0][6] = 6;
        originalDots[1][6] = 7;
        originalDots[2][6] = 0;
        originalDots[3][6] = 1;

        originalDots[0][7] = 10;
        originalDots[1][7] = 7;
        originalDots[2][7] = 0;
        originalDots[3][7] = 1;

        originalDots[0][8] = 10;
        originalDots[1][8] = 9;
        originalDots[2][8] = 0;
        originalDots[3][8] = 1;

        originalDots[0][9] = 4;
        originalDots[1][9] = 9;
        originalDots[2][9] = 0;
        originalDots[3][9] = 1;

        Utils.showMatrix(originalDots);
        rotatedDots = new double[4][4];
        rotatedDots = Utils.multiply(Utils.transformationMatrix(angle), originalDots);
        Utils.showMatrix(rotatedDots);
        calculateRows();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        angle += Gdx.graphics.getDeltaTime() * 360;
        if (angle >= 360) {
            angle -= 360;
        }
        rotatedDots = Utils.multiply(Utils.transformationMatrix(angle), originalDots);
        calculateRows();

        batch.begin();
        drawAxes();
        drawPixels();
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawLines();
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        pixel.dispose();
    }

    private void calculateRows() {
        originalXRow = new double[originalDots[0].length];
        originalYRow = new double[originalDots[0].length];
        rotatedXRow = new double[rotatedDots[0].length];
        rotatedYRow = new double[rotatedDots[0].length];
        for (int column = 0; column < originalDots[0].length; column++) {
            originalXRow[column] = originalDots[0][column];
            originalYRow[column] = originalDots[1][column];
        }
        for (int column = 0; column < rotatedDots[0].length; column++) {
            rotatedXRow[column] = rotatedDots[0][column];
            rotatedYRow[column] = rotatedDots[1][column];
        }
    }

    private void drawAxes() {
        batch.setColor(Color.BLACK);
        batch.draw(pixel,
                0,
                Constants.SCREEN_MID_Y - Constants.AXIS_THICKNESS / 2,
                Constants.SCREEN_WIDTH,
                Constants.AXIS_THICKNESS);
        batch.draw(pixel,
                Constants.SCREEN_MID_X - Constants.AXIS_THICKNESS / 2,
                0,
                Constants.AXIS_THICKNESS,
                Constants.SCREEN_HEIGHT);
    }

    private void drawPixels() {
        batch.setColor(Color.BLUE);
        for (int column = 0; column < originalDots[0].length; column++) {
            batch.draw(pixel,
                    (float) originalXRow[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_X,
                    (float) originalYRow[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_Y,
                    Constants.PIXEL_SIZE,
                    Constants.PIXEL_SIZE);
        }
        batch.setColor(Color.GREEN);
        for (int column = 0; column < rotatedDots[0].length; column++) {
            batch.draw(pixel,
                    (float) rotatedXRow[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_X,
                    (float) rotatedYRow[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_Y,
                    Constants.PIXEL_SIZE,
                    Constants.PIXEL_SIZE);
        }
    }

    private void drawLines() {
        float[] poly = new float[originalDots[0].length * 2];
        for (int column = 0; column < originalDots[0].length; column++) {
            poly[column * 2] = (float) (originalXRow[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_X);
            poly[column * 2 + 1] = (float) (originalYRow[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_Y);
        }
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.polygon(poly);
        poly = new float[rotatedDots[0].length * 2];
        for (int column = 0; column < rotatedDots[0].length; column++) {
            poly[column * 2] = (float) (rotatedXRow[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_X);
            poly[column * 2 + 1] = (float) (rotatedYRow[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_Y);
        }
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.polygon(poly);
    }
}
