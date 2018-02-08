/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer.stateJoueur;

import org.iut.observer.Joueur;

/**
 *
 * @author Gwen
 */
public abstract class EtatJoueur {
    protected Joueur joueur_;

    public EtatJoueur(Joueur j) {
        joueur_ = j;
    }
    
    //Transitions
    public void piocherCarte(){};
    public void jouer(){};
    public void finirTour(){};
    public void debuterTour() {};
    
    
    //Actions
    public abstract void afficherMessage();
}
