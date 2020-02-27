
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import static javafx.scene.paint.Color.color;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MF
 */
public class Login extends javax.swing.JFrame {
    java.util.Date tglsekarang = new java.util.Date();
private SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd MMMMMMMMM yyyy", Locale.getDefault());
//diatas adalah pengaturan format penulisan, bisa diubah sesuai keinginan.
private String tanggal = smpdtfmt.format(tglsekarang);
 Connection con;
 Statement stat;
 ResultSet rs;
 String sql;
    private final Dimension screensize;
    
    public final void setJam(){
ActionListener taskPerformer = new ActionListener() {

public void actionPerformed(ActionEvent evt) {
String nol_jam = "", nol_menit = "",nol_detik = "";

java.util.Date dateTime = new java.util.Date();
int nilai_jam = dateTime.getHours();
int nilai_menit = dateTime.getMinutes();
int nilai_detik = dateTime.getSeconds();

if(nilai_jam <= 9) nol_jam= "0";
if(nilai_menit <= 9) nol_menit= "0";
if(nilai_detik <= 9) nol_detik= "0";

String jam = nol_jam + Integer.toString(nilai_jam);
String menit = nol_menit + Integer.toString(nilai_menit);
String detik = nol_detik + Integer.toString(nilai_detik);

}
};
new Timer(1000, taskPerformer).start();
}

    /**
     * Creates new form Login
     */
    public Login() {
        this.setAlwaysOnTop(true);
        initComponents();
        setJam();
        btnminimize.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae)
            {
            setState(ICONIFIED);
            }
            });
        
        txtusername.requestFocus(true);
        
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
      
        screensize = Toolkit.getDefaultToolkit().getScreenSize();
          setLocation((screensize.width / 2) - (getSize().width / 2), (screensize.height / 2) - (getSize().height / 2));
          
        btnlogin.setBackground(Color.black);
        btncancel.setBackground(Color.black);
        
        btnexit.setOpaque(false);
        btnexit.setContentAreaFilled(false); //to make the content area transparent
        btnexit.setBorderPainted(false);
        btnminimize.setOpaque(false);
        btnminimize.setContentAreaFilled(false); //to make the content area transparent
        btnminimize.setBorderPainted(false);//to make the borders transparent
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        btnlogin = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnexit = new javax.swing.JButton();
        btnminimize = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("A C C O U N T   N  A M E ");

        jLabel2.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("P  A  S  S  W O R D");

        txtusername.setBackground(new java.awt.Color(0, 0, 0));
        txtusername.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtusername.setForeground(new java.awt.Color(255, 255, 255));

        btnlogin.setBackground(new java.awt.Color(0, 0, 0));
        btnlogin.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnlogin.setForeground(new java.awt.Color(255, 255, 255));
        btnlogin.setText("L O G I N");
        btnlogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnloginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnloginMouseExited(evt);
            }
        });
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        btncancel.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.foreground"));
        btncancel.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btncancel.setForeground(new java.awt.Color(255, 255, 255));
        btncancel.setText("C  A N C E L");
        btncancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncancelMouseExited(evt);
            }
        });
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        txtpassword.setBackground(new java.awt.Color(0, 0, 0));
        txtpassword.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpasswordFocusGained(evt);
            }
        });
        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpasswordKeyPressed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SpareService-01.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mohammad Fahmi @ 2017");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FORM LOGIN");

        btnexit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnexit.setForeground(new java.awt.Color(153, 153, 153));
        btnexit.setText("X");
        btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnexit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnexitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnexitMouseExited(evt);
            }
        });
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        btnminimize.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnminimize.setForeground(new java.awt.Color(153, 153, 153));
        btnminimize.setText("_");
        btnminimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnminimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnminimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnminimizeMouseExited(evt);
            }
        });
        btnminimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnminimizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnminimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnexit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)))
                        .addGap(0, 54, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnexit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(btnminimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addContainerGap())
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

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        txtusername.setText("");
        txtpassword.setText("");
        txtusername.requestFocus(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed

       System.exit(0);  
            // TODO add your handling code here:
    }//GEN-LAST:event_btnexitActionPerformed

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        if (txtusername.getText().equals("") || txtpassword.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Masukkan Data Login Terlebih Dahulu");
        } else {
            try {
            sql = "SELECT * FROM login WHERE username='"+txtusername.getText()+"' AND password='"+txtpassword.getText()+"'";
            rs = stat.executeQuery(sql);
            if(rs.next()){
                if(txtusername.getText().equals(rs.getString("username")) && txtpassword.getText().equals(rs.getString("password"))){
                    String lvl = rs.getString("hak_akses");
                    String usm = rs.getString("username");
                    String id = rs.getString("id_login");
                    
                    if(lvl.equals("admin")) {
                        Form_Utama a = new Form_Utama();
                        a.setVisible(true);
                        a.gettxt_username().setText(usm);
                        a.gettxt_typeuser().setText(lvl);
                        a.gettxt_idpetugas().setText(id);
                        this.hide();
               
                    } 
                    else if(lvl.equals("Karyawan")) {
                        Form_Utama b = new Form_Utama();
                        b.setVisible(true);
                        b.gettxt_username().setText(usm);
                        b.gettxt_typeuser().setText(lvl);
                        b.gettxt_idpetugas().setText(id);
                        b.getbtn_pemberitahuan().setVisible(false);
                        b.getlbl_pemberitahuan().setVisible(false);
                        b.getbtn_petugas().setVisible(false);
                        b.getlblpetugas().setVisible(false);
                        b.getbtnsupplier().setVisible(false);
                        b.getlbl_pemberitahuan2().setVisible(false);
                        b.getlblsukucadang().setVisible(false);
                        b.getlbltransaksi().setVisible(false);
                        b.getbtn_petugas2().setVisible(false);
                        b.getlblshortcut_konsumen().setVisible(false);                        
                        b.getlblshortcut_pember().setVisible(false);
                        b.getbtn_pembelian().setVisible(false);
                        this.hide();
                        
                    }
                    
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Username atau Password Salah");
             txtusername.setText("");
             txtpassword.setText("");
             txtusername.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }         
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnloginActionPerformed

    private void txtpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpasswordFocusGained
      
// TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordFocusGained

    private void txtpasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnloginActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press Login"+ ""));
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordKeyPressed

    private void btnminimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminimizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnminimizeActionPerformed

    private void btnexitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnexitMouseEntered
        btnexit.setForeground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_btnexitMouseEntered

    private void btnexitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnexitMouseExited
        btnexit.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_btnexitMouseExited

    private void btnminimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminimizeMouseEntered
        btnminimize.setForeground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_btnminimizeMouseEntered

    private void btnminimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminimizeMouseExited
        btnminimize.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_btnminimizeMouseExited

    private void btnloginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnloginMouseEntered
        btnlogin.setBackground(Color.white);
        btnlogin.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btnloginMouseEntered

    private void btnloginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnloginMouseExited
        btnlogin.setBackground(Color.black);
        btnlogin.setForeground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_btnloginMouseExited

    private void btncancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelMouseEntered
        btncancel.setBackground(Color.white);
        btncancel.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btncancelMouseEntered

    private void btncancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelMouseExited
        btncancel.setBackground(Color.black);
        btncancel.setForeground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_btncancelMouseExited

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnlogin;
    private javax.swing.JButton btnminimize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables

    private Border LineBorder(Color BLACK) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}