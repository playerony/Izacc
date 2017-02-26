/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.izacc.utility.Entity;

/**
 *
 * @author pawel_000
 */
public abstract class Ability extends Entity{
    protected float attackSpeed;
    
    protected boolean actived = false;
    
    public enum Direction { LEFT, RIGHT, UP, DOWN };
    protected Direction direction;
    
    public Ability(int direction, float x, float y){
        super(x, y);
        
        this.direction = Direction.values()[direction];
        init();
    }
    
    private void init() {
        initSpeed();
        attack();
    }
    
    public abstract void initSpeed();
    
    public abstract void attack();
    
    public abstract void update();
    
    public abstract void render(float delta);
    
    /**
     * 
     * Gettets and setters
     */

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }
    
    
}
