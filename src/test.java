
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class test extends javax.swing.JFrame {
    Connection con;
    Statement sta;
    ResultSet rs;
    DefaultTableModel tbm;
    /**
     * Creates new form test
     */
    public test() {
        initComponents();
        
        koneksi DB = new koneksi();
        DB.config();
        sta = DB.stm;
        con = DB.con;
        
    }
    
    
    private void masuktable() {
        try{
                sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                tbm = new DefaultTableModel();
                tbm.addColumn("NO TRANSAKSI");  
                tbm.addColumn("JENIS");
                tbm.addColumn("NAMA KONSUMEN");
                tbm.addColumn("TRANSAKSI");
                tbm.addColumn("BESAR TRANSAKSI");
                tbm.addColumn("TGL");
                
                jTable2.setModel(tbm);
                rs = sta.executeQuery("SELECT * FROM transaksi WHERE status = 'UTANG'");
                while (rs.next()){
                    tbm.addRow(new Object[]{
                    rs.getString("no_transaksi"),    
                    rs.getString("jenis_transaksi"),
                    rs.getString("nm_konsumen"),    
                    rs.getString("nm_transaksi"),
                    rs.getString("besar_transaksi"),
                    rs.getString("tgl_transaksi"),
                    
                    
                });
                    jTable2.setModel(tbm);
                    }
                }catch(Exception e){
                        JOptionPane.showMessageDialog(rootPane,"Gagal"+e);
                        
                    }
    }
    
//     public PageFormat getPageFormat(PrinterJob pj)
//{
//    
//    PageFormat pf = pj.defaultPage();
//    Paper paper = pf.getPaper();    
//
//    double middleHeight =8.0;  
//    double headerHeight = 2.0;                  
//    double footerHeight = 2.0;                  
//    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
//    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
//    paper.setSize(width, height);
//    paper.setImageableArea(                    
//        0,
//        10,
//        width,            
//        height - convert_CM_To_PPI(1)
//    );   //define boarder size    after that print area width is about 180 points
//            
//    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
//    pf.setPaper(paper);    
//
//    return pf;
//    }
//    
//    protected static double convert_CM_To_PPI(double cm) {            
//	        return toPPI(cm * 0.393600787);            
//    }
// 
//    protected static double toPPI(double inch) {            
//	        return inch * 72d;            
//    }
//
//
//
//
//
//
//    public class BillPrintable implements Printable {
//    
//   
//    
//    
//    public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
//    throws PrinterException 
//    {    
//      
//                
//        
//      int result = NO_SUCH_PAGE;    
//        if (pageIndex == 0) {                    
//        
//            Graphics2D g2d = (Graphics2D) graphics;                    
//
//            double width = pageFormat.getImageableWidth();                    
//           
//            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 
//
//            ////////// code by alqama//////////////
//
//            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
//        //    int idLength=metrics.stringWidth("000000");
//            //int idLength=metrics.stringWidth("00");
//            int idLength=metrics.stringWidth("000");
//            int amtLength=metrics.stringWidth("000000");
//            int qtyLength=metrics.stringWidth("00000");
//            int priceLength=metrics.stringWidth("000000");
//            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;
//
//        //    int idPosition=0;
//        //    int productPosition=idPosition + idLength + 2;
//        //    int pricePosition=productPosition + prodLength +10;
//        //    int qtyPosition=pricePosition + priceLength + 2;
//        //    int amtPosition=qtyPosition + qtyLength + 2;
//            
//            int productPosition = 0;
//            int discountPosition= prodLength+5;
//            int pricePosition = discountPosition +idLength+10;
//            int qtyPosition=pricePosition + priceLength + 4;
//            int amtPosition=qtyPosition + qtyLength;
//            
//            
//              
//        try{
//            /*Draw Header*/
//            int y=20;
//            int yShift = 10;
//            int headerRectHeight=15;
//            int headerRectHeighta=40;
            
            ///////////////// Product names Get ///////////
//                String  pn1a=pn1.getText();
//                String pn2a=pn2.getText();
//                String pn3a=pn3.getText();
//                String pn4a=pn4.getText();
//            ///////////////// Product names Get ///////////
//                
//            
//            ///////////////// Product price Get ///////////
//                int pp1a=Integer.valueOf(pp1.getText());
//                int pp2a=Integer.valueOf(pp2.getText());
//                int pp3a=Integer.valueOf(pp3.getText());
//                int pp4a=Integer.valueOf(pp4.getText());
//                int sum=pp1a+pp2a+pp3a+pp4a;
            ///////////////// Product price Get ///////////
//                String sql = "SELECT transaksi.nm_konsumen,transaksi.tgl_transaksi,suku_cadang.nm_suku_cadang,detail_transaksi.kode_barang,detail_transaksi.qty,detail_transaksi.sub_total  FROM transaksi,suku_cadang,detail_transaksi "
//                        + "WHERE transaksi.no_transaksi=detail_transaksi.no_transaksi AND detail_transaksi.kode_barang=suku_cadang.kode_barang AND detail_transaksi.no_transaksi = 'SC0002'";
//                rs = sta.executeQuery(sql);
//                g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
//            g2d.drawString("-----------------------------------",12,y);y+=yShift;
//            g2d.drawString("           Nota Transaksi          ",12,y);y+=yShift;
//            g2d.drawString("-----------------------------------",12,y);y+=headerRectHeight;
//            g2d.drawString("K.Barang  || N.Barang || QTY. || harga"                      ,10,y);y+=yShift;
//                while (rs.next()) {                
//           
//           String kode = rs.getString("kode_barang");
//           String nm_barang = rs.getString("nm_suku_cadang");
//           String qty = rs.getString("qty");
//           String harga = rs.getString("sub_total");
//           
//            g2d.drawString("-----------------------------------",10,y);y+=headerRectHeight;
//            
//            g2d.drawString("-----------------------------------",10,y);y+=headerRectHeight;
//            g2d.drawString(  kode+"||"   +nm_barang+ "||"   +qty+    "||" +harga   ,10,y);y+=yShift;
//            g2d.drawString("-----------------------------------",10,y);y+=headerRectHeight;
////            g2d.drawString("",10,y);y+=headerRectHeight; 
////            g2d.drawString(" QTY."                               ,10,y);y+=yShift;
////            g2d.drawString("-----------------------------------",10,y);y+=headerRectHeight;
////            g2d.drawString(rs.getString("qty")                  ,10,y);y+=yShift;
////            g2d.drawString("-----------------------------------",10,y);y+=headerRectHeight;
////             g2d.drawString("",10,y);y+=headerRectHeight; 
////            g2d.drawString(" Harga"                             ,10,y);y+=yShift;
////            g2d.drawString("-----------------------------------",10,y);y+=headerRectHeight;
////            g2d.drawString(rs.getString("sub_total")            ,10,y);y+=yShift;
////            g2d.drawString("-----------------------------------",10,y);y+=yShift;
//            
//             
//           
//            }
//             
//            g2d.drawString("Java Is The Best Programming Language",10,y);y+=yShift;
//            g2d.drawString("             10011011101             ",10,y);y+=yShift;
//            g2d.drawString("*************************************",10,y);y+=yShift;
//            g2d.drawString("     THANKS TO VISIT OUR BENGKEL     ",10,y);y+=yShift;
//            g2d.drawString("*************************************",10,y);y+=yShift;
//             
//                   
//           
//             
//           
//            
////            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
////            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; ;
          
//
//    }
//    catch(Exception r){
//    r.printStackTrace();
//    }
//
//              result = PAGE_EXISTS;    
//          }    
//          return result;    
//      }
//   }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtno_transaksi = new javax.swing.JTextField();
        btnprint = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(txtno_transaksi, java.awt.BorderLayout.CENTER);

        btnprint.setText("PRINT");
        btnprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintActionPerformed(evt);
            }
        });
        getContentPane().add(btnprint, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

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
        jScrollPane1.setViewportView(jTable2);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
                               
//        PrinterJob pj = PrinterJob.getPrinterJob();        
//        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
//        try {
//             pj.print();
//          
//        }
//         catch (PrinterException ex) {
//                 ex.printStackTrace();
//        }
    }//GEN-LAST:event_btnprintActionPerformed

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
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnprint;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtno_transaksi;
    // End of variables declaration//GEN-END:variables
}
