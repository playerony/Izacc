package com.izacc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.izacc.game.Izacc;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public abstract class AbstractScreen implements Screen {
    public OrthographicCamera camera;
    public SpriteBatch batch, equipmentBatch;
    public Izacc izacc;
    protected Stage stage;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;

    public AbstractScreen(Izacc izacc){
        this.izacc = izacc;
        createCamera();
        stage = new Stage(new StretchViewport(Izacc.SCREEN_WIDTH, Izacc.SCREEN_HEIGHT, camera));
        batch = new SpriteBatch();
        equipmentBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        Gdx.input.setInputProcessor(stage);

        init();
    }

    protected abstract void init();

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Izacc.SCREEN_WIDTH, Izacc.SCREEN_HEIGHT);
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0.6f, 0.8f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resume() {
        izacc.setPause(false);
    }

    @Override
    public void pause() {
        izacc.setPause(true);
    }

    @Override
    public void dispose() {
        izacc.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
