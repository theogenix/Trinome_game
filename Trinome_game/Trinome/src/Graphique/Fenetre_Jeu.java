/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import projet_trinome.DemiSphere;
import projet_trinome.Jeu;
import projet_trinome.piece;

import projet_trinome.Cube;
import projet_trinome.DemiSphere;
import projet_trinome.DemiSphere_spe;
import projet_trinome.pyramide;
import projet_trinome.pyramide_spe;

/**
 *
 * @author Louis
 */
public class Fenetre_Jeu extends javax.swing.JDialog {

    private JButton[][] Cases;
    public String Case_clique;
    public Jeu jeu;

    /**
     * Creates new form Fenetre_Jeu
     */
    public Fenetre_Jeu(java.awt.Frame parent, boolean modal, Jeu jeu) {
        super(parent, modal);
        Cases = new JButton[23][23];
        initComponents();

        this.jeu = jeu;

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String texte_case;
        int cpt = 0;
        int cptpair = 0;
        for (int i = 0; i < 23; i++) { //Initialisation du plateau de jeu avec un tableau de JButton
            for (int j = 0; j < 23; j++) {
                Cases[i][j] = new JButton();
                Cases[i][j].setFont(new java.awt.Font("Tahoma", 0, 1));
                Cases[i][j].setText(alphabet.charAt(i) + Integer.toString(j));
                Cases[i][j].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                cptpair = cpt % 2;
                if (cptpair == 0) {
                    Cases[i][j].setForeground(new java.awt.Color(200, 200, 200));
                    Cases[i][j].setBackground(new java.awt.Color(255, 255, 255));
                } else {
                    Cases[i][j].setForeground(new java.awt.Color(0, 0, 0));
                    Cases[i][j].setBackground(new java.awt.Color(0, 0, 0));
                }
                cpt++;
                Cases[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        String text = ((JButton) evt.getSource()).getText();
                        jeu.ClickEvent(text, Cases);
                        
                        MAJ_Affichage();
                    }
                });
                pPlateau.add(Cases[i][j]);
            }
        }
                
        MAJ_Affichage();
    }
    public void MAJ_Affichage() { //Met à jour l'affichage des pièces à chaque tour, suivant leurs déplacement et actions
        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 23; j++) {
                piece[][] plateau = jeu.plateau_jeu();
                
                if (plateau[i][j] == null) {
                    Cases[i][j].setIcon(null);
                } else if (plateau[i][j].getClass() == DemiSphere.class) {
                    String path = plateau[i][j].couleur == "vert" ? "/Images/DemiS_V.png" : "/Images/DemiS_R.png";
                    ImageIcon icon = new ImageIcon(getClass().getResource(path));   
                    Image img = icon.getImage() ;  
                    Image newimg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
                    icon = new ImageIcon( newimg );
                    Cases[i][j].setIcon(icon);
                } else if (plateau[i][j].getClass() == pyramide.class) {
                    String path = plateau[i][j].couleur == "vert" ? "/Images/Pyramide_V.png" : "/Images/Pyramide_R.png";
                    ImageIcon icon = new ImageIcon(getClass().getResource(path));   
                    Image img = icon.getImage() ;  
                    Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
                    icon = new ImageIcon( newimg );
                    Cases[i][j].setIcon(icon);
                } else if (plateau[i][j].getClass() == Cube.class) {
                    String path = plateau[i][j].couleur == "vert" ? "/Images/Cube_V.png" : "/Images/Cube_R.png";
                    ImageIcon icon = new ImageIcon(getClass().getResource(path));   
                    Image img = icon.getImage() ;  
                    Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
                    icon = new ImageIcon( newimg );
                    Cases[i][j].setIcon(icon);
                }
                else if (plateau[i][j].getClass() == DemiSphere_spe.class) {
                    String path = plateau[i][j].couleur == "vert" ? "/Images/DemiS_V_spe.png" : "/Images/DemiS_R_spe.png";
                    ImageIcon icon = new ImageIcon(getClass().getResource(path));   
                    Image img = icon.getImage() ;  
                    Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
                    icon = new ImageIcon( newimg );
                    Cases[i][j].setIcon(icon);
                }
                else if (plateau[i][j].getClass() == pyramide_spe.class) {
                    String path = plateau[i][j].couleur == "vert" ? "/Images/Pyramide_V_spe.png" : "/Images/Pyramide_R_spe.png";
                    ImageIcon icon = new ImageIcon(getClass().getResource(path));   
                    Image img = icon.getImage() ;  
                    Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
                    icon = new ImageIcon( newimg );
                    Cases[i][j].setIcon(icon);
                }
            }
        }
        //Affiche quel joueur doit jouer
        if (jeu.tours % 2 == 0){ 
            lTourJ1.setVisible(true);
            lTourJ2.setVisible(false);
        }
        else{
            lTourJ1.setVisible(false);
            lTourJ2.setVisible(true);
        }
        //Affiche les cases rouges 
        Cases[0][10].setBorder(new LineBorder(Color.RED, 2));
        Cases[0][11].setBorder(new LineBorder(Color.RED, 2));
        Cases[0][12].setBorder(new LineBorder(Color.RED, 2));
        Cases[22][10].setBorder(new LineBorder(Color.RED, 2));
        Cases[22][11].setBorder(new LineBorder(Color.RED, 2));
        Cases[22][12].setBorder(new LineBorder(Color.RED, 2));
    }

    public void SetPseudo(){
        String pseudo1 = jeu.J1.Pseudo();
        String pseudo2 = jeu.J2.Pseudo();
        lTourJ1.setText("Au tour de " + pseudo1);
        lTourJ2.setText("Au tour de " + pseudo2);
    }
    
    public void ActionCases(ActionEvent evt) {
        String text = ((JButton) evt.getSource()).getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pPlateau = new javax.swing.JPanel();
        lTourJ1 = new javax.swing.JLabel();
        lTourJ2 = new javax.swing.JLabel();
        bSauvQuitter = new javax.swing.JButton();
        bSauvRetour = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        pPlateau.setLayout(new java.awt.GridLayout(23, 23));

        lTourJ1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lTourJ1.setForeground(new java.awt.Color(0, 153, 0));
        lTourJ1.setText("Au tour du J1");

        lTourJ2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lTourJ2.setForeground(new java.awt.Color(204, 0, 0));
        lTourJ2.setText("Au tour du J2");

        bSauvQuitter.setText("Sauvegarder et quitter");
        bSauvQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSauvQuitterActionPerformed(evt);
            }
        });

        bSauvRetour.setText("Retour menu principal");
        bSauvRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSauvRetourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bSauvRetour)
                    .addComponent(pPlateau, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lTourJ2)
                        .addComponent(lTourJ1))
                    .addComponent(bSauvQuitter, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(pPlateau, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSauvQuitter)
                    .addComponent(bSauvRetour))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(lTourJ1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lTourJ2)
                .addGap(81, 81, 81))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSauvQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSauvQuitterActionPerformed
        jeu.Sauvegarder();
        System.exit(0);
    }//GEN-LAST:event_bSauvQuitterActionPerformed

    private void bSauvRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSauvRetourActionPerformed
        jeu.Sauvegarder();
        this.getParent().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bSauvRetourActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fenetre_Jeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fenetre_Jeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fenetre_Jeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fenetre_Jeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Fenetre_Jeu dialog = new Fenetre_Jeu(new javax.swing.JFrame(), true, new Jeu(1, "", "",2));
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception E) {

                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSauvQuitter;
    private javax.swing.JButton bSauvRetour;
    private javax.swing.JLabel lTourJ1;
    private javax.swing.JLabel lTourJ2;
    private javax.swing.JPanel pPlateau;
    // End of variables declaration//GEN-END:variables
}
