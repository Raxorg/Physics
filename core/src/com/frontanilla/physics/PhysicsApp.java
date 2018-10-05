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

    private float angle = 32;
    private boolean dynamicAngle;

    private Formula formula1, formula2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.gl20.glLineWidth(Constants.AXIS_THICKNESS);
        pixel = new Texture("pixel.png");

        Figure a = new Figure(Letters.A(), Color.RED);
        Figure a2 = new Figure(Utils.copy(a.getMatrix()), Color.GREEN);
        Figure f = new Figure(Letters.F(), Color.BLUE);
        Figure v = new Figure(Letters.V(), Color.ORANGE);
        Figure b = new Figure(Letters.B(), Color.OLIVE);

        Vector2 aCentroid = a.centroid();
        a2.subtract(aCentroid);
        a2.transform(Utils.transformationMatrix(angle));
        Vector2 fCentroid = f.centroid();
        f.subtract(fCentroid);
        f.subtract(fCentroid);
        f.subtract(fCentroid);
        Vector2 vCentroid = v.centroid();
        v.add(vCentroid);
        v.add(vCentroid);
        Vector2 bCentroid = b.centroid();
        b.add(bCentroid);
        b.add(bCentroid);

        figures = new DelayedRemovalArray<Figure>();
        figures.add(a);
        figures.add(a2);
        figures.add(f);
        figures.add(v);
        figures.add(b);

        Gdx.input.setInputProcessor(new InputManager(this));

        formula1 = new Formula(68.263f, 20, 47.798f, 10.147f, Color.RED);
        formula2 = new Formula(187.938f, 0, 68.404f, 10.642f, Color.BLUE);
        formula2.setFontOffsetY(-Constants.PIXEL_SIZE * 3);
        formula2.setDataOffsetX(165);
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
            // fig.render(batch, shapeRenderer);
            // fig.render(batch, shapeRenderer); TEMP, INSPECTION ESTO ES ÚTIL, DESCOMENTAR
        }


        //rotatedDots = Utils.multiply(Utils.transformationMatrix(angle), originalDots);
        //rotatedDots = Utils.add(rotatedDots, new Vector2(-5, -3));

        batch.begin();
        formula1.render(batch);
        formula2.render(batch);
        drawAxes();
        //drawPixels();
        batch.end();
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //drawLines();
        //shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void drawAxes() {
        batch.setColor(Color.BLACK);
        batch.draw(pixel,
                0,
                Constants.SCREEN_QUARTER_Y - Constants.AXIS_THICKNESS / 2,
                Constants.SCREEN_WIDTH,
                Constants.AXIS_THICKNESS);
        batch.draw(pixel,
                Constants.SCREEN_QUARTER_X - Constants.AXIS_THICKNESS / 2,
                0,
                Constants.AXIS_THICKNESS,
                Constants.SCREEN_HEIGHT);
    }

    public void changeAngle() {
        dynamicAngle = !dynamicAngle;
    }
}
