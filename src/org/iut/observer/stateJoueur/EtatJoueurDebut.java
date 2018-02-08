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
    public void debuterTour(){
        joueur_.changerEtatJoueurDebuterTour();
        int nb;
        if(joueur_.isFirst()){
            nb = 3;
        }else{
            nb = 4;
        }
        joueur_.changerEtatJoueurPiocher();
        for(int i=0; i<nb; i++){
            joueur_.piocherCarteAleatoirement();
        }
        joueur_.changerEtatJoueurJouer();
    };

    @Override
    public void afficherMessage() {

    }
    
}
