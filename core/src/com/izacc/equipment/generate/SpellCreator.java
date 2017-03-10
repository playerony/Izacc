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
            switch(rank)
            {
                case 0:
                    jsonValue = itemLoader.getSpells().get(random.nextInt(3));
                    break;
                    
                case 1:
                    jsonValue = itemLoader.getSpells().get(random.nextInt(3) + 3);
                    break;
                    
                default:
                    jsonValue = itemLoader.getSpells().get(random.nextInt(3) + 6);
                    break;
            }
            
            if(jsonValue != null)
                spellCard = new SpellCard(jsonValue);
        }
        
        return spellCard;
    }
}
