package org.iut.actionspeciale;

import org.iut.carte.CarteServiteur;
import org.iut.cible.Cible;
import org.iut.observer.Sujet;

public class Renfort extends ActionSpeciale{

    public Renfort() {
        super("Renfort", "Invoque un serviteur \"Recrue de la Main d'argent\" 1/1",false,false);
    }
    
    public void executer(Sujet sujet, Cible cible){
        sujet.invoqueCarte(new CarteServiteur("Recrue de la Main d'argent", 0, 1, 1));
    }
}
