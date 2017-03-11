/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author pawel_000
 */
public class District extends Ability
{
    private float MAX_RANGE = 150.0f;
    private float timeState = 0.1f;
    
    public District(int direction, float x, float y)
    {
        super(direction, x, y);
        
        this.r = 1.0f;
        this.xVel = 0.5f;
        this.yVel = 0.5f;
        this.shield = true;
    }

    @Override
    public void initSpeed()
    {
        
    }

    @Override
    public void update()
    {
        if(r < MAX_RANGE){
            r+=0.5f;
            
            xVel = r / timeState;
            yVel = r / timeState;
        }
        
        timeState+=Gdx.graphics.getDeltaTime();
        if(timeState>=7.5f && !actived){
            actived = true;
        }
    }

    @Override
    public void render(float delta)
    {
        shapeRenderer.setColor(new Color(1.0f, 1.0f, 102.0f / 255.0f, 0.15f));
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
    
}
