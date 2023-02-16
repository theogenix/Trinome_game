/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_trinome;

import static projet_trinome.pyramide.ANSI_GREEN;
import static projet_trinome.pyramide.ANSI_RED;
import static projet_trinome.pyramide.ANSI_RESET;

/**
 *
 * @author theog
 */
public class pyramide_spe extends pyramide {
    
    public pyramide_spe(String couleur){
        super(couleur);
    }
    // afficher en couleur+ lettre correspondante
       public String affichage(){
        if(this.couleur == "vert"){
            return ANSI_GREEN + "4" + ANSI_RESET;
        } else {
            return ANSI_RED + "4" + ANSI_RESET;
        }
    }
       
       @Override
       // méthode déplacement pyramide spé
       public boolean Deplacement(int L, int C, int newL, int newC, piece[][] plateau_jeu, boolean departOK, boolean prenable){
           if ((Math.abs(L - newL) == 1 && Math.abs(C - newC) == 1)) {
                    if (plateau_jeu[L][C].couleur == couleur) {
                        if ((plateau_jeu[newL][newC] == null || plateau_jeu[newL][newC].couleur != couleur) && departOK && prenable) {
                            plateau_jeu[newL][newC] = plateau_jeu[L][C];
                            plateau_jeu[L][C] = null;
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                else if ((Math.abs(L - newL) == 2 && Math.abs(C - newC) == 2)) {
                    if (plateau_jeu[L][C].couleur == couleur) {
                        if ((plateau_jeu[newL][newC] == null) && departOK) {
                            int L2 = L + (newL - L) / 2;
                            int C2 = C + (newC - C) / 2;
                            if (plateau_jeu[L2][C2] != null && plateau_jeu[L2][C2].couleur != couleur) {
                                plateau_jeu[newL][newC] = plateau_jeu[L][C];
                                plateau_jeu[L][C] = null;
                                plateau_jeu[L2][C2] = null;
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
                } else {
                    return false;
                }
       }
}
