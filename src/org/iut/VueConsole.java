/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut;

/**
 *
 * @author Gwen
 */
public class VueConsole {

    public VueConsole() {
    }
    
    public static void afficherChoixHero(int i) {
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
}
