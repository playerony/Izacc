/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pawel_000
 */
public class Equipment 
{
    private ItemCreator itemCreator;
    private ArrayList<Item> bagpack; 
    private static final int capacity = 25;
    
    public Equipment()
    {
        init();
    }
    
    public void init()
    {
        bagpack = new ArrayList<Item>(capacity);
        itemCreator = new ItemCreator();
        
        for(int i=0 ; i<capacity ; i++)
            bagpack.add(null);
        
        Random r = new Random();
    }

    public void addItem(Item item)
    {
        if(item != null)
        {
            boolean isAdd = false;
            
            for(Item it : bagpack)
                if(it != null && item.name.equals(it.name) && it.packable)
                {
                    it.count++;
                    isAdd = true;
                    
                    break;
                }

            if(!isAdd)
                for(int i=0 ; i<capacity ; i++)
                {
                    if(bagpack.get(i) == null)
                    {
                        bagpack.set(i, item);
                        break;
                    }
                }
        }
    }

    public ArrayList<Item> getBagpack()
    {
        return bagpack;
    }
    
    public void addPotion()
    {
        
    }
}
