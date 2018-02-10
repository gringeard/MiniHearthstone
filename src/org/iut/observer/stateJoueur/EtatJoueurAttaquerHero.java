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
public class EtatJoueurAttaquerHero extends EtatJoueur {

    public EtatJoueurAttaquerHero(Sujet j) {
        super(j);
    }

    @Override
    public void afficherMessage() {
        System.out.println("\n============================================");
        System.out.println(joueur_.getNom() + " attaque le héros");
        System.out.println("============================================");
    }
    
}
