/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment.generate;

import com.izacc.equipment.SpellCard;

/**
 *
 * @author pawel_000
 */
public class SpellCreator extends Creator
{
    public SpellCard createSpell(int mobRank)
    {
        int rank = mobRank - 1;
        SpellCard spellCard = null;
        
        if(isDrop())
        {
            if(rank >= 0 && rank < 1 && isDrop())
            {
                jsonValue = itemLoader.getSpells().get(rank);
            }
            else
            {
                int value = random.nextInt(9) + 1;

                if(value <= 6)
                    jsonValue = itemLoader.getSpells().get(1);
            }
            
            if(jsonValue != null)
                spellCard = new SpellCard(jsonValue);
        }
        
        return spellCard;
    }
}
