package org.iut.carte;

import org.iut.carte.state.Etat;
import org.iut.carte.state.EtatLancerSort;

public class CarteSort extends Carte {
    
    private Etat etatLancerSort_;

    public CarteSort(String nom, int mana) {
        super(nom, mana);
        etatLancerSort_ = new EtatLancerSort(this);
    }

    //Transitions
    public void lancerSort(){
        etatCourant_.lancerSort();
    }
    
    //Actions
    public void changerEtatLancerSort(){
        etatCourant_ = etatLancerSort_;
        afficherMessage();
    }
    
    
    @Override
    public String toString() {
        return super.toString();
    }
        
        
	
	
}
