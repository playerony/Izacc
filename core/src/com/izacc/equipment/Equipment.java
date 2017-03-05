/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import com.izacc.equipment.generate.Creator;
import com.izacc.equipment.generate.ItemCreator;
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
    
    public Equipment()
    {
        init();
    }
    
    public void init()
    {
        bagpack = new ArrayList<Item>();
        itemCreator = new ItemCreator();
    }

    public void addItem(Item item)
    {
        if(item != null)
        {
            boolean isAdd = false;
            
            for(Item it : bagpack)
                if(item.name.equals(it.name) && it.packable)
                {
                    it.count++;
                    if(it.disable)
                        it.time += item.time;
                    
                    isAdd = true;
                    
                    break;
                }

            if(!isAdd)
                bagpack.add(item);
        }
    }

    public ArrayList<Item> getBagpack()
    {
        return bagpack;
    }
}
