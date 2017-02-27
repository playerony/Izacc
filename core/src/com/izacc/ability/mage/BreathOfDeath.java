/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability.mage;

import com.izacc.ability.Ability;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public class BreathOfDeath extends Ability{
    private ArrayList<IceBall> iceBalls;

    public BreathOfDeath(int direction, float x, float y) {
        super(direction, x, y);
    }
    
    @Override
    public void attack() {
        actived = true;
    }

    @Override
    public void update() {
         for(IceBall i : iceBalls){
            i.update();
        }
    }

    @Override
    public void initSpeed() {
        iceBalls = new ArrayList<IceBall>();
        
        float value = 20.0f;
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
            
            IceBall iceBall = new IceBall(direction.ordinal(), xPos, yPos, xV, yV);
            iceBalls.add(iceBall);
        }
    }

    @Override
    public void render(float delta) {
        for(IceBall i : iceBalls){
            i.render(delta);
        }
    }
    
}
