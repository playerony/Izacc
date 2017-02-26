package com.izacc.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.izacc.game.Izacc;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public class SplashScreen extends AbstractScreen {
    private Texture texture;

    public SplashScreen(Izacc gdxGame){
        super(gdxGame);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                izacc.setScreen(new GameScreen(izacc));
            }
        }, 1);
    }

    @Override
    protected void init() {
        texture = new Texture("photos/photo.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        batch.draw(texture, 100, 100);
        batch.end();
    }
}
