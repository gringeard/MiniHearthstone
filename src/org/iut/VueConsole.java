/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut;

import org.iut.carte.Carte;
import org.iut.carte.state.EtatDors;
import org.iut.observer.Sujet;

/**
 *
 * @author Gwen
 */
public class VueConsole {

    public VueConsole() {
    }
    
    public void afficherChoixHero(int i) {
        System.out.println("============================================");
        System.out.println("Choix du héros");
        System.out.println("1 - Mage");
        System.out.println("2 - Guerrier");
        System.out.println("3 - Paladin");
        System.out.println("Joueur "+i+" : choisissez votre héros : ");
    }
    
    public void afficherJoueurCommence(String nom){
        System.out.println("\n============================================");
        System.out.println(nom+" commence");
        System.out.println("============================================");
    }
    
    public void afficherChoixAction(){
        System.out.println("\n============================================");
        System.out.println("Choix de l'action");
        System.out.println("1 - Jouer une carte");
        System.out.println("2 - Attaquer");
        System.out.println("3 - Utiliser l'action spéciale du héros");
        System.out.println("4 - Terminer son tour");
        System.out.println("============================================");
    }
     
    public void afficherChoixCarteAJouer(Sujet joueur){
        System.out.println("\n============================================");
        System.out.println("Choix de la carte à poser");
        int i = 1;
        for(Carte c : joueur.getCartesEnMain()){
            System.out.println(i + " - " + c.toString());
            i++;
        }
        System.out.println("-1 - retour");
        System.out.println("============================================");
    }
    
    public void afficherChoixCartePourAttaquer(Sujet joueur){
        System.out.println("\n============================================");
        System.out.println("Choix de la carte pour attaquer");
        int i = 1;
        for(Carte c : joueur.getCartesPosees()){
            System.out.print("\n"+i + " - " + c.toString());
            if(c.getEtatCourant_() instanceof  EtatDors){
                System.out.print(" dors!");
            }
            i++;
        }
        System.out.println("\n-1 - retour");
        System.out.println("============================================");
    }

    void infosCombat(Sujet joueur, Sujet adversaire) {
        System.out.println("\n============================================");
        System.out.println("Adversaire :");
        System.out.println(adversaire.getHero());
        System.out.println("cartes posées :" + adversaire.getCartesPosees());
        System.out.println("============================================");
        System.out.println("Vous :");
        System.out.println(joueur.getHero());
        System.out.println("cartes posées :" + joueur.getCartesPosees());
         System.out.println("============================================");
    }

    void afficherChoixCarteOuHeroAAttaquer(Sujet adversaire) {
        System.out.println("\n============================================");
        System.out.println("Choix de la carte ou du héros à attaquer");
        System.out.println("0 - " + adversaire.getHero());
        int i = 1;
        for(Carte c : adversaire.getCartesPosees()){
            System.out.println(i + " - " + c.toString());
            i++;
        }
        System.out.println("-1 - retour");
        System.out.println("============================================");
    }
}
