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
public class Bullet extends Ability
{
    protected boolean actived = false;
    private float angle = 0.0f;
    private float test = 0.5f;
    private float lifeTime = 0.0f;
    
    public Bullet(int direction, float x, float y) {
        super(direction, x, y);
        
        init();
        
        this.r = 5.0f;
    }

    public Bullet(int direction, float x, float y, float xVel, float yVel) {
        super(direction, x, y);
        
        this.xVel = xVel;
        this.yVel = yVel;
    }
    
    public Bullet(int direction, float x, float y, float xVel, float yVel, float r) {
        super(direction, x, y);
        
        this.xVel = xVel;
        this.yVel = yVel;
        this.r = r;
    }
    
    @Override
    public void initSpeed() 
    {
        attackSpeed = 5.2f;
        
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
        super.update();
        
        x+=xVel;
        y+=yVel;
        
        if(isRotate){
            angle += 0.1f;
            
            if(angle >= 360.0f)
                angle = 0.0f;
            
            if(test < 15.0f)
                test+=0.1f;
            
            xVel = test * (float) Math.sin(angle);
            yVel = test * (float) Math.cos(angle);
        }
    }

    @Override
    public void render(float delta) 
    {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
}
