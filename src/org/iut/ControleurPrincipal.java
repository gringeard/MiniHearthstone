/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut;

import java.util.Scanner;
import org.iut.hero.FactoryHero;
import org.iut.hero.FactoryHeroGuerrier;
import org.iut.hero.FactoryHeroMage;
import org.iut.hero.FactoryHeroPaladin;
import org.iut.hero.Hero;
import org.iut.observer.AffichageJoueur;
import org.iut.observer.Joueur;

/**
 *
 * @author Gwen
 */
public class ControleurPrincipal {
    private VueConsole vc;
    private Scanner scanner;
    private Joueur joueur1;
    private Joueur joueur2;
    private AffichageJoueur affJoueur1;
    private AffichageJoueur affJoueur2;
    private FactoryHero fh;
    private int tour;
    
    public ControleurPrincipal() {
        this.vc = new VueConsole();
        this.scanner = new Scanner(System.in);
        this.tour = 1;
        this.joueur1 = new Joueur("Joueur 1", this.tour%2);
        this.joueur2 = new Joueur("Joueur 2", this.tour%2);
        this.affJoueur1 = new AffichageJoueur(joueur1);
        this.affJoueur2 = new AffichageJoueur(joueur2);
        this.tour = 1;
        
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    public AffichageJoueur getAffJoueur1() {
        return affJoueur1;
    }

    public void setAffJoueur1(AffichageJoueur affJoueur1) {
        this.affJoueur1 = affJoueur1;
    }

    public AffichageJoueur getAffJoueur2() {
        return affJoueur2;
    }

    public void setAffJoueur2(AffichageJoueur affJoueur2) {
        this.affJoueur2 = affJoueur2;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }
    
    
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControleurPrincipal cp = new ControleurPrincipal();
        cp.getJoueur1().setHero(cp.choixHero(1));
        cp.getJoueur2().setHero(cp.choixHero(2));
    }

    private Hero choixHero(int i) {
        vc.afficherChoixHero(i);
        String choix = scanner.nextLine();
        switch (choix) {
            case "1":
                this.fh = new FactoryHeroMage();
                break;
            case "2":
                this.fh = new FactoryHeroGuerrier();
                break;
            case "3":
                this.fh = new FactoryHeroPaladin();
                break;
        }
        return this.fh.creerHero();
    }
    
}
