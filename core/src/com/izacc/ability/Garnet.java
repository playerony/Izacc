/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

/**
 *
 * @author pawel_000
 */
public class Garnet extends Ability
{
    private float timeState = 0.0f;
    private int power = 20;
    
    public Garnet(int direction, float x, float y)
    {
        super(direction, x, y);
        
        this.r = 30.0f;
    }
    
    public Garnet(int direction, float x, float y, float r)
    {
        super(direction, x, y);
        
        this.r = r;
    }
    
    public Garnet(int direction, float x, float y, float r, int power)
    {
        super(direction, x, y);
        
        this.r = r;
        this.power = power;
    }

    @Override
    public void initSpeed()
    {
        attackSpeed = 4.5f;
    }

    @Override
    public void update()
    {
        timeState+=Gdx.graphics.getDeltaTime();
        if(timeState>=2.0f && !actived){
            Random random = new Random();
            
            for(int i=0 ; i<10 ; i++){
                int xV = 0;
                int yV = 0;
                
                do{
                  xV = random.nextInt(power) - power / 2;
                  yV = random.nextInt(power) - power / 2;
                }while(xV == 0 || yV == 0);
                
                bullets.add(new Bullet(direction.ordinal(), x, y, xV, yV));
            }
            
            timeState = 0;
            actived = true;
        }
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
