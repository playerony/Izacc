/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.izacc.utility.Entity;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public abstract class Ability extends Entity
{
    protected ArrayList<Bullet> bullets;
    
    protected float attackSpeed;
    
    protected boolean actived = false;
    protected boolean shield = false;
    
    public enum Direction { LEFT, RIGHT, UP, DOWN };
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
    
    public abstract void update();
    
    public abstract void render(float delta);
    
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
    
    
}
