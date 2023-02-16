/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_trinome;

/**
 *
 * @author theog
 */
public class DemiSphere extends piece {
     public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    
     public DemiSphere(String couleur){
        super(couleur);
    }
     // afficher en couleur+ lettre correspondante
    public String affichage(){
        if(this.couleur == "vert"){
            return ANSI_GREEN + "3" + ANSI_RESET;
        } else {
            return ANSI_RED + "3" + ANSI_RESET;
        }
    }
    
    @Override
    // méthode déplacement demisphère
       public boolean Deplacement(int L, int C, int newL, int newC, piece[][] plateau_jeu, boolean departOK, boolean prenable){
       
           if (((Math.abs(L - newL) == 2 && Math.abs(C - newC) == 2)) || ((Math.abs(L-newL) == 0 && Math.abs(C-newC) == 2)) || (Math.abs(L-newL) == 2 && Math.abs(C-newC) == 0)) {
                    if (plateau_jeu[L][C].couleur == couleur) {

                        if (plateau_jeu[newL][newC] == null && departOK) {
                            plateau_jeu[newL][newC] = plateau_jeu[L][C];
                            plateau_jeu[L][C] = null;
                            return true;
                        } else {
                            return false;
                        }

                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
       
       }
}
