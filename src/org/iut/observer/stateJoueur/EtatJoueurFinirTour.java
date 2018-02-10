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
        for(Carte c : joueur_.getCartesPosees()){
            ((CarteServiteur) c).changerEtatPretAAttaquer();
        }
        joueur_.changerEtatJoueurJouer();
    };

    @Override
    public void defendreHero(Carte c) {
        joueur_.changerEtatJoueurDefendreHero();
        int degats = 0;
        int armure = joueur_.getHero().getArmure_();
        int pdv = joueur_.getHero().getPdv_();
        if(c instanceof CarteServiteur){
            degats = ((CarteServiteur) c).getDegat();
        }
        if(armure > 0){
            if(degats >= armure){
                joueur_.getHero().setArmure_(0);
                degats -= armure;
            }else{
                joueur_.getHero().setArmure_(armure - degats);
                degats = 0;
            }
        }
        joueur_.getHero().setPdv_(pdv - degats);
        joueur_.changerEtatJoueurFinirTour();
        
    }
    
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
        joueur_.changerEtatJoueurFinirTour();
    }
    
    

    @Override
    public void afficherMessage() {
        System.out.println("\n============================================");
        System.out.println(joueur_.getNom() + " termine son tour");
        System.out.println("============================================");
    }
    
}
