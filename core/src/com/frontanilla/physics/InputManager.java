package com.frontanilla.physics;

import com.badlogic.gdx.InputAdapter;

public class InputManager extends InputAdapter {

    private PhysicsApp app;

    public InputManager(PhysicsApp app) {
        this.app = app;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        app.changeAngle();
        return true;
    }
}
