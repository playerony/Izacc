/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import com.badlogic.gdx.utils.JsonValue;
import java.util.ArrayList;
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
    
    private boolean isDrop()
    {
        int value = random.nextInt(9) + 1;
        
        if(value <= 4)
            return false;
        
        return true;
    }
    
    public Item createHealPotion(int mobRank)
    {
        int rank = mobRank - 1;
        Item item = null;
        
        if(isDrop())
        {
            if(rank >= 0 && rank < 2)
            {
                jsonValue = itemLoader.getHealPotions().get(rank);
            }
            else
            {
                int value = random.nextInt(9) + 1;

                if(value <= 6)
                    jsonValue = itemLoader.getHealPotions().get(2);
                else
                    jsonValue = itemLoader.getHealPotions().get(3);
            }

            item = new Item(jsonValue);
        }
        
        return item;
    }
    
    public Item createBag(int mobRank)
    {
        int rank = mobRank - 1;
        Item item = null;
        
        if(isDrop())
        {
            if(rank >= 0 && rank < 2)
            {
                jsonValue = itemLoader.getBags().get(rank);
            }
            else
            {
                int value = random.nextInt(9) + 1;

                if(value >= 6)
                    jsonValue = itemLoader.getBags().get(2);
            }

            item = new Item(jsonValue);
        }
        
        return item;
    }
    
    public Item createMoveSpeedPotion(int mobRank)
    {
        int rank = mobRank - 1;
        Item item = null;
        
        if(isDrop())
        {
            if(rank >= 0 && rank < 2)
            {
                jsonValue = itemLoader.getMoveSpeedPotions().get(rank);
            }
            else
            {
                int value = random.nextInt(9) + 1;

                if(value >= 6)
                    jsonValue = itemLoader.getMoveSpeedPotions().get(2);
            }

            item = new Item(jsonValue);
        }
        
        return item;
    }
    
    public Item createShield(int mobRank)
    {
        int rank = mobRank - 1;
        Item item = null;
        
        if(isDrop())
        {
            if(rank >= 0 && rank < 2)
            {
                jsonValue = itemLoader.getShields().get(rank);
            }
            else
            {
                int value = random.nextInt(9) + 1;

                if(value >= 6)
                    jsonValue = itemLoader.getShields().get(2);
            }

            item = new Item(jsonValue);
        }
        
        return item;
    }
    
    public Item createRandomItem(int mobRank){
        Item item = null;
        
        if(item == null)
            item = createHealPotion(mobRank);
        
        if(item == null)
            item = createShield(mobRank);
        
        if(item == null)
            item = createBag(mobRank);
        
        return item;
    }
    
    public ArrayList<Item> createPackOfItems(int mobRank){
        ArrayList<Item> result = new ArrayList<Item>();
        
        for(int i=0 ; i<2 ; i++){
            Item item = null;
            
            item = createHealPotion(mobRank);
            if(item != null) result.add(item);
            
            item = createShield(mobRank);
            if(item != null) result.add(item);
            
            item = createBag(mobRank);
            if(item != null) result.add(item);
        }
        
        return result;
    }
}
