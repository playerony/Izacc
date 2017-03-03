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
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public String name;
    public String type;
    public String file;
    public String description;
    
    public int id;
    public int count = 1;
    
    public boolean disable;
    public boolean packable;
    public boolean isPermanent;
    
    public int bonus;
    public int buy;
    public int sell;
    
    public Item(JsonValue json){
        this.disable = json.has("disable") ? json.getBoolean("disable") : false;
        this.description = json.getString("description");
        this.name = json.getString("name");
        this.type = json.getString("type");
        this.packable = json.has("packable") ? json.getBoolean("packable") : true;
        this.buy = json.has("buy") ? json.getInt("buy") : 0;
        this.sell = json.has("sell") ? json.getInt("sell") : 0;
    }
    
    public String getIcon(){
        return Path.IMAGE_PATH + file;
    }
}
