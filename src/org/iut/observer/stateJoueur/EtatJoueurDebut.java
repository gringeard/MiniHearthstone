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
public class EtatJoueurDebut extends EtatJoueur {

    public EtatJoueurDebut(Sujet j) {
        super(j);
    }

    @Override
    public void debuterTour(int tour){
        //Le joueur récupère autant de mana que le numéro du tour, nous sommes au premier tour donc 1
        joueur_.setMana(tour);
        joueur_.changerEtatJoueurDebuterTour();
        int nb;
        //Si le joueur a commencé, il ne piochera que 3 cartes au premier tour
        if(joueur_.isFirst()){
            nb = 3;
        //Sinon 4
        }else{
            nb = 4;
        }
        joueur_.changerEtatJoueurPiocher();
        //Le joueur pioche ses 3 ou 4 cartes
        for(int i=0; i<nb; i++){
            joueur_.piocherCarteAleatoirement();
        }
        joueur_.changerEtatJoueurJouer();
    };

    @Override
    public void afficherMessage() {

    }
    
}
