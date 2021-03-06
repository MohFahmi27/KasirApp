
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MF
 */
public class Pengaturan_Profil extends javax.swing.JFrame {

    private final Dimension screensize;
    private final Connection con;
    private final Statement sta;

    /**
     * Creates new form Pengaturan_Profil
     */
    public Pengaturan_Profil() {
        initComponents();

        //lblusername.setVisible(false);
        //lblhak_akses.setVisible(false);
        btnbatal.setBackground(Color.black);
        btnubah.setBackground(Color.black);

        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screensize.width / 2) - (getSize().width / 2), (screensize.height / 2) - (getSize().height / 2));

        btnback.setContentAreaFilled(false);

        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        sta = DB.stm;
    }

    public JLabel gettxt_typeuser() {
        return lblusername;
    }

    public JLabel getlblhak_akses() {
        return lblhak_akses;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        btnback = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblusername = new javax.swing.JLabel();
        lblhak_akses = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        txtrepassword = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        btnubah = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pengaturan Profil");
        setMinimumSize(new java.awt.Dimension(583, 331));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/left-arrow.png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });
        jPanel1.add(btnback, java.awt.BorderLayout.LINE_START);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PENGATURAN PROFIL");
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 5));

        lblusername.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        lblusername.setForeground(new java.awt.Color(255, 255, 255));
        lblusername.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        jPanel4.add(lblusername);

        lblhak_akses.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        lblhak_akses.setForeground(new java.awt.Color(255, 255, 255));
        lblhak_akses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_lock.png"))); // NOI18N
        jPanel4.add(lblhak_akses);

        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel2Layout.rowHeights = new int[] {0, 5, 0, 5, 0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel5.setFont(new java.awt.Font("Palatino Linotype", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(13, 10, 13, 10);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Palatino Linotype", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(13, 10, 13, 10);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Palatino Linotype", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Re-Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(13, 10, 13, 10);
        jPanel2.add(jLabel7, gridBagConstraints);

        txtusername.setBackground(new java.awt.Color(0, 0, 0));
        txtusername.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtusername.setForeground(new java.awt.Color(255, 255, 255));
        txtusername.setPreferredSize(new java.awt.Dimension(175, 30));
        txtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusernameKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel2.add(txtusername, gridBagConstraints);

        txtpassword.setBackground(new java.awt.Color(0, 0, 0));
        txtpassword.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtpassword.setPreferredSize(new java.awt.Dimension(175, 30));
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpasswordKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel2.add(txtpassword, gridBagConstraints);

        txtrepassword.setBackground(new java.awt.Color(0, 0, 0));
        txtrepassword.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtrepassword.setForeground(new java.awt.Color(255, 255, 255));
        txtrepassword.setPreferredSize(new java.awt.Dimension(175, 30));
        txtrepassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrepasswordKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel2.add(txtrepassword, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 10));

        btnubah.setFont(new java.awt.Font("Lucida Bright", 1, 10)); // NOI18N
        btnubah.setForeground(new java.awt.Color(255, 255, 255));
        btnubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnubah.setText("UBAH");
        btnubah.setPreferredSize(new java.awt.Dimension(107, 38));
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });
        jPanel3.add(btnubah);

        btnbatal.setFont(new java.awt.Font("Lucida Bright", 1, 10)); // NOI18N
        btnbatal.setForeground(new java.awt.Color(255, 255, 255));
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/error.png"))); // NOI18N
        btnbatal.setText("BATAL");
        btnbatal.setPreferredSize(new java.awt.Dimension(107, 38));
        jPanel3.add(btnbatal);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        String pass, pass2;
        pass = txtpassword.getText();
        pass2 = txtrepassword.getText();
        if (txtusername.getText().equals("")
                || txtpassword.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Harap Isi Semua Data");
            txtusername.requestFocus(true);
            txtpassword.setText("");
            txtrepassword.setText("");
        } else if (pass.equals(pass2)) {
            try {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Yakin Ingin Mengubah Profil \n Dengan Perubahan ini Program akan Menutup", "Pengubahan Profil",
                        JOptionPane.YES_NO_OPTION);
                //dibuat untuk melakukan konfirmasi
                if (confirmed == JOptionPane.YES_OPTION) {
                    String sql = "UPDATE login set username='" + txtusername.getText() + "', password='" + txtpassword.getText() + "' where id_login='" + lblusername.getText() + "'";
                    sta.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Harap Login Ulang");
                    System.exit(0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "GAGAL DI UBAH" + e);
                System.out.println(e.getMessage());
            }
            //
        } else {
            JOptionPane.showMessageDialog(rootPane, "Password Tidak Sama");
            txtpassword.requestFocus(true);
            txtpassword.setText("");
            txtrepassword.setText("");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnubahActionPerformed

    private void txtrepasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrepasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnubahActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press" + ""));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrepasswordKeyPressed

    private void txtpasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnubahActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press" + ""));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordKeyPressed

    private void txtusernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnubahActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press" + ""));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameKeyPressed

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
            java.util.logging.Logger.getLogger(Pengaturan_Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengaturan_Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengaturan_Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengaturan_Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengaturan_Profil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnubah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblhak_akses;
    private javax.swing.JLabel lblusername;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JPasswordField txtrepassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
