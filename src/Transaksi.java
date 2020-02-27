
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
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
public class Transaksi extends javax.swing.JFrame {
    Connection con;
    Statement sta;
    ResultSet rs;
    DefaultTableModel tbm;
    String tanggal4;
    private String tanggal;
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        
        btntambah_barang.setBackground(Color.black);
        btnbatal.setBackground(Color.black);
        btnlaporan.setBackground(Color.black);
        btnback.setContentAreaFilled(false);
        date_transaksi.setEnabled(false);
        btnbayar.setBackground(Color.black);
        btnpemberitahuan.setBackground(Color.black);
        txtno_transaksi.setEditable(false);
        txtkembalian.setEditable(false);
        
        
        jTable2.getTableHeader().setDefaultRenderer(new Supplier.HeaderColor());
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        koneksi DB = new koneksi();
        DB.config();
        sta = DB.stm;
        con = DB.con;
        
        tgl_sekarang();
       
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
                rs = sta.executeQuery("SELECT detail_transaksi.*,suku_cadang.* FROM detail_transaksi,suku_cadang "
                        + "WHERE detail_transaksi.kode_barang = suku_cadang.kode_barang AND no_transaksi='" +txtno_transaksi.getText()+ "'");
                while (rs.next()){
                    tbm.addRow(new Object[]{ 
                    rs.getString("kode_barang"),
                    rs.getString("nm_suku_cadang"),    
                    rs.getString("qty"),
                    rs.getString("sub_total"),
                    
                    
                });
                    jTable2.setModel(tbm);
                    }
                }catch(Exception e){
                        JOptionPane.showMessageDialog(rootPane,"Gagal"+e);
                        
                    }
    }
     
     private void kosong() {
         cmbjenis_transaksi.setSelectedItem("---PILIHAN---");
         lbltotal_transaksi.setText("");
         txtnama_konsumen.setText("");
         txtnama_transaksi.setText("");
         txttotal.setText("");
         txttunai.setText("");
         txtkembalian.setText("");
         cmbstatus_transaksi.setSelectedItem("TUNAI");
         cmbjenis_transaksi.requestFocus(true);
     }
    
     private void pemberitahuan() {
         try {
             String sql = "SELECT COUNT(*) AS 'jumlah' FROM transaksi WHERE status='UTANG'";
             rs = sta.executeQuery(sql);
             if(rs.next()) {
                 String jumlah = rs.getString("jumlah");
                 lblpemberitahuan.setText(jumlah);
             }
             
         } catch (Exception e) {
         }
     }
     
     
    private void tgl_sekarang() {
        Date date = new Date(); 
        date_transaksi.setDate(date);
    }

    public JLabel getlbl_username() {
        return lblpetugas;
    }
    public JLabel getlbl_id_petugas() {
        return lblid_petugas;
    }
    
    private void kembalian() {
        int total = Integer.parseInt(txttotal.getText());
        int tunai = Integer.parseInt(txttunai.getText());
        if (cmbstatus_transaksi.getSelectedItem().equals("LUNAS") || total<tunai) {
                    int kembalian = tunai-total;
                    String kembalian2 = String.valueOf(kembalian);
                    txtkembalian.setText(kembalian2);
                    txtkembalian.requestFocus(true);
            }  else if (cmbstatus_transaksi.getSelectedItem().equals("LUNAS") && total>tunai) {
                   JOptionPane.showMessageDialog(rootPane, "Uang Tidak Cukup");
            
        }   else if (total==tunai) {
            txtkembalian.setText("0");
            
        } else if (txttotal.getText().equals("") && txttunai.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Error Happen");
        } else if (cmbstatus_transaksi.getSelectedItem().equals("UTANG")) {
            txtkembalian.setText("0");
        }
    }
    
    private void jumlah() {
        try {
            String sql = "SELECT SUM(sub_total) AS 'jumlah' FROM detail_transaksi WHERE no_transaksi = '" +txtno_transaksi.getText()+ "'";
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                String total = rs.getString("jumlah");
                lbltotal_transaksi.setText(total);
                txttotal.setText(total);
            }
            
        } catch (Exception e) {
        }
    }
    
    private void pengecekan() {
        try {
            String sql6 = "SELECT COUNT(*) AS 'jumlah' FROM detail_transaksi WHERE no_transaksi = '" +txtno_transaksi.getText()+ "' ";
             rs = sta.executeQuery(sql6);
             
             if (rs.next()) {
                 String jumlah = rs.getString("jumlah");
                 int jumlah2 = Integer.valueOf(jumlah);
                 if(jumlah2 == 0){
                     JOptionPane.showMessageDialog(rootPane, "Masukkan Barang Terlebih Dahulu \n F1 = Add Barang");
                 }
             }
        } catch (Exception e) {
        }
    }
    
    public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}






public class BillPrintable implements Printable {
    
   
    
    
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
                
        
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

            ////////// code by alqama//////////////

            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        //    int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

        //    int idPosition=0;
        //    int productPosition=idPosition + idLength + 2;
        //    int pricePosition=productPosition + prodLength +10;
        //    int qtyPosition=pricePosition + priceLength + 2;
        //    int amtPosition=qtyPosition + qtyLength + 2;
            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
            ///////////////// Product names Get ///////////
              
                
                g2d.setFont(new Font("Monospaced",Font.PLAIN,7));
                
            g2d.drawString("-----------------------------------",12,y);y+=yShift;
            g2d.drawString("           BERDIKARI MOTOR         ",12,y);y+=yShift;
            g2d.drawString("-----------------------------------",12,y);y+=headerRectHeight;
             g2d.drawString("TGL  : "+tanggal,12,y);y+=yShift;
             g2d.drawString("N.Cashier  : "+lblpetugas.getText(),12,y);y+=yShift;
             g2d.drawString("",10,y);y+=yShift;
            g2d.drawString("K.Barang  || N.Barang || QTY. || Harga",12,y);y+=yShift;
            g2d.drawString("-----------------------------------",10,y);y+=headerRectHeight;    
            
            String sql = "SELECT transaksi.nm_konsumen,transaksi.tgl_transaksi,suku_cadang.nm_suku_cadang,detail_transaksi.kode_barang,detail_transaksi.qty,detail_transaksi.sub_total  FROM transaksi,suku_cadang,detail_transaksi "
                        + "WHERE transaksi.no_transaksi=detail_transaksi.no_transaksi AND detail_transaksi.kode_barang=suku_cadang.kode_barang AND detail_transaksi.no_transaksi = '"+txtno_transaksi.getText()+"'";
                rs = sta.executeQuery(sql);
                
            while (rs.next()) {                
           
           String kode = rs.getString("kode_barang");
           String nm_barang = rs.getString("nm_suku_cadang");
           String qty = rs.getString("qty");
           String harga = rs.getString("sub_total");
           

            
            
            
            g2d.drawString(  kode+" || "   +nm_barang+ " || "   +qty+    " || Rp." +harga,10,y);y+=yShift;
            
           
             
           
            }
            g2d.drawString("-----------------------------------",10,y);y+=headerRectHeight;
            g2d.drawString("Total = "+txttotal.getText(),10,y);y+=yShift; 
            g2d.drawString("Tunai = "+txttunai.getText(),10,y);y+=yShift; 
            g2d.drawString("Kembalian = "+txtkembalian.getText(),10,y);y+=yShift; 
            g2d.drawString("",10,y);y+=yShift;
            g2d.drawString("",10,y);y+=yShift;
            g2d.drawString("Konsumen : "+txtnama_konsumen.getText(),10,y);y+=yShift;
            g2d.drawString("",10,y);y+=yShift;
            g2d.drawString("Java Is The Best Programming Language",10,y);y+=yShift;
            g2d.drawString("             10011011101             ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("     THANKS TO VISIT OUR BENGKEL     ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
             
                   
           
             
           
            
//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; ;
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
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
        lblpemberitahuan = new javax.swing.JLabel();
        btnpemberitahuan = new javax.swing.JButton();
        btntambah_barang = new javax.swing.JButton();
        btnbayar = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnlaporan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtno_transaksi = new javax.swing.JTextField();
        date_transaksi = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        cmbjenis_transaksi = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbltotal_transaksi = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtnama_konsumen = new javax.swing.JTextField();
        cmbstatus_transaksi = new javax.swing.JComboBox();
        txtnama_transaksi = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txttunai = new javax.swing.JTextField();
        txtkembalian = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
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
        jLabel1.setText("TRANSAKSI");
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

        lblpemberitahuan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblpemberitahuan.setForeground(new java.awt.Color(204, 0, 0));
        lblpemberitahuan.setText("1");
        lblpemberitahuan.setFocusable(false);
        lblpemberitahuan.setPreferredSize(new java.awt.Dimension(7, 42));
        lblpemberitahuan.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblpemberitahuan);

        btnpemberitahuan.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnpemberitahuan.setForeground(new java.awt.Color(255, 255, 255));
        btnpemberitahuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        btnpemberitahuan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnpemberitahuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnpemberitahuanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnpemberitahuanMouseExited(evt);
            }
        });
        btnpemberitahuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpemberitahuanActionPerformed(evt);
            }
        });
        jPanel3.add(btnpemberitahuan);

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

        btnbayar.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnbayar.setForeground(new java.awt.Color(255, 255, 255));
        btnbayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/give-money.png"))); // NOI18N
        btnbayar.setMnemonic('2');
        btnbayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnbayarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnbayarMouseExited(evt);
            }
        });
        btnbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbayarActionPerformed(evt);
            }
        });
        jPanel3.add(btnbayar);

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
        jLabel2.setText("NO TRANSAKSI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 0, 14);
        jPanel7.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TANGGAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 0, 14);
        jPanel7.add(jLabel3, gridBagConstraints);

        txtno_transaksi.setBackground(new java.awt.Color(0, 0, 0));
        txtno_transaksi.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtno_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        txtno_transaksi.setPreferredSize(new java.awt.Dimension(150, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(8, 19, 8, 19);
        jPanel7.add(txtno_transaksi, gridBagConstraints);

        date_transaksi.setBackground(new java.awt.Color(51, 51, 51));
        date_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        date_transaksi.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        date_transaksi.setPreferredSize(new java.awt.Dimension(150, 35));
        date_transaksi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date_transaksiPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(8, 19, 8, 19);
        jPanel7.add(date_transaksi, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("JENIS TRANSAKSI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 0, 14);
        jPanel7.add(jLabel4, gridBagConstraints);

        cmbjenis_transaksi.setBackground(new java.awt.Color(0, 0, 0));
        cmbjenis_transaksi.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        cmbjenis_transaksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---PILIHAN---", "SERVICE", "PEMB.BARANG" }));
        cmbjenis_transaksi.setNextFocusableComponent(txtnama_konsumen);
        cmbjenis_transaksi.setOpaque(false);
        cmbjenis_transaksi.setPreferredSize(new java.awt.Dimension(150, 35));
        cmbjenis_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbjenis_transaksiActionPerformed(evt);
            }
        });
        cmbjenis_transaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbjenis_transaksiKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(8, 19, 8, 19);
        jPanel7.add(cmbjenis_transaksi, gridBagConstraints);

        jPanel4.add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TOTAL : ");
        jPanel8.add(jLabel7, new java.awt.GridBagConstraints());

        lbltotal_transaksi.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        lbltotal_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        lbltotal_transaksi.setText("LBLTOTAL");
        jPanel8.add(lbltotal_transaksi, new java.awt.GridBagConstraints());

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/coin.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel9.add(jLabel5, gridBagConstraints);

        jPanel4.add(jPanel9, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new java.awt.BorderLayout(5, 0));

        jTable2.setBackground(new java.awt.Color(51, 51, 51));
        jTable2.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
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
        jTable2.setShowHorizontalLines(false);
        jTable2.setShowVerticalLines(false);
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
        jLabel10.setText("ALT + 2 / ENTER = BAYAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel10.add(jLabel10, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ALT + 3 = REFRESH");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        jPanel10.add(jLabel17, gridBagConstraints);

        jPanel6.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NAMA KONSUMEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("NAMA TRANSAKSI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("STATUS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jLabel13, gridBagConstraints);

        txtnama_konsumen.setBackground(new java.awt.Color(0, 0, 0));
        txtnama_konsumen.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtnama_konsumen.setForeground(new java.awt.Color(255, 255, 255));
        txtnama_konsumen.setNextFocusableComponent(txtnama_transaksi);
        txtnama_konsumen.setPreferredSize(new java.awt.Dimension(258, 35));
        txtnama_konsumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtnama_konsumenMouseEntered(evt);
            }
        });
        txtnama_konsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnama_konsumenActionPerformed(evt);
            }
        });
        txtnama_konsumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnama_konsumenKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 9, 6);
        jPanel11.add(txtnama_konsumen, gridBagConstraints);

        cmbstatus_transaksi.setBackground(new java.awt.Color(0, 0, 0));
        cmbstatus_transaksi.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        cmbstatus_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        cmbstatus_transaksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TUNAI" }));
        cmbstatus_transaksi.setNextFocusableComponent(txttunai);
        cmbstatus_transaksi.setPreferredSize(new java.awt.Dimension(125, 35));
        cmbstatus_transaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbstatus_transaksiKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 9, 6);
        jPanel11.add(cmbstatus_transaksi, gridBagConstraints);

        txtnama_transaksi.setBackground(new java.awt.Color(0, 0, 0));
        txtnama_transaksi.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtnama_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        txtnama_transaksi.setNextFocusableComponent(cmbstatus_transaksi);
        txtnama_transaksi.setPreferredSize(new java.awt.Dimension(258, 35));
        txtnama_transaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnama_transaksiKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 9, 6);
        jPanel11.add(txtnama_transaksi, gridBagConstraints);

        jPanel6.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel14.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("TUNAI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("KEMBALIAN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel15, gridBagConstraints);

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

        txttunai.setBackground(new java.awt.Color(0, 0, 0));
        txttunai.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txttunai.setForeground(new java.awt.Color(255, 255, 255));
        txttunai.setNextFocusableComponent(txtkembalian);
        txttunai.setPreferredSize(new java.awt.Dimension(145, 35));
        txttunai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttunaiActionPerformed(evt);
            }
        });
        txttunai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttunaiKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttunaiKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 9, 6);
        jPanel12.add(txttunai, gridBagConstraints);

        txtkembalian.setBackground(new java.awt.Color(0, 0, 0));
        txtkembalian.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtkembalian.setForeground(new java.awt.Color(255, 255, 255));
        txtkembalian.setPreferredSize(new java.awt.Dimension(145, 35));
        txtkembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkembalianActionPerformed(evt);
            }
        });
        txtkembalian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtkembalianKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 9, 6);
        jPanel12.add(txtkembalian, gridBagConstraints);

        jPanel6.add(jPanel12);

        jPanel2.add(jPanel6, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    private void txttunaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttunaiActionPerformed
       
    }//GEN-LAST:event_txttunaiActionPerformed

    private void date_transaksiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_transaksiPropertyChange
        
        if (date_transaksi.getDate()!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tanggal = simpleDateFormat.format(date_transaksi.getDate());}
        
    }//GEN-LAST:event_date_transaksiPropertyChange

    private void cmbjenis_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbjenis_transaksiActionPerformed
         if (cmbjenis_transaksi.getSelectedItem().equals("SERVICE")) {
                    try {
                        String sql;
                        sql="SELECT * FROM transaksi WHERE jenis_transaksi = 'SERVICE' ORDER BY no_transaksi DESC LIMIT 1";
                            rs =  sta.executeQuery(sql);
                        if (rs.next()) {
                            String nofak = rs.getString("no_transaksi").substring(2,6);
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
                                
                                txtno_transaksi.setText("SC" + Nol + AN);
                
                        } else {
                            txtno_transaksi.setText("SC0001");
                        }
                    } catch (Exception e) {
                    }
                    

                } else if (cmbjenis_transaksi.getSelectedItem().equals("PEMB.BARANG")) {
                   try {
                        String sql;
                        sql="SELECT * FROM transaksi WHERE jenis_transaksi = 'PEMB.BARANG' ORDER BY no_transaksi DESC LIMIT 1";
                            rs =  sta.executeQuery(sql);
                        if (rs.next()) {
                            String nofak = rs.getString("no_transaksi").substring(2,6);
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
                                
                                txtno_transaksi.setText("PB" + Nol + AN);
                
                        } else {
                            txtno_transaksi.setText("PB0001");
                        }
                    } catch (Exception e) {
                    }

                } else if (cmbjenis_transaksi.getSelectedItem().equals("---PILIHAN---")) {
                     
                        txtno_transaksi.setText("");
                        
        }
    }//GEN-LAST:event_cmbjenis_transaksiActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        masuktable();
        pemberitahuan();
        int pemberitahuan = Integer.valueOf(lblpemberitahuan.getText());
        if (pemberitahuan<1) {
            lblpemberitahuan.setVisible(false);
            btnpemberitahuan.setVisible(false);
                    
        } else if(pemberitahuan >= 1) {
            lblpemberitahuan.setVisible(true);
            btnpemberitahuan.setVisible(true);
        }
        jumlah();
    }//GEN-LAST:event_formWindowActivated

    private void txtnama_konsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnama_konsumenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnama_konsumenActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void cmbjenis_transaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbjenis_transaksiKeyPressed
        
    }//GEN-LAST:event_cmbjenis_transaksiKeyPressed

    private void txtnama_konsumenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnama_konsumenKeyPressed
        if (txtno_transaksi.getText().equals("")) {
            JOptionPane.showConfirmDialog(rootPane,"Masukkan Terlebih dahulu semua data");
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_F1) {
            Barang_Transaksi a = new Barang_Transaksi();
            a.setVisible(true);
            a.getlblno_transaksi().setText(txtno_transaksi.getText());
        } else  if (evt.getKeyCode() == KeyEvent.VK_F2) {
            kosong();
        }
        }
        
        
    }//GEN-LAST:event_txtnama_konsumenKeyPressed

    private void txttunaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttunaiKeyTyped
        char karakter = evt.getKeyChar();
        if(karakter >= 'A' && karakter <= 'z' ){
            getToolkit().beep();
                evt.consume();
        }
    }//GEN-LAST:event_txttunaiKeyTyped

    private void txtkembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkembalianActionPerformed
          
    }//GEN-LAST:event_txtkembalianActionPerformed

    private void txttunaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttunaiKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           kembalian(); 
         }
         else  if (evt.getKeyCode() == KeyEvent.VK_F2) {
            kosong();
        }
    }//GEN-LAST:event_txttunaiKeyPressed

    private void btntambah_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambah_barangActionPerformed
            if (txtno_transaksi.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Harap Masukkan Jenis Service Dahulu");
            } else {
                Barang_Transaksi a = new Barang_Transaksi();            
                a.setVisible(true);
            a.getlblno_transaksi().setText(txtno_transaksi.getText());
            }
        
    }//GEN-LAST:event_btntambah_barangActionPerformed

    private void txtnama_transaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnama_transaksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            Barang_Transaksi a = new Barang_Transaksi();
            a.setVisible(true);
            a.getlblno_transaksi().setText(txtno_transaksi.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            kosong();
        }
    }//GEN-LAST:event_txtnama_transaksiKeyPressed

    private void cmbstatus_transaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbstatus_transaksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            Barang_Transaksi a = new Barang_Transaksi();
            a.setVisible(true);
            a.getlblno_transaksi().setText(txtno_transaksi.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            kosong();
        }
    }//GEN-LAST:event_cmbstatus_transaksiKeyPressed

    private void btnbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbayarActionPerformed
           try {
            String sql6 = "SELECT COUNT(*) AS 'jumlah' FROM detail_transaksi WHERE no_transaksi = '" +txtno_transaksi.getText()+ "' ";
             rs = sta.executeQuery(sql6);
             
             if (rs.next()) {
                 String jumlah = rs.getString("jumlah");
                 int jumlah2 = Integer.valueOf(jumlah);
                 if(jumlah2 == 0){
                     JOptionPane.showMessageDialog(rootPane, "Masukkan Barang Terlebih Dahulu \n ALT + 1 = Add Barang");
                 } else {
                      if (txtno_transaksi.getText().equals("") || txttotal.getText().equals("") ||
            txtnama_transaksi.getText().equals("") || txttunai.getText().equals("") || txtkembalian.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Harap Isi Semua Data");
        } else if (cmbstatus_transaksi.getSelectedItem().equals("TUNAI")){
            
           
         
                String sql;
                sql = "INSERT INTO transaksi VALUES ('" +txtno_transaksi.getText()+ "','" +cmbjenis_transaksi.getSelectedItem()+ "','" +lblid_petugas.getText()+ "', '" +txtnama_konsumen.getText()+ "','" +tanggal+ "','" +txttotal.getText()+ "','" +cmbstatus_transaksi.getSelectedItem()+ "')";
                sta.executeUpdate(sql);
                
                 PrinterJob pj = PrinterJob.getPrinterJob();        
                    pj.setPrintable(new BillPrintable(),getPageFormat(pj));
                        pj.print();
                
                kosong();
                masuktable();
        }
//        else if (cmbstatus_transaksi.getSelectedItem().equals("UTANG")){
//            
//                String sql;
//                sql = "INSERT INTO transaksi VALUES ('" +txtno_transaksi.getText()+ "','" +cmbjenis_transaksi.getSelectedItem()+ "', '" +txtnama_konsumen.getText()+ "','" +lblid_petugas.getText()+ "','" +txtnama_transaksi.getText()+ "','" +txttunai.getText()+ "','" +tanggal+ "','" +cmbstatus_transaksi.getSelectedItem()+ "')";
//                sta.executeUpdate(sql);
//                kosong();
//                masuktable();
//        } 
                 }
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,"Gagal"+e);
        }
        
             
             
             
       
    }//GEN-LAST:event_btnbayarActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        try {
            String sql = "DELETE FROM detail_transaksi WHERE no_transaksi = '" +txtno_transaksi.getText()+ "' ORDER BY id_detail_transaksi DESC LIMIT 1";
            sta.executeUpdate(sql);
            masuktable();
            jumlah();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btntambah_barangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_barangMouseEntered
        btntambah_barang.setText("ADD BARANG");
    }//GEN-LAST:event_btntambah_barangMouseEntered

    private void btntambah_barangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_barangMouseExited
         btntambah_barang.setText("");
    }//GEN-LAST:event_btntambah_barangMouseExited

    private void btnbayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbayarMouseEntered
        btnbayar.setText("BAYAR");
    }//GEN-LAST:event_btnbayarMouseEntered

    private void btnbayarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbayarMouseExited
        btnbayar.setText("");
    }//GEN-LAST:event_btnbayarMouseExited

    private void btnbatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbatalMouseEntered
        btnbatal.setText("HAPUS BARANG");
    }//GEN-LAST:event_btnbatalMouseEntered

    private void btnbatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbatalMouseExited
        btnbatal.setText("");
    }//GEN-LAST:event_btnbatalMouseExited

    private void btnlaporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlaporanMouseEntered
        btnlaporan.setText("LAPORAN");
    }//GEN-LAST:event_btnlaporanMouseEntered

    private void btnlaporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlaporanMouseExited
        btnlaporan.setText("");
    }//GEN-LAST:event_btnlaporanMouseExited

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
//        
//        Edit_Barang_Transaksi a = new Edit_Barang_Transaksi();
//        a.setVisible(true);
//        
//        a.getlblid_detail_transaksi().setText(tbm.getValueAt(jTable2.getSelectedRow(), 0)+"");
//        a.gettxtkode_barang().setText(tbm.getValueAt(jTable2.getSelectedRow(), 1)+"");
//        a.gettxtnama_barang().setText(tbm.getValueAt(jTable2.getSelectedRow(), 2)+"");
//        a.gettxtqty().setText(tbm.getValueAt(jTable2.getSelectedRow(), 3)+"");
//        try {
//            String sql = "SELECT * FROM suku_cadang WHERE kode_barang = '" + tbm.getValueAt(jTable2.getSelectedRow(), 1)+"" + "'";
//            rs = sta.executeQuery(sql);
//            if(rs.next()) {
//                String stock = rs.getString("stock");
//                a.gettxtstock().setText(stock);
//            }
//        } catch (Exception e) {
//        }
//        a.gettxtharga().setText(tbm.getValueAt(jTable2.getSelectedRow(), 4)+"");
//        a.getlblkode_barang_().setText(tbm.getValueAt(jTable2.getSelectedRow(), 1)+"");
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnpemberitahuanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpemberitahuanMouseEntered
           btnpemberitahuan.setText("PEMBERITAHUAN");
    }//GEN-LAST:event_btnpemberitahuanMouseEntered

    private void btnpemberitahuanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpemberitahuanMouseExited
        btnpemberitahuan.setText("");
    }//GEN-LAST:event_btnpemberitahuanMouseExited

    private void btnpemberitahuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpemberitahuanActionPerformed
        Utang a = new Utang();
        a.setVisible(true);
    }//GEN-LAST:event_btnpemberitahuanActionPerformed

    private void txtkembalianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkembalianKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnbayarActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Key Press"+ ""));
         }
    }//GEN-LAST:event_txtkembalianKeyPressed

    private void txtnama_konsumenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnama_konsumenMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnama_konsumenMouseEntered

    private void btnlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaporanActionPerformed
        Laporan_Transaksi a = new Laporan_Transaksi();
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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnbayar;
    private javax.swing.JButton btnlaporan;
    private javax.swing.JButton btnpemberitahuan;
    private javax.swing.JButton btntambah_barang;
    private javax.swing.JComboBox cmbjenis_transaksi;
    private javax.swing.JComboBox cmbstatus_transaksi;
    private com.toedter.calendar.JDateChooser date_transaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JLabel lblpemberitahuan;
    private javax.swing.JLabel lblpetugas;
    private javax.swing.JLabel lbltotal_transaksi;
    private javax.swing.JTextField txtkembalian;
    private javax.swing.JTextField txtnama_konsumen;
    private javax.swing.JTextField txtnama_transaksi;
    private javax.swing.JTextField txtno_transaksi;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttunai;
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

