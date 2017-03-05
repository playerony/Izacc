/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment.generate;

import com.izacc.equipment.Item;

/**
 *
 * @author pawel_000
 */
public class PotionCreator extends Creator
{
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
    
    public Item createDamagePotion(int mobRank)
    {
        int rank = mobRank - 1;
        Item item = null;
        
        if(isDrop())
        {
            if(rank >= 0 && rank < 2)
            {
                jsonValue = itemLoader.getDamagePotions().get(rank);
            }
            else
            {
                int value = random.nextInt(9) + 1;

                if(value >= 6)
                    jsonValue = itemLoader.getDamagePotions().get(2);
            }

            item = new Item(jsonValue);
        }
        
        return item;
    }
    
    public Item createAttackSpeedPotion(int mobRank)
    {
        int rank = mobRank - 1;
        Item item = null;
        
        if(isDrop())
        {
            if(rank >= 0 && rank < 2)
            {
                jsonValue = itemLoader.getAttackSpeedPotions().get(rank);
            }
            else
            {
                int value = random.nextInt(9) + 1;

                if(value >= 6)
                    jsonValue = itemLoader.getAttackSpeedPotions().get(2);
            }

            item = new Item(jsonValue);
        }
        
        return item;
    }
}
