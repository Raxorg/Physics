package com.frontanilla.physics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class PhysicsApp extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private DelayedRemovalArray<Figure> figures;

    private Texture pixel;

    private float angle = 0;
    private boolean dynamicAngle;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.gl20.glLineWidth(Constants.AXIS_THICKNESS);
        pixel = new Texture("pixel.png");

        figures = new DelayedRemovalArray<Figure>();
        Figure a = new Figure(Letters.A(), Color.RED);
        figures.add(a);
        Figure b = new Figure(Utils.copy(a.getData()), Color.GREEN);
        figures.add(b);
        figures.add(new Figure(Letters.F(), Color.BLUE));
        figures.add(new Figure(Letters.V(), Color.ORANGE));

        //Vector2 centroid = Utils.centroid(originalDots);
        //originalDots = Utils.add(originalDots, centroid.scl(-1));
        //rotatedDots = new double[4][4];
        //rotatedDots = Utils.multiply(Utils.transformationMatrix(angle), originalDots);

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

        for (Figure fig : figures) {
            fig.render(batch, shapeRenderer);
        }

        //rotatedDots = Utils.multiply(Utils.transformationMatrix(angle), originalDots);
        //rotatedDots = Utils.add(rotatedDots, new Vector2(-5, -3));

        batch.begin();
        drawAxes();
        //drawPixels();
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //drawLines();
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
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

    public void changeAngle() {
        dynamicAngle = !dynamicAngle;
    }
}
