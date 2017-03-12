/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.izacc.equipment.EffectType;

/**
 *
 * @author pawel_000
 */
public class IceArrow extends Ability
{

    public IceArrow(int direction, float x, float y, float speed, float damage, EffectType effectType)
    {
        super(direction, x, y, speed, damage, effectType);
        
        this.r = 3.0f;
        
        init();
    }
    
    public IceArrow(int direction, float x, float y, float speed, float damage, EffectType effectType, float r)
    {
        super(direction, x, y, speed, damage, effectType);
        
        this.r = r;
        
        init();
    }

    @Override
    public void initSpeed()
    {
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
    
    public void update()
    {
        super.update();
        
        x+=xVel;
        y+=yVel;
    }

    @Override
    public void render(float delta)
    {
        shapeRenderer.setColor(Color.NAVY);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
    
}
