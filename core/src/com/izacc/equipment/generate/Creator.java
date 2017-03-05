/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment.generate;

import com.izacc.equipment.generate.ItemLoader;
import com.badlogic.gdx.utils.JsonValue;
import com.izacc.equipment.Item;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pawel_000
 */
public abstract class Creator 
{
    protected ItemLoader itemLoader;
    protected JsonValue jsonValue;
    protected Random random;
    
    public Creator()
    {
        init();
    }
    
    public void init()
    {
        itemLoader = new ItemLoader();
        random = new Random();
    }
    
    protected boolean isDrop()
    {
        int value = random.nextInt(9) + 1;
        
        if(value <= 2)
            return false;
        
        return true;
    }
}
