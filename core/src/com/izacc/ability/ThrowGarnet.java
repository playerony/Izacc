/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.izacc.character.Player;

/**
 *
 * @author pawel_000
 */
public class ThrowGarnet extends Ability
{
    private static final float friction = 0.985f;
    private static float timeState;

    public ThrowGarnet(int direction, float x, float y)
    {
        super(direction, x, y);
        
        this.r = 10.0f;
        this.timeState = 0.0f;
        this.actived = false;
        
        init();
    }

    @Override
    public void initSpeed()
    {
        attackSpeed = 6.9f;
        
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
    public void update()
    {
        x+=xVel;
        y+=yVel;
        
        xVel *= friction;
        yVel *= friction;
        
        timeState+=Gdx.graphics.getDeltaTime();
        if(timeState>=1.0f){
            SunShot sunShot = new SunShot(direction.ordinal(), x, y, 5.0f);
            for(Bullet b : sunShot.getBullets()){
                bullets.add(b);
            }
            
            sunShot.clearBullets();
            
            actived = true;
            timeState = 0.0f;
        }
    }

    @Override
    public void render(float delta)
    {
        shapeRenderer.setColor(Color.FOREST);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
    
}
