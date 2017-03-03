/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import com.badlogic.gdx.utils.JsonValue;
import com.izacc.utility.Path;
import java.io.Serializable;

/**
 *
 * @author pawel_000
 */
public class Item implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public String name;
    public String file;
    public String description;
    
    public int count = 1;
    
    public boolean disable;
    public boolean packable;
    public boolean isPermanent;
    
    public float bonus;
    public int time;
    public int buy;
    public int sell;
    
    public ItemType itemType;
    public EffectType effectType;
    
    public Item(JsonValue json)
    {
        this.file = json.getString("file");
        this.name = json.getString("name");
        this.description = json.getString("description");
        
        this.disable = json.has("disable") ? json.getBoolean("disable") : false;
        this.packable = json.has("packable") ? json.getBoolean("packable") : true;
        this.isPermanent = json.has("isPermanent") ? json.getBoolean("isPermanent") : true;
        
        this.bonus = json.has("bonus") ? json.getInt("bonus") : 0.0f;
        this.time = json.has("time") ? json.getInt("time") : 0;
        this.buy = json.has("buy") ? json.getInt("buy") : 0;
        this.sell = json.has("sell") ? json.getInt("sell") : 0;
        
         this.itemType = ItemType.valueOf(json.getString("itemType"));
         this.effectType = EffectType.valueOf(json.getString("type"));
    }
    
    public String getIcon(){
        return Path.IMAGE_PATH + file;
    }
}
