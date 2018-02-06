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
public class EtatEnPile extends Etat {

    public EtatEnPile(Carte c) {
        super(c);
    }

    @Override
    public void piocherCarte() {
        carte_.changerEtatEnMain();
    }

    @Override
    public void afficherMessage() {
        
    }
    
}
