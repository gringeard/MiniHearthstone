/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer.stateJoueur;

import org.iut.observer.Sujet;

/**
 *
 * @author Gwen
 */
public abstract class EtatJoueur {
    protected Sujet joueur_;

    public EtatJoueur(Sujet j) {
        joueur_ = j;
    }
    
    //Transitions
    public void jouer(){};
    public void attaquer(){};
    public void jouerCarte(){};
    public void lancerActionSpeciale(){};
    public void finirTour(){};
    public void debuterTour() {};
    public void piocher(){};
    
    //Actions
    public abstract void afficherMessage();
}
