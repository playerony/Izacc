/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment.generate;

import com.izacc.equipment.Item;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pawel_000
 */
public class ItemCreator
{
    private PotionCreator potionCreator;
    private StoneCreator stoneCreator;
    private BagCreator bagCreator;
    private SpellCreator spellCreator;
    private Random random;
    
    public ItemCreator()
    {
        potionCreator = new PotionCreator();
        stoneCreator = new StoneCreator();
        bagCreator = new BagCreator();
        spellCreator = new SpellCreator();
        
        random = new Random();
    }
    
    public Item createRandomItem(int mobRank)
    {
        Item item = null;
        int rand = random.nextInt(7);
        
        switch(rand)
        {
            case 0:
                item = potionCreator.createDamagePotion(mobRank);
                break;
                
            case 1:
                item = potionCreator.createHealPotion(mobRank);
                break;
                    
            case 2:
                item = stoneCreator.createShieldStone(mobRank);
                break;
                
            case 3:
                item = potionCreator.createMoveSpeedPotion(mobRank);
                break;
                
            case 4:
                item = potionCreator.createAttackSpeedPotion(mobRank);
                break;
                
            case 5:
                item = spellCreator.createSpell(mobRank);
                break;
                    
            default:
                item = bagCreator.createBag(mobRank);
                break;
            }
        
        return item;
    }
    
    public ArrayList<Item> createPackOfItems(int mobRank)
    {
        ArrayList<Item> result = new ArrayList<Item>();
        
        for(int i=0 ; i<2 ; i++)
        {
            Item item = null;
            
            item = potionCreator.createHealPotion(mobRank);
            if(item != null) result.add(item);
            
            item = stoneCreator.createShieldStone(mobRank);
            if(item != null) result.add(item);
            
            item = bagCreator.createBag(mobRank);
            if(item != null) result.add(item);
        }
        
        return result;
    }
}
