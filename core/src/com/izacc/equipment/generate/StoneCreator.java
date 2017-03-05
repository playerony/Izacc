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
public class StoneCreator extends Creator
{
    public Item createShieldStone(int mobRank)
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
}
