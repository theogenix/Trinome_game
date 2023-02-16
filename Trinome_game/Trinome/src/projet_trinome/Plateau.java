/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_trinome;

/**
 *
 * @author Louis
 */
public class Plateau {

    public piece[][] plateau_jeu;

    public Plateau(){
        plateau_jeu = new piece[23][23];
    }
    
    public Plateau(boolean spe) {
        // initialiser les pièces sur le plateau
        
        plateau_jeu = new piece[23][23];

        plateau_jeu[0][2] = new Cube("vert");
        plateau_jeu[1][1] = new Cube("vert");
        plateau_jeu[2][0] = new Cube("vert");
        plateau_jeu[2][22] = new Cube("vert");
        plateau_jeu[1][21] = new Cube("vert");
        plateau_jeu[0][20] = new Cube("vert");

        plateau_jeu[20][0] = new Cube("rouge");
        plateau_jeu[21][1] = new Cube("rouge");
        plateau_jeu[22][2] = new Cube("rouge");
        plateau_jeu[20][22] = new Cube("rouge");
        plateau_jeu[21][21] = new Cube("rouge");
        plateau_jeu[22][20] = new Cube("rouge");

        plateau_jeu[0][0] = new pyramide("vert");
        plateau_jeu[0][1] = new pyramide("vert");
        plateau_jeu[1][0] = new pyramide("vert");
        plateau_jeu[0][22] = new pyramide("vert");
        plateau_jeu[0][21] = new pyramide("vert");
        plateau_jeu[1][22] = new pyramide("vert");

        plateau_jeu[21][0] = new pyramide("rouge");
        plateau_jeu[22][0] = new pyramide("rouge");
        plateau_jeu[22][1] = new pyramide("rouge");
        plateau_jeu[22][22] = new pyramide("rouge");
        plateau_jeu[21][22] = new pyramide("rouge");
        plateau_jeu[22][21] = new pyramide("rouge");

        plateau_jeu[0][10] = new DemiSphere("vert");
        plateau_jeu[0][11] = new DemiSphere("vert");
        plateau_jeu[0][12] = new DemiSphere("vert");

        plateau_jeu[22][11] = new DemiSphere("rouge");
        plateau_jeu[22][12] = new DemiSphere("rouge");
        plateau_jeu[22][10] = new DemiSphere("rouge");

        plateau_jeu[22][0] = spe ? new pyramide_spe("rouge") : new pyramide("rouge");
        plateau_jeu[0][0] = spe ? new pyramide_spe("vert") : new pyramide("vert");
        
        plateau_jeu[22][11] = spe ? new DemiSphere_spe("rouge") : new DemiSphere("rouge");
        plateau_jeu[0][11] = spe? new DemiSphere_spe("vert") : new DemiSphere("vert");

    }

    public int Gagnant() {
        // 0 pas terminé
        // 1 vert win
        // 2 rouge win
        String[] casesRouges = new String[]{"0-10", "0-11", "0-12"};
        String[] casesVertes = new String[]{"22-10", "22-11", "22-12"};
        boolean nonNullR = plateau_jeu[0][10] != null && plateau_jeu[0][11] != null && plateau_jeu[0][12] != null;
        boolean nonNullV = plateau_jeu[22][10] != null && plateau_jeu[22][11] != null && plateau_jeu[22][12] != null;

        int PyramideV = 0;
        int CubeV = 0;
        int SphereV = 0;
        int PyramideR = 0;
        int CubeR = 0;
        int SphereR = 0;
        for (int i = 0; i < plateau_jeu.length; i++) {
            for (int j = 0; j < plateau_jeu.length; j++) {
                if (plateau_jeu[i][j] != null) {
                    if (plateau_jeu[i][j].getClass() == Cube.class) {
                        if (plateau_jeu[i][j].couleur == "vert") {
                            CubeV++;
                        } else {
                            CubeR++;
                        }
                    } else if (plateau_jeu[i][j].getClass() == pyramide.class || plateau_jeu[i][j].getClass() == pyramide_spe.class) {
                        if (plateau_jeu[i][j].couleur == "vert") {
                            PyramideV++;
                        } else {
                            PyramideR++;
                        }
                    } else if (plateau_jeu[i][j].getClass() == DemiSphere.class || plateau_jeu[i][j].getClass() == DemiSphere_spe.class) {
                        if (plateau_jeu[i][j].couleur == "vert") {
                            SphereV++;
                        } else {
                            SphereR++;
                        }
                    }
                }
            }
        }

        if (nonNullR) {
            boolean sontRouges = plateau_jeu[0][10].couleur == "rouge" && plateau_jeu[0][11].couleur == "rouge" && plateau_jeu[0][12].couleur == "rouge";
            boolean differentsR = plateau_jeu[0][10].getClass() != plateau_jeu[0][11].getClass() && plateau_jeu[0][10].getClass() != plateau_jeu[0][12].getClass() && plateau_jeu[0][11].getClass() != plateau_jeu[0][12].getClass();
            if (sontRouges && differentsR) {
                return 2;
            }
        }
        if (nonNullV) {
            boolean sontVerts = plateau_jeu[22][10].couleur == "vert" && plateau_jeu[22][11].couleur == "vert" && plateau_jeu[22][12].couleur == "vert";
            boolean differentsV = plateau_jeu[22][10].getClass() != plateau_jeu[22][11].getClass() && plateau_jeu[22][10].getClass() != plateau_jeu[22][12].getClass() && plateau_jeu[22][11].getClass() != plateau_jeu[22][12].getClass();
            if (sontVerts && differentsV) {
                return 1;
            }
        }
        if (CubeR == 0 || SphereR == 0 || PyramideR == 0) {
            return 1;
        }
        if (CubeV == 0 || SphereV == 0 || PyramideV == 0) {
            return 2;
        }
        return 0;
    }

    public void afficher() {
        // afficher tableau croisé dynamique pour mieux se repérer lors du test
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.print("   ");
        for (int i = 0; i < plateau_jeu.length; i++) {
            System.out.print(i + " ");
            if (i < 10) {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int i = 0; i < plateau_jeu.length; i++) {
            System.out.print(alphabet.charAt(i) + "  ");
            for (int j = 0; j < plateau_jeu.length; j++) {
                if (plateau_jeu[i][j] == null) {
                    System.out.print("-  ");
                } else {
                    System.out.print(plateau_jeu[i][j].affichage() + "  ");
                }
            }
            System.out.println();
        }
    }
    
    public boolean deplacement(int L, int C, int newL, int newC, String couleur) {
        //Déplacer les pièces
        if (newL >= 0 && newC >= 0 && newC < 23 && newL < 23 && L >= 0 && C >= 0 && C < 23 && L < 23) {
            String[] casesRouges = new String[]{"0-10", "0-11", "0-12"};
            String[] casesVertes = new String[]{"22-10", "22-11", "22-12"};
            boolean departOK = plateau_jeu[L][C] != null && plateau_jeu[L][C].couleur == couleur && ( (couleur == "rouge" && java.util.Arrays.asList(casesRouges).indexOf("" + L + "-" + C) == -1) || (couleur == "vert" && java.util.Arrays.asList(casesVertes).indexOf("" + L + "-" + C) == -1));
            boolean prenable = plateau_jeu[newL][newC] == null || ((couleur == "rouge" && plateau_jeu[newL][newC] != null && plateau_jeu[newL][newC].couleur != couleur && java.util.Arrays.asList(casesVertes).indexOf("" + newL + "-" + newC) == -1) || (couleur == "vert" && plateau_jeu[newL][newC] != null && plateau_jeu[newL][newC].couleur != couleur && java.util.Arrays.asList(casesRouges).indexOf("" + newL + "-" + newC) == -1));

            if (plateau_jeu[L][C] == null) {
                return false;
            } else {
                return plateau_jeu[L][C].Deplacement(L, C, newL, newC, plateau_jeu, departOK, prenable);
            }
        } else {
            // valeurs hors du plateau
            return false;
        }
    }
}  