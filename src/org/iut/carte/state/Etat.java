/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.carte.state;

import org.iut.carte.Carte;

/**
 *
 * @author Gwen
 */
public abstract class Etat {
    
    protected Carte carte_;

    public Etat(Carte c) {
        carte_ = c;
    }
    
    //Transitions
    public void piocherCarte(){};
    public void jouerCarte(){};
    public void dors(){};
    public void pretAAttaquer() {};
    public void lancerSort() {};
    public void defausserCarte(){};
    
    //Actions
    public abstract void afficherMessage();
}
