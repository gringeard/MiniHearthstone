package org.iut.actionspeciale;

import org.iut.cible.Cible;
import org.iut.observer.Sujet;

public class Armure extends ActionSpeciale{

    public Armure() {
        super("Armure", "Confère 2 points d'armure au héros",false,false);
    }
    
    public void executer(Sujet sujet, Cible cible){
        sujet.getHero().bonusArmure(2);
    }
}
