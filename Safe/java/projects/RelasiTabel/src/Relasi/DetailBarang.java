/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Relasi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author malsoryz
 */
public class DetailBarang extends javax.swing.JFrame {

    /**
     * Creates new form DetailBarang
     */
    
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel<String> comboBoxModel;
    
    public DetailBarang() {
        initComponents();
        
        tableModel = new DefaultTableModel(null, new String[] {"No Faktur","Kode Barang","Nama Barang","Harga Barang","Kuantitas"});
        tableDetail.setModel(tableModel);
        comboBoxModel = new DefaultComboBoxModel<>();
        kodeBarangComboBox.setModel(comboBoxModel);
        kodeBarangComboBox.addItem("Pilih Kode");
        for (String key : mapKodeColumnFromDB().keySet()) {
            kodeBarangComboBox.addItem(key);
        }
        showDataFromDB();
       
    }
    
    private void resetAllField() {
        fieldNoFaktur.setText("");
        kodeBarangComboBox.setSelectedIndex(0);
        viewNamaBarang.setText("");
        viewHargaBarang.setText("");
        fieldKuantitas.setText("");
    }
    
    private void showDataFromDB() {
        try {
            java.sql.Connection conn = koneksi.koneksiDB();
            String query = "SELECT detail.noFaktur, barang.kode, barang.nama, barang.harga, detail.qty FROM detail JOIN barang ON detail.kodeBarang = barang.kode ORDER BY detail.noFaktur ASC;";
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {                
                String[] data = {
                    String.valueOf(set.getLong("noFaktur")),
                    String.valueOf(set.getLong("kode")),
                    set.getString("nama"),
                    String.valueOf(set.getLong("harga")),
                    String.valueOf(set.getLong("qty"))
                };
                tableModel.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    
    private void resetTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }
    
    private void selectRowFromTable() {
        try {
            String clickTable = (tableDetail.getModel().getValueAt(tableDetail.getSelectedRow(), 0).toString());
            String query = String.format(
                    "SELECT detail.noFaktur, barang.kode, barang.nama, barang.harga, detail.qty FROM detail JOIN barang ON detail.kodeBarang = barang.kode WHERE detail.noFaktur = %s;",
                    clickTable
            );
            ResultSet set = koneksi.koneksiDB().createStatement().executeQuery(query);
            while (set.next()) {
                fieldNoFaktur.setText(String.valueOf(set.getLong("noFaktur")));
                kodeBarangComboBox.setSelectedItem(set.getLong("kode") + " (" + set.getString("nama") + ")");
                viewNamaBarang.setText(set.getString("nama"));
                viewHargaBarang.setText(String.valueOf(set.getLong("harga")));
                fieldKuantitas.setText(String.valueOf(set.getLong("qty")));
            }
        } catch (SQLException e) {
        }
    }
    
    private LinkedHashMap<String, String> mapKodeColumnFromDB() {
        LinkedHashMap<String, String> mapItem = new LinkedHashMap<>();
        try {
            java.sql.Connection conn = koneksi.koneksiDB();
            String query = "SELECT kode, nama FROM barang ORDER BY kode ASC";
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String key = set.getString("nama");
                String value = String.valueOf(set.getLong("kode"));
                mapItem.put(String.format("%s (%s)", value, key), value);
            }
        } catch (SQLException e) {
        } finally {
            return mapItem;
        }
    }
    
    private String getIdFromSelectedItem() {
        HashMap<String, String> list = mapKodeColumnFromDB();
        return list.get(kodeBarangComboBox.getSelectedItem());
    }

    private void getDataFromSelectedItem() {
        try {
            String getKode = getIdFromSelectedItem();
            if (getKode != null) {
                java.sql.Connection conn = koneksi.koneksiDB();
                String query = String.format("SELECT * FROM barang WHERE kode = %s", getKode);
                ResultSet set = conn.createStatement().executeQuery(query);
                while (set.next()) {
                    viewNamaBarang.setText(set.getString("nama"));
                    viewHargaBarang.setText(String.valueOf(set.getString("harga")));
                }
            } else {
                viewNamaBarang.setText("");
                viewHargaBarang.setText("");
            }
        } catch (Exception e) {
        }
    }
    
    private void saveToDetailDB() {
        try {
            String noFaktur = fieldNoFaktur.getText();
            String kodeBarang = getIdFromSelectedItem();
            String kuantitas = fieldKuantitas.getText();
            String query = String.format("INSERT INTO detail VALUES (%s,%s,%s);", noFaktur, kodeBarang, kuantitas);
            koneksi.koneksiDB().prepareStatement(query).execute();
        } catch (Exception e) {
        }
    }
    
    private void editDataToDB() {
        try {
            String noFaktur = fieldNoFaktur.getText();
            String kodeBarang = getIdFromSelectedItem();
            String kuantitas = fieldKuantitas.getText();
            String query = String.format("UPDATE detail SET kodeBarang = %s, qty = %s WHERE noFaktur = %s;", kodeBarang, kuantitas, noFaktur);
            koneksi.koneksiDB().prepareStatement(query).execute();
        } catch (Exception e) {
        }
    }
    
    private void deleteRowFromDB() {
        try {
            String noFaktur = fieldNoFaktur.getText();
            String query = String.format("DELETE FROM detail WHERE noFaktur = %s", noFaktur);
            koneksi.koneksiDB().prepareStatement(query).execute();
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetail = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fieldNoFaktur = new javax.swing.JTextField();
        kodeBarangComboBox = new javax.swing.JComboBox<>();
        viewNamaBarang = new javax.swing.JTextField();
        viewHargaBarang = new javax.swing.JTextField();
        fieldKuantitas = new javax.swing.JTextField();
        resetButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Detail Barang");

        tableDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDetail);

        jLabel2.setText("No Faktur");

        jLabel3.setText("Kode Barang");

        jLabel4.setText("Nama Barang");

        jLabel5.setText("Harga Barang");

        jLabel6.setText("Kuantitas");

        kodeBarangComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        kodeBarangComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kodeBarangComboBoxItemStateChanged(evt);
            }
        });

        viewNamaBarang.setEditable(false);

        viewHargaBarang.setEditable(false);

        resetButton.setText("RESET");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        editButton.setText("EDIT");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(viewHargaBarang)
                            .addComponent(viewNamaBarang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldKuantitas)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(kodeBarangComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldNoFaktur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldNoFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kodeBarangComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viewNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viewHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resetButton)
                            .addComponent(saveButton)
                            .addComponent(editButton)
                            .addComponent(deleteButton))
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kodeBarangComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kodeBarangComboBoxItemStateChanged
        getDataFromSelectedItem();
    }//GEN-LAST:event_kodeBarangComboBoxItemStateChanged

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        resetTable();
        saveToDetailDB();
        showDataFromDB();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        resetAllField();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void tableDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailMouseClicked
        selectRowFromTable();
    }//GEN-LAST:event_tableDetailMouseClicked

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        resetTable();
        editDataToDB();
        showDataFromDB();
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        resetTable();
        deleteRowFromDB();
        showDataFromDB();
        resetAllField();
    }//GEN-LAST:event_deleteButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DetailBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField fieldKuantitas;
    private javax.swing.JTextField fieldNoFaktur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kodeBarangComboBox;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable tableDetail;
    private javax.swing.JTextField viewHargaBarang;
    private javax.swing.JTextField viewNamaBarang;
    // End of variables declaration//GEN-END:variables
}
