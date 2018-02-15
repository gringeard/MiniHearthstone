/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer.stateJoueur;

import org.iut.carte.Carte;
import org.iut.cible.Cible;
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
    public void attaquerHero(int indexCartePourAttaquer){};
    public void defendreHero(Carte c){};
    public void affronterCarte(int indexCJoueur, Carte cAdversaire){};
    public void jouerCarte(int index){};
    public void lancerActionSpeciale(Cible cible){};
    public void finirTour(){};
    public void attendreTour(){};
    public void debuterTour(int tour) {};
    public void piocher(){};
    
    //Actions
    public abstract void afficherMessage();
}
