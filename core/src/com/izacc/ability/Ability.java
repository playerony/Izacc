/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.badlogic.gdx.Gdx;
import com.izacc.character.Direction;
import com.izacc.utility.Entity;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public abstract class Ability extends Entity
{
    protected ArrayList<Bullet> bullets;
    
    protected float lifeTime = 0.0f;
    protected float attackSpeed = 0.0f;
    
    protected boolean shield = false;
    protected boolean actived = false;
    protected boolean isRotate = false;
    protected boolean toRemove = false;
    
    protected Direction direction;
    
    public Ability(int direction, float x, float y)
    {
        super(x, y);
        
        bullets = new ArrayList<Bullet>();
        this.direction = Direction.values()[direction];
    }
    
    public void init() 
    {
        initSpeed();
    }
    
    public abstract void initSpeed();
    
    public abstract void render(float delta);
    
    public void update(){
        lifeTime+=Gdx.graphics.getDeltaTime();
        if(lifeTime>=10.0f){
            lifeTime = 0.0f;
            toRemove = true;
        }
    }
    
    public void clearBullets()
    {
        this.bullets.clear();
    }
    
    /**
     * 
     * Gettets and setters
     */

    public boolean isActived() 
    {
        return actived;
    }

    public void setActived(boolean actived) 
    {
        this.actived = actived;
    }
    
    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }

    public boolean isShield()
    {
        return shield;
    }

    public boolean isRotate()
    {
        return isRotate;
    }

    public void setIsRotate(boolean isRotate)
    {
        this.isRotate = isRotate;
    }

    public boolean isToRemove()
    {
        return toRemove;
    }
    
    
}
