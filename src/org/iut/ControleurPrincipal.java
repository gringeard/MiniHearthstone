/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut;

import java.util.Scanner;
import org.iut.carte.Carte;
import org.iut.carte.CarteServiteur;
import org.iut.carte.CarteSort;
import org.iut.carte.decorator.serviteur.Provocation;
import org.iut.carte.decorator.sort.Consecration;
import org.iut.carte.decorator.sort.ExplosionArcanes;
import org.iut.carte.decorator.sort.ImageMiroir;
import org.iut.carte.decorator.sort.MaitriseBlocage;
import org.iut.carte.decorator.sort.Tourbillon;
import org.iut.carte.state.EtatDors;
import org.iut.cible.Cible;
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

    public void nouveauTour() {
        this.tour++;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControleurPrincipal cp = new ControleurPrincipal();
        //Création du joueur 1 et des cartes dont il peut disposer, il doit choisir un héros parmis les héros disponibles
        cp.getJoueur1().setHero(cp.choixHero(cp.getJoueur1().getNom()));
        cp.getJoueur1().setCartesEnPile(cp.getFh().creerCartes());
        //Création du joueur 2 et des cartes dont il peut disposer, il doit choisir un héros parmis les héros disponibles
        cp.getJoueur2().setHero(cp.choixHero(cp.getJoueur2().getNom()));
        cp.getJoueur2().setCartesEnPile(cp.getFh().creerCartes());

        //Qui commence ?
        Sujet firstJoueur;
        Sujet secondJoueur;
        //On crée un nombre aléatoirement valant 0 ou 1
        int numero = (int) (Math.random() * 2);
        //S'il vaut 0, joueur 1 commence
        if (numero == 0) {
            cp.getJoueur1().setFirst(true);
            firstJoueur = cp.getJoueur1();
            secondJoueur = cp.getJoueur2();
            //Sinon c'est le joueur 2
        } else {
            cp.getJoueur2().setFirst(true);
            firstJoueur = cp.getJoueur2();
            secondJoueur = cp.getJoueur1();
        }
        //On affiche à l'écran le nom du joueur qui commence
        cp.getVc().afficherJoueurCommence(firstJoueur.getNom());

        //On joue tant que les 2 héros ont plus de 1 pv
        while ((firstJoueur.getHero().getPdv_() > 0) && (secondJoueur.getHero().getPdv_() > 0)) {
            //Au début de chaque tour, on incrémente la valeur de tour
            cp.nouveauTour();
            //La valeur de tour sert à déterminer le nombre de mana dont dispose le joueur
            firstJoueur.debuterTour(cp.getTour());
            String choix;
            //Tant que le joueur ne choisi pas 4 (Terminer tour), on lui demande de choisir une action
            do {
                choix = cp.choixAction(firstJoueur, secondJoueur);
            } while (!choix.equals("4"));
            //Si le héros du second joueur est mort à la fin du tour du premier, on stop
            if (secondJoueur.getHero().getPdv_() < 0) {
                break;
            }
            //Même action que pour le premier joueur
            secondJoueur.debuterTour(cp.getTour());
            do {
                choix = cp.choixAction(secondJoueur, firstJoueur);
            } while (!choix.equals("4"));
        }
    }

    /**
     * Crée un héros en fonction du choix du joueur
     *
     * @param i
     * @return Hero
     */
    private Hero choixHero(String nom) {
        //On affiche les choix possibles
        vc.afficherChoixHero(nom);
        String choix;
        //On contrôle que le choix est valide
        do {
            choix = scanner.nextLine();
            //selon le choix, on adapte la Factory
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
        } while (choix.equals("0"));

        return this.fh.creerHero();
    }

    /**
     * Réalise les actions en fonction des choix du joueur
     *
     * @param joueur
     * @param adversaire
     * @return String
     */
    private String choixAction(Sujet joueur, Sujet adversaire) {
        vc.afficherChoixAction();
        String choix = scanner.nextLine();
        switch (choix) {
            //Si le joueur veut jouer une carte
            case "1":
                //On lui demande de choisir
                int indexCarteAJouer = choixCarteAJouer(joueur);
                //Si le joueur a fait un retour, indexCarteAJouer vaut -2 et on exécute rien
                //Sinon
                if (indexCarteAJouer != -2) {
                    //On exécute 
                    joueur.jouerCarte(indexCarteAJouer);

                    if(joueur.getCartesPosees().get(indexCarteAJouer) instanceof CarteSort){
                        this.lancerSort(joueur, adversaire, joueur.getCartesPosees().get(indexCarteAJouer));;
                    }
                }
                break;
            //Si le joueur veut attaquer
            case "2":
                //On lui donne les infos sur les cartes en jeu et sur le héros adverse
                vc.infosCombat(joueur, adversaire);
                //On lui demande de choisir une carte pour attaquer
                int indexCartePourAttaquer = choixCartePourAttaquer(joueur);
                //Si le joueur a fait un retour, indexCarteAJouer vaut -2 et on casse l'exécution
                if (indexCartePourAttaquer == -2) {
                    break;
                }
                Carte cartePourAttaquer = joueur.getCartesPosees().get(indexCartePourAttaquer);
                //On lui demande de choisir d'attaquer une carte adverse ou le héros
                int indexCarteOuHeroAAttaquer = choixCarteOuHeroAAttaquer(joueur, adversaire);
                //Si le joueur a fait un retour, indexCarteOuHeroAAttaquer vaut -2 et on casse l'exécution
                if (indexCarteOuHeroAAttaquer == -2) {
                    break;
                }
                //Si le joueur a choisi d'attaquer le héros, indexCarteOuHeroAAttaquer vaut -1
                //Et on exécute l'attaque du héros
                if (indexCarteOuHeroAAttaquer == -1) {
                    joueur.attaquerHero(indexCartePourAttaquer);
                    adversaire.defendreHero(cartePourAttaquer);
                    //Sinon, on exécute l'attaque de la carte choisie
                } else {
                    Carte cartePourDefendre = adversaire.getCartesPosees().get(indexCarteOuHeroAAttaquer);
                    adversaire.affronterCarte(indexCarteOuHeroAAttaquer, cartePourAttaquer);
                    joueur.affronterCarte(indexCartePourAttaquer, cartePourDefendre);
                }
                break;
            //Si le joueur veut exécuter son action spéciale
            case "3":
                Cible cible = null;

                //Si l'action a besoin d'une cible
                if (joueur.getHero().getActionSpeciale().isBesoinCible()) {
                    //On lui donne les infos sur les cartes en jeu et sur le héros adverse
                    vc.infosCombat(joueur, adversaire);
                
                    //On lui demande de choisir d'attaquer une carte adverse ou le héros
                    int idCible = choixCarteOuHeroAAttaquer(joueur, adversaire);
                    //Si le joueur a fait un retour, indexCarteOuHeroAAttaquer vaut -2 et on casse l'exécution
                    if (idCible == -2) {
                        break;
                    }
                    //Si le joueur a choisi d'utiliser son action spéciale sur le héros, idCible vaut -1
                    if (idCible == -1) {
                        cible = adversaire.getHero();
                    } else {
                        //Sinon affecte une carte de l'adversaire
                        cible = (Cible)adversaire.getCartesPosees().get(idCible);
                    }
                }
                
                //Si l'action speciale est offensive lance sur l'adversaire
                //Sinon sur lui même
                if (joueur.getHero().getActionSpeciale().isOffensive()) {
                    adversaire.lancerActionSpeciale(cible);
                }
                else{
                    joueur.lancerActionSpeciale(cible);
                }
                
                break;
            //Si le joueur veut finir son tour
            case "4":
                joueur.finirTour();
                break;
            //Si le joueur ne saisi aucun des nombres attendus
            default:
                System.out.println("Veuillez entrer un nombre correct");
                choix = "0";
                break;
        }

        return choix;
    }

    /**
     * Retourne l'index de la carte sélectionnée que le joueur veut jouer ou -2
     * s'il veut revenir en arrière
     *
     * @param joueur
     * @return int
     */
    private int choixCarteAJouer(Sujet joueur) {
        vc.afficherChoixCarteAJouer(joueur);
        int choix;
        //On boucle tant que le choix n'est pas valide
        do {
            try {
                choix = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException ex) {
                System.out.println("Vous n'avez pas saisi un nombre");
                choix = -99;
            }
            if ((choix < 0 && choix != -99 && choix != -2) || (choix + 1) > joueur.getCartesEnMain().size()) {
                System.out.println("Vous n'avez pas saisi un nombre valide");
                choix = -99;
            }
        } while (choix == -99);

        return choix;
    }

    /**
     * Retourne l'index de la carte sélectionnée avec laquel le joueur veut
     * attaquer ou -2 s'il veut annuler
     *
     * @param joueur
     * @return int
     */
    private int choixCartePourAttaquer(Sujet joueur) {
        Carte c;
        int choix;
        //On boucle si la carte choisie dors
        do {
            vc.afficherChoixCartePourAttaquer(joueur);
            //On boucle tant que le choix n'est pas valide
            do {
                try {
                    choix = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException ex) {
                    System.out.println("Vous n'avez pas saisi un nombre");
                    choix = -99;
                }
                if ((choix < 0 && choix != -99 && choix != -2) || (choix + 1) > joueur.getCartesPosees().size()) {
                    System.out.println("Vous n'avez pas saisi un nombre valide");
                    choix = -99;
                }
            } while (choix == -99);
            //Si le joueur a demandé un retour, choix vaut -2 et on retourne de suite sa valeur
            if (choix == -2) {
                return choix;
            }
            c = joueur.getCartesPosees().get(choix);
            if (c.getEtatCourant_() instanceof EtatDors) {
                System.out.println("veuillez choisir un serviteur qui ne dors pas");
            }
        } while (c.getEtatCourant_() instanceof EtatDors);

        return choix;
    }

    /**
     * Retourne l'index de la carte sélectionnée que le joueur veut attaquer ou
     * -1 si c'est le héros ou -2 s'il veut annuler
     *
     * @param joueur
     * @param adversaire
     * @return int
     */
    private int choixCarteOuHeroAAttaquer(Sujet joueur, Sujet adversaire) {
        Carte c;
        boolean ok = true;
        int choix;
        //On boucle si le choix de carte à attaquer est impossible
        do {
            vc.afficherChoixCarteOuHeroAAttaquer(adversaire);
            //On boucle tant que le choix n'est pas valide
            do {
                try {
                    choix = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException ex) {
                    System.out.println("Vous n'avez pas saisi un nombre");
                    choix = -99;
                }
                if ((choix < -1 && choix != -99 && choix != -2) || (choix + 1) > adversaire.getCartesPosees().size()) {
                    System.out.println("Vous n'avez pas saisi un nombre valide");
                    choix = -99;
                }
            } while (choix == -99);
            //Si le joueur a demandé un retour, choix vaut -2 et on retourne de suite sa valeur
            if (choix == -2) {
                return choix;
            }
            //On vérifie s'il y a une carte Provocation de posée
            for (Carte uneCarte : adversaire.getCartesPosees()) {
                //Si oui, on passe ok a false au cas où le joueur n'attaque pas une carte Provocation
                if (uneCarte instanceof Provocation) {
                    ok = false;
                }
            }
            //Si le héros n'est pas choisi et qu'une carte provocation a été trouvée, 
            //on vérifie que la carte choisie est de type Provocation
            if (choix != -1 && !ok) {
                c = adversaire.getCartesPosees().get(choix);
                //Si la carte choisie est bien de type Provocation, on accepte l'attaque
                if (c instanceof Provocation) {
                    ok = true;
                }
            }
            if (!ok) {
                System.out.println("Impossible, il y a une carte provocation sur le terrain");
                vc.infosCombat(joueur, adversaire);
            }
        } while (!ok);

        return choix;
    }

    private void lancerSort(Sujet joueur, Sujet adversaire, Carte carte) {
        if (carte instanceof ImageMiroir){
            joueur.invoqueCarte(new Provocation(new CarteServiteur("Serviteur", 0, 0, 2 )));
            joueur.invoqueCarte(new Provocation(new CarteServiteur("Serviteur", 0, 0, 2 )));
        }
        else if (carte instanceof ExplosionArcanes){
            adversaire.subirDegatServiteurs(1);
        }
        else if (carte instanceof Tourbillon){
            adversaire.subirDegatServiteurs(1);
            joueur.subirDegatServiteurs(1);
        }
        else if (carte instanceof MaitriseBlocage){
            joueur.getHero().bonusArmure(5);
            joueur.piocherCarteAleatoirement();
        }
        else if (carte instanceof Consecration){
            adversaire.subirDegatServiteurs(1);
            adversaire.getHero().recevoirDegats(2);
        }
    }

}
