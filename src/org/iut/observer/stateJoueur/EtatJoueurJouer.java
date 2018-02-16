/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer.stateJoueur;

import org.iut.carte.Carte;
import org.iut.carte.CarteServiteur;
import org.iut.carte.decorator.serviteur.VolDeVie;
import org.iut.cible.Cible;
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
        //On vérifie que le joueur a assez de mana
        if(joueur_.getMana() >= joueur_.getCartesEnMain().get(index).getMana()){
            joueur_.changerEtatJoueurJouerCarte();
            //On retire alors au joueur le nombre de points de mana que vaut la carte
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
            //On récupère les pv de la carte du joueur et les dégats de la carte adverse
            int pdvCJoueur = ((CarteServiteur) cJoueur).getPdv_();
            int degatsCAdversaire = ((CarteServiteur) cAdversaire).getDegat();
            //Si la carte a plus de pv que les degats infligés
            if(pdvCJoueur > degatsCAdversaire){
                //On lui retire autant de pv que de dégats
                ((CarteServiteur)joueur_.getCartesPosees().get(indexCJoueur)).setPv(pdvCJoueur - degatsCAdversaire);
                if(cJoueur instanceof VolDeVie){
                    ((VolDeVie) cJoueur).getHeros().soigner(degatsCAdversaire);
                }
                //La carte s'endors pour le tour
                ((CarteServiteur)joueur_.getCartesPosees().get(indexCJoueur)).dors();
            //Sinon la carte est détruite et part en défausse
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
            //Le héros n'ayant pas de points de dégats, la carte s'endors juste
            ((CarteServiteur)joueur_.getCartesPosees().get(indexCartePourAttaquer)).dors();
        }
        joueur_.changerEtatJoueurJouer();
    }
    
    @Override
    public void lancerActionSpeciale(Cible cible){
        //On vérifie que le joueur a assez de mana
        if(joueur_.getMana() >= 2){
            joueur_.changerEtatJoueurActionSpeciale();
            //On retire le cout en mana d'une action spéciale (2)
            joueur_.setMana(joueur_.getMana() - 2);
            joueur_.lancerActionSpeciale(cible);
            joueur_.changerEtatJoueurJouer();            
        }else{
            System.out.println("\n============================================");
            System.out.println("Vous n'avez pas assez de mana");
            System.out.println("============================================");
        }
    };

    @Override
    public void finirTour(){
        joueur_.changerEtatJoueurFinirTour();
        joueur_.changerEtatJoueurAttendreTour();
    };

    @Override
    public void afficherMessage() {

    }
    
}
