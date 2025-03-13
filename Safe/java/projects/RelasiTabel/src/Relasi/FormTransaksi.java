/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Relasi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eternity
 */
public class FormTransaksi extends javax.swing.JFrame {

    /**
     * Creates new form FormTransaksi
     */
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel<String> comboBoxDetailTable, comboBoxPelanggantable;
    
    public FormTransaksi() {
        initComponents();
        
        tableModel = new DefaultTableModel(null, new String[] {"No Faktur","Nama Barang","Qty","Tanggal","Kode Pelanggan","Nama Pelanggan"});
        tableTransaksi.setModel(tableModel);
        comboBoxDetailTable = new DefaultComboBoxModel<>();
        noFakturCBB.setModel(comboBoxDetailTable);
        noFakturCBB.addItem("Pilih No Faktur");
        for (String key : mapDetailTable().keySet()) {
            noFakturCBB.addItem(key);
        }
        comboBoxPelanggantable = new DefaultComboBoxModel<>();
        kodePelangganCBB.setModel(comboBoxPelanggantable);
        kodePelangganCBB.addItem("Pilih Pelanggan");
        for (String key : mapPelangganTable().keySet()) {
            kodePelangganCBB.addItem(key);
        }
        showDataFromDB();
    }
    
    private void showDataFromDB() {
        try {
            java.sql.Connection conn = koneksi.koneksiDB();
            String query = "SELECT transaksi.noFaktur, "
                    + "barang.nama AS namaBarang, detail.qty, "
                    + "transaksi.tgl, "
                    + "pelanggan.kode AS kodePelanggan, "
                    + "pelanggan.nama AS namaPelanggan "
                    + "FROM transaksi "
                    + "JOIN detail ON transaksi.noFaktur = detail.noFaktur "
                    + "JOIN barang ON detail.kodeBarang = barang.kode "
                    + "JOIN pelanggan ON transaksi.kodePelanggan = pelanggan.kode;";
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {                
                String[] data = {
                    String.valueOf(set.getLong("noFaktur")),
                    set.getString("namaBarang"),
                    String.valueOf(set.getLong("qty")),
                    set.getString("tgl"),
                    String.valueOf(set.getLong("kodePelanggan")),
                    set.getString("namaPelanggan")
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
        int getTableRowIndex = tableTransaksi.getSelectedRow();
        String getNoFaktur = tableTransaksi.getValueAt(getTableRowIndex, 0).toString();
        String getNamaBarang = tableTransaksi.getValueAt(getTableRowIndex, 1).toString();
        String getQty = tableTransaksi.getValueAt(getTableRowIndex, 2).toString();
        String getTgl = tableTransaksi.getValueAt(getTableRowIndex, 3).toString();
        String getKodePelanggan = tableTransaksi.getValueAt(getTableRowIndex, 4).toString();
        String getNamaPelanggan = tableTransaksi.getValueAt(getTableRowIndex, 5).toString();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            Date date = sdf.parse(getTgl);
            noFakturCBB.setSelectedItem(getNoFaktur + " (" + getNamaBarang + " " + getQty + ")");
            JCalendarTanggal.setDate(date);
            kodePelangganCBB.setSelectedItem(getKodePelanggan + ". " + getNamaPelanggan);
        } catch (Exception e) {
        }
    }
    
    private LinkedHashMap<String, String> mapDetailTable() {
        LinkedHashMap<String, String> itemMap = new LinkedHashMap<>();
        try {
            java.sql.Connection conn = koneksi.koneksiDB();
            String query = "SELECT "
                    + "detail.noFaktur,"
                    + "barang.nama,"
                    + "detail.qty "
                    + "FROM detail "
                    + "JOIN barang ON barang.kode = detail.kodeBarang "
                    + "ORDER BY detail.noFaktur ASC;";
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String key = String.valueOf(set.getLong("noFaktur")) + " (" + set.getString("nama") + " " + String.valueOf(set.getLong("qty")) + ")";
                String value = String.valueOf(set.getLong("noFaktur"));
                itemMap.put(key,value);
            }
        } catch (SQLException e) {
        } finally {
            return itemMap;
        }
    }
    
    private String getNoFakturFromCBB() {
        HashMap<String, String> list = mapDetailTable();
        return list.get(noFakturCBB.getSelectedItem());
    }
    
    private LinkedHashMap<String, String> mapPelangganTable() {
        LinkedHashMap<String, String> itemMap = new LinkedHashMap<>();
        try {
            java.sql.Connection conn = koneksi.koneksiDB();
            String query = "SELECT * FROM pelanggan ORDER BY kode ASC;";
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String key = String.valueOf(set.getLong("kode")) + ". " + set.getString("nama");
                String value =  String.valueOf(set.getLong("kode"));
                itemMap.put(key,value);
            }
        } catch (SQLException e) {
        } finally {
            return itemMap;
        }
    }
    
    private String getKodePelangganFromCBB() {
        HashMap<String, String> list = mapPelangganTable();
        return list.get(kodePelangganCBB.getSelectedItem());
    }
    
    private String getDate() {
        Date getDate = JCalendarTanggal.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(getDate);
    }
    
    private void saveToTransaksiTable() {
        try {
            String getNoFaktur = getNoFakturFromCBB();
            String getDate = getDate();
            String getKodePelanggan = getKodePelangganFromCBB();
            String query = String.format("INSERT INTO transaksi VALUES (%s,'%s',%s);", getNoFaktur, getDate, getKodePelanggan);
            koneksi.koneksiDB().prepareStatement(query).execute();
        } catch (SQLException e) {
        }
    }
    
    private void deleteRowFromTransaksiTable() {
        try {
            String noFaktur = getNoFakturFromCBB();
            String tgl = getDate();
            String kodePelanggan = getKodePelangganFromCBB();
            String query = String.format("DELETE FROM transaksi WHERE noFaktur = %s AND tgl = '%s' AND kodePelanggan = %s", 
                    noFaktur,tgl,kodePelanggan);
            koneksi.koneksiDB().prepareStatement(query).execute();
        } catch (Exception e) {
        }
    }
    
    private void resetAllField() {
        noFakturCBB.setSelectedIndex(0);
        kodePelangganCBB.setSelectedIndex(0);
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
        tableTransaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        kodePelangganCBB = new javax.swing.JComboBox<>();
        noFakturCBB = new javax.swing.JComboBox<>();
        JCalendarTanggal = new de.wannawork.jcalendar.JCalendarComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableTransaksi);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Form Transaksi");

        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

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

        jLabel2.setText("No Faktur");

        jLabel3.setText("Tanggal");

        jLabel4.setText("Pelanggan");

        kodePelangganCBB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        noFakturCBB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addGap(78, 78, 78))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kodePelangganCBB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JCalendarTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noFakturCBB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(54, 54, 54)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(noFakturCBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JCalendarTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(kodePelangganCBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resetButton)
                            .addComponent(saveButton)
                            .addComponent(deleteButton))
                        .addContainerGap(214, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        resetTable();
        deleteRowFromTransaksiTable();
        showDataFromDB();
        resetAllField();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        resetAllField();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        resetTable();
        saveToTransaksiTable();
        showDataFromDB();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void tableTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTransaksiMouseClicked
        selectRowFromTable();
    }//GEN-LAST:event_tableTransaksiMouseClicked

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
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.wannawork.jcalendar.JCalendarComboBox JCalendarTanggal;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kodePelangganCBB;
    private javax.swing.JComboBox<String> noFakturCBB;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable tableTransaksi;
    // End of variables declaration//GEN-END:variables
}
