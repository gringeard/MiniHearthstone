/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.carte.decorator;

import org.iut.carte.Carte;

/**
 *
 * @author Gwen
 */
public class Decorator extends Carte {
    
    protected Carte noyau;

    public Decorator(Carte carte) {
        super(carte.getMana());
        noyau = carte;
    }
    
    
}
