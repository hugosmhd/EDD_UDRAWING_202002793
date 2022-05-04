/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import estructuras.ArbolB;
import estructuras.ListaAdyacencia;
import estructuras.TablaHash;

import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import objetos.Cliente;

/**
 *
 * @author hugom
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    private ImageIcon imagen;
    private Icon icono;
    private ArbolB arbolitoB;
    private TablaHash tablaMsj;
    private ListaAdyacencia listaAdyacencia;
    
    public Login(ArbolB arbolitoB, TablaHash tablaMsj, ListaAdyacencia listaAdyacencia) {
        initComponents();
        this.arbolitoB = arbolitoB;
        this.tablaMsj = tablaMsj;
        this.listaAdyacencia = listaAdyacencia;
        
        this.pintarImagen(this.imgDTT, getClass().getResource("/image/ECYS.png"));
        this.pintarImagen(this.imgUser, getClass().getResource("/image/usuario.png"));
        this.pintarImagen(this.imgPassword, getClass().getResource("/image/padlock.png"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDpi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblRegistrarUsuario = new javax.swing.JLabel();
        imgDTT = new javax.swing.JLabel();
        imgPassword = new javax.swing.JLabel();
        imgUser = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtDpi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDpiActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario");

        jLabel2.setText("Password");

        lblRegistrarUsuario.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblRegistrarUsuario.setForeground(new java.awt.Color(0, 0, 204));
        lblRegistrarUsuario.setText("Crear un nuevo usuario");
        lblRegistrarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistrarUsuarioMouseClicked(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(204, 204, 204));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogin.setText("Iniciar Sesion");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(imgDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRegistrarUsuario)
                            .addComponent(txtDpi, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                            .addComponent(txtPassword)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(imgDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(imgUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel2)
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(imgPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblRegistrarUsuario)
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDpiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDpiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDpiActionPerformed

    private void lblRegistrarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarUsuarioMouseClicked
        // TODO add your handling code here:
        this.dispose();
        Registro registro = new Registro(this.arbolitoB, tablaMsj, listaAdyacencia);
        registro.setVisible(true);
        registro.setLocationRelativeTo(null);
    }//GEN-LAST:event_lblRegistrarUsuarioMouseClicked

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // TODO add your handling code here:
        String username = this.txtDpi.getText();
        String password = passwordString(this.txtPassword.getPassword());
        if(username.equals("admin") && password.equals("EDD2022")){
            this.dispose();
            InicioAdmin admin = new InicioAdmin(this.arbolitoB, this.tablaMsj, this.listaAdyacencia);
            admin.setVisible(true);
            admin.setLocationRelativeTo(null);
        } else {
            try {
                Cliente clienteEncontrado = arbolitoB.buscarUsuario(username);
                if(clienteEncontrado != null) {
                    if(password.equals(clienteEncontrado.getPassword())) {
                        this.dispose();
                        InicioUsuario user = new InicioUsuario(this.arbolitoB, clienteEncontrado.getArbolitoAVL(), clienteEncontrado.getArbolitoBB(), 
                                clienteEncontrado.getListaAlbumes(), tablaMsj, this.listaAdyacencia);
                        user.setVisible(true);
                        user.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "DPI y/o password incorrecta");
                    }
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "DPI y/o password incorrecta");
            }
        }
    }//GEN-LAST:event_btnLoginMouseClicked

    /**
     * @param args the command line arguments
     */
  
    
    private void pintarImagen(JLabel lbl, URL ruta){
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(
                this.imagen.getImage().getScaledInstance(
                        lbl.getWidth(), 
                        lbl.getHeight(), 
                        Image.SCALE_DEFAULT
                )
        );
        lbl.setIcon(this.icono);
        this.repaint();
        
    }
    
    private String passwordString(char[] input) {
        String password = "";
        for(char carac: input) {
            password += carac;
        }
        return password;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel imgDTT;
    private javax.swing.JLabel imgPassword;
    private javax.swing.JLabel imgUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblRegistrarUsuario;
    private javax.swing.JTextField txtDpi;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
