
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MF
 */
public class Supplier extends javax.swing.JFrame {
    Connection con;
    Statement sta;
    ResultSet rs;
    DefaultTableModel tbm;
    private final Statement stat;

    /**
     * Creates new form Supplier
     */
    public Supplier() {
        initComponents();
        
       
        /*btnminimize.setContentAreaFilled(false);
        btnlaporan.setContentAreaFilled(false);
        btnlogout.setContentAreaFilled(false);*/
        
        btnhapus.setBackground(Color.black);
        btnedit.setBackground(Color.black);
        btntambah.setBackground(Color.black);
        btncari.setBackground(Color.black);
        btnbatal.setBackground(Color.black);
        btnback.setContentAreaFilled(false);
        
        btnedit.setEnabled(false);
        btnhapus.setEnabled(false);
        txtid.setEditable(false);
        txtnama.requestFocus(true);
       
        jTable2.getTableHeader().setDefaultRenderer(new HeaderColor());
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
        
         koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        masuktable();
        autonomor();
        
    }

    private void kosong() {
        txtnama.setText("");
        txtalamat.setText("");
        txttelepon.setText("");
        autonomor();
    }
    
    
    
    private void masuktable() {
        try{
                sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                tbm = new DefaultTableModel();
                tbm.addColumn("ID SUPPLIER");
                tbm.addColumn("NAMA SUPPLIER");
                tbm.addColumn("ALAMAT");
                tbm.addColumn("NO TELP");
                
                jTable2.setModel(tbm);
                rs = sta.executeQuery("SELECT * FROM supplier");
                while (rs.next()){
                    tbm.addRow(new Object[]{
                    rs.getString("id_supplier"),
                    rs.getString("nm_supplier"),
                    rs.getString("alamat"),
                    rs.getString("no_telp"),
                    
                    
                });
                    jTable2.setModel(tbm);
                    }
                }catch(Exception e){
                        JOptionPane.showMessageDialog(rootPane,"Gagal"+e);
                        
                    }
    }
    
    private void autonomor(){
        String sql = "select max(id_supplier) from supplier";
        try{
            rs = sta.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                txtid.setText(""+ Integer.toString(a+1));
            }
        }catch (Exception e){
            System.out.println(""+ e.getMessage());
        }

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
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btntambah = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        txttelepon = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1380, 809));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1380, 809));

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
        jLabel1.setText("SUPPLIER");
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btntambah.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btntambah.setForeground(new java.awt.Color(255, 255, 255));
        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btntambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntambahMouseExited(evt);
            }
        });
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        jPanel5.add(btntambah);

        btnedit.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnedit.setForeground(new java.awt.Color(255, 255, 255));
        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/diskette-outline-with-rounded-edges.png"))); // NOI18N
        btnedit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btneditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btneditMouseExited(evt);
            }
        });
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel5.add(btnedit);

        btnhapus.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnhapus.setForeground(new java.awt.Color(255, 255, 255));
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash.png"))); // NOI18N
        btnhapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnhapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnhapusMouseExited(evt);
            }
        });
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel5.add(btnhapus);

        btnbatal.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnbatal.setForeground(new java.awt.Color(255, 255, 255));
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnbatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnbatalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnbatalMouseExited(evt);
            }
        });
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        jPanel5.add(btnbatal);

        jPanel2.add(jPanel5);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID SUPPLIER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(11, 29, 11, 29);
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NAMA SUPPLIER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(11, 28, 11, 28);
        jPanel4.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ALAMAT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(11, 28, 11, 28);
        jPanel4.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("NO. TELEPON");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(11, 28, 11, 28);
        jPanel4.add(jLabel5, gridBagConstraints);

        txtid.setBackground(new java.awt.Color(0, 0, 0));
        txtid.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtid.setForeground(new java.awt.Color(255, 255, 255));
        txtid.setToolTipText("");
        txtid.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtid.setPreferredSize(new java.awt.Dimension(208, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(39, 63, 39, 63);
        jPanel4.add(txtid, gridBagConstraints);

        txtnama.setBackground(new java.awt.Color(0, 0, 0));
        txtnama.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtnama.setForeground(new java.awt.Color(255, 255, 255));
        txtnama.setNextFocusableComponent(txtalamat);
        txtnama.setPreferredSize(new java.awt.Dimension(208, 35));
        txtnama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnamaKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(39, 63, 39, 63);
        jPanel4.add(txtnama, gridBagConstraints);

        txtalamat.setBackground(new java.awt.Color(0, 0, 0));
        txtalamat.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtalamat.setForeground(new java.awt.Color(255, 255, 255));
        txtalamat.setNextFocusableComponent(txttelepon);
        txtalamat.setPreferredSize(new java.awt.Dimension(208, 75));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(39, 63, 39, 63);
        jPanel4.add(txtalamat, gridBagConstraints);

        txttelepon.setBackground(new java.awt.Color(0, 0, 0));
        txttelepon.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txttelepon.setForeground(new java.awt.Color(255, 255, 255));
        txttelepon.setPreferredSize(new java.awt.Dimension(208, 35));
        txttelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtteleponKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtteleponKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(39, 63, 39, 63);
        jPanel4.add(txttelepon, gridBagConstraints);

        jPanel6.add(jPanel4, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel6, java.awt.BorderLayout.LINE_START);

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new java.awt.GridBagLayout());
        jPanel3.add(jPanel7, java.awt.BorderLayout.LINE_END);

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hotel-supplier256.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 16);
        jPanel8.add(jLabel6, gridBagConstraints);

        txtcari.setBackground(new java.awt.Color(0, 0, 0));
        txtcari.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtcari.setForeground(new java.awt.Color(255, 255, 255));
        txtcari.setPreferredSize(new java.awt.Dimension(250, 40));
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(23, 5, 23, 5);
        jPanel8.add(txtcari, gridBagConstraints);

        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/magnifying-glass-search.png"))); // NOI18N
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel8.add(btncari, gridBagConstraints);

        jPanel3.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 150));

        jTable2.setBackground(new java.awt.Color(51, 51, 51));
        jTable2.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setOpaque(false);
        jTable2.setPreferredSize(new java.awt.Dimension(300, 250));
        jTable2.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel9.add(jScrollPane2, java.awt.BorderLayout.SOUTH);

        jPanel3.add(jPanel9, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbackActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        if (txtnama.getText().equals("") || 
            txtalamat.getText().equals("") || 
            txttelepon.getText().equals("")) 
        {
            JOptionPane.showMessageDialog(rootPane, "Isi Semua Data Terlebih Dahulu!");
                
        } else {
            try {
                String sql;
                sql = ("INSERT INTO supplier VALUES('"+ txtid.getText() +"','"+ txtnama.getText() +"','"+ txtalamat.getText() +"','"+ txttelepon.getText() +"')");
                sta.executeUpdate(sql);
                JOptionPane.showMessageDialog(rootPane, "BERHASIL");
      
                kosong();
                masuktable();
                
                txtnama.requestFocus(true);
                
            } catch (Exception e) {
            }
        } 
        
// TODO add your handling code here:
    }//GEN-LAST:event_btntambahActionPerformed

    private void txtteleponKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtteleponKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btntambahActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press"+ ""));
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtteleponKeyPressed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        btnedit.setEnabled(true);
        btntambah.setEnabled(false);
        btnhapus.setEnabled(true);
                
        txtid.setText(tbm.getValueAt(jTable2.getSelectedRow(), 0)+"");
        txtnama.setText(tbm.getValueAt(jTable2.getSelectedRow(), 1)+"");
        txtalamat.setText(tbm.getValueAt(jTable2.getSelectedRow(), 2)+"");
        txttelepon.setText(tbm.getValueAt(jTable2.getSelectedRow(), 3)+ "");
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        int confirmed = JOptionPane.showConfirmDialog(null, 
        "Yakin Ingin Menghapus Data", "HAPUS DATA",
        JOptionPane.YES_NO_OPTION); 
                                        //dibuat untuk melakukan konfirmasi 
    if (confirmed == JOptionPane.YES_OPTION) {
      try {
            String sql;
            sql = ("DELETE FROM supplier WHERE id_supplier='" + txtid.getText() + "'");
            sta.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"DATA DIHAPUS");
            
            kosong();
            masuktable();
            
             btnedit.setEnabled(false);
            btntambah.setEnabled(true);
            btnhapus.setEnabled(false);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        int confirmed = JOptionPane.showConfirmDialog(null,
            "Yakin Ingin Merubah Data", "EDIT DATA",
            JOptionPane.YES_NO_OPTION);
        //dibuat untuk melakukan konfirmasi
        if (confirmed == JOptionPane.YES_OPTION) {
        try {
            
            String sql;
            
            sql = ("UPDATE supplier set nm_supplier='" +txtnama.getText()+ "', "
                    + "alamat = '" +txtalamat.getText()+ "', no_telp='" 
                    +txttelepon.getText()+ "' WHERE id_supplier='" +txtid.getText()+ "'");
            
            sta.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null,"DATA DIEDIT");
            
            kosong();
            masuktable();
            
            btnedit.setEnabled(false);
            btntambah.setEnabled(true);
            btnhapus.setEnabled(false);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditActionPerformed

    private void txtteleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtteleponKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
                evt.consume();
}
        // TODO add your handling code here:
    }//GEN-LAST:event_txtteleponKeyTyped

    private void txtnamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnamaKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= 'A') && (karakter <= 'z') || (karakter == KeyEvent.VK_SPACE) || (karakter == KeyEvent.VK_BACKSPACE)))){
            getToolkit().beep();
                evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaKeyTyped

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        TableRowSorter sorter = new TableRowSorter(jTable2.getModel());
        jTable2.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(Pattern.compile("(?i).*"+ txtcari.getText()+".*",Pattern.CASE_INSENSITIVE | Pattern.DOTALL).toString()));
        jTable2.setSelectionMode(1);
        // TODO add your handling code here:
    }//GEN-LAST:event_btncariActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btncariActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press"+ ""));
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyPressed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        btnedit.setEnabled(false);
        btnhapus.setEnabled(false);
        btntambah.setEnabled(true);
        kosong();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btntambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahMouseEntered
        
        btntambah.setText("TAMBAH");
    }//GEN-LAST:event_btntambahMouseEntered

    private void btntambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahMouseExited
        btntambah.setBackground(Color.black);
        btntambah.setText("");
    }//GEN-LAST:event_btntambahMouseExited

    private void btneditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditMouseEntered
        btnedit.setText("EDIT");
    }//GEN-LAST:event_btneditMouseEntered

    private void btneditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditMouseExited
        btnedit.setText("");
    }//GEN-LAST:event_btneditMouseExited

    private void btnhapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhapusMouseEntered
        btnhapus.setText("HAPUS");
    }//GEN-LAST:event_btnhapusMouseEntered

    private void btnhapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhapusMouseExited
        btnhapus.setText("");
    }//GEN-LAST:event_btnhapusMouseExited

    private void btnbatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbatalMouseEntered
        btnbatal.setText("REFRESH");
    }//GEN-LAST:event_btnbatalMouseEntered

    private void btnbatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbatalMouseExited
        btnbatal.setText("");
    }//GEN-LAST:event_btnbatalMouseExited

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
            java.util.logging.Logger.getLogger(Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btntambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txttelepon;
    // End of variables declaration//GEN-END:variables
    
    
    static public class HeaderColor extends DefaultTableCellRenderer {
       public HeaderColor(){
           setOpaque(true);
       }   
       public Component getTableCellRendererComponent(JTable jTable2, Object value, boolean selected, boolean focused, int row, int column) { 
        super.getTableCellRendererComponent(jTable2, value, selected, focused, row, column);
           setBackground(Color.black);
           return this;
       }
    }
}


