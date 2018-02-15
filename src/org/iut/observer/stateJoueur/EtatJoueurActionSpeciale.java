/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer.stateJoueur;

import org.iut.cible.Cible;
import org.iut.observer.Sujet;

/**
 *
 * @author Zebraska
 */
public class EtatJoueurActionSpeciale extends EtatJoueur{

    public EtatJoueurActionSpeciale(Sujet j) {
        super(j);
    }
    
    @Override
    public void lancerActionSpeciale(Cible cible){
        joueur_.getHero().getActionSpeciale().executer(joueur_,cible);
    };

    @Override
    public void afficherMessage() {
        System.out.println("\n============================================");
        System.out.println(joueur_.getNom() + " lance "+joueur_.getHero().getActionSpeciale().getNom());
        System.out.println("============================================");
        System.out.println(joueur_.getHero().getActionSpeciale().getDescription());
    }
    
}
