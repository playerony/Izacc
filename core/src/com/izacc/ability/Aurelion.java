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
public class Aurelion extends Ability
{
    private float elements = 4.0f;
    
    public Aurelion(int direction, float x, float y, float speed, float damage, EffectType effectType)
    {
        super(direction, x, y, speed, damage, effectType);
        
        init();
        
        this.isRotate = true;
    }
    
    public Aurelion(int direction, float x, float y, float speed, float damage, EffectType effectType, float elements)
    {
        super(direction, x, y, speed, damage, effectType);
        
        init();
        
        this.isRotate = true;
        this.elements = elements;
    }

    @Override
    public void initSpeed()
    {
        float radius = 10.0f;
        float xPos = 0.0f;
        float yPos = 0.0f;
        float xV = 0.0f;
        float yV = 0.0f;
        
        for(int i=0 ; i<elements ; i++)
        {
            xPos = (float) (radius * Math.sin(2 * Math.PI * (i / elements))) + x;
            yPos = (float) (radius * -Math.cos(2 * Math.PI * (i / elements))) + y;
            xV = (float) (radius * Math.sin(2 * Math.PI * (i / elements)));
            yV = (float) (radius * -Math.cos(2 * Math.PI * (i / elements)));
            
            Bullet bullet = new Bullet(direction.ordinal(), xPos, yPos, attackSpeed, damage, effectType, xV, yV);
            bullets.add(bullet);
        }
        
        actived = true;
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public void render(float delta)
    {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
    }
    
}
