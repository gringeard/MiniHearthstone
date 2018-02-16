/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.carte.decorator.serviteur;

import org.iut.carte.CarteServiteur;

/**
 *
 * @author Gwen
 */
public class Provocation extends Decorator {
    
    public Provocation(CarteServiteur carte) {
        super(carte);
    }
    
    @Override
    public String toString() {
        return noyau.toString() + " provocation";
    }
    
}
