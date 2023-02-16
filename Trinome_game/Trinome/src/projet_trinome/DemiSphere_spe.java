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
public class DemiSphere_spe extends piece {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public DemiSphere_spe(String couleur) {
        super(couleur);
    }

    public String affichage() {
        if (this.couleur == "vert") {
            return ANSI_GREEN + "5" + ANSI_RESET;
        } else {
            return ANSI_RED + "5" + ANSI_RESET;
        }
    }
    
    @Override
    //méthode déplacement Demisphere 
       public boolean Deplacement(int L, int C, int newL, int newC, piece[][] plateau_jeu, boolean departOK, boolean prenable){
       
           if (((Math.abs(L - newL) == 2 && Math.abs(C - newC) == 2)) || ((Math.abs(L-newL) == 0 && Math.abs(C-newC) == 2)) || (Math.abs(L-newL) == 2 && Math.abs(C-newC) == 0)) {
                    if (plateau_jeu[L][C].couleur == couleur) {
                        boolean avance = (plateau_jeu[L][C].couleur == "vert" && newL >= L) || (plateau_jeu[L][C].couleur == "rouge" && newL <= L);
                        boolean estBloque = false;
                        if(plateau_jeu[L][C].couleur == "vert"){
                            int nbb= 0;
                            for(int l = L + 2; l >= L; l--){
                                for(int c = C + 2; c >= C - 2; c--){
                                    if((Math.abs(L - l) == 2 || Math.abs(C - c) == 2)){
                                        if(l < 0 || c < 0 || l > 22 || c > 22 || plateau_jeu[l][c] != null) nbb++;
                                    }
                                }
                            }
                            if(nbb == 5) estBloque = true; 
                        }
                        if(plateau_jeu[L][C].couleur == "rouge"){
                            int nbb= 0;
                            for(int l = L-2; l <= L; l++){
                                for(int c = C - 2; c <= C + 2; c++){
                                    if((Math.abs(L - l) == 2 || Math.abs(C - c) == 2)){
                                        if(l < 0 || c < 0 || l > 22 || c > 22 || plateau_jeu[l][c] != null) nbb++;
                                    }
                                }
                            }
                            
                            if(nbb == 5) estBloque = true; 
                        }
                        
                        if (plateau_jeu[newL][newC] == null && departOK && (estBloque || avance)) {
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
