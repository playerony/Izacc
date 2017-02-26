/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.ability.mage;

import com.izacc.ability.Ability;

/**
 *
 * @author pawel_000
 */
public class BreathOfDeath extends Ability{

    public BreathOfDeath(int direction, float x, float y) {
        super(direction, x, y);
    }
    
    @Override
    public void attack() {
        actived = true;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initSpeed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
