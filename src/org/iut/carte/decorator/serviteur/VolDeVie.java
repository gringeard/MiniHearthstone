/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.carte.decorator.serviteur;

import org.iut.carte.CarteServiteur;
import org.iut.hero.Hero;

/**
 *
 * @author Gwen
 */
public class VolDeVie extends Decorator {
    
    private Hero heros;
    
    public VolDeVie(CarteServiteur carte) {
        super(carte);
    }
    
    @Override
    public String toString() {
        return noyau.toString() + " vol-de-vie";
    }

    public Hero getHeros() {
        return heros;
    }

    public void setHeros(Hero heros) {
        this.heros = heros;
    }
}
