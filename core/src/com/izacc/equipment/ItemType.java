/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import java.io.Serializable;

/**
 *
 * @author pawel_000
 */
public enum ItemType implements Serializable{
    potion,
    stone,
    bag,
    spell;
    
    public String getStatus() {
        return this.name();
    }
}
