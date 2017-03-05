/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment.generate;

import com.izacc.equipment.Item;
import com.izacc.utility.Entity;

/**
 *
 * @author pawel_000
 */
public class ItemDropped 
{
    private Entity entity = null;
    private Item item = null;
    
    public ItemDropped(Item item, float x, float y)
    {
        entity = new Entity(x, y);
        this.item = item;
    }

    public Entity getEntity()
    {
        return entity;
    }

    public Item getItem()
    {
        return item;
    }
    
    
}
