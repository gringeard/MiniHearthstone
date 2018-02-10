/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut;

import java.util.Scanner;
import org.iut.carte.Carte;
import org.iut.carte.decorator.Provocation;
import org.iut.carte.state.EtatDors;
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
                choix = cp.choixAction(firstJoueur, secondJoueur);
            }while(!choix.equals("4"));
            secondJoueur.debuterTour(cp.getTour());
            do{
                choix = cp.choixAction(secondJoueur, firstJoueur);
            }while(!choix.equals("4"));
        }
    }

    private Hero choixHero(int i) {
        vc.afficherChoixHero(i);
        String choix;
        do{
            choix = scanner.nextLine();
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
                default:
                    System.out.println("Veuillez saisir un nombre correct");
                    choix = "0";
                    break;
            }
        }while(choix.equals("0"));
        
        return this.fh.creerHero();
    }
    
    private String choixAction(Sujet joueur, Sujet adversaire){
        vc.afficherChoixAction();
        String choix = scanner.nextLine();
        switch (choix) {
            case "1":
                int indexCarteAJouer = choixCarteAJouer(joueur);
                if( indexCarteAJouer != -2 ){
                    joueur.jouerCarte(indexCarteAJouer);
                }
                break;
            case "2":
                vc.infosCombat(joueur, adversaire);
                int indexCartePourAttaquer = choixCartePourAttaquer(joueur);
                if(indexCartePourAttaquer == -2){
                    break;
                }
                Carte cartePourAttaquer = joueur.getCartesPosees().get(indexCartePourAttaquer);
                int indexCarteOuHeroAAttaquer = choixCarteOuHeroAAttaquer(joueur, adversaire);
                if(indexCarteOuHeroAAttaquer == -2){
                    break;
                }
                if(indexCarteOuHeroAAttaquer == -1){
                    joueur.attaquerHero(indexCartePourAttaquer);
                    adversaire.defendreHero(cartePourAttaquer);
                }else{
                    Carte cartePourDefendre = adversaire.getCartesPosees().get(indexCarteOuHeroAAttaquer);
                    adversaire.affronterCarte(indexCarteOuHeroAAttaquer, cartePourAttaquer);
                    joueur.affronterCarte(indexCartePourAttaquer, cartePourDefendre);
                }
                break;
            case "3":
                joueur.lancerActionSpeciale();
                break;
            case "4":
                joueur.finirTour();
                break;
            default :
                System.out.println("Veuillez entrer un nombre correct");
                choix = "0";
                break;
        }
        
        return choix;
    }
    
    private int choixCarteAJouer(Sujet joueur){
        vc.afficherChoixCarteAJouer(joueur);
        int choix;
        do{
            try{
                choix = Integer.parseInt(scanner.nextLine())-1;
            }catch(NumberFormatException ex){
                System.out.println("Vous n'avez pas saisi un nombre");
                choix = -99;
            }
            if((choix <0 && choix != -99 && choix != -2) || (choix+1) > joueur.getCartesEnMain().size()){
                System.out.println("Vous n'avez pas saisi un nombre valide");
                choix = -99;
            }
        }while(choix == -99);
        
        
        return choix;
    }
    
    private int choixCartePourAttaquer(Sujet joueur){
        Carte c;
        int choix;
        do{
            vc.afficherChoixCartePourAttaquer(joueur);
            do{
                try{
                    choix = Integer.parseInt(scanner.nextLine())-1;
                }catch(NumberFormatException ex){
                    System.out.println("Vous n'avez pas saisi un nombre");
                    choix = -99;
                }
                if((choix <0 && choix != -99 && choix != -2) || (choix+1) > joueur.getCartesPosees().size()){
                    System.out.println("Vous n'avez pas saisi un nombre valide");
                    choix = -99;
                }
            }while(choix == -99);
            if(choix == -2){
                return choix;
            }
            c = joueur.getCartesPosees().get(choix);
            if(c.getEtatCourant_() instanceof  EtatDors){
            System.out.println("veuillez choisir un serviteur qui ne dors pas");
        }
        }while (c.getEtatCourant_() instanceof  EtatDors);
        
        
        return choix;
    }
    
    private int choixCarteOuHeroAAttaquer(Sujet joueur, Sujet adversaire){
        Carte c;
        boolean ok = true;
        int choix;
        do{
            vc.afficherChoixCarteOuHeroAAttaquer(adversaire);
            //Si le numéro choisi équivaut à -1, alors c'est le héros qui a été choisi
            do{
                try{
                    choix = Integer.parseInt(scanner.nextLine())-1;
                }catch(NumberFormatException ex){
                    System.out.println("Vous n'avez pas saisi un nombre");
                    choix = -99;
                }
                if((choix <0 && choix != -99 && choix != -2) || (choix+1) > joueur.getCartesPosees().size()){
                    System.out.println("Vous n'avez pas saisi un nombre valide");
                    choix = -99;
                }
            }while(choix == -99);
            if(choix == -2){
                return choix;
            }
            //On vérifie s'il y a une carte Provocation de posée
            for(Carte uneCarte : adversaire.getCartesPosees()){
                if(uneCarte instanceof Provocation){
                    ok = false;
                }
            }
            //Si le héros n'est pas choisi et qu'une carte provocation a été trouvée, 
            //on vérifie que la carte choisie est de type Provocation
            if(choix != -1 && !ok){
                c = adversaire.getCartesPosees().get(choix);
                if(c instanceof Provocation){
                    ok = true;
                }
            }
            if(!ok){
                System.out.println("Impossible, il y a une carte provocation sur le terrain");
                vc.infosCombat(joueur, adversaire);
            }
        }while(!ok);
        
        
        return choix;
    }
    
}
