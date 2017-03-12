/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability;

import com.izacc.equipment.EffectType;

/**
 *
 * @author pawel_000
 */
public class HellFire extends Ability
{

    public HellFire(int direction, float x, float y, float speed, float damage, EffectType effectType)
    {
        super(direction, x, y, speed, damage, effectType);
    }
 
     @Override
     public void update() {
          super.update();
     }
 
     @Override
     public void initSpeed() {
         float value = 8.0f;
         float r = 2.0f;
         float xPos = 0.0f;
         float yPos = 0.0f;
         float xV = 0.0f;
         float yV = 0.0f;
         
         for(int i=0 ; i<value ; i++){
             xPos = (float) (r * Math.sin(2 * Math.PI * (i / value))) + x;
             yPos = (float) (r * -Math.cos(2 * Math.PI * (i / value))) + y;
             xV = (float) (r * Math.sin(2 * Math.PI * (i / value)));
             yV = (float) (r * -Math.cos(2 * Math.PI * (i / value)));
             
             FireBall fireBall = new FireBall(direction.ordinal(), xPos, yPos, attackSpeed, damage, effectType, xV, yV);
             bullets.add(fireBall);
         }
     }
 
     @Override
     public void render(float delta) {
         
     }
}
