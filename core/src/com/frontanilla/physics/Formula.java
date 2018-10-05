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

    public Formula(float velocityX, float initY, float initVY, float duration, Color color) {
        this.velocityX = velocityX;
        this.initY = initY;
        this.initVY = initVY;
        this.duration = duration;
        pixel = new Texture("pixel.png");
        this.color = color;
        font = new BitmapFont();
    }

    public void render(SpriteBatch batch) {
        batch.setColor(color);
        for (float time = 0; time < duration; time += 0.5f) {
            if (time == 0) {
                font.draw(
                        batch,
                        "0",
                        Constants.SCREEN_QUARTER_X,
                        Constants.SCREEN_QUARTER_Y);
            }
            batch.draw(
                    pixel,
                    Constants.SCREEN_QUARTER_X + calcX(velocityX, time),
                    Constants.SCREEN_QUARTER_Y + calcY(initY, initVY, time),
                    Constants.PIXEL_SIZE,
                    Constants.PIXEL_SIZE);
        }
    }

    private float calcX(float velocity, float time) {
        return velocity * time;
    }

    private float calcY(float initY, float initV, float time) {
        return initY + initV * time - 4.905f * time * time;
    }
}
