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
    public int id;
    
    public float damage;
    public float speed;
    
    public SpellType spellType;
    
    public SpellCard(JsonValue json) 
    {
        super(json);
        
        this.id = json.has("id") ? json.getInt("id") : 0;
        this.speed = json.has("speed") ? json.getFloat("speed") : 0.0f;
        this.damage = json.has("damage") ? json.getFloat("damage") : 0.0f;
        this.spellType = SpellType.valueOf(json.getString("spellType"));
    }
}
