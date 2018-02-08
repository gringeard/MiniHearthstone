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
public class EtatJoueurJouer extends EtatJoueur {

    public EtatJoueurJouer(Sujet j) {
        super(j);
    }

    @Override
    public void piocher(){
        joueur_.changerEtatJoueurPiocher();
        joueur_.piocherCarteAleatoirement();
        joueur_.changerEtatJoueurJouer();
    };
    
    @Override
    public void finirTour(){
        joueur_.changerEtatJoueurFinirTour();
    };

    @Override
    public void afficherMessage() {

    }
    
}
