/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.carte.decorator.sort;

import org.iut.carte.decorator.sort.*;
import org.iut.carte.CarteSort;

/**
 *
 * @author Gwen
 */
public abstract class Decorator extends CarteSort {
    
    protected CarteSort noyau;

    public Decorator(CarteSort carte) {
        super(carte.getNom_(), carte.getMana());
        noyau = carte;
    }
    
    
}
