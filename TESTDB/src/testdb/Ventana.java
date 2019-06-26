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
import java.awt.event.KeyEvent;
import static testdb.ModificarProducto.CAMP1;

/**
 *
 * @author neiseil
 */
public class Ventana extends javax.swing.JFrame {

    Conectar con = new Conectar();
    Connection cn = con.conexion();
    String atributo="ID_PRODUCTO";
    /**
     * Creates new form Ventana
     */

    public Ventana() {
        initComponents();
        mostrartabla("");
        agregarItem();
        mostrartablaProd("");
    }
    
    public int existeID(String ID_PRODUCTO) throws SQLException
    {
        try{
        PreparedStatement pps = cn.prepareStatement("SELECT COUNT(ID_PRODUCTO) FROM PRODUCTO WHERE ID_PRODUCTO = ?");
        ResultSet rs = null;
        pps.setString(1, ID_PRODUCTO);
        rs = pps.executeQuery();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        
        return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE,null,ex);
            return 1;
        } 
    }
    
    public int existeNOMBRE(String NOMBRE_PROD) throws SQLException
    {
        try{
        PreparedStatement pps = cn.prepareStatement("SELECT COUNT(NOMBRE_PROD) FROM PRODUCTO WHERE NOMBRE_PROD = ?");
        ResultSet rs = null;
        pps.setString(1, NOMBRE_PROD);
        rs = pps.executeQuery();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        
        return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE,null,ex);
            return 1;
        } 
    }
    
    void mostrartabla(String valor)
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("TIPO");
        modelo.addColumn("CONTENIDO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("PRECIO VENTA");
        modelo.addColumn("PROVEEDOR");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("PRECIO COMPRA");
        Mostrar2.setModel(modelo);
        
        String sql = "";
        if(valor.equals(""))
        {
            sql = "SELECT PRODUCTO.ID_PRODUCTO,NOMBRE_PROD,ID_T,CANTIDAD,DESCRIPCION_P,PRECIO,NOMBRE_PR,TELEFONO,PRECIO_COMPRA FROM PRODUCTO INNER JOIN OFRECEN ON PRODUCTO.ID_PRODUCTO=OFRECEN.ID_PRODUCTO INNER JOIN PROVEEDOR ON OFRECEN.ID_PROVEEDOR=PROVEEDOR.ID_PROVEEDOR ORDER BY PROVEEDOR.ID_PROVEEDOR";
        }
        else
        {
            sql = "SELECT PRODUCTO.ID_PRODUCTO,NOMBRE_PROD,ID_T,CANTIDAD,DESCRIPCION_P,PRECIO,NOMBRE_PR,TELEFONO,PRECIO_COMPRA FROM PRODUCTO INNER JOIN OFRECEN ON PRODUCTO.ID_PRODUCTO=OFRECEN.ID_PRODUCTO INNER JOIN PROVEEDOR ON OFRECEN.ID_PROVEEDOR=PROVEEDOR.ID_PROVEEDOR WHERE "+atributo+"='"+valor+"' ORDER BY PROVEEDOR.ID_PROVEEDOR";
        }
        String datos[] = new String [9];
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
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                datos[8]=rs.getString(9);

                modelo.addRow(datos);
            }
            Mostrar2.setModel(modelo);
        } catch (SQLException ex)
        {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    void mostrartablaProd(String valor)
    {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("TIPO");
        modelo.addColumn("CONTENIDO");
        modelo.addColumn("DESCRIPCION");
        mostrarProd.setModel(modelo);
        
        String sql = "";
        if(valor.equals(""))
        {
            sql = "SELECT PRODUCTO.ID_PRODUCTO,NOMBRE_PROD,ID_T,CANTIDAD,DESCRIPCION_P FROM PRODUCTO ORDER BY ID_PRODUCTO";
        }
        else
        {
            sql = "SELECT PRODUCTO.ID_PRODUCTO,NOMBRE_PROD,ID_T,CANTIDAD,DESCRIPCION_P FROM PRODUCTO WHERE "+atributo+"='"+valor+"' ORDER BY ID_PRODUCTO";
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
            mostrarProd.setModel(modelo);
        } catch (SQLException ex)
        {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    void limpiar_campos()
    {
        CAMP1.setText("");
        CAMP2.setText("");
        Select_tipo.setSelectedIndex(0);
        CAMP4.setText("");
        CAMP5.setText("");
        CAMP6.setText("");
        Select_proveedor.setSelectedItem("Seleccionar");
        CAMP7.setText("");
    }
    
    void agregarItem()
    {
        Select_tipo.addItem("FRUTAS");
        Select_tipo.addItem("BEBIDAS");
        Select_tipo.addItem("VERDURAS");
        Select_proveedor.addItem("PROVEEDOR1");
        Select_proveedor.addItem("PROVEEDOR2");
        Select_proveedor.addItem("PROVEEDOR3");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Filtros = new javax.swing.ButtonGroup();
        FiltrosL = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mostrarProd = new javax.swing.JTable();
        CAMPBL = new javax.swing.JTextField();
        BNombreL = new javax.swing.JRadioButton();
        BCodigoL = new javax.swing.JRadioButton();
        BBuscarL = new javax.swing.JButton();
        NuevoLote = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        CAMP2 = new javax.swing.JTextField();
        CAMP4 = new javax.swing.JTextField();
        CAMP5 = new javax.swing.JTextField();
        CAMP6 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Select_tipo = new javax.swing.JComboBox<>();
        CAMP1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Select_proveedor = new javax.swing.JComboBox<>();
        CAMP7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        LIMPIAR = new javax.swing.JButton();
        ACEPTAR1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        ACTUALIZAR = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Mostrar2 = new javax.swing.JTable();
        MODIFICAR = new javax.swing.JButton();
        CAMPB = new javax.swing.JTextField();
        BNombre = new javax.swing.JRadioButton();
        BTipo = new javax.swing.JRadioButton();
        BBuscar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("VENTAS", jPanel2);

        mostrarProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(mostrarProd);

        BNombreL.setText("Nombre");

        BCodigoL.setText("Codigo");

        BBuscarL.setText("Buscar");
        BBuscarL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarLActionPerformed(evt);
            }
        });

        NuevoLote.setText("Ingresar Nuevo Lote");
        NuevoLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoLoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(CAMPBL, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BNombreL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BCodigoL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BBuscarL)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(NuevoLote)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CAMPBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BNombreL)
                    .addComponent(BCodigoL)
                    .addComponent(BBuscarL))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NuevoLote)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("INGRESAR LOTES", jPanel4);

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        CAMP6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CAMP6KeyTyped(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Codigo");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tipo");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Capacidad");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Descripcion");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Precio Venta");

        Select_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        Select_tipo.setToolTipText("");
        Select_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Select_tipoActionPerformed(evt);
            }
        });

        CAMP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CAMP1KeyTyped(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Proveedor");

        Select_proveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        Select_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Select_proveedorActionPerformed(evt);
            }
        });

        CAMP7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAMP7ActionPerformed(evt);
            }
        });
        CAMP7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CAMP7KeyTyped(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Precio Compra");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CAMP5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Select_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(CAMP7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Select_proveedor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CAMP1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CAMP2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CAMP4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CAMP6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CAMP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(CAMP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Select_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(CAMP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(CAMP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CAMP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Select_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CAMP7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LIMPIAR.setText("Limpiar");
        LIMPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPIARActionPerformed(evt);
            }
        });

        ACEPTAR1.setText("Guardar Nuevo Producto");
        ACEPTAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACEPTAR1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LIMPIAR, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ACEPTAR1))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(LIMPIAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ACEPTAR1))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 126, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("REGISTRAR PRODUCTOS", jPanel3);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos Registrados"));

        ACTUALIZAR.setText("Actualizar");
        ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACTUALIZARActionPerformed(evt);
            }
        });

        Mostrar2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(Mostrar2);

        MODIFICAR.setText("Modificar Seleccion");
        MODIFICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODIFICARActionPerformed(evt);
            }
        });

        BNombre.setText("Nombre");

        BTipo.setText("Tipo");

        BBuscar.setText("Buscar");
        BBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(MODIFICAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ACTUALIZAR))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(CAMPB, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BBuscar)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CAMPB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BNombre)
                    .addComponent(BTipo)
                    .addComponent(BBuscar))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ACTUALIZAR)
                    .addComponent(MODIFICAR))
                .addContainerGap())
        );

        jTabbedPane1.addTab("VER/EDITAR PRODUCTOS", jPanel9);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed
        mostrartabla("");
    }//GEN-LAST:event_ACTUALIZARActionPerformed

    private void ACEPTAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACEPTAR1ActionPerformed
        try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO PRODUCTO(ID_PRODUCTO,NOMBRE_PROD,ID_T,CANTIDAD,DESCRIPCION_P,PRECIO) VALUES(?,?,?,?,?,?)");
            if (CAMP1.getText()=="" || CAMP2.getText()=="" || Select_tipo.getSelectedIndex()==0 || CAMP4.getText()=="" || CAMP5.getText()=="" || CAMP6.getText()==""){
                JOptionPane.showMessageDialog(null, "No pueden haber campos vacíos");
            }
            
            else{
            if (existeID(CAMP1.getText())==0 && existeNOMBRE(CAMP2.getText())==0){
            pps.setString(1,CAMP1.getText());
            pps.setString(2,CAMP2.getText());
            pps.setString(3,Select_tipo.getSelectedItem().toString());
            pps.setString(4,CAMP4.getText());
            pps.setString(5,CAMP5.getText());
            pps.setString(6,CAMP6.getText());
            pps.executeUpdate();
            pps = cn.prepareStatement("INSERT INTO OFRECEN(ID_PRODUCTO,ID_PROVEEDOR,PRECIO_COMPRA) VALUES(?,?,?)");
            pps.setString(1, CAMP1.getText());
            if (Select_proveedor.getSelectedIndex()==0)
            {
                pps.setInt(2,Select_proveedor.getSelectedIndex()+4);
            }
            else{
            pps.setInt(2,Select_proveedor.getSelectedIndex());}
            pps.setString(3,CAMP7.getText());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS GUARDADOS");
            limpiar_campos();
            mostrartabla("");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "EL CODIGO Y/O NOMBRE INGRESADO YA EXISTE");
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_ACEPTAR1ActionPerformed

    private void LIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPIARActionPerformed
        limpiar_campos();
    }//GEN-LAST:event_LIMPIARActionPerformed

    private void MODIFICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODIFICARActionPerformed
        
        int fila = Mostrar2.getSelectedRow();
        if (fila>=0)
        {

            ModificarProducto MP = new ModificarProducto(this,false);
            MP.setVisible(true);
            ModificarProducto.CAMP1.setEditable(false); 
            ModificarProducto.CAMP1.setEnabled(false);
            ModificarProducto.CAMP1.setText(Mostrar2.getValueAt(fila, 0).toString());
            ModificarProducto.CAMP2.setText(Mostrar2.getValueAt(fila, 1).toString());
            ModificarProducto.Select_tipo.setSelectedItem(Mostrar2.getValueAt(fila, 2).toString());
            ModificarProducto.CAMP4.setText(Mostrar2.getValueAt(fila, 3).toString());
            ModificarProducto.CAMP5.setText(Mostrar2.getValueAt(fila, 4).toString());
            ModificarProducto.CAMP6.setText(Mostrar2.getValueAt(fila, 5).toString());
            ModificarProducto.Select_proveedor.setSelectedItem(Mostrar2.getValueAt(fila, 6).toString());
            ModificarProducto.CAMP7.setText(Mostrar2.getValueAt(fila, 8).toString());

        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione el Producto a Modificar");
        }
    }//GEN-LAST:event_MODIFICARActionPerformed

    private void BBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarActionPerformed
        Filtros.add(BNombre);
        Filtros.add(BTipo);
        if(BTipo.isSelected()){atributo="ID_T"; mostrartabla(CAMPB.getText());}
        else if(BNombre.isSelected()){atributo="NOMBRE_PROD"; mostrartabla(CAMPB.getText());}
        else{JOptionPane.showMessageDialog(null, "No hay filtros de búsqueda seleccionados");
    }//GEN-LAST:event_BBuscarActionPerformed
    }
    
    

    
    private void CAMP1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CAMP1KeyTyped
                                 
        char c = evt.getKeyChar();
        if (c<'0' || c>'9') evt.consume();
              
    }//GEN-LAST:event_CAMP1KeyTyped

    private void CAMP6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CAMP6KeyTyped
        char c = evt.getKeyChar();
        if (c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_CAMP6KeyTyped

    private void Select_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Select_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Select_tipoActionPerformed

    private void Select_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Select_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Select_proveedorActionPerformed

    private void CAMP7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAMP7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CAMP7ActionPerformed

    private void CAMP7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CAMP7KeyTyped
        char c = evt.getKeyChar();
        if (c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_CAMP7KeyTyped

    private void BBuscarLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarLActionPerformed
        FiltrosL.add(BNombreL);
        FiltrosL.add(BCodigoL);
        if(BCodigoL.isSelected()){atributo="ID_PRODUCTO"; mostrartablaProd(CAMPBL.getText());}
        else if(BNombreL.isSelected()){atributo="NOMBRE_PROD"; mostrartablaProd(CAMPBL.getText());}
        else{JOptionPane.showMessageDialog(null, "No hay filtros de búsqueda seleccionados");
    }  
    }//GEN-LAST:event_BBuscarLActionPerformed

    private void NuevoLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoLoteActionPerformed
        int fila = mostrarProd.getSelectedRow();
        if (fila>=0)
        {

            NuevoLote NL = new NuevoLote(this,false);
            NL.setVisible(true);
            NL.IDPRODUCTO.setEditable(false); 
            NL.IDPRODUCTO.setEnabled(false);
            NL.NOMBREPRODUCTO.setEditable(false);
            NL.NOMBREPRODUCTO.setEnabled(false);
            NL.IDPRODUCTO.setText(mostrarProd.getValueAt(fila, 0).toString());
            NL.NOMBREPRODUCTO.setText(mostrarProd.getValueAt(fila, 1).toString());

        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione el Producto a Modificar");
        }
    }//GEN-LAST:event_NuevoLoteActionPerformed

        
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACEPTAR1;
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JButton BBuscar;
    private javax.swing.JButton BBuscarL;
    private javax.swing.JRadioButton BCodigoL;
    private javax.swing.JRadioButton BNombre;
    private javax.swing.JRadioButton BNombreL;
    private javax.swing.JRadioButton BTipo;
    private javax.swing.JTextField CAMP1;
    private javax.swing.JTextField CAMP2;
    private javax.swing.JTextField CAMP4;
    private javax.swing.JTextField CAMP5;
    private javax.swing.JTextField CAMP6;
    private javax.swing.JTextField CAMP7;
    private javax.swing.JTextField CAMPB;
    private javax.swing.JTextField CAMPBL;
    private javax.swing.ButtonGroup Filtros;
    private javax.swing.ButtonGroup FiltrosL;
    private javax.swing.JButton LIMPIAR;
    private javax.swing.JButton MODIFICAR;
    public static javax.swing.JTable Mostrar2;
    private javax.swing.JButton NuevoLote;
    private javax.swing.JComboBox<String> Select_proveedor;
    private javax.swing.JComboBox<String> Select_tipo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable mostrarProd;
    // End of variables declaration//GEN-END:variables

}
