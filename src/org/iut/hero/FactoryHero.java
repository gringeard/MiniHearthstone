/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.hero;

import java.util.ArrayList;
import org.iut.carte.Carte;

/**
 *
 * @author Gwen
 */
public interface FactoryHero {
    public Hero creerHero();
    public ArrayList<Carte> creerCartes();
}
