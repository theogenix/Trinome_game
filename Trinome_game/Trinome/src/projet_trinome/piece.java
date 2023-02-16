package projet_trinome;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author theog
 */
public class piece{
    public String couleur;
   public piece(String couleur){
       this.couleur=couleur;
   }
   public String get_couleur(){
       return this.couleur;
   }
   public String affichage(){
    return "0";
}
   
   public boolean Deplacement(int L, int C, int newL, int newC, piece[][] plateau_jeu, boolean departOK, boolean prenable){
       return true;
   }
    }
