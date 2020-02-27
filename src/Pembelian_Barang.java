
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createTitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
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
public class Pembelian_Barang extends javax.swing.JFrame {
    Connection con;
    Statement sta;
    ResultSet rs;
    DefaultTableModel tbm;
    String tanggal4;
    private String tanggal;
    /**
     * Creates new form Pembelian_Barang
     */
    public Pembelian_Barang() {
        initComponents();
        
        this.setExtendedState(MAXIMIZED_BOTH);
        btnback.setContentAreaFilled(false);
        txtnopembelian.setEditable(false);
        tgl_pembelian.setEnabled(false);
        btntambah_barang.setContentAreaFilled(false);
        btntambah.setBackground(Color.black);
        btncancel.setBackground(Color.black);
        btnlaporan.setBackground(Color.black);
        btncari.setBackground(Color.black);
        txtid_supplier.setVisible(false);
        cmbnama_supplier.requestFocus(true);
        txtkode_barang.setEditable(false);
        txtid_petugas.setVisible(false);
        txtusername.setVisible(false);
        
        jTable2.getTableHeader().setDefaultRenderer(new Supplier.HeaderColor());
        
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        sta = DB.stm;
        
        no_pembelian();
        tgl_sekarang();
        masuktable();
        merk();
        supplier();
    }

    public JTextField gettxtusername() {
        return txtusername;
    }
    
    public JTextField gettxtid_petugas() {
        return txtid_petugas;
    }
    
    private void no_pembelian() {
        try {
             String sql;
                        sql="SELECT * FROM pembelian_barang ORDER BY no_pembelian DESC LIMIT 1";
                            rs =  sta.executeQuery(sql);
                        if (rs.next()) {
                            String nofak = rs.getString("no_pembelian").substring(5,6);
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
    
    private void tgl_sekarang() {
        Date date = new Date();
        tgl_pembelian.setDate(date);
    }
    
    public JLabel getlbl_username() {
        return lblpetugas;
    }
    public JLabel getlbl_id_petugas() {
        return lblid_petugas;
    }
    
    private void masuktable() {
        try{
                sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                tbm = new DefaultTableModel();
                tbm.addColumn("NO PEMBELIAN");
                tbm.addColumn("ID LOGIN");
                tbm.addColumn("USERNAME");
                tbm.addColumn("TOTAL PEMBAYARAN");
                tbm.addColumn("TGL PEMBAYARAN");
                
                jTable2.setModel(tbm);
                rs = sta.executeQuery("SELECT pembelian_barang.*,login.* FROM pembelian_barang, login WHERE pembelian_barang.id_login = login.id_login");
                while (rs.next()){
                    tbm.addRow(new Object[]{
                    rs.getString("no_pembelian"),
                    rs.getString("id_login"),    
                    rs.getString("username"),
                    rs.getString("total_pembayaran"),
                    rs.getString("tgl_pembelian"),
                    
                    
                });
                    jTable2.setModel(tbm);
                    }
                }catch(Exception e){
                        JOptionPane.showMessageDialog(rootPane,"Gagal"+e);
                        
                    }
    }
    
    private void jumlah_pengeluaran() {
        try {
            String sql;
            sql = "SELECT SUM(pembayaran) AS 'total_pembayaran' FROM detail_pembelian WHERE no_pembelian = '" +txtnopembelian.getText()+ "'";
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                String jumlah_pengeluaran = rs.getString("total_pembayaran");
                lbljumlah.setText(jumlah_pengeluaran);
                
            }
        } catch (Exception e) {
        }
    }
    
    
    private void kosong_tab() {
        cmbnama_barang.setSelectedItem("-----PILIHAN-----");
        cmbnama_supplier.setSelectedItem("-----PILIHAN-----");
        txtid_supplier.setText("");
        txtqty.setText("");
        txtkode_barang.setText("");
        txtharga.setText("");
        cmbnama_supplier.requestFocus(true);
        
    }
    
    private void kosong_enter() {
        txtkode_barang.setText("");
        cmbnama_barang.setSelectedItem("-----PILIHAN-----");
        cmbnama_supplier.setSelectedItem("-----PILIHAN-----");
        txtqty.setText("");
        txtid_supplier.setText("");
        txtharga.setText("");
        lbljumlah.setText("");
        cmbnama_supplier.requestFocus(true);
        
        no_pembelian();
    }
    
    private void merk() {
        try {
            String sql;
            sql = ("SELECT * FROM suku_cadang ORDER BY nm_suku_cadang ASC");
            rs = sta.executeQuery(sql);
            while (rs.next()) {
                cmbnama_barang.addItem(rs.getString("nm_suku_cadang"));
                
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
                cmbnama_supplier.addItem(rs.getString("nm_supplier"));
                
            } 
        } catch (Exception e) {
        }
    }
    
    private void masukkan_tab() {
        try {
             String sql;
                sql = ("INSERT INTO detail_pembelian(no_pembelian,id_supplier,kode_barang,qty,pembayaran) VALUES('"+ txtnopembelian.getText() +"','"+ txtid_supplier.getText() +"','"+ txtkode_barang.getText() +"','"+ txtqty.getText() +"','"+ txtharga.getText() +"')");
                sta.executeUpdate(sql);
                JOptionPane.showMessageDialog(rootPane, "BERHASIL");
                kosong_tab();
                jumlah_pengeluaran();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        jPanel7 = new javax.swing.JPanel();
        btnback = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtid_petugas = new javax.swing.JTextField();
        txtusername = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lblid_petugas = new javax.swing.JLabel();
        lblpetugas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnopembelian = new javax.swing.JTextField();
        tgl_pembelian = new com.toedter.calendar.JDateChooser();
        cmbnama_supplier = new javax.swing.JComboBox();
        cmbnama_barang = new javax.swing.JComboBox();
        txtqty = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtid_supplier = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtkode_barang = new javax.swing.JTextField();
        lbljumlah = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        btntambah_barang = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btntambah = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        btnlaporan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/left-arrow.png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });
        jPanel7.add(btnback);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PEMBELIAN BARANG");
        jPanel7.add(jLabel1);

        txtid_petugas.setPreferredSize(new java.awt.Dimension(50, 19));
        jPanel7.add(txtid_petugas);

        txtusername.setPreferredSize(new java.awt.Dimension(50, 19));
        jPanel7.add(txtusername);

        jPanel1.add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 25, 5));

        lblid_petugas.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        lblid_petugas.setForeground(new java.awt.Color(255, 255, 255));
        lblid_petugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_lock.png"))); // NOI18N
        lblid_petugas.setText("jLabel17");
        lblid_petugas.setPreferredSize(new java.awt.Dimension(124, 42));
        jPanel8.add(lblid_petugas);

        lblpetugas.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        lblpetugas.setForeground(new java.awt.Color(255, 255, 255));
        lblpetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        lblpetugas.setText("jLabel11");
        lblpetugas.setPreferredSize(new java.awt.Dimension(125, 42));
        jPanel8.add(lblpetugas);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setMinimumSize(new java.awt.Dimension(678, 650));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kode Barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 36, 0, 36);
        jPanel5.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("QTY.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 36, 0, 36);
        jPanel5.add(jLabel6, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No Pembelian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 36, 0, 36);
        jPanel5.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TGL Pembelian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 36, 0, 36);
        jPanel5.add(jLabel3, gridBagConstraints);

        txtnopembelian.setBackground(new java.awt.Color(0, 0, 0));
        txtnopembelian.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtnopembelian.setForeground(new java.awt.Color(255, 255, 255));
        txtnopembelian.setToolTipText("");
        txtnopembelian.setPreferredSize(new java.awt.Dimension(180, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(15, 22, 15, 22);
        jPanel5.add(txtnopembelian, gridBagConstraints);

        tgl_pembelian.setBackground(new java.awt.Color(51, 51, 51));
        tgl_pembelian.setForeground(new java.awt.Color(255, 255, 255));
        tgl_pembelian.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tgl_pembelian.setPreferredSize(new java.awt.Dimension(180, 30));
        tgl_pembelian.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tgl_pembelianPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(15, 22, 15, 22);
        jPanel5.add(tgl_pembelian, gridBagConstraints);

        cmbnama_supplier.setBackground(new java.awt.Color(51, 51, 51));
        cmbnama_supplier.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        cmbnama_supplier.setForeground(new java.awt.Color(255, 255, 255));
        cmbnama_supplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----PILIHAN-----" }));
        cmbnama_supplier.setNextFocusableComponent(cmbnama_barang);
        cmbnama_supplier.setPreferredSize(new java.awt.Dimension(180, 30));
        cmbnama_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnama_supplierActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(15, 22, 15, 22);
        jPanel5.add(cmbnama_supplier, gridBagConstraints);

        cmbnama_barang.setBackground(new java.awt.Color(51, 51, 51));
        cmbnama_barang.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        cmbnama_barang.setForeground(new java.awt.Color(0, 0, 0));
        cmbnama_barang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----PILIHAN-----" }));
        cmbnama_barang.setNextFocusableComponent(txtkode_barang);
        cmbnama_barang.setPreferredSize(new java.awt.Dimension(180, 30));
        cmbnama_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnama_barangActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(15, 22, 15, 22);
        jPanel5.add(cmbnama_barang, gridBagConstraints);

        txtqty.setBackground(new java.awt.Color(0, 0, 0));
        txtqty.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtqty.setForeground(new java.awt.Color(255, 255, 255));
        txtqty.setNextFocusableComponent(txtharga);
        txtqty.setPreferredSize(new java.awt.Dimension(180, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(15, 22, 15, 22);
        jPanel5.add(txtqty, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama Supplier");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 36, 0, 36);
        jPanel5.add(jLabel7, gridBagConstraints);

        txtid_supplier.setBackground(new java.awt.Color(0, 0, 0));
        txtid_supplier.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtid_supplier.setForeground(new java.awt.Color(255, 255, 255));
        txtid_supplier.setPreferredSize(new java.awt.Dimension(70, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel5.add(txtid_supplier, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 36, 0, 36);
        jPanel5.add(jLabel8, gridBagConstraints);

        txtkode_barang.setBackground(new java.awt.Color(0, 0, 0));
        txtkode_barang.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtkode_barang.setForeground(new java.awt.Color(255, 255, 255));
        txtkode_barang.setNextFocusableComponent(txtqty);
        txtkode_barang.setPreferredSize(new java.awt.Dimension(180, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(15, 22, 15, 22);
        jPanel5.add(txtkode_barang, gridBagConstraints);

        lbljumlah.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        lbljumlah.setForeground(new java.awt.Color(255, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 24, 0, 24);
        jPanel5.add(lbljumlah, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Harga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 36, 0, 36);
        jPanel5.add(jLabel4, gridBagConstraints);

        txtharga.setBackground(new java.awt.Color(0, 0, 0));
        txtharga.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtharga.setForeground(new java.awt.Color(255, 255, 255));
        txtharga.setPreferredSize(new java.awt.Dimension(180, 30));
        txtharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txthargaKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(15, 22, 15, 22);
        jPanel5.add(txtharga, gridBagConstraints);

        btntambah_barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add-circular-button.png"))); // NOI18N
        btntambah_barang.setToolTipText("");
        btntambah_barang.setPreferredSize(new java.awt.Dimension(50, 34));
        btntambah_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambah_barangActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        jPanel5.add(btntambah_barang, gridBagConstraints);

        jPanel2.add(jPanel5, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setForeground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Shopping256.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 16);
        jPanel4.add(jLabel9, gridBagConstraints);

        txtcari.setBackground(new java.awt.Color(0, 0, 0));
        txtcari.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        txtcari.setForeground(new java.awt.Color(255, 255, 255));
        txtcari.setPreferredSize(new java.awt.Dimension(225, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(23, 8, 23, 8);
        jPanel4.add(txtcari, gridBagConstraints);

        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/magnifying-glass-search.png"))); // NOI18N
        btncari.setPreferredSize(new java.awt.Dimension(62, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 4, 1, 4);
        jPanel4.add(btncari, gridBagConstraints);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(90, 150));

        jTable2.setBackground(new java.awt.Color(51, 51, 51));
        jTable2.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
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
        jTable2.setPreferredSize(new java.awt.Dimension(300, 300));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel6.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

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
        jPanel3.add(btntambah);

        btncancel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btncancel.setForeground(new java.awt.Color(255, 255, 255));
        btncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
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
        jPanel3.add(btncancel);

        btnlaporan.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnlaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnlaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/printer.png"))); // NOI18N
        btnlaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnlaporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnlaporanMouseExited(evt);
            }
        });
        jPanel3.add(btnlaporan);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tgl_pembelianPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tgl_pembelianPropertyChange
        if (tgl_pembelian.getDate()!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tanggal = simpleDateFormat.format(tgl_pembelian.getDate());}
    }//GEN-LAST:event_tgl_pembelianPropertyChange

    private void txthargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthargaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            btntambah_barangActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press"+ ""));
         }  else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             btntambahActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Tombol Ditekan"+ ""));
                
            }
    }//GEN-LAST:event_txthargaKeyPressed

    private void btntambah_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambah_barangActionPerformed
        masukkan_tab();
        
    }//GEN-LAST:event_btntambah_barangActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        try {
            String sql2 = "SELECT COUNT(*) AS 'jumlah' FROM pembelian_barang";
            rs = sta.executeQuery(sql2);
            String id = txtid_petugas.getText();
            int id_petugas = Integer.parseInt(id);
            if (rs.next()) {
                String brp = rs.getString("jumlah");
                int jumlah_row =  Integer.parseInt(brp);
                if (jumlah_row == 0 ) {
                    String sql;
             
                sql = "INSERT INTO pembelian_barang VALUES('" +txtnopembelian.getText()+ "', '" +id_petugas+ "', '" +txtharga.getText()+ "', '" +tanggal+ "')";
                sta.executeUpdate(sql);
                String sql3 = "INSERT INTO detail_pembelian(no_pembelian,id_supplier,kode_barang,qty,pembayaran) VALUES('" + txtnopembelian.getText() + "', "
                        + "'" +txtid_supplier.getText()+ "', '" +txtkode_barang.getText()+ "', '" +txtqty.getText()+ "', '" +txtharga.getText()+ "')";
                sta.executeUpdate(sql3);
                 
               
                JOptionPane.showMessageDialog(rootPane, "BERHASIL");
                masuktable();
                kosong_enter();
                } else if (jumlah_row > 0) {
                    
                   
                   String sql5 = "INSERT INTO detail_pembelian(no_pembelian,id_supplier,kode_barang,qty,pembayaran) VALUES('" + txtnopembelian.getText() + "', "
                           + "'" +txtid_supplier.getText()+ "', '" +txtkode_barang.getText()+ "', "
                           + "'" +txtqty.getText()+ "', '" +txtharga.getText()+ "')";
                    sta.executeUpdate(sql5);
                    jumlah_pengeluaran();
                    
                   String sql4 = "INSERT INTO pembelian_barang VALUES('" +txtnopembelian.getText()+ "','" +id_petugas+ "', '" +lbljumlah.getText()+ "','" +tanggal+ "')";
                   sta.executeUpdate(sql4);
                    
                   JOptionPane.showMessageDialog(rootPane, "BERHASIL");
                   masuktable();
                   kosong_enter();
                }
            }
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btntambahActionPerformed

    private void cmbnama_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnama_barangActionPerformed
        try {
            String sql;
            sql="SELECT * FROM suku_cadang WHERE nm_suku_cadang = '" + cmbnama_barang.getSelectedItem() + "'";
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                String kode = rs.getString("kode_barang");
                    txtkode_barang.setText(kode);
                }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbnama_barangActionPerformed

    private void cmbnama_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnama_supplierActionPerformed
         try {
            String sql;
            sql="SELECT * FROM supplier WHERE nm_supplier = '" + cmbnama_supplier.getSelectedItem() + "'";
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                String kode = rs.getString("id_supplier");
                    txtid_supplier.setText(kode);
                }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbnama_supplierActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        Daftar_Pembelian a = new Daftar_Pembelian();
        a.setVisible(true);
//        a.getpembelian().setText(tbm.getValueAt(jTable2.getSelectedRow(), 0)+"");
    }//GEN-LAST:event_jTable2MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        cmbnama_supplier.requestFocus(true);
        masuktable();
    }//GEN-LAST:event_formWindowActivated

    private void btntambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahMouseEntered
        btntambah.setText("TAMBAH");
    }//GEN-LAST:event_btntambahMouseEntered

    private void btntambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahMouseExited
        btntambah.setText("");
    }//GEN-LAST:event_btntambahMouseExited

    private void btncancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelMouseEntered
       btncancel.setText("REFRESH");
    }//GEN-LAST:event_btncancelMouseEntered

    private void btncancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelMouseExited
        btncancel.setText("");
    }//GEN-LAST:event_btncancelMouseExited

    private void btnlaporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlaporanMouseEntered
        btnlaporan.setText("LAPORAN");
    }//GEN-LAST:event_btnlaporanMouseEntered

    private void btnlaporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlaporanMouseExited
        btnlaporan.setText("");
    }//GEN-LAST:event_btnlaporanMouseExited

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
            java.util.logging.Logger.getLogger(Pembelian_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembelian_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembelian_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembelian_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembelian_Barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnlaporan;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btntambah_barang;
    private javax.swing.JComboBox cmbnama_barang;
    private javax.swing.JComboBox cmbnama_supplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblid_petugas;
    private javax.swing.JLabel lbljumlah;
    private javax.swing.JLabel lblpetugas;
    private com.toedter.calendar.JDateChooser tgl_pembelian;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtid_petugas;
    private javax.swing.JTextField txtid_supplier;
    private javax.swing.JTextField txtkode_barang;
    private javax.swing.JTextField txtnopembelian;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtusername;
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
