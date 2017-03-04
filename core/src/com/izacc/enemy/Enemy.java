/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.izacc.utility.Entity;

/**
 *
 * @author pawel_000
 */
public class Enemy extends Entity
{
    private int mobRank;
    private float col;
    
    public Enemy(int mobRank, float x, float y)
    {
        super(x, y);
        col = (mobRank * 80.0f) / 255.0f;
        
        this.mobRank = mobRank;
    }

    public int getMobRank()
    {
        return mobRank;
    }
    
    public void render(float delta) 
    {
        shapeRenderer.setColor(new Color(col, col, col, 1.0f));
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, 20);
        shapeRenderer.end();
    }
}
