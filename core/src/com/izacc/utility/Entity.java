package com.izacc.utility;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public class Entity{
    protected float x;
    protected float y;
    protected float xVel;
    protected float yVel;
    protected float health;
    protected float energy;
    protected float shield;
    protected float gold;

    protected ShapeRenderer shapeRenderer;

    public Entity(float x, float y)
    {
        this.x = x;
        this.y = y;
        this.xVel = 0.0f;
        this.yVel = 0.0f;

        shapeRenderer = new ShapeRenderer();
    }

    /**
     *
     * Getters and Setters
     */

    public float getxVel() 
    {
        return xVel;
    }

    public void setxVel(float xVel) 
    {
        this.xVel = xVel;
    }

    public float getyVel() 
    {
        return yVel;
    }

    public void setyVel(float yVel) 
    {
        this.yVel = yVel;
    }

    public float getY() 
    {
        return y;
    }

    public void setY(float y) 
    {
        this.y = y;
    }

    public float getX() 
    {
        return x;
    }

    public void setX(float x) 
    {
        this.x = x;
    }
}
