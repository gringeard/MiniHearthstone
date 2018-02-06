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
    
    public void afficherChoixHero(int i) {
        System.out.println("============================================");
        System.out.println("Choix du héros");
        System.out.println("1 - Mage");
        System.out.println("2 - Guerrier");
        System.out.println("3 - Paladin");
        System.out.println("Joueur "+i+" : choisissez votre héros : ");
    }
    
}
