/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author neiseil
 */
public class VerBoletas extends javax.swing.JFrame {

    Conectar con = new Conectar();
    Connection cn = con.conexion();
    String atributo="ID_PRODUCTO";
    public VerBoletas() {
        initComponents();
        mostrarTabla("");
        mostrarVencen();
        calcularIngresos30();
        calcularIngresos24h();
        calcularIngresos2min();
        
    }
    
    void mostrarTabla(String valor)
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NUMERO BOLETA");
        modelo.addColumn("TRABAJADOR");
        modelo.addColumn("MONTO BOLETA");
        modelo.addColumn("FECHA");

        verBoletas.setModel(modelo);
        
        String sql = "";
        if(valor.equals(""))
        {
            sql = "SELECT DISTINCT VENDEN.NUM_BOLETA,VENDEN.RUT_TRAB,BOLETA.MONTO,BOLETA.FECHA FROM VENDEN INNER JOIN BOLETA ON VENDEN.NUM_BOLETA = BOLETA.NUM_B WHERE BOLETA.MONTO>0 ORDER BY FECHA DESC";
        }
        else
        {
            sql = "SELECT DISTINCT VENDEN.NUM_BOLETA,VENDEN.RUT_TRAB,BOLETA.MONTO,BOLETA.FECHA FROM VENDEN INNER JOIN BOLETA ON VENDEN.NUM_BOLETA = BOLETA.NUM_B WHERE "+atributo+"='"+valor+"' AND BOLETA.MONTO>0 ORDER BY FECHA DESC";
        }
        String datos[] = new String [4];
        Statement st;
        try{
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);

                modelo.addRow(datos);
            }
            verBoletas.setModel(modelo);
        } catch (SQLException ex)
        {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    void mostrarVencen()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("FECHA VENCIMIENTO");
        modelo.addColumn("STOCK DISPONIBLE");

        tablaVencen.setModel(modelo);
        
        String sql = "";


        sql = "SELECT DISTINCT LOTE.ID_PRODUCTO,PRODUCTO.NOMBRE_PROD,LOTE.FECHA_VENCIMIENTO,LOTE.STOCK FROM PRODUCTO INNER JOIN LOTE WHERE LOTE.FECHA_VENCIMIENTO-CURDATE()>0 AND LOTE.FECHA_VENCIMIENTO-CURDATE()<7 AND STOCK>0 AND LOTE.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO";

        String datos[] = new String [4];
        Statement st;
        try{
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);

                modelo.addRow(datos);
            }
            tablaVencen.setModel(modelo);
            tablaVencen.setEnabled(false);
        } catch (SQLException ex)
        {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    void calcularIngresos30()
    {
        String sql_1 = "SELECT DISTINCT SUM(BOLETA.MONTO) \n" +
        "FROM BOLETA \n" +
        "WHERE NOW()-BOLETA.FECHA<2592000";
        String NUMM;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql_1);
            while (rs.next()) {                                
            NUMM = rs.getString(1);        
            txt30dias.setText(NUMM);}
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    void calcularIngresos24h()
    {
        String sql_1 = "SELECT DISTINCT SUM(BOLETA.MONTO) \n" +
        "FROM BOLETA \n" +
        "WHERE NOW()-BOLETA.FECHA<86400";
        String NUMM;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql_1);
            while (rs.next()) {                                
            NUMM = rs.getString(1);        
            txt24h.setText(NUMM);}
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
        void calcularIngresos2min()
    {
        String sql_1 = "SELECT DISTINCT SUM(BOLETA.MONTO) \n" +
        "FROM BOLETA \n" +
        "WHERE NOW()-BOLETA.FECHA<120";
        String NUMM;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql_1);
            while (rs.next()) {                                
            NUMM = rs.getString(1);        
            txt2min.setText(NUMM);}
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
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

        Filtros = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        verBoletas = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        BNumB = new javax.swing.JRadioButton();
        BRut = new javax.swing.JRadioButton();
        BBuscar = new javax.swing.JButton();
        BVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt30dias = new javax.swing.JLabel();
        txt24h = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt2min = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVencen = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        verBoletas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        verBoletas.setEnabled(false);
        jScrollPane1.setViewportView(verBoletas);

        BNumB.setText("Num Boleta");

        BRut.setText("Rut Trabajador");

        BBuscar.setText("Buscar");
        BBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarActionPerformed(evt);
            }
        });

        BVolver.setText("VOLVER");
        BVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BVolverActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingresos en los ultimos 30 dias:");

        jLabel3.setText("Ingresos en las ultimas 24 horas:");

        txt30dias.setText("0");

        txt24h.setText("0");

        jLabel4.setText("Ingresos en los ultimos 2 min:");

        txt2min.setText("0");

        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("AVISO: LOS PRODUCTOS DE LA TABLA INFERIOR VENCEN EN MENOS DE 7 DIAS.");

        tablaVencen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaVencen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BNumB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BRut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BBuscar)
                                .addGap(0, 556, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txt30dias, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt24h, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                    .addComponent(txt2min, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BNumB)
                    .addComponent(BRut)
                    .addComponent(BBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt30dias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt24h))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt2min))
                .addGap(51, 51, 51)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarActionPerformed
        Filtros.add(BNumB);
        Filtros.add(BRut);
        if(BNumB.isSelected()){atributo="BOLETA.NUM_B"; mostrarTabla(txtBuscar.getText());}
        else if(BRut.isSelected()){atributo="VENDEN.RUT_TRAB"; mostrarTabla(txtBuscar.getText());}
        else{JOptionPane.showMessageDialog(null, "No hay filtros de búsqueda seleccionados");
    }//GEN-LAST:event_BBuscarActionPerformed
    }
    private void BVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BVolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_BVolverActionPerformed
    
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
            java.util.logging.Logger.getLogger(VerBoletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerBoletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerBoletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerBoletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerBoletas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBuscar;
    private javax.swing.JRadioButton BNumB;
    private javax.swing.JRadioButton BRut;
    private javax.swing.JButton BVolver;
    private javax.swing.ButtonGroup Filtros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaVencen;
    private javax.swing.JLabel txt24h;
    private javax.swing.JLabel txt2min;
    private javax.swing.JLabel txt30dias;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTable verBoletas;
    // End of variables declaration//GEN-END:variables
}
