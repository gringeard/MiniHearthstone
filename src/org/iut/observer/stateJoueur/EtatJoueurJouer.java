/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer.stateJoueur;

import org.iut.carte.Carte;
import org.iut.carte.CarteServiteur;
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
    public void jouerCarte(int index){
        if(joueur_.getMana() >= joueur_.getCartesEnMain().get(index).getMana()){
            joueur_.changerEtatJoueurJouerCarte();
            joueur_.setMana(joueur_.getMana() - joueur_.getCartesEnMain().get(index).getMana());
            joueur_.poserCarte(index);
            joueur_.changerEtatJoueurJouer();            
        }else{
            System.out.println("\n============================================");
            System.out.println("Vous n'avez pas assez de mana");
            System.out.println("============================================");
        }
    };

    @Override
    public void affronterCarte(int indexCJoueur, Carte cAdversaire) {
        joueur_.changerEtatJoueurAffronterCarte();
        Carte cJoueur = joueur_.getCartesPosees().get(indexCJoueur);
        if(cJoueur instanceof CarteServiteur){
            int pdvCJoueur = ((CarteServiteur) cJoueur).getPv();
            int degatsCAdversaire = ((CarteServiteur) cAdversaire).getDegat();
            if(pdvCJoueur > degatsCAdversaire){
                ((CarteServiteur)joueur_.getCartesPosees().get(indexCJoueur)).setPv(pdvCJoueur - degatsCAdversaire);
                ((CarteServiteur)joueur_.getCartesPosees().get(indexCJoueur)).dors();
            }else{
                ((CarteServiteur)joueur_.getCartesPosees().get(indexCJoueur)).changerEtatEnDefausse();
                joueur_.getCartesPosees().remove(indexCJoueur);
                System.out.println("\n============================================");
                System.out.println("carte de " + joueur_.getNom() + " détruite");
                System.out.println("============================================");
            }
            
        }
        joueur_.changerEtatJoueurJouer();
    }

    @Override
    public void attaquerHero(int indexCartePourAttaquer) {
        joueur_.changerEtatJoueurAttaquerHero();
        Carte cJoueur = joueur_.getCartesPosees().get(indexCartePourAttaquer);
        if(cJoueur instanceof CarteServiteur){
            ((CarteServiteur)joueur_.getCartesPosees().get(indexCartePourAttaquer)).dors();
        }
    }
    
    
    
    @Override
    public void finirTour(){
        joueur_.changerEtatJoueurFinirTour();
    };

    @Override
    public void afficherMessage() {

    }
    
}
