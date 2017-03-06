/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author pawel_000
 */
public class SunShot extends Ability
{
    public SunShot(int direction, float x, float y)
    {
        super(direction, x, y);
        
        init();
    }

    @Override
    public void initSpeed()
    {
        float value = 20.0f;
        float r = 2.0f;
        float xPos = 0.0f;
        float yPos = 0.0f;
        float xV = 0.0f;
        float yV = 0.0f;
        
        for(int i=0 ; i<value ; i++){
            xPos = (float) (r * Math.sin(2 * Math.PI * (i / value))) + x;
            yPos = (float) (r * -Math.cos(2 * Math.PI * (i / value))) + y;
            xV = (float) (r * Math.sin(2 * Math.PI * (i / value)));
            yV = (float) (r * -Math.cos(2 * Math.PI * (i / value)));
            
            Bullet bullet = new Bullet(direction.ordinal(), xPos, yPos, xV, yV);
            bullets.add(bullet);
        }
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public void render(float delta)
    {
        shapeRenderer.setColor(Color.LIME);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, 10);
        shapeRenderer.end();
    }
}
