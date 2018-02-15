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
public class EtatJoueurEnAttente extends EtatJoueur {

    public EtatJoueurEnAttente(Sujet j) {
        super(j);
    }

    @Override
    public void debuterTour(int tour){
        //Si le nombre de tour est inférieur à 10, le joueur aura autant de mana de que tour
        if(tour < 10){
            joueur_.setMana(tour);
        //Sinon on ne dépasse pas 10 mana au début du tour
        }else{
            joueur_.setMana(10);
        }
        joueur_.changerEtatJoueurDebuterTour();
        joueur_.changerEtatJoueurPiocher();
        joueur_.piocherCarteAleatoirement();
        //Toutes les cartes qui dormaient à la fin du tour peuvent attaquer au nouveau 
        for(Carte c : joueur_.getCartesPosees()){
            ((CarteServiteur) c).changerEtatPretAAttaquer();
        }
        joueur_.changerEtatJoueurJouer();
    };

    @Override
    public void defendreHero(Carte c) {
        //Lorsque le héros subit des dégats
        joueur_.changerEtatJoueurDefendreHero();
        int degats = 0;
        //On récupère son armure et ses pv
        int armure = joueur_.getHero().getArmure_();
        int pdv = joueur_.getHero().getPdv_();
        //On récupère les dégats de la carte assaillante
        if(c instanceof CarteServiteur){
            degats = ((CarteServiteur) c).getDegat();
        }
        //Si le héros a de l'armure
        if(armure > 0){
            //Et que les dégats sont supérieurs
            if(degats >= armure){
                //Les dégats perdent autant de points que l'armure avait
                degats -= armure;
                //L'armure passe à 0
                joueur_.getHero().setArmure_(0);
            //Sinon
            }else{
                //L'armure chute d'autant de points qu'il y avait de dégats
                joueur_.getHero().setArmure_(armure - degats);
                //Les dégats passent à 0
                degats = 0;
            }
        }
        //Enfin, les points de vie du héros décendent d'autant de point qu'il restait de dégats
        joueur_.getHero().setPdv_(pdv - degats);
        joueur_.changerEtatJoueurAttendreTour();
        
    }
    
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
            //Sinon la carte est détruite et part en défausse
            }else{
                ((CarteServiteur)joueur_.getCartesPosees().get(indexCJoueur)).changerEtatEnDefausse();
                joueur_.getCartesPosees().remove(indexCJoueur);
                System.out.println("\n============================================");
                System.out.println("carte de " + joueur_.getNom() + " détruite");
                System.out.println("============================================");
            }
            
        }
        joueur_.changerEtatJoueurAttendreTour();
    }
    
    @Override
    public void afficherMessage() {

    }
    
}
