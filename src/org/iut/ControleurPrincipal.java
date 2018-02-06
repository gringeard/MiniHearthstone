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

/**
 *
 * @author Gwen
 */
public class ControleurPrincipal {
    private VueConsole vc;
    private Scanner scanner;
    private Hero joueur1;
    private Hero joueur2;
    private FactoryHero fh;
    
    public ControleurPrincipal() {
        this.vc = new VueConsole();
        this.scanner = new Scanner(System.in);
    }

    public Hero getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Hero joueur1) {
        this.joueur1 = joueur1;
    }

    public Hero getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Hero joueur2) {
        this.joueur2 = joueur2;
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControleurPrincipal cp = new ControleurPrincipal();
        cp.setJoueur1(cp.choixHero(1));
        cp.setJoueur2(cp.choixHero(2));
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
