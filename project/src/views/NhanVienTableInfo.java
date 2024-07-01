/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.QuanLyBan;
import controllers.QuanLyHoaDon;
import controllers.QuanLyVP;
import controllers.QuanLyVoucher;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.Ban;
import models.VatPham;
import models.orderItem;
import models.voucher;

/**
 *
 * @author ACER
 */
public class NhanVienTableInfo extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienTableInfo
     */
    private QuanLyVoucher qlVoucher;
    public NhanVienTableInfo() {
        initComponents();
        this.setLocationRelativeTo(null);

    }

    private QuanLyBan qlBan;
    public void setTable(Ban table, QuanLyBan qlBan) {
        this.table = table;
        this.qlBan = qlBan;
        qlHoaDon = new QuanLyHoaDon();
        QuanLyVP qlVatPham = new QuanLyVP();

        LoadTable(qlHoaDon.GetHoaDonWithIndex(table.getSoBan()));
        LoadMenu(qlVatPham.GetMenu());
        LoadOrdering();
    }

    private QuanLyHoaDon qlHoaDon;

    private void LoadTable(ArrayList<orderItem> orderItemArr) {
        txtNumTable.setText("Số Bàn: " + table.getSoBan());
        TieuDeOrdered();
        LoadOrdered(orderItemArr);
    }

    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0 && column != 1 && column != 2 && column != 3;
        }
    };

    DefaultTableModel dtmMenu = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0 && column != 1;
        }
    };

    DefaultTableModel dtmOrdering = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0 && column != 1 && column != 2 && column != 3;
        }
    };

    private void LoadOrdered(ArrayList<orderItem> orderItemArr) {
        vNDungOrdered = new Vector();
        int sum = 0;
        for (orderItem order : orderItemArr) {
            Nhap(order);
            sum += order.getSoLuong() * order.getvatpham().getGiaThanh();
        }

        dtm.setDataVector(vNDungOrdered, vTieuDeOrdered);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtm);
        tableOrdered.setRowSorter(sorter);
        sorter.setSortable(2, true);
        sorter.sort();

        tableOrdered.setModel(dtm);

        btnSumPrice.setText(sum + "");
    }

    private void LoadMenu(ArrayList<VatPham> vatPhamArr) {
        TieuDeMenu();
        vNDungMenu = new Vector();
        for (VatPham vp : vatPhamArr) {
            NhapMenu(vp);
        }

        dtmMenu.setDataVector(vNDungMenu, vTieuDeMenu);
        tableMenu.setModel(dtmMenu);

        tableMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ClickRowMenu(vatPhamArr.get(tableMenu.getSelectedRow()));
                }
            }
        });
    }

    private void LoadOrdering() {
        orderingArr = new ArrayList<orderItem>();
        vNDungOrdering = new Vector();
        TieuDeOrdering();
        dtmOrdering.setDataVector(vNDungOrdering, vTieuDeOrdered);
        tableOrdering.setModel(dtmOrdering);

        tableOrdering.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ClickRowOrdering(tableOrdering.getSelectedRow());
                }
            }
        });
    }

    private void ClickRowMenu(VatPham vp) {
        OrderFrame order = new OrderFrame();
        order.setData(vp, this);
        order.setVisible(true);
    }

    private void ClickRowOrdering(int index) {
        DefaultTableModel model = (DefaultTableModel) tableOrdering.getModel();
        if (index != -1) { // Check if a row is selected
            model.removeRow(index);
            orderingArr.remove(index);
        }
    }

    Vector vTieuDeOrdered = new Vector();
    Vector vNDungOrdered = new Vector();
    Vector vDongOrdered;
    Vector vTieuDeMenu = new Vector();
    Vector vNDungMenu = new Vector();
    Vector vDongMenu;
    Vector vTieuDeOrdering = new Vector();
    Vector vNDungOrdering = new Vector();
    Vector vDongOrdering;

    void TieuDeOrdered() {
        vTieuDeOrdered = new Vector();
        vTieuDeOrdered.add("Tên Món");
        vTieuDeOrdered.add("Số Lượng");
        vTieuDeOrdered.add("Thời Gian");
        vTieuDeOrdered.add("Tổng");
    }

    void TieuDeMenu() {
        vTieuDeMenu = new Vector();
        vTieuDeMenu.add("Tên Món");
        vTieuDeMenu.add("Đơn Giá");
    }

    void TieuDeOrdering() {
        vTieuDeOrdering = new Vector();
        vTieuDeOrdering.add("Tên Món");
        vTieuDeOrdering.add("Số Lượng");
        vTieuDeOrdering.add("Thời Gian");
        vTieuDeOrdering.add("Tổng");
    }

    void Nhap(orderItem order) {
        vDongOrdered = new Vector();
        vDongOrdered.add(order.getvatpham().getTenVatPham());
        vDongOrdered.add(order.getSoLuong());
        vDongOrdered.add(order.getThoiGianGoiMon());
        vDongOrdered.add(order.getSoLuong() * order.getvatpham().getGiaThanh());

        vNDungOrdered.add(vDongOrdered);
    }

    void NhapMenu(VatPham vp) {
        vDongMenu = new Vector();
        vDongMenu.add(vp.getTenVatPham());
        vDongMenu.add(vp.getGiaThanh());
        vNDungMenu.add(vDongMenu);
    }

    private ArrayList<orderItem> orderingArr;

    public void NhapOrdering(VatPham vp, int sl) {
        if (sl <= 0) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTime = calendar.getTime();
        java.sql.Time sqlTime = new java.sql.Time(currentTime.getTime());
        orderingArr.add(new orderItem(vp, sl, sqlTime));

        vDongOrdering = new Vector();
        vDongOrdering.add(vp.getTenVatPham());
        vDongOrdering.add(sl);
        //int hr = LocalTime.now().getHour();
        //int minutes = LocalTime.now().getMinute();
        vDongOrdering.add(sqlTime);
        vDongOrdering.add(sl * vp.getGiaThanh());

        vNDungOrdering.add(vDongOrdering);
        dtmOrdering.setDataVector(vNDungOrdering, vTieuDeOrdered);
        tableOrdering.setModel(dtmOrdering);
    }

    private Ban table;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSumPrice = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOrdering = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableOrdered = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtNumTable = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        voucherInput = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMenu = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        btnSumPrice.setBackground(new java.awt.Color(0, 204, 255));
        btnSumPrice.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnSumPrice.setText("0000");
        btnSumPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSumPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumPriceActionPerformed(evt);
            }
        });

        tableOrdering.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Món", "Số Lượng", "Thời Gian", "Đơn Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableOrdering);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đang Order", jPanel2);

        tableOrdered.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Món", "Số Lượng", "Thời Gian", "Đơn Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableOrdered);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đã Order", jPanel3);

        txtNumTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNumTable.setText("Số Bàn");

        jButton1.setText("Quay Lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNumTable, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(txtNumTable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton3.setText("Order");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        voucherInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voucherInputActionPerformed(evt);
            }
        });

        jButton4.setText("Áp dụng");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSumPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(voucherInput, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(voucherInput))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSumPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tableMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên Món", "Đơn Giá"
            }
        ));
        jScrollPane2.setViewportView(tableMenu);

        jTextField1.setText(".......");

        jLabel1.setText("Tìm Kiếm");

        jButton2.setText("Lọc");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSumPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumPriceActionPerformed
        // TODO add your handling code here:
        int tongTien = Integer.parseInt(btnSumPrice.getText()) ;
        qlHoaDon.PayTable(tongTien);
        NhanVienTable nvTable = new NhanVienTable();
        nvTable.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSumPriceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        NhanVienTable table = new NhanVienTable();
        table.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (orderingArr == null || orderingArr.size() <= 0) {
            return;
        }
        if (qlHoaDon.getIdhoaDon() == -1) {
            qlHoaDon.CreateIDHoaDon();
            qlBan.updateBan(table.getSoBan());
        }
        
        for (orderItem order : orderingArr) {
            qlHoaDon.OrderCurrentHoaDon(order.getvatpham().getID(), order.getSoLuong(), order.getThoiGianGoiMon());
        }

        DefaultTableModel model = (DefaultTableModel) tableOrdering.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        
        model.fireTableDataChanged();
        orderingArr = new ArrayList<orderItem>();

        model = (DefaultTableModel) tableOrdered.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        model.fireTableDataChanged();

        LoadTable(qlHoaDon.GetHoaDonWithIndex(table.getSoBan()));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void voucherInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voucherInputActionPerformed
    
    }//GEN-LAST:event_voucherInputActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String maVoucher = voucherInput.getText();
        this.qlVoucher = new QuanLyVoucher();
        boolean isaccess = this.qlVoucher.checkAccessVoucher(maVoucher);
        System.out.println(isaccess);
        if(isaccess){
            System.out.println(table.getSoBan());
            ArrayList<orderItem> orderItemArr =  qlHoaDon.GetHoaDonWithIndex(table.getSoBan());
            int sum = 0;
            for (orderItem order : orderItemArr) {
            Nhap(order);
            sum += order.getSoLuong() * order.getvatpham().getGiaThanh();
             }
            voucher vc = this.qlVoucher.getVoucherByMaVoucher(maVoucher);
            
            btnSumPrice.setText((sum - (sum * vc.getGiaTri()/100)) + "");

        }
        voucherInput.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienTableInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienTableInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienTableInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienTableInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienTableInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSumPrice;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableMenu;
    private javax.swing.JTable tableOrdered;
    private javax.swing.JTable tableOrdering;
    private javax.swing.JLabel txtNumTable;
    private javax.swing.JTextField voucherInput;
    // End of variables declaration//GEN-END:variables
}
