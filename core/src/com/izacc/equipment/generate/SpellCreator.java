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
        return new SpellCard(itemLoader.getSpells().get(0));
    }
}
