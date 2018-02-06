/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.carte.state;

import org.iut.carte.Carte;

/**
 *
 * @author Gwen
 */
public class EtatLancerSort extends Etat {

    public EtatLancerSort(Carte c) {
        super(c);
    }
    
    @Override
    public void defausserCarte() {
        carte_.changerEtatEnDefausse();
    }

    @Override
    public void afficherMessage() {
        
    }
    
}
