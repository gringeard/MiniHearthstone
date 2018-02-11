/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.carte.state;

import org.iut.carte.Carte;
import org.iut.carte.CarteServiteur;
import org.iut.carte.CarteSort;
import org.iut.carte.decorator.Charge;

/**
 *
 * @author Gwen
 */
public class EtatEnMain extends Etat {

    public EtatEnMain(Carte c) {
        super(c);
    }

    @Override
    public void jouerCarte() {
        carte_.changerEtatJouee();
        
        //Si la carte est un serviteur
        if(carte_ instanceof CarteServiteur){
            //Et qu'elle est décorée de charge
            if(carte_ instanceof Charge){
                //une fois posée elle est directement prête à attaquer
                ((CarteServiteur) carte_).changerEtatPretAAttaquer();
            //Sinon
            }else{
                //Elle commence par dormir
                ((CarteServiteur) carte_).changerEtatDors();
            }
        //Si c'est un sort
        }else if(carte_ instanceof CarteSort){
            //On lance le sort
            ((CarteSort) carte_).changerEtatLancerSort();
            //exécuter l'effet
            //Et on la défausse
            ((CarteSort) carte_).changerEtatEnDefausse();
        }
    }

    @Override
    public void afficherMessage() {
        
    }
    
}
