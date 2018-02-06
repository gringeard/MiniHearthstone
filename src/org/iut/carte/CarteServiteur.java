package org.iut.carte;

public class CarteServiteur extends Carte {

    public int degat;
    public int pv;
    
    public CarteServiteur(int mana, int degat, int pv) {
        super(mana);
        this.degat = degat;
        this.pv = pv;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }



}
