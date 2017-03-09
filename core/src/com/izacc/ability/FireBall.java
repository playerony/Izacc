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
public class FireBall extends Ability
{
    private float INCREASE_RADIUS_BY = 0.01f;

    public FireBall(int direction, float x, float y) 
    {
        super(direction, x, y);
        
        this.r = 0.5f;
        
        init();
    }

    @Override
    public void initSpeed() 
    {
        attackSpeed = 7.5f;
        
        switch(direction){
            case LEFT:
                xVel = -attackSpeed;
                yVel = 0;
                break;
                
            case RIGHT:
                xVel = attackSpeed;
                yVel = 0;
                break;
                
            case UP:
                yVel = attackSpeed;
                xVel = 0;
                break;
                
            default:
                yVel = -attackSpeed;
                xVel = 0;
                break;
        }
    }

    @Override
    public void update() {
        if(r < 25.0f){
            INCREASE_RADIUS_BY+=0.1f;
            r+=INCREASE_RADIUS_BY;
        }
        
        x+=xVel;
        y+=yVel;
    }

    @Override
    public void render(float delta) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
    
}
