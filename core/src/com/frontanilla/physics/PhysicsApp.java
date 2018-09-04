package com.frontanilla.physics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class PhysicsApp extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture pixel;
    private double[][] originalDots, originalDotsCopy, rotatedDots;
    private double[] originalXRow, originalYRow, originalXRowCopy, originalYRowCopy, rotatedXRow, rotatedYRow;
    private float angle = 0;
    private boolean dynamicAngle;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.gl20.glLineWidth(Constants.AXIS_THICKNESS);
        pixel = new Texture("pixel.png");

        originalDots = Letters.V();
        originalDotsCopy = Utils.copy(originalDots);

        Vector2 centroid = Utils.centroid(originalDots);
        originalDots = Utils.add(originalDots, centroid.scl(-1));
        rotatedDots = new double[4][4];
        rotatedDots = Utils.multiply(Utils.transformationMatrix(angle), originalDots);
        calculateRows();

        Gdx.input.setInputProcessor(new InputManager(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (dynamicAngle) {
            angle += Gdx.graphics.getDeltaTime() * 360;
            if (angle >= 360) {
                angle -= 360;
            }
        } else {
            angle = 32;
        }

        rotatedDots = Utils.multiply(Utils.transformationMatrix(angle), originalDots);
        rotatedDots = Utils.add(rotatedDots, new Vector2(-5, -3));
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
        originalXRowCopy = new double[originalDotsCopy[0].length];
        originalYRowCopy = new double[originalDotsCopy[0].length];
        rotatedXRow = new double[rotatedDots[0].length];
        rotatedYRow = new double[rotatedDots[0].length];
        for (int column = 0; column < originalDots[0].length; column++) {
            originalXRow[column] = originalDots[0][column];
            originalYRow[column] = originalDots[1][column];
        }
        for (int column = 0; column < originalDotsCopy[0].length; column++) {
            originalXRowCopy[column] = originalDotsCopy[0][column];
            originalYRowCopy[column] = originalDotsCopy[1][column];
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
        batch.setColor(Color.YELLOW);
        for (int column = 0; column < originalDotsCopy[0].length; column++) {
            batch.draw(pixel,
                    (float) originalXRowCopy[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_X,
                    (float) originalYRowCopy[column] * Constants.PIXEL_SIZE + Constants.PIXEL_OFFSET_Y,
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

        poly = new float[originalDotsCopy[0].length * 2];
        for (int column = 0; column < originalDotsCopy[0].length; column++) {
            poly[column * 2] = (float) (originalXRowCopy[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_X);
            poly[column * 2 + 1] = (float) (originalYRowCopy[column] * Constants.PIXEL_SIZE + Constants.SCREEN_MID_Y);
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

    public void changeAngle() {
        dynamicAngle = !dynamicAngle;
    }
}
