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
public class Equipment {
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
        
        Random r = new Random();
        
        for(int i=0;  i<100 ; i++){
            addItem(itemCreator.createHealPotion(1));
            addItem(itemCreator.createHealPotion(2));
            addItem(itemCreator.createHealPotion(3));
        }
        
        for(Item it : bagpack)
        {
            System.out.println(it.name + " " + it.count + " " + it.effectType.toString());
        }
    }

    public void addItem(Item item){
        if(item != null)
        {
            boolean isAdd = false;
            for(Item it : bagpack)
                if(item.name.equals(it.name) && it.packable)
                {
                    it.count++;
                    isAdd = true;
                }

            if(!isAdd)
                bagpack.add(item);
        }
    }
    
    public void addPotion(){
        
    }
}
