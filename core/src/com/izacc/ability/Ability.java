/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.badlogic.gdx.Gdx;
import com.izacc.character.Direction;
import com.izacc.equipment.EffectType;
import com.izacc.utility.Entity;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public abstract class Ability extends Entity
{
    protected ArrayList<Ability> bullets;
    
    protected float lifeTime = 0.0f;
    protected float attackSpeed = 0.0f;
    protected float damage = 0.0f;
    
    protected boolean actived = false;
    protected boolean isRotate = false;
    protected boolean toRemove = false;
    
    protected EffectType effectType;
    protected Direction direction;
    
    public Ability(int direction, float x, float y, float speed, float damage, EffectType effectType)
    {
        super(x, y);
        
        bullets = new ArrayList<Ability>();
        this.attackSpeed = speed;
        this.damage = damage;
        
        this.effectType = effectType;
        this.direction = Direction.values()[direction];
    }
    
    public void init() 
    {
        initSpeed();
    }
    
    public abstract void initSpeed();
    
    public abstract void render(float delta);
    
    public void update()
    {
        lifeTime+=Gdx.graphics.getDeltaTime();
        if(lifeTime>=10.0f)
        {
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
    
    public ArrayList<Ability> getBullets()
    {
        return bullets;
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

    public float getAttackSpeed()
    {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed)
    {
        this.attackSpeed = attackSpeed;
    }

    public float getDamage()
    {
        return damage;
    }

    public void setDamage(float damage)
    {
        this.damage = damage;
    }

    public EffectType getEffectType()
    {
        return effectType;
    }

    public void setEffectType(EffectType effectType)
    {
        this.effectType = effectType;
    }
}
