package org.iut.actionspeciale;

import org.iut.carte.CarteServiteur;
import org.iut.cible.Cible;
import org.iut.observer.Sujet;

public class BouleDeFeu extends ActionSpeciale{

    public BouleDeFeu() {
        super("Boule de feu", "Inflige un point de dégats à un adversaire (serviteur ou héros)",true,true);
    }
    
    @Override
    public void executer(Sujet sujet, Cible cible){
        cible.recevoirDegats(1);
    }
}
