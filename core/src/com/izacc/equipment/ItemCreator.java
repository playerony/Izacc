/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import com.badlogic.gdx.utils.JsonValue;
import java.util.Random;

/**
 *
 * @author pawel_000
 */
public class ItemCreator 
{
    private ItemLoader itemLoader;
    private JsonValue jsonValue;
    private Random random;
    
    public ItemCreator()
    {
        init();
    }
    
    public void init()
    {
        itemLoader = new ItemLoader();
        random = new Random();
    }
    
    private boolean isDrop(){
        int value = random.nextInt(9) + 1;
        
        if(value <= 8)
            return false;
        
        return true;
    }
    
    public Item createHealPotion(int mobRank)
    {
        int rank = mobRank - 1;
        Item item = null;
        
        if(isDrop()){
            if(rank >= 0 && rank < 2)
            {
                jsonValue = itemLoader.getHealPotions().get(rank);
            }
            else
            {
                int value = random.nextInt(9) + 1;

                if(value <= 7)
                    jsonValue = itemLoader.getHealPotions().get(2);
                else
                    jsonValue = itemLoader.getHealPotions().get(3);
            }

            item = new Item(jsonValue);
        }
        
        return item;
    }
    
    public Item createSpeedPotion()
    {
        JsonValue jsonValue = itemLoader.getSpeedPotions().get(1);
        Item item = new Item(jsonValue);
        
        return item;
    }
}
