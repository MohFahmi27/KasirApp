
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.ICONIFIED;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicMenuBarUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MF
 */



public class Form_Utama extends javax.swing.JFrame {
    Connection con;
    Statement sta;
    ResultSet rs;
    java.util.Date tglsekarang = new java.util.Date();
private SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd MMMMMMMMM yyyy", Locale.getDefault());
//diatas adalah pengaturan format penulisan, bisa diubah sesuai keinginan.
private String tanggal = smpdtfmt.format(tglsekarang);
      
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

lblwaktu.setText(jam+":"+menit+"");
}
};
new Timer(1000, taskPerformer).start();
}
    
    /**
     * Creates new form Form_Login
     */
    public Form_Utama() {
        initComponents();
        
        txt_tyuser.setVisible(false);
        txtusername.setVisible(false);
        txtid_petugas.setVisible(false);
                
        
         btnminimize.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae)
            {
            setState(ICONIFIED);
            }
            });
        
        setJam();
        lbltanggal.setText(tanggal);
        
        
        btnsupply.setBackground(Color.black);
        btnsetting.setBackground(Color.black);
        btnpetugas.setBackground(Color.black);
        btnstockbarang.setBackground(Color.black);
        btnminimize.setBackground(Color.black);
        btnpembelian_barang.setBackground(Color.black);
        btnstockbarang.setContentAreaFilled(false); //to make the content area transparent
        btntransaksi.setContentAreaFilled(false); //to make the content area transparen
        btnkonsumen.setContentAreaFilled(false);
        btnsuku_cadang.setContentAreaFilled(false);
        btnlogout.setBackground(Color.black);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);    
        
       
        
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        sta = DB.stm;
        
        pemberitahuan();
         String lbl = lblpemberitahuan.getText();
        int lbl2 = Integer.parseInt(lbl);
        if (lbl2 == 0) {
            lblpemberitahuan.setVisible(false);
            
        } else {
            lblpemberitahuan.setVisible(true);
        }
    }

    private void pemberitahuan() {
        try {
            String sql;
            sql = "SELECT COUNT(*) AS 'jumlah' FROM suku_cadang WHERE stock <= 3";
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                
                String jumlah_nya = rs.getString("jumlah");
                int jumlah = Integer.parseInt(jumlah_nya);
                if (jumlah == 0) {
                    lblpemberitahuan.setVisible(false);
                } else {lblpemberitahuan.setText(jumlah_nya);}
                
            }
        } catch (Exception e) {
        }
    }
    
    private void pemberitahuan_transaksi() {
        try {
            String sql;
            sql = "SELECT COUNT(*) AS 'jumlah' FROM transaksi WHERE status = 'UTANG'";
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                String jumlah_nya = rs.getString("jumlah");
                
                lblpemberitahuan_transaksi.setText(jumlah_nya);
            }
        } catch (Exception e) {
        }
    }
    
 public JTextField gettxt_username() {
        return txtusername;
    }
 
 public JLabel getlbl_pemberitahuan() {
     return label_pemberitahuan;
    
 }
         
public JLabel getlbl_pemberitahuan2() {
    return lblpemberitahuan;
}

    public JButton getbtn_pemberitahuan() {
        return btnstockbarang;
    }
    
    public JLabel getlblpetugas() {
        return lbl_detapetugas;
    }
    
    public JButton getbtn_petugas() {
        return btnkonsumen;
    }
    
    public JButton getbtn_petugas2() {
        return btnpetugas;
    }
    
    public JButton getbtnsupplier() {
        return btnsupply;
    }
    
    public JLabel getlblshortcut_pember() {
        return lblshortcut_pemberitahuan;
    }
    
    public JLabel getlblshortcut_konsumen() {
        return lblshortcut_konsumen;
    }
    
    public JButton getpembelian_barang() {
        return btnpembelian_barang;
    }
    
    public JLabel getlbltransaksi() {
        return lbltransaksi;
        
    }
    
    public JLabel getlblsukucadang() {
        return lblsuku_cadang;
    }
    
    public JButton getbtn_pembelian() {
        return btnpembelian_barang;
    }
    
    public void settxt_username(JTextField txt_username) {
        this.txtusername = txtusername;
    }
    
    public JTextField gettxt_typeuser() {
        return txt_tyuser;
    }

    public void settxt_typeuser(JTextField txt_username) {
        this.txt_tyuser = txt_tyuser;
    }
    
    public JTextField gettxt_idpetugas() {
        return txtid_petugas;
    }

    public void settxt_idpetugas(JTextField txt_username) {
        this.txtid_petugas = txtid_petugas;
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
        jPanel4 = new javax.swing.JPanel();
        btnpembelian_barang = new javax.swing.JButton();
        btnsupply = new javax.swing.JButton();
        btnpetugas = new javax.swing.JButton();
        btnsetting = new javax.swing.JButton();
        btnminimize = new javax.swing.JButton();
        btnlogout = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblshortcut_pemberitahuan = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblshortcut_konsumen = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtid_petugas = new javax.swing.JTextField();
        txtusername = new javax.swing.JTextField();
        txt_tyuser = new javax.swing.JTextField();
        lblwaktu = new javax.swing.JLabel();
        lbltanggal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnstockbarang = new javax.swing.JButton();
        btntransaksi = new javax.swing.JButton();
        btnkonsumen = new javax.swing.JButton();
        btnsuku_cadang = new javax.swing.JButton();
        lbltransaksi = new javax.swing.JLabel();
        lbl_detapetugas = new javax.swing.JLabel();
        lblsuku_cadang = new javax.swing.JLabel();
        label_pemberitahuan = new javax.swing.JLabel();
        lblpemberitahuan = new javax.swing.JLabel();
        lblpemberitahuan_transaksi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        btnpembelian_barang.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnpembelian_barang.setForeground(new java.awt.Color(255, 255, 255));
        btnpembelian_barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/shopping.png"))); // NOI18N
        btnpembelian_barang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnpembelian_barang.setFocusable(false);
        btnpembelian_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnpembelian_barangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnpembelian_barangMouseExited(evt);
            }
        });
        btnpembelian_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpembelian_barangActionPerformed(evt);
            }
        });
        jPanel4.add(btnpembelian_barang);

        btnsupply.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnsupply.setForeground(new java.awt.Color(255, 255, 255));
        btnsupply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hotel-supplier2.png"))); // NOI18N
        btnsupply.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsupply.setFocusable(false);
        btnsupply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupplyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupplyMouseExited(evt);
            }
        });
        btnsupply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupplyActionPerformed(evt);
            }
        });
        jPanel4.add(btnsupply);

        btnpetugas.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnpetugas.setForeground(new java.awt.Color(255, 255, 255));
        btnpetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        btnpetugas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnpetugas.setFocusable(false);
        btnpetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnpetugasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnpetugasMouseExited(evt);
            }
        });
        btnpetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpetugasActionPerformed(evt);
            }
        });
        jPanel4.add(btnpetugas);

        btnsetting.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnsetting.setForeground(new java.awt.Color(255, 255, 255));
        btnsetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/settings.png"))); // NOI18N
        btnsetting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsetting.setFocusable(false);
        btnsetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsettingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsettingMouseExited(evt);
            }
        });
        btnsetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsettingActionPerformed(evt);
            }
        });
        jPanel4.add(btnsetting);

        btnminimize.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnminimize.setForeground(new java.awt.Color(255, 255, 255));
        btnminimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diminish.png"))); // NOI18N
        btnminimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnminimize.setFocusable(false);
        btnminimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnminimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnminimizeMouseExited(evt);
            }
        });
        jPanel4.add(btnminimize);

        btnlogout.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnlogout.setForeground(new java.awt.Color(255, 255, 255));
        btnlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout (2).png"))); // NOI18N
        btnlogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlogout.setFocusable(false);
        btnlogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnlogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnlogoutMouseExited(evt);
            }
        });
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });
        jPanel4.add(btnlogout);

        jPanel1.add(jPanel4, java.awt.BorderLayout.LINE_END);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 15));

        lblshortcut_pemberitahuan.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        lblshortcut_pemberitahuan.setForeground(new java.awt.Color(255, 255, 255));
        lblshortcut_pemberitahuan.setText("ALT+1 = PEMBERITAHUAN;");
        jPanel5.add(lblshortcut_pemberitahuan);

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ALT+2 = TRANSAKSI;");
        jPanel5.add(jLabel6);

        lblshortcut_konsumen.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        lblshortcut_konsumen.setForeground(new java.awt.Color(255, 255, 255));
        lblshortcut_konsumen.setText("ALT+3 = DATA KONSUMEN;");
        jPanel5.add(lblshortcut_konsumen);

        jLabel8.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ALT+4 = SUKU CADANG;");
        jPanel5.add(jLabel8);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 40, 5));
        jPanel2.add(txtid_petugas);
        jPanel2.add(txtusername);
        jPanel2.add(txt_tyuser);

        lblwaktu.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        lblwaktu.setForeground(new java.awt.Color(255, 255, 255));
        lblwaktu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/time.png"))); // NOI18N
        jPanel2.add(lblwaktu);

        lbltanggal.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        lbltanggal.setForeground(new java.awt.Color(255, 255, 255));
        lbltanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendar (2).png"))); // NOI18N
        jPanel2.add(lbltanggal);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnstockbarang.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        btnstockbarang.setForeground(new java.awt.Color(0, 0, 0));
        btnstockbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exclamation.png"))); // NOI18N
        btnstockbarang.setMnemonic('1');
        btnstockbarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnstockbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnstockbarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnstockbarangMouseExited(evt);
            }
        });
        btnstockbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstockbarangActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(8, 25, 8, 25);
        jPanel3.add(btnstockbarang, gridBagConstraints);

        btntransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/coin.png"))); // NOI18N
        btntransaksi.setMnemonic('2');
        btntransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btntransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransaksiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(8, 25, 8, 25);
        jPanel3.add(btntransaksi, gridBagConstraints);

        btnkonsumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user128.png"))); // NOI18N
        btnkonsumen.setMnemonic('3');
        btnkonsumen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnkonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkonsumenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(8, 25, 8, 25);
        jPanel3.add(btnkonsumen, gridBagConstraints);

        btnsuku_cadang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-circular-button.png"))); // NOI18N
        btnsuku_cadang.setMnemonic('4');
        btnsuku_cadang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsuku_cadang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuku_cadangActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(8, 25, 8, 25);
        jPanel3.add(btnsuku_cadang, gridBagConstraints);

        lbltransaksi.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        lbltransaksi.setForeground(new java.awt.Color(255, 255, 255));
        lbltransaksi.setText("TRANSAKSI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel3.add(lbltransaksi, gridBagConstraints);

        lbl_detapetugas.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        lbl_detapetugas.setForeground(new java.awt.Color(255, 255, 255));
        lbl_detapetugas.setText("DATA PETUGAS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel3.add(lbl_detapetugas, gridBagConstraints);

        lblsuku_cadang.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        lblsuku_cadang.setForeground(new java.awt.Color(255, 255, 255));
        lblsuku_cadang.setText("SUKU CADANG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel3.add(lblsuku_cadang, gridBagConstraints);

        label_pemberitahuan.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        label_pemberitahuan.setForeground(new java.awt.Color(255, 255, 255));
        label_pemberitahuan.setText("PEMBERITAHUAN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        jPanel3.add(label_pemberitahuan, gridBagConstraints);

        lblpemberitahuan.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblpemberitahuan.setForeground(new java.awt.Color(204, 0, 0));
        lblpemberitahuan.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 24, 0, 24);
        jPanel3.add(lblpemberitahuan, gridBagConstraints);

        lblpemberitahuan_transaksi.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblpemberitahuan_transaksi.setForeground(new java.awt.Color(204, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 49, 0, 49);
        jPanel3.add(lblpemberitahuan_transaksi, gridBagConstraints);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsupplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupplyActionPerformed
       Supplier a = new Supplier();
       a.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupplyActionPerformed

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
         int confirmed = JOptionPane.showConfirmDialog(null, 
        "Yakin Ingin Keluar", "Log Out",
        JOptionPane.YES_NO_OPTION); 
                //dibuat untuk melakukan konfirmasi 
    if (confirmed == JOptionPane.YES_OPTION) {
      this.dispose();
       Login a = new Login();
       a.setVisible(true);
    }
        
              
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btnsettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsettingActionPerformed
        String type = txtusername.getText();
        String hak_akses = txt_tyuser.getText();
        String id = txtid_petugas.getText();
        Pengaturan_Profil a = new Pengaturan_Profil();
        a.getlblhak_akses().setText(hak_akses);
        a.gettxt_typeuser().setText(id);
        a.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsettingActionPerformed

    private void btnsuku_cadangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuku_cadangActionPerformed
        new Suku_Cadang().setVisible(true);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsuku_cadangActionPerformed

    private void btnpetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpetugasActionPerformed
        Pembuatan_Login a = new Pembuatan_Login();
        a.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnpetugasActionPerformed

    private void btnstockbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstockbarangActionPerformed
        Pemberitahuan a = new Pemberitahuan();
        a.setVisible(true);
        a.gettxt_username().setText(txtusername.getText());
        a.gettxtid_petugas().setText(txtid_petugas.getText());
    }//GEN-LAST:event_btnstockbarangActionPerformed

    private void btntransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransaksiActionPerformed
        Transaksi a = new Transaksi();
        a.setVisible(true);
        a.getlbl_username().setText(txtusername.getText());
        a.getlbl_id_petugas().setText(txtid_petugas.getText());
    }//GEN-LAST:event_btntransaksiActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        pemberitahuan();
        
       
    }//GEN-LAST:event_formWindowActivated

    private void btnsupplyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupplyMouseEntered
        btnsupply.setText("SUPPLIER");
    }//GEN-LAST:event_btnsupplyMouseEntered

    private void btnsupplyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupplyMouseExited
        btnsupply.setText("");
    }//GEN-LAST:event_btnsupplyMouseExited

    private void btnsettingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsettingMouseEntered
        btnsetting.setText("PROFIL SET.");
    }//GEN-LAST:event_btnsettingMouseEntered

    private void btnsettingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsettingMouseExited
        btnsetting.setText("");
    }//GEN-LAST:event_btnsettingMouseExited

    private void btnpetugasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpetugasMouseEntered
        btnpetugas.setText("LOGIN SET.");
    }//GEN-LAST:event_btnpetugasMouseEntered

    private void btnpetugasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpetugasMouseExited
        btnpetugas.setText("");
    }//GEN-LAST:event_btnpetugasMouseExited

    private void btnminimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminimizeMouseEntered
        
        btnminimize.setText("MINIMIZE");
    }//GEN-LAST:event_btnminimizeMouseEntered

    private void btnminimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminimizeMouseExited
        
        btnminimize.setText("");
    }//GEN-LAST:event_btnminimizeMouseExited

    private void btnlogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseEntered
        
        btnlogout.setText("LOG OUT");
    }//GEN-LAST:event_btnlogoutMouseEntered

    private void btnlogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseExited
        btnlogout.setText("");
    }//GEN-LAST:event_btnlogoutMouseExited

    private void btnstockbarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnstockbarangMouseEntered
       
        
    }//GEN-LAST:event_btnstockbarangMouseEntered

    private void btnstockbarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnstockbarangMouseExited
       
    }//GEN-LAST:event_btnstockbarangMouseExited

    private void btnpembelian_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpembelian_barangActionPerformed
        Pembelian_Barang_V02 a  = new Pembelian_Barang_V02();
        a.setVisible(true);
        a.getlblid_petugas().setText(txtid_petugas.getText());
        a.getlblusername().setText(txtusername.getText());
    }//GEN-LAST:event_btnpembelian_barangActionPerformed

    private void btnpembelian_barangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpembelian_barangMouseEntered
        btnpembelian_barang.setText("PEMB.BARANG");
    }//GEN-LAST:event_btnpembelian_barangMouseEntered

    private void btnpembelian_barangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpembelian_barangMouseExited
        btnpembelian_barang.setText("");
    }//GEN-LAST:event_btnpembelian_barangMouseExited

    private void btnkonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkonsumenActionPerformed
        Pembuatan_Login a = new Pembuatan_Login();
        a.setVisible(true);
    }//GEN-LAST:event_btnkonsumenActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnkonsumen;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnminimize;
    private javax.swing.JButton btnpembelian_barang;
    private javax.swing.JButton btnpetugas;
    private javax.swing.JButton btnsetting;
    private javax.swing.JButton btnstockbarang;
    private javax.swing.JButton btnsuku_cadang;
    private javax.swing.JButton btnsupply;
    private javax.swing.JButton btntransaksi;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel label_pemberitahuan;
    private javax.swing.JLabel lbl_detapetugas;
    private javax.swing.JLabel lblpemberitahuan;
    private javax.swing.JLabel lblpemberitahuan_transaksi;
    private javax.swing.JLabel lblshortcut_konsumen;
    private javax.swing.JLabel lblshortcut_pemberitahuan;
    private javax.swing.JLabel lblsuku_cadang;
    private javax.swing.JLabel lbltanggal;
    private javax.swing.JLabel lbltransaksi;
    private javax.swing.JLabel lblwaktu;
    private javax.swing.JTextField txt_tyuser;
    private javax.swing.JTextField txtid_petugas;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables

    private void setContentAreaFilled(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
