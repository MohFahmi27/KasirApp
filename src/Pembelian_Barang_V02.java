
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MF
 */
public class Pembelian_Barang_V02 extends javax.swing.JFrame {
    private String tanggal;
    Connection con;
    Statement sta;
    ResultSet rs;
    DefaultTableModel tbm;
    /**
     * Creates new form Pembelian_Barang_V02
     */
    public Pembelian_Barang_V02() {
        initComponents();
        
        txtid_supplier.setEditable(false);
        txttotal.setEditable(false);
        btnback.setContentAreaFilled(false);
        btntambah_barang.setBackground(Color.black);
        btntambah_pembelian.setBackground(Color.black);
        btnbatal.setBackground(Color.black);
        btnlaporan.setBackground(Color.black);
        btntambah_supplier.setContentAreaFilled(false);
        date_pembelian.setEnabled(false);
        txtnopembelian.setEditable(false);
        cmbsupplier.requestFocus(true);
        
        lblidsupplier.setVisible(false);
        txtid_supplier.setVisible(false);
        
        this.setExtendedState(MAXIMIZED_BOTH);
        
        jTable2.getTableHeader().setDefaultRenderer(new Supplier.HeaderColor());
        
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        sta = DB.stm;
        
        no_pembelian();
        supplier();
        tgl_sekarang();
        masuktable();
    }
    
    public JLabel getlblid_petugas() {
        return lblid_petugas;
    }
    public JLabel getlblusername() {
        return lblpetugas;
    }
    
    private void jumlah() {
        try {
            String sql = "SELECT SUM(pembayaran) AS 'jumlah' FROM detail_pembelian WHERE no_pembelian = '" +txtnopembelian.getText()+ "'";
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                String total = rs.getString("jumlah");
                lbltotal_pembelian.setText(total);
                txttotal.setText(total);
            }
            
        } catch (Exception e) {
        }
    }
    
    private void supplier() {
        try {
            String sql;
            sql = ("SELECT * FROM supplier ORDER BY nm_supplier ASC");
            rs = sta.executeQuery(sql);
            while (rs.next()) {
                cmbsupplier.addItem(rs.getString("nm_supplier"));
                
            } 
        } catch (Exception e) {
        }
    }

    private void tgl_sekarang() {
        Date date = new Date();
        date_pembelian.setDate(date);
    }
    
    private void kosong() {
        no_pembelian();
        cmbsupplier.setSelectedItem("---PILIHAN---");
        txtid_supplier.setText("");
        jumlah();
    }
    
    private void masuktable() {
        try{
                sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                tbm = new DefaultTableModel();
                tbm.addColumn("KODE BARANG");
                tbm.addColumn("NAMA BARANG");
                tbm.addColumn("QTY");
                tbm.addColumn("HARGA");
                
                jTable2.setModel(tbm);
                rs = sta.executeQuery("SELECT detail_pembelian.*,suku_cadang.kode_barang,suku_cadang.nm_suku_cadang FROM detail_pembelian,suku_cadang WHERE suku_cadang.kode_barang = detail_pembelian.kode_barang AND detail_pembelian.no_pembelian='" + txtnopembelian.getText() + "'");
                while (rs.next()){
                    tbm.addRow(new Object[]{
                    rs.getString("kode_barang"),
                    rs.getString("nm_suku_cadang"),
                    rs.getString("qty"),
                    rs.getString("pembayaran"),
                    
                    
                });
                    jTable2.setModel(tbm);
                    }
                
                }catch(Exception e){
                        JOptionPane.showMessageDialog(rootPane,"Gagal"+e);
                        
                    }
    }
    
     private void no_pembelian() {
        try {
             String sql;
                        sql="SELECT * FROM pembelian_barang ORDER BY no_pembelian DESC LIMIT 1";
                            rs =  sta.executeQuery(sql);
                        if (rs.next()) {
                            String nofak = rs.getString("no_pembelian").substring(2,6);
                            String AN = "" + (Integer.parseInt(nofak) + 1);
                            String Nol = "";

                                if(AN.length()==1)
                                {Nol = "000";}
                                else if(AN.length()==2)
                                {Nol = "00";}
                                else if(AN.length()==3)
                                {Nol = "0";}
                                else if(AN.length()==4)
                                {Nol = "";}
                                
                                txtnopembelian.setText("NP" + Nol + AN);
                        } else {
                            txtnopembelian.setText("NP0001");
                        }
        } catch (Exception e) {
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
        jPanel13 = new javax.swing.JPanel();
        btnback = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblid_petugas = new javax.swing.JLabel();
        lblpetugas = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btntambah_barang = new javax.swing.JButton();
        btntambah_pembelian = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnlaporan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnopembelian = new javax.swing.JTextField();
        date_pembelian = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbltotal_pembelian = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        LOL = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cmbsupplier = new javax.swing.JComboBox();
        btntambah_supplier = new javax.swing.JButton();
        lblidsupplier = new javax.swing.JLabel();
        txtid_supplier = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/left-arrow.png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });
        jPanel13.add(btnback);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PEMBELIAN");
        jPanel13.add(jLabel1);

        jPanel1.add(jPanel13, java.awt.BorderLayout.LINE_START);

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 25, 5));

        lblid_petugas.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        lblid_petugas.setForeground(new java.awt.Color(255, 255, 255));
        lblid_petugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_lock.png"))); // NOI18N
        lblid_petugas.setText("jLabel17");
        lblid_petugas.setPreferredSize(new java.awt.Dimension(124, 42));
        jPanel14.add(lblid_petugas);

        lblpetugas.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        lblpetugas.setForeground(new java.awt.Color(255, 255, 255));
        lblpetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        lblpetugas.setText("jLabel11");
        lblpetugas.setPreferredSize(new java.awt.Dimension(125, 42));
        jPanel14.add(lblpetugas);

        jPanel1.add(jPanel14, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btntambah_barang.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btntambah_barang.setForeground(new java.awt.Color(255, 255, 255));
        btntambah_barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btntambah_barang.setMnemonic('1');
        btntambah_barang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btntambah_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntambah_barangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntambah_barangMouseExited(evt);
            }
        });
        btntambah_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambah_barangActionPerformed(evt);
            }
        });
        jPanel3.add(btntambah_barang);

        btntambah_pembelian.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btntambah_pembelian.setForeground(new java.awt.Color(255, 255, 255));
        btntambah_pembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/shopping.png"))); // NOI18N
        btntambah_pembelian.setMnemonic('2');
        btntambah_pembelian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btntambah_pembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntambah_pembelianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntambah_pembelianMouseExited(evt);
            }
        });
        btntambah_pembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambah_pembelianActionPerformed(evt);
            }
        });
        jPanel3.add(btntambah_pembelian);

        btnbatal.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnbatal.setForeground(new java.awt.Color(255, 255, 255));
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnbatal.setMnemonic('3');
        btnbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jPanel3.add(btnbatal);

        btnlaporan.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnlaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnlaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/printer.png"))); // NOI18N
        btnlaporan.setMnemonic('4');
        btnlaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnlaporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnlaporanMouseExited(evt);
            }
        });
        btnlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlaporanActionPerformed(evt);
            }
        });
        jPanel3.add(btnlaporan);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NO PEMBELIAN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 0, 14);
        jPanel7.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TANGGAL PEMBELIAN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 0, 14);
        jPanel7.add(jLabel3, gridBagConstraints);

        txtnopembelian.setBackground(new java.awt.Color(0, 0, 0));
        txtnopembelian.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtnopembelian.setForeground(new java.awt.Color(255, 255, 255));
        txtnopembelian.setPreferredSize(new java.awt.Dimension(150, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(8, 19, 8, 19);
        jPanel7.add(txtnopembelian, gridBagConstraints);

        date_pembelian.setBackground(new java.awt.Color(51, 51, 51));
        date_pembelian.setForeground(new java.awt.Color(255, 255, 255));
        date_pembelian.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        date_pembelian.setPreferredSize(new java.awt.Dimension(150, 35));
        date_pembelian.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date_pembelianPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(8, 19, 8, 19);
        jPanel7.add(date_pembelian, gridBagConstraints);

        jPanel4.add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TOTAL : ");
        jPanel8.add(jLabel7, new java.awt.GridBagConstraints());

        lbltotal_pembelian.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        lbltotal_pembelian.setForeground(new java.awt.Color(255, 255, 255));
        lbltotal_pembelian.setText("LBLTOTAL");
        jPanel8.add(lbltotal_pembelian, new java.awt.GridBagConstraints());

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/coin.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel9.add(jLabel4, gridBagConstraints);

        jPanel4.add(jPanel9, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new java.awt.BorderLayout(5, 0));

        jTable2.setBackground(new java.awt.Color(51, 51, 51));
        jTable2.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
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
        ));
        jTable2.setEnabled(false);
        jTable2.setPreferredSize(new java.awt.Dimension(300, 500));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("  LIST BARANG : ");
        jLabel6.setPreferredSize(new java.awt.Dimension(78, 50));
        jPanel5.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ALT + 1 = ADD BARANG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel10.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ALT + 4 = LAPORAN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel10.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ALT + 2 = ADD DATA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel10.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ALT + 3 = REFRESH");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel10.add(jLabel11, gridBagConstraints);

        jPanel6.add(jPanel10);

        LOL.setBackground(new java.awt.Color(51, 51, 51));
        LOL.setLayout(new java.awt.GridBagLayout());

        jLabel13.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("SUPPLIER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        LOL.add(jLabel13, gridBagConstraints);

        cmbsupplier.setBackground(new java.awt.Color(0, 0, 0));
        cmbsupplier.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        cmbsupplier.setForeground(new java.awt.Color(255, 255, 255));
        cmbsupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---PILIHAN---" }));
        cmbsupplier.setPreferredSize(new java.awt.Dimension(150, 35));
        cmbsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbsupplierActionPerformed(evt);
            }
        });
        cmbsupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbsupplierKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 9, 6);
        LOL.add(cmbsupplier, gridBagConstraints);

        btntambah_supplier.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        btntambah_supplier.setForeground(new java.awt.Color(255, 255, 255));
        btntambah_supplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add-circular-button.png"))); // NOI18N
        btntambah_supplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btntambah_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntambah_supplierMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntambah_supplierMouseExited(evt);
            }
        });
        btntambah_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambah_supplierActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        LOL.add(btntambah_supplier, gridBagConstraints);

        lblidsupplier.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        lblidsupplier.setForeground(new java.awt.Color(255, 255, 255));
        lblidsupplier.setText("ID SUPPLIER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        LOL.add(lblidsupplier, gridBagConstraints);

        txtid_supplier.setBackground(new java.awt.Color(0, 0, 0));
        txtid_supplier.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtid_supplier.setForeground(new java.awt.Color(255, 255, 255));
        txtid_supplier.setPreferredSize(new java.awt.Dimension(150, 35));
        txtid_supplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtid_supplierKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 9, 6);
        LOL.add(txtid_supplier, gridBagConstraints);

        jPanel6.add(LOL);

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel16.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("TOTAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel16, gridBagConstraints);

        txttotal.setBackground(new java.awt.Color(0, 0, 0));
        txttotal.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txttotal.setForeground(new java.awt.Color(255, 255, 255));
        txttotal.setPreferredSize(new java.awt.Dimension(145, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 9, 6);
        jPanel12.add(txttotal, gridBagConstraints);

        jPanel6.add(jPanel12);

        jPanel2.add(jPanel6, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    private void btntambah_barangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_barangMouseEntered
        btntambah_barang.setText("ADD BARANG");
    }//GEN-LAST:event_btntambah_barangMouseEntered

    private void btntambah_barangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_barangMouseExited
        btntambah_barang.setText("");
    }//GEN-LAST:event_btntambah_barangMouseExited

    private void btntambah_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambah_barangActionPerformed
        Barang_Pembelian a = new Barang_Pembelian();
        
        a.setVisible(true);
        a.getlbl_pembelian().setText(txtnopembelian.getText());
    }//GEN-LAST:event_btntambah_barangActionPerformed

    private void btntambah_pembelianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_pembelianMouseEntered
        btntambah_pembelian.setText("BAYAR");
    }//GEN-LAST:event_btntambah_pembelianMouseEntered

    private void btntambah_pembelianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_pembelianMouseExited
        btntambah_pembelian.setText("");
    }//GEN-LAST:event_btntambah_pembelianMouseExited

    private void btntambah_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambah_pembelianActionPerformed
            try {
            String sql2 = "SELECT COUNT(*) AS 'jumlah' FROM detail_pembelian WHERE no_pembelian = '" +txtnopembelian.getText()+ "'";
            rs = sta.executeQuery(sql2);
            String id = lblid_petugas.getText();
            int id_petugas = Integer.parseInt(id);
            if (rs.next()) {
                String brp = rs.getString("jumlah");
                int jumlah_row =  Integer.parseInt(brp);
                if (jumlah_row == 0 ) {
                    JOptionPane.showMessageDialog(rootPane, "Masukkan Barang Terlebih Dahulu \n F1 = Add Barang");
                } else if (jumlah_row > 0) {
                    if(txtid_supplier.getText().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "Harap Isi Semua Data");
                    } else {
                        String sql4 = "INSERT INTO pembelian_barang VALUES('" +txtnopembelian.getText()+ "','" +id_petugas+ "','" +txtid_supplier.getText()+ "', '" +txttotal.getText()+ "','" +tanggal+ "')";
                        sta.executeUpdate(sql4);
                    
                        JOptionPane.showMessageDialog(rootPane, "BERHASIL");
                        masuktable();
                        kosong();
                    }
                   
                }
            }
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_btntambah_pembelianActionPerformed

    private void btnbatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbatalMouseEntered
        btnbatal.setText("REFRESH");
    }//GEN-LAST:event_btnbatalMouseEntered

    private void btnbatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbatalMouseExited
        btnbatal.setText("");
    }//GEN-LAST:event_btnbatalMouseExited

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
          try {
            String sql = "DELETE FROM detail_pembelian WHERE no_pembelian = '" +txtnopembelian.getText()+ "' ORDER BY id_detail_pembelian DESC LIMIT 1";
            sta.executeUpdate(sql);
            masuktable();
              jumlah();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnlaporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlaporanMouseEntered
        btnlaporan.setText("LAPORAN");
    }//GEN-LAST:event_btnlaporanMouseEntered

    private void btnlaporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlaporanMouseExited
        btnlaporan.setText("");
    }//GEN-LAST:event_btnlaporanMouseExited

    private void date_pembelianPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_pembelianPropertyChange

        if (date_pembelian.getDate()!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tanggal = simpleDateFormat.format(date_pembelian.getDate());}

    }//GEN-LAST:event_date_pembelianPropertyChange

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
//            Daftar_Pembelian a = new Daftar_Pembelian();
//            a.setVisible(true);
//            a.getid_detail_pembelian().setText(txtnopembelian.getText());
    }//GEN-LAST:event_jTable2MouseClicked

    private void cmbsupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbsupplierKeyPressed
        
    }//GEN-LAST:event_cmbsupplierKeyPressed

    private void btntambah_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambah_supplierActionPerformed
        Supplier a = new Supplier();
        a.setVisible(true);
    }//GEN-LAST:event_btntambah_supplierActionPerformed

    private void btntambah_supplierMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_supplierMouseEntered
        btntambah_supplier.setText("Add Supplier");
    }//GEN-LAST:event_btntambah_supplierMouseEntered

    private void txtid_supplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtid_supplierKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_F1) {
            btntambah_barangActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press"+ ""));
         }  else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             btntambah_pembelianActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Tombol Ditekan"+ ""));
                
            }
    }//GEN-LAST:event_txtid_supplierKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        jumlah();
        supplier();
        masuktable();
        
    }//GEN-LAST:event_formWindowActivated

    private void cmbsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsupplierActionPerformed
       
        try {
            String sql = "SELECT * FROM supplier WHERE nm_supplier = '" +cmbsupplier.getSelectedItem()+ "'";
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                String id = rs.getString("id_supplier");
                        txtid_supplier.setText(id);
                
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbsupplierActionPerformed

    private void btntambah_supplierMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_supplierMouseExited
           btntambah_supplier.setText("");
    }//GEN-LAST:event_btntambah_supplierMouseExited

    private void btnlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaporanActionPerformed
       Laporan_Pembelian a = new Laporan_Pembelian();
       a.setVisible(true);
    }//GEN-LAST:event_btnlaporanActionPerformed

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
            java.util.logging.Logger.getLogger(Pembelian_Barang_V02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembelian_Barang_V02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembelian_Barang_V02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembelian_Barang_V02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembelian_Barang_V02().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LOL;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnlaporan;
    private javax.swing.JButton btntambah_barang;
    private javax.swing.JButton btntambah_pembelian;
    private javax.swing.JButton btntambah_supplier;
    private javax.swing.JComboBox cmbsupplier;
    private com.toedter.calendar.JDateChooser date_pembelian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
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
    private javax.swing.JLabel lblid_petugas;
    private javax.swing.JLabel lblidsupplier;
    private javax.swing.JLabel lblpetugas;
    private javax.swing.JLabel lbltotal_pembelian;
    private javax.swing.JTextField txtid_supplier;
    private javax.swing.JTextField txtnopembelian;
    private javax.swing.JTextField txttotal;
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
