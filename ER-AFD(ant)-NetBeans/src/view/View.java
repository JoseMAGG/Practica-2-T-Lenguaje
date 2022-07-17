/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.AutomataFinito;

/**
 *
 * @author Jose
 */
public class View extends javax.swing.JFrame {

    private AutomataFinito AF;

    /**
     * Creates new form view
     */
    public View() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outTbl = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cadenaTxt = new javax.swing.JTextField();
        generarBtn = new javax.swing.JButton();
        Lbl1 = new javax.swing.JLabel();
        expresionTxt = new javax.swing.JTextField();
        validarBtn = new javax.swing.JButton();
        outLbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(445, 535));
        setMinimumSize(new java.awt.Dimension(445, 535));
        setPreferredSize(new java.awt.Dimension(445, 535));
        setSize(new java.awt.Dimension(445, 535));

        jPanel1.setMaximumSize(new java.awt.Dimension(420, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(416, 450));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        outTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        outTbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        outTbl.setAutoscrolls(false);
        jScrollPane1.setViewportView(outTbl);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Expresión Regular:");

        generarBtn.setText("Generar AF");
        generarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarBtnActionPerformed(evt);
            }
        });

        Lbl1.setText("Cadena para validar: ");

        validarBtn.setText("Validar Cadena");
        validarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarBtnActionPerformed(evt);
            }
        });

        outLbl.setText("Validación: ");

        jButton1.setText("Ayuda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(expresionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(validarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(generarBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(Lbl1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(cadenaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(outLbl)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(expresionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl1)
                    .addComponent(cadenaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generarBtn)
                    .addComponent(validarBtn)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(outLbl)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Muestra en la JTable el autómata finito creado a partir de la expresión
     * regular ingresada
     *
     * @param evt
     */
    private void generarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarBtnActionPerformed
        String entrada = expresionTxt.getText().trim();

        try {
            if (!entrada.isEmpty()) {
                String simbolos = Controller.hallarSimbolos(entrada);
                System.out.println(simbolos);
                if (simbolos == null) {
                    throw new Exception("No hay símbolos en la entrada ingresada");
                }
                DefaultTableModel model = (DefaultTableModel) outTbl.getModel();
                model.setColumnCount(0);
                model.setRowCount(0);

                DefaultTableColumnModel columnModel = (DefaultTableColumnModel) outTbl.getColumnModel();
                int columnas = simbolos.length() + 2;
                model.setColumnCount(columnas);
                outTbl.getColumn(columnModel.getColumn(0).getIdentifier()).setHeaderValue("Estados");
                for (int i = 1; i < columnas - 1; i++) {
                    outTbl.getColumn(columnModel.getColumn(i)
                            .getIdentifier()).setHeaderValue(simbolos.charAt(i - 1));
                }
                outTbl.getColumn(columnModel.getColumn(columnas - 1)
                        .getIdentifier()).setHeaderValue("Acepta");
                outTbl.setColumnModel(columnModel);

                model = (DefaultTableModel) outTbl.getModel();
                AF = Controller.generarAF(entrada);
                String[][] salidas = Controller.generarSalidas(AF);
                int filas = salidas.length;
                model.setRowCount(filas);

                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        model.setValueAt(salidas[i][j], i, j);
                    }
                }
                outLbl.setText("Salida: Se muestra el AFD generado de " + entrada);
                JOptionPane.showMessageDialog(rootPane,
                        "Se ha generado el AFD mínimo con éxito");
                return;
            }
            throw new Exception("Debe ingresar una expresión regular");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }

    }//GEN-LAST:event_generarBtnActionPerformed

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarBtnActionPerformed

        try {
            if (AF == null) {
                throw new Exception("Debe generar un AF antes de validar una cadena");
            }
            String entrada = cadenaTxt.getText().trim();
            boolean validacion = Controller.validarCadena(AF, entrada);
            if (validacion) {
                JOptionPane.showMessageDialog(rootPane,
                        "La expresión ingresada ha sido ACEPTADA");
            } else {
                JOptionPane.showMessageDialog(rootPane,
                        "La expresión ingresada ha sido RECHAZADA");
            }
            outLbl.setText("Validación: " + validacion);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,
                    ex.getMessage());
        }
    }//GEN-LAST:event_validarBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String message = "• En el campo de \"Espresión Regular\" puede ingresar una"
                + "\nexpresión regular (ER) o regex para generar un autómata finíto"
                + "\ndeterminístico (AF) presionando el botón \"Generar AF\""
                + "\n\n• Para escribir la ER tenga en cuenta los operadores:"
                + "\n     • OR: se escribe con \"|\""
                + "\n     • CONCAT: se escribe con \".\""
                + "\n        o se escriben dos símbolos seguidos"
                + "\n     • KLEENE STAR: se escribe con \"*\""
                + "\n     • KLEENE PLUS: se escribe con \"+\""
                + "\n     • AGRUPAR: se utilizan solamente paréntesis"
                + "\n        \"(\"  y  \")\" para agrupar"
                + "\n\n• Cuando se genera un AF este se muestra en la tabla."
                + "\n\n• Una vez generado el AF podrá ingresar una expresión en"
                + "\nel campo \"Cadena para validar\" y presionar el botón"
                + "\n\"Validar Cadena\", en la parte inferior se mostrará"
                + "\n\"Validación: true\" si la expresión hace parte de la regex"
                + "\ninicial o \"Validación: false\" si no hace parte de la misma.";
        JOptionPane.showMessageDialog(rootPane, message, "Ayuda", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lbl1;
    private javax.swing.JTextField cadenaTxt;
    private javax.swing.JTextField expresionTxt;
    private javax.swing.JButton generarBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel outLbl;
    private javax.swing.JTable outTbl;
    private javax.swing.JButton validarBtn;
    // End of variables declaration//GEN-END:variables
}
