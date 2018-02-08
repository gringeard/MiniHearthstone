/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.hero;

import java.util.ArrayList;
import org.iut.carte.Carte;
import org.iut.services.Service;

/**
 *
 * @author Gwen
 */
public class FactoryHeroMage implements FactoryHero {

    @Override
    public Hero creerHero() {
        return new HeroMage();
    }

    @Override
    public ArrayList<Carte> creerCartes() {
        ArrayList<Carte> cartes = Service.getCartesCommunes();
        cartes.addAll(Service.getCartesMage());
        return cartes;
    }
    
}