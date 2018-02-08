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
import org.iut.observer.Sujet;

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
        this.tour = 0;
        this.joueur1 = new Joueur("Joueur 1");
        this.joueur2 = new Joueur("Joueur 2");
        this.affJoueur1 = new AffichageJoueur(joueur1);
        this.affJoueur2 = new AffichageJoueur(joueur2);
        
    }

    public VueConsole getVc() {
        return vc;
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

    public FactoryHero getFh() {
        return fh;
    }

    public void setFh(FactoryHero fh) {
        this.fh = fh;
    }
    
    public void nouveauTour(){
        this.tour++;
    }
    
    
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControleurPrincipal cp = new ControleurPrincipal();
        cp.getJoueur1().setHero(cp.choixHero(1));
        cp.getJoueur1().setCartesEnPile(cp.getFh().creerCartes());
        cp.getJoueur2().setHero(cp.choixHero(2));
        cp.getJoueur2().setCartesEnPile(cp.getFh().creerCartes());
        
        //Qui commence ?
        Sujet firstJoueur;
        Sujet secondJoueur;
        int numero = (int) (Math.random() * 2);
        if(numero == 0){
            cp.getJoueur1().setFirst(true);
            firstJoueur = cp.getJoueur1();
            secondJoueur = cp.getJoueur2();
        }else{
            cp.getJoueur2().setFirst(true);
            firstJoueur = cp.getJoueur2();
            secondJoueur = cp.getJoueur1();
        }
        cp.getVc().afficherJoueurCommence(firstJoueur.getNom());
        
        //On joue tant que les 2 héros ont plus de 1 pv
        while((firstJoueur.getHero().getPdv_() > 0) && (secondJoueur.getHero().getPdv_() > 0)){
            cp.nouveauTour();
            firstJoueur.debuterTour(cp.getTour());
            String choix;
            do{
                choix = cp.choixAction(firstJoueur);
            }while(!choix.equals("4"));
            secondJoueur.debuterTour(cp.getTour());
            do{
                choix = cp.choixAction(secondJoueur);
            }while(!choix.equals("4"));
        }
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
    
    private String choixAction(Sujet joueur){
        vc.afficherChoixAction();
        String choix = scanner.nextLine();
        switch (choix) {
            case "1":
                choixCarteAJouer(joueur);
                break;
            case "2":
                joueur.attaquer();
                break;
            case "3":
                joueur.lancerActionSpeciale();
                break;
            case "4":
                joueur.finirTour();
                break;
        }
        
        return choix;
    }
    
    private void choixCarteAJouer(Sujet joueur){
        vc.afficherChoixCarteAJouer(joueur);
        int choix = Integer.parseInt(scanner.nextLine());
        joueur.jouerCarte(choix-1);
        
    }
    
}
