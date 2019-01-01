/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.Apostador;
import business.BetESS;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author vitorpeixoto
 */
public class DepositarLevantar extends javax.swing.JFrame {

    BetESS betess;
    Apostador apostador;
    /**
     * Creates new form DepositarLevantar
     */
    public DepositarLevantar(BetESS b, Apostador a) {
        initComponents();
        this.betess = b;
        this.apostador = a;
        setStyle();
    }
    
    private void setStyle(){
        this.setTitle("Depositar/Levantar");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/logo2.png"));
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(155, 35, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        logo.setIcon(icon);
        this.Saldo.setText("Saldo atual: " + apostador.getESSCoins() + " ESScoins");
        perfilButton.setBackground(new Color(0,0,0));
        perfilButton.setContentAreaFilled(false);
        perfilButton.setOpaque(true);
        perfilButton.setText(this.apostador.getNome());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        perfilButton = new javax.swing.JButton();
        apostasButton = new javax.swing.JButton();
        eventosButton = new javax.swing.JButton();
        Saldo = new javax.swing.JLabel();
        Levantar = new javax.swing.JLabel();
        Levantar1 = new javax.swing.JLabel();
        levSpinner = new javax.swing.JSpinner();
        levButton = new javax.swing.JButton();
        depSpinner = new javax.swing.JSpinner();
        depButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        logo.setText("jLabel1");

        perfilButton.setBackground(new java.awt.Color(0, 0, 0));
        perfilButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        perfilButton.setForeground(new java.awt.Color(255, 102, 102));
        perfilButton.setText("Perfil");
        perfilButton.setBorderPainted(false);
        perfilButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        perfilButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perfilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilButtonActionPerformed(evt);
            }
        });

        apostasButton.setBackground(new java.awt.Color(0, 0, 0));
        apostasButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        apostasButton.setForeground(new java.awt.Color(204, 204, 204));
        apostasButton.setText("Minhas Apostas");
        apostasButton.setBorderPainted(false);
        apostasButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        apostasButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        apostasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apostasButtonActionPerformed(evt);
            }
        });

        eventosButton.setBackground(new java.awt.Color(0, 0, 0));
        eventosButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        eventosButton.setForeground(new java.awt.Color(204, 204, 204));
        eventosButton.setText("Eventos ativos");
        eventosButton.setBorderPainted(false);
        eventosButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eventosButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eventosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventosButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(apostasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(perfilButton, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(perfilButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(apostasButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventosButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Saldo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Saldo.setForeground(new java.awt.Color(255, 255, 255));
        Saldo.setText("Saldo:");

        Levantar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Levantar.setForeground(new java.awt.Color(255, 255, 255));
        Levantar.setText("Levantar");

        Levantar1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Levantar1.setForeground(new java.awt.Color(255, 255, 255));
        Levantar1.setText("Depositar");

        levSpinner.setModel(new javax.swing.SpinnerNumberModel(20, 20, 1000, 1));

        levButton.setText("Levantar");
        levButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                levButtonActionPerformed(evt);
            }
        });

        depSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));

        depButton.setText("Depositar");
        depButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Levantar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Saldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Levantar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(levSpinner)
                    .addComponent(depSpinner, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(depButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(levButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Saldo)
                .addGap(41, 41, 41)
                .addComponent(Levantar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(levButton)
                    .addComponent(levSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(Levantar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(depSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(depButton))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void apostasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apostasButtonActionPerformed
        MinhasApostas ma = new MinhasApostas(this.betess, apostador);
        ma.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_apostasButtonActionPerformed

    private void eventosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventosButtonActionPerformed
        Home home = new Home(this.betess, apostador);
        home.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_eventosButtonActionPerformed

    private void levButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_levButtonActionPerformed
        int quantia = (Integer) levSpinner.getValue();
        if (apostador.getESSCoins()-quantia < 5)
            this.betess.notification(3, "O seu saldo atual não lhe permite levantar essa quantia. Tem de manter um saldo mínimo de 5 ESScoins!", "Aviso");
        else{
            this.betess.getApostadores().get(apostador.getID()).levantarESSCoins(quantia);
            this.saveNrefresh();
            this.betess.notification(1, "Quantia depositada na sua conta bancária!", "Sucesso");
        }
    }//GEN-LAST:event_levButtonActionPerformed

    private void depButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depButtonActionPerformed
        int quantia = (Integer) depSpinner.getValue();
        this.betess.getApostadores().get(apostador.getID()).adicionarESSCoins(quantia);
        this.saveNrefresh();
        this.betess.notification(1, "Quantia adicionada com sucesso!", "Sucesso");
    }//GEN-LAST:event_depButtonActionPerformed

    private void perfilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilButtonActionPerformed
        Login login = new Login(this.betess);
        login.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_perfilButtonActionPerformed

    //Função para remover código duplicado
    private void saveNrefresh(){
        try {
            betess.save(betess);
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DepositarLevantar dl = new DepositarLevantar(this.betess, this.apostador);
        dl.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }
    
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
            java.util.logging.Logger.getLogger(DepositarLevantar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepositarLevantar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepositarLevantar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepositarLevantar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Levantar;
    private javax.swing.JLabel Levantar1;
    private javax.swing.JLabel Saldo;
    private javax.swing.JButton apostasButton;
    private javax.swing.JButton depButton;
    private javax.swing.JSpinner depSpinner;
    private javax.swing.JButton eventosButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton levButton;
    private javax.swing.JSpinner levSpinner;
    private javax.swing.JLabel logo;
    private javax.swing.JButton perfilButton;
    // End of variables declaration//GEN-END:variables
}
