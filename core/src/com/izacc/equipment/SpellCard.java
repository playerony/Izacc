/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import com.badlogic.gdx.utils.JsonValue;

/**
 *
 * @author pawel_000
 */
public class SpellCard extends Item
{
    int id;
    
    public SpellCard(JsonValue json) 
    {
        super(json);
        
        this.id = json.has("id") ? json.getInt("id") : 0;
    }
}
