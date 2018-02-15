package org.iut.actionspeciale;

import org.iut.cible.Cible;
import org.iut.observer.Sujet;

public abstract class ActionSpeciale{
    protected String nom;
    protected String description;
    protected boolean besoinCible;
    protected boolean offensive;

    public ActionSpeciale(String nom, String description, boolean besoinCible, boolean offensive) {
        this.nom = nom;
        this.description = description;
        this.besoinCible = besoinCible;
        this.offensive = offensive;
    }
    
    public void executer(Sujet sujet, Cible cible) {
        System.out.println("Le joueur lance "+this.nom+" ("+this.description+")");
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isBesoinCible(){
        return this.besoinCible;
    }
    
    public boolean isOffensive() {
        return this.offensive;
    }
}
