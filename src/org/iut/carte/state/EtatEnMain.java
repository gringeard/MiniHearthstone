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
        
        if(carte_ instanceof CarteServiteur){
            ((CarteServiteur) carte_).changerEtatDors();
        }else if(carte_ instanceof Charge){
                ((CarteServiteur) carte_).changerEtatPretAAttaquer();
        }else if(carte_ instanceof CarteSort){
                ((CarteSort) carte_).changerEtatLancerSort();
        }
    }

    @Override
    public void afficherMessage() {
        
    }
    
}
