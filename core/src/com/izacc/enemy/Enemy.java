/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.izacc.utility.Entity;

/**
 *
 * @author pawel_000
 */
public class Enemy extends Entity
{
    private int mobRank;
    private float color;
    private float friction = 0.9f;
    private boolean isDead = false;
    
    private float alpha = 1.0f;
    
    public Enemy(int mobRank, float x, float y)
    {
        super(x, y);
        color = (mobRank * 80.0f) / 255.0f;
        
        this.r = 20.0f;
        this.mobRank = mobRank;
        this.health = 20 * mobRank;
        this.MAX_HEALTH = this.health;
    }

    public int getMobRank()
    {
        return mobRank;
    }
    
    public void update(){
        x += xVel;
        y += yVel;

        xVel*=friction;
        yVel*=friction;
    }
    
    public void render(float delta) 
    {
        shapeRenderer.setColor(new Color(color, color, color, alpha));
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
    
    public void lostHealth(float health){
        this.health -= health;
        
        if(this.health <= 0){
            this.health = 0;
            this.isDead = true;
        }
    }

    public boolean isDead()
    {
        return isDead;
    }
}
