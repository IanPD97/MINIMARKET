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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static testdb.Ventana.mostrarProd;

/**
 *
 * @author neiseil
 */
public class NuevaBoleta extends javax.swing.JFrame {
    Conectar con = new Conectar();
    Connection cn = con.conexion();
    String atributo="ID_PRODUCTO";

    /**
     * Creates new form aaaa
     */
    public NuevaBoleta() {
        initComponents();
        mostrartablaProd("");
        mostrarVencen();
    }

    void mostrartablaProd(String valor)
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CONTENIDO");
        modelo.addColumn("STOCK TOTAL");
        modelo.addColumn("PRECIO");
        verProducto.setModel(modelo);
        
        String sql = "";
        if(valor.equals(""))
        {
            sql = "SELECT A.ID_PRODUCTO, A.NOMBRE_PROD, A.CANTIDAD, SUM(B.STOCK), A.PRECIO FROM PRODUCTO A LEFT JOIN LOTE B ON A.ID_PRODUCTO=B.ID_PRODUCTO WHERE STOCK>0 GROUP BY A.ID_PRODUCTO, A.NOMBRE_PROD, A.PRECIO";
        }
        else
        {
            sql = "SELECT A.ID_PRODUCTO, A.NOMBRE_PROD, A.CANTIDAD, SUM(B.STOCK), A.PRECIO FROM PRODUCTO A LEFT JOIN LOTE B ON A.ID_PRODUCTO=B.ID_PRODUCTO WHERE "+atributo+"='"+valor+"' AND STOCK>0 GROUP BY A.ID_PRODUCTO, A.NOMBRE_PROD, A.PRECIO";
        }
        String datos[] = new String [5];
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
                datos[4]=rs.getString(5);

                modelo.addRow(datos);
            }
            verProducto.setModel(modelo);
        } catch (SQLException ex)
        {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    void mostrartablaBol(String NUMB)
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("COD_REG");
        modelo.addColumn("CODIGO PRODUCTO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MONTO");
        verDetalle.setModel(modelo);
        
        String sql = "";

        sql = "SELECT VENDEN.COD_VENTA,VENDEN.ID_PRODUCTO,PRODUCTO.NOMBRE_PROD,PRODUCTO.PRECIO FROM VENDEN INNER JOIN PRODUCTO ON VENDEN.ID_PRODUCTO=PRODUCTO.ID_PRODUCTO WHERE NUM_BOLETA = "+NUMB+"";
        
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
            
            verDetalle.setModel(modelo);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Filtros = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        bAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        verDetalle = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        BRegistrarBoleta = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        BNombre = new javax.swing.JRadioButton();
        BCodigo = new javax.swing.JRadioButton();
        bBuscar = new javax.swing.JButton();
        BSeleccionar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNumboleta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtVendedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        verProducto = new javax.swing.JTable();
        txtPrecio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaVencen = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        eliminar_detalle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setText("Precio:");

        bAgregar.setText("Agregar Producto");
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
            }
        });

        verDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(verDetalle);

        jLabel9.setText("Detalle Boleta:");

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel10.setText("TOTAL:");

        BRegistrarBoleta.setText("REGISTRAR BOLETA");
        BRegistrarBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRegistrarBoletaActionPerformed(evt);
            }
        });

        jLabel6.setText("Seleccione un producto de la lista:");

        BNombre.setText("Nombre");

        BCodigo.setText("Codigo");

        bBuscar.setText("Buscar");
        bBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarActionPerformed(evt);
            }
        });

        BSeleccionar.setText("Seleccionar Producto");
        BSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeleccionarActionPerformed(evt);
            }
        });

        jLabel4.setText("Codigo Producto:");

        jLabel1.setText("Numero de Boleta:");

        jLabel2.setText("Fecha:");

        jLabel3.setText("Vendedor:");

        verProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(verProducto);

        jLabel5.setText("Nombre:");

        jLabel8.setText("Nota: SI NO SE ENCUENTRA EL PRODUCTO ES PORQUE NO HAY STOCK");

        tablaVencen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaVencen);

        jLabel11.setForeground(new java.awt.Color(153, 0, 0));
        jLabel11.setText("AVISO: LOS PRODUCTOS DE LA TABLA INFERIOR VENCEN EN MENOS DE 7 DIAS.");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(0, 51, 51));
        jTextArea1.setRows(5);
        jTextArea1.setText("SI EL STOCK DE LOS PRODUCTOS NO ES VENDIDO ANTES DE\nSU FECHA DE VENCIMIENTO, EL LOTE SERA ELIMINADO \nAUTOMATICAMENTE DEL SISTEMA.");
        jScrollPane4.setViewportView(jTextArea1);

        eliminar_detalle.setText("ELIMINAR SELECCION");
        eliminar_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_detalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(BSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtVendedor)
                                                .addComponent(txtNumboleta, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(BNombre)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(BCodigo)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(bBuscar))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eliminar_detalle)
                                .addGap(93, 93, 93)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(498, 498, 498)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(BRegistrarBoleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumboleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BNombre)
                            .addComponent(BCodigo)
                            .addComponent(bBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BSeleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bAgregar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(eliminar_detalle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BRegistrarBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BRegistrarBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRegistrarBoletaActionPerformed
        String MONTO = txtTotal.getText();
        String NUMBOL = txtNumboleta.getText();
        try {
            PreparedStatement pps = cn.prepareStatement("UPDATE BOLETA SET MONTO ="+MONTO+" WHERE NUM_B = "+NUMBOL+"");
            if (txtTotal.getText().equals("")){
                JOptionPane.showMessageDialog(null, "No hay productos ingresados");
            }
            else{
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS GUARDADOS");
            this.setVisible(false);
            

            
        }} catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BRegistrarBoletaActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void bBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarActionPerformed
        Filtros.add(BNombre);
        Filtros.add(BCodigo);
        if(BCodigo.isSelected()){atributo="A.ID_PRODUCTO"; mostrartablaProd(txtBuscar.getText());}
        else if(BNombre.isSelected()){atributo="A.NOMBRE_PROD"; mostrartablaProd(txtBuscar.getText());}
        else{JOptionPane.showMessageDialog(null, "No hay filtros de búsqueda seleccionados");
    }//GEN-LAST:event_bBuscarActionPerformed
    }
    private void BSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeleccionarActionPerformed
        int fila = verProducto.getSelectedRow();
        if (fila>=0)
        {
            txtCodigo.setText(verProducto.getValueAt(fila, 0).toString());
            txtNombre.setText(verProducto.getValueAt(fila, 1).toString());
            txtPrecio.setText(verProducto.getValueAt(fila, 4).toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto.");
        }
    }//GEN-LAST:event_BSeleccionarActionPerformed

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        if (txtCodigo.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto.");
        }
        else{
        try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO VENDEN(RUT_TRAB,NUM_BOLETA,ID_PRODUCTO) VALUES(?,?,?)");
            pps.setString(1,txtVendedor.getText());
            pps.setString(2,txtNumboleta.getText());
            pps.setString(3,txtCodigo.getText());
            pps.executeUpdate();

            String idlote = "SELECT ID_LOTE FROM LOTE\n" +
            "WHERE FECHA_VENCIMIENTO = \n" +
            "(SELECT MIN(FECHA_VENCIMIENTO) FROM LOTE WHERE ID_PRODUCTO = "+txtCodigo.getText()+" AND STOCK>0)\n" +
            "AND ID_PRODUCTO = "+txtCodigo.getText()+"";
            String getlote;
            Statement stmta;
            ResultSet rss;
            try {
                stmta = cn.createStatement();
                rss = stmta.executeQuery(idlote);
                while (rss.next()) {                                
                getlote = rss.getString(1);        
                PreparedStatement pps1 = cn.prepareStatement("UPDATE LOTE SET STOCK = STOCK-1 WHERE ID_LOTE = "+getlote+"");
                pps1.executeUpdate();}
            } catch (SQLException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }   
            JOptionPane.showMessageDialog(null, "Producto Registrado");
            mostrartablaBol(txtNumboleta.getText());
            mostrartablaProd("");
            txtCodigo.setText("");
            txtNombre.setText("");
            txtPrecio.setText("");
            String sql_1 = "SELECT SUM(PRODUCTO.PRECIO) FROM  VENDEN INNER JOIN PRODUCTO ON VENDEN.ID_PRODUCTO=PRODUCTO.ID_PRODUCTO WHERE NUM_BOLETA = "+txtNumboleta.getText()+"";
            String NUMM;
            Statement stmt;
            ResultSet rs;
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql_1);
                while (rs.next()) {                                
                NUMM = rs.getString(1);        
                txtTotal.setText(NUMM);}
            } catch (SQLException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
            
            } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }}
    }//GEN-LAST:event_bAgregarActionPerformed

    private void eliminar_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_detalleActionPerformed
        int fila = verDetalle.getSelectedRow();
        if (fila>=0)
        {
            String cod_reg = verDetalle.getValueAt(fila, 0).toString();
            String cod = verDetalle.getValueAt(fila, 1).toString();
            String nombre = verDetalle.getValueAt(fila, 2).toString();
            
            String idlote = "SELECT ID_LOTE FROM LOTE\n" +
            "WHERE FECHA_VENCIMIENTO = \n" +
            "(SELECT MIN(FECHA_VENCIMIENTO) FROM LOTE WHERE ID_PRODUCTO = "+cod+" AND STOCK>0)\n" +
            "AND ID_PRODUCTO = "+cod+"";
            String getlote;
            Statement stmta;
            ResultSet rss;
            try {
                stmta = cn.createStatement();
                rss = stmta.executeQuery(idlote);
                while (rss.next()) {                                
                getlote = rss.getString(1);        
                PreparedStatement pps1 = cn.prepareStatement("UPDATE LOTE SET STOCK = STOCK+1 WHERE ID_LOTE = "+getlote+"");
                pps1.executeUpdate();
                PreparedStatement pps2 = cn.prepareStatement("DELETE FROM VENDEN WHERE NUM_BOLETA = "+txtNumboleta.getText()+" AND ID_PRODUCTO = "+cod+" AND COD_VENTA = "+cod_reg+"");
                pps2.executeUpdate();
                mostrartablaBol(txtNumboleta.getText());
                mostrartablaProd("");
                String sql_1 = "SELECT SUM(PRODUCTO.PRECIO) FROM  VENDEN INNER JOIN PRODUCTO ON VENDEN.ID_PRODUCTO=PRODUCTO.ID_PRODUCTO WHERE NUM_BOLETA = "+txtNumboleta.getText()+"";
            String NUMM;
            Statement stmt;
            ResultSet rs;
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql_1);
                while (rs.next()) {                                
                NUMM = rs.getString(1);        
                txtTotal.setText(NUMM);}
            } catch (SQLException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                }
                 catch (SQLException ex) {
                Logger.getLogger(NuevaBoleta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione el Producto a Eliminar");
        }
    }//GEN-LAST:event_eliminar_detalleActionPerformed

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
            java.util.logging.Logger.getLogger(NuevaBoleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaBoleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaBoleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaBoleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevaBoleta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BCodigo;
    private javax.swing.JRadioButton BNombre;
    private javax.swing.JButton BRegistrarBoleta;
    private javax.swing.JButton BSeleccionar;
    private javax.swing.ButtonGroup Filtros;
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bBuscar;
    private javax.swing.JButton eliminar_detalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tablaVencen;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtNumboleta;
    public static javax.swing.JTextField txtPrecio;
    public static javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtVendedor;
    private javax.swing.JTable verDetalle;
    private javax.swing.JTable verProducto;
    // End of variables declaration//GEN-END:variables
}
