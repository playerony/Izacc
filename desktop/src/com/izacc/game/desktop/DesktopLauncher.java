package com.izacc.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.izacc.game.Izacc;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        config.title = Izacc.GAME_NAME;
        config.width = Izacc.SCREEN_WIDTH;
        config.height = Izacc.SCREEN_HEIGHT;
        config.resizable = false;
                
        new LwjglApplication(new Izacc(), config);
    }
}
