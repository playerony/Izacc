package com.izacc.utility;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public class Entity
{
    protected float x;
    protected float y;
    protected float r;
    protected float xVel;
    protected float yVel;
    protected float health;
    protected float MAX_HEALTH;
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
        this.r = 10.0f;

        shapeRenderer = new ShapeRenderer();
    }
    
    // Checking circle collision
    public boolean isCircleCollision(Entity e1)
    {
        float xPos = e1.x - x;
        float yPos = e1.y - y;
        float distance = (float) Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
        
        if(distance >= e1.r + r)
            return false;
        
        return true;
    }

    // checking square collision
    public boolean isColision(Entity e1)
    {
        float leftA, leftB;
        float rightA, rightB;
        float topA, topB;
        float bottomA, bottomB;

        leftA = e1.getX() - e1.getR();
        rightA = e1.getX() + e1.getR();
        topA =  e1.getY() - e1.getR();
        bottomA =  e1.getY() + e1.getR();

        leftB = x - r;
        rightB = x + r;
        topB = y - r;
        bottomB = y + r;
        
        if( bottomA <= topB )
        {
            return false;
        }

        if( topA >= bottomB )
        {
            return false;
        }

        if( rightA <= leftB )
        {
            return false;
        }

        if( leftA >= rightB )
        {
            return false;
        }

        return true;
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
    
    public float getR()
    {
        return r;
    }

    public void setR(float r)
    {
        this.r = r;
    }

    public float getHealth()
    {
        return health;
    }

    public float getMaxHealth()
    {
        return MAX_HEALTH;
    }
}
