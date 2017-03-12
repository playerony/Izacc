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
public class SunShot extends Ability
{
    private float shots = 25.0f;
    
    public SunShot(int direction, float x, float y, float speed, float damage, EffectType effectType)
    {
        super(direction, x, y, speed, damage, effectType);
        
        init();
    }
    
    public SunShot(int direction, float x, float y, float speed, float damage, EffectType effectType, float shots)
    {
        super(direction, x, y, speed, damage, effectType);
        
        this.shots = shots;
        
        init();
    }

    @Override
    public void initSpeed()
    {
        float radius = 2.0f;
        float xPos = 0.0f;
        float yPos = 0.0f;
        float xV = 0.0f;
        float yV = 0.0f;
        
        for(int i=0 ; i<shots ; i++)
        {
            xPos = (float) (radius * Math.sin(2 * Math.PI * (i / shots))) + x;
            yPos = (float) (radius * -Math.cos(2 * Math.PI * (i / shots))) + y;
            xV = (float) (radius * Math.sin(2 * Math.PI * (i / shots)));
            yV = (float) (radius * -Math.cos(2 * Math.PI * (i / shots)));
            
            Bullet bullet = new Bullet(direction.ordinal(), xPos, yPos, attackSpeed, damage, effectType, xV, yV);
            bullets.add(bullet);
        }
        
        this.actived = true;
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public void render(float delta)
    {
        shapeRenderer.setColor(Color.LIME);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
}
