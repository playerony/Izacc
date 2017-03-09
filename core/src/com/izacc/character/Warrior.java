package com.izacc.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.izacc.utility.Entity;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public class Warrior extends Player {
    public Warrior(float x, float y) {
        super(x, y);
    }

    @Override
    public void render(float delta) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }

    @Override
    public void attack(Entity entity) {

    }
}
