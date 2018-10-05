package com.frontanilla.physics;

import com.badlogic.gdx.Gdx;

public class Constants {

    public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static final float SCREEN_MID_X = SCREEN_WIDTH / 2;
    public static final float SCREEN_MID_Y = SCREEN_HEIGHT / 2;
    public static final float SCREEN_QUARTER_X = SCREEN_WIDTH / 4;
    public static final float SCREEN_QUARTER_Y = SCREEN_HEIGHT / 4;
    public static final float PIXEL_SIZE = SCREEN_HEIGHT / 70;
    public static final float PIXEL_OFFSET_X = SCREEN_MID_X - PIXEL_SIZE / 2;
    public static final float PIXEL_OFFSET_Y = SCREEN_MID_Y - PIXEL_SIZE / 2;
    public static final float AXIS_THICKNESS = PIXEL_SIZE / 3;
}
