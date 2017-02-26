package com.izacc.game;

import com.badlogic.gdx.Game;
import com.izacc.screen.SplashScreen;

public class Izacc extends Game
{
    public static String GAME_NAME = "Izac";

    public static final int SCREEN_WIDTH = 700;
    public static final int SCREEN_HEIGHT = 480;

    private boolean paused = false;

    @Override
    public void create() {
        this.setScreen(new SplashScreen(this));
    }

    /**
    * ---------------
    * Getters and setters
    */

    public boolean isPaused() {
        return paused;
    }

    public void setPause(boolean paused) {
        this.paused = paused;
    }
}
