/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability.mage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.izacc.ability.Ability;

/**
 *
 * @author pawel_000
 */
public class IceBall extends Ability{
    private float r = 0.1f;

    public IceBall(int direction, float x, float y, float xVel, float yVel) {
        super(direction, x, y);
        
        this.xVel = xVel;
        this.yVel = yVel;
    }

    @Override
    public void initSpeed() {
        
    }

    @Override
    public void attack() {
        actived = true;
    }

    @Override
    public void update() {
        if(r < 20.0f)
            r+=0.5f;
        
        x+=xVel;
        y+=yVel;
    }

    @Override
    public void render(float delta) {
        shapeRenderer.setColor(Color.CORAL);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
    
}
