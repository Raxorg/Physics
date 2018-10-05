package com.frontanilla.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Formula {

    private float velocityX, initY, initVY, duration;
    private Texture pixel;
    private Color color;
    private BitmapFont font;
    private float fontOffsetY, dataOffsetX;

    public Formula(float velocityX, float initY, float initVY, float duration, Color color) {
        this.velocityX = velocityX;
        this.initY = initY;
        this.initVY = initVY;
        this.duration = duration;
        pixel = new Texture("pixel.png");
        this.color = color;
        font = new BitmapFont();
    }

    public void setFontOffsetY(float fontOffsetY) {
        this.fontOffsetY = fontOffsetY;
    }

    public void setDataOffsetX(float dataOffsetX) {
        this.dataOffsetX = dataOffsetX;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(color);
        font.setColor(color);
        float time;
        // DRAW X LABEL
        font.draw(
                batch,
                "X",
                0 + dataOffsetX,
                Constants.SCREEN_HEIGHT);
        // DRAW Y LABEL
        font.draw(
                batch,
                "Y",
                75 + dataOffsetX,
                Constants.SCREEN_HEIGHT);
        for (time = 0; time < duration; time += 0.5f) {
            // DRAW TIME
            font.draw(
                    batch,
                    "" + time,
                    Constants.SCREEN_QUARTER_X + calcX(velocityX, time),
                    Constants.SCREEN_QUARTER_Y + fontOffsetY);
            // DRAW X COORD
            font.draw(
                    batch,
                    "" + calcX(velocityX, time),
                    0 + dataOffsetX,
                    Constants.SCREEN_HEIGHT - (Constants.PIXEL_SIZE * 2.25f) * (time+0.5f) * 2);
            // DRAW Y COORD
            font.draw(
                    batch,
                    "" + calcY(initY, initVY, time),
                    75 + dataOffsetX,
                    Constants.SCREEN_HEIGHT - (Constants.PIXEL_SIZE * 2.25f) * (time+0.5f) * 2);
            batch.draw(
                    pixel,
                    Constants.SCREEN_QUARTER_X + calcX(velocityX, time),
                    Constants.SCREEN_QUARTER_Y + calcY(initY, initVY, time),
                    Constants.PIXEL_SIZE,
                    Constants.PIXEL_SIZE);
        }
        font.draw(
                batch,
                "" + duration,
                Constants.SCREEN_QUARTER_X + calcX(velocityX, duration),
                Constants.SCREEN_QUARTER_Y + Constants.PIXEL_SIZE * 2);
    }

    private float calcX(float velocity, float time) {
        return velocity * time;
    }

    private float calcY(float initY, float initVY, float time) {
        return initY + initVY * time - 4.905f * time * time;
    }
}
