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
public class EtatJoueurFinirTour extends EtatJoueur {

    public EtatJoueurFinirTour(Sujet j) {
        super(j);
    }

    @Override
    public void debuterTour(int tour){
        if(tour < 10){
            joueur_.setMana(tour);
        }else{
            joueur_.setMana(10);
        }
        joueur_.changerEtatJoueurDebuterTour();
        joueur_.changerEtatJoueurPiocher();
        joueur_.piocherCarteAleatoirement();
        joueur_.changerEtatJoueurJouer();
    };

    @Override
    public void afficherMessage() {
        System.out.println("\n============================================");
        System.out.println(joueur_.getNom() + " termine son tour");
        System.out.println("============================================");
    }
    
}
