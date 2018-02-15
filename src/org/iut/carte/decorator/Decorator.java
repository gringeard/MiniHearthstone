/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.carte.decorator;

import org.iut.carte.CarteServiteur;

/**
 *
 * @author Gwen
 */
public abstract class Decorator extends CarteServiteur {
    
    protected CarteServiteur noyau;

    public Decorator(CarteServiteur carte) {
        super(carte.getNom_(), carte.getMana(), carte.getDegat(), carte.getPdv_());
        noyau = carte;
    }
    
    
}
