package com.frontanilla.physics.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frontanilla.physics.PhysicsApp;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = config.width*2;
        new LwjglApplication(new PhysicsApp(), config);
    }
}
