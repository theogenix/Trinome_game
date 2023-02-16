/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_trinome;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author theog
 * 
 */
public class Jeu {

    public Joueur J1;
    public Joueur J2;
    boolean spe;
    Plateau plateau;
    ArrayList<piece> EnsPieces;

    public int tours = 0;
    String caseDepart = "";
    int L_;
    int C_;
    int newL_;
    int newC_;


    public Jeu(int ON, String pseudo1, String pseudo2, int choix_spe) throws IOException, FileNotFoundException {
        //Définit les pseudos et le ype de partie
        J1 = new Joueur(pseudo1, "vert");
        J2 = new Joueur(pseudo2, "rouge");
        plateau = new Plateau(false);
        if (ON == 1) {
            if (choix_spe == 0) {
                spe = true;
            } else {
                spe = false;
            }
            plateau = new Plateau(spe);
        }
        if (ON == 2) {
            this.Charger();
        }
    }

    public void Charger() throws FileNotFoundException, IOException {
        //Methode de chargement de la partie
        FileReader fich = new FileReader("Partie.txt");
        BufferedReader br = new BufferedReader(fich);
        tours = Integer.parseInt(br.readLine());
        String pseudo1 = br.readLine();
        String pseudo2 = br.readLine();
        J1 = new Joueur(pseudo1, "vert");
        J2 = new Joueur(pseudo2, "rouge");
        plateau = new Plateau();
        String ligne = "";
        while (ligne != null) {
            ligne = br.readLine();
            if (ligne.equals(" ")) {
                break;
            }
            String[] ligne_piece = ligne.split(":");
            switch (Integer.valueOf(ligne_piece[0])) {
                case (1):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new Cube("vert");
                    break;
                case (2):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new pyramide("vert");
                    break;
                case (3):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new DemiSphere("vert");
                    break;
                case (4):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new Cube("rouge");
                    break;
                case (5):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new pyramide("rouge");
                    break;
                case (6):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new DemiSphere("rouge");
                    break;
                case (7):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new DemiSphere_spe("vert");
                    break;
                case (8):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new pyramide_spe("vert");
                    break;
                case (9):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new DemiSphere_spe("rouge");
                    break;
                case (10):
                    plateau.plateau_jeu[Integer.valueOf(ligne_piece[1])][Integer.valueOf(ligne_piece[2])] = new pyramide_spe("rouge");
                    break;
            }
        }
        fich.close();

    }

    public void Sauvegarder() {
        //Methode de sauvegarde de la partie
        try {
            FileWriter fich = new FileWriter("Partie.txt");
            fich.write(tours + System.lineSeparator());
            fich.write(J1.pseudo + System.lineSeparator());
            fich.write(J2.pseudo + System.lineSeparator());
            for (int a = 0; a < plateau.plateau_jeu.length; a++) {
                for (int j = 0; j < plateau.plateau_jeu.length; j++) {
                    if (plateau.plateau_jeu[a][j] != null) {
                        if (plateau.plateau_jeu[a][j].getClass() == Cube.class) {
                            if (plateau.plateau_jeu[a][j].couleur == "vert") {
                                fich.write("1:" + a + ":" + j + System.lineSeparator());
                            } else {
                                fich.write("4:" + a + ":" + j + System.lineSeparator());
                            }
                        } else if (plateau.plateau_jeu[a][j].getClass() == pyramide.class) {
                            if (plateau.plateau_jeu[a][j].couleur == "vert") {
                                fich.write("2:" + a + ":" + j + System.lineSeparator());
                            } else {
                                fich.write("5:" + a + ":" + j + System.lineSeparator());
                            }
                        } else if (plateau.plateau_jeu[a][j].getClass() == DemiSphere.class) {
                            if (plateau.plateau_jeu[a][j].couleur == "vert") {
                                fich.write("3:" + a + ":" + j + System.lineSeparator());
                            } else {
                                fich.write("6:" + a + ":" + j + System.lineSeparator());
                            }
                        } else if (plateau.plateau_jeu[a][j].getClass() == DemiSphere_spe.class) {
                            if (plateau.plateau_jeu[a][j].couleur == "vert") {
                                fich.write("7:" + a + ":" + j + System.lineSeparator());
                            } else {
                                fich.write("9:" + a + ":" + j + System.lineSeparator());
                            }
                        } else if (plateau.plateau_jeu[a][j].getClass() == pyramide_spe.class) {
                            if (plateau.plateau_jeu[a][j].couleur == "vert") {
                                fich.write("8:" + a + ":" + j + System.lineSeparator());
                            } else {
                                fich.write("10:" + a + ":" + j + System.lineSeparator());
                            }
                        }
                    }
                }
            }
            fich.write(" ");
            fich.close();
        } catch (IOException e) {
            System.out.println("Erreur : le fichier de sauvegarde n'existe pas.");
        }
    }

    public void ClickEvent(String Case, JButton[][] Cases) {
        //Permet de gérer tour du joueur et de définir un gagnant
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if (caseDepart.length() == 0) {
            
            // Le joueur a cliqué sur la case de départ
            caseDepart = Case;
            L_ = alphabet.indexOf(Case.charAt(0));

            try {
                C_ = Integer.parseInt(Case.substring(1, Case.length()));
            } catch (Exception E) {
                C_ = -1;
            }
            
            Cases[L_][C_].setBorder(new LineBorder(Color.BLUE, 5));

        } else {
            
            caseDepart = "";
            // Le joueur a cliqué sur la case d'arrivée
            newL_ = alphabet.indexOf(Case.charAt(0));
            try {
                newC_ = Integer.parseInt(Case.substring(1, Case.length()));
            } catch (Exception E) {
                newC_ = -1;
            }
            
            
            Border emptyBorder = BorderFactory.createEmptyBorder();
            Cases[L_][C_].setBorder(emptyBorder);

            boolean depl = plateau.deplacement(L_, C_, newL_, newC_, (tours % 2 == 0) ? J1.Couleur() : J2.Couleur());
            
            if(depl) tours++;
            
            if(plateau.Gagnant() != 0) {
                JOptionPane.showMessageDialog(null, "Le gagnant est " + (plateau.Gagnant() == 1? J1.Pseudo() : J2.Pseudo()));
            }
        }        
    }
    
    public piece[][] plateau_jeu() {
        return plateau.plateau_jeu;
    }
}
