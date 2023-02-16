/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_trinome;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Louis
 */
public class Joueur {
    public String pseudo;
    String couleur;
    int nb_cubes;
    int nb_pyr;
    int nb_demisph;
    ArrayList<piece> ListePieces;    
    
    public Joueur(String pseudo, String couleur){
        this.pseudo = pseudo;
        this.couleur = couleur;
        nb_cubes = 6;
        nb_pyr = 6;
        nb_demisph = 3;
    }
    
    public String Couleur() {
        return this.couleur;
    }
    
    public String Pseudo() {
        return this.pseudo;
    }
}
