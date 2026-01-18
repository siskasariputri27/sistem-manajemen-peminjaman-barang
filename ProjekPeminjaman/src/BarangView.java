import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BarangView extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;

    public BarangView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Daftar Barang & Stok");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        String[] columnNames = {"Nama Barang", "Stok", "Status"};
        Object[][] data = {{"Kursi", "10", "Tersedia"}, {"Laptop", "5",
                "Tersedia"}, {"Proyektor", "2", "Tersedia"}};

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Panel Tombol Aksi ---
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionPanel.setBackground(Color.WHITE);

        JButton btnTambah = new JButton("Tambah Barang");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");

        // Mewarnai Tombol
        btnTambah.setBackground(new Color(255, 182, 193));
        btnHapus.setBackground(new Color(255, 100, 100));
        btnHapus.setForeground(Color.WHITE);

        // --- LOGIKA TOMBOL TAMBAH ---
        btnTambah.addActionListener(e -> {
            String nama = JOptionPane.showInputDialog(this, "Masukkan Nama Barang:");
            String stok = JOptionPane.showInputDialog(this, "Masukkan Jumlah Stok:");
            if (nama != null && stok != null) {
                tableModel.addRow(new Object[]{nama, stok, "Tersedia"});
            }
        });

        // --- LOGIKA TOMBOL HAPUS ---
        btnHapus.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
            }
        });

        // --- LOGIKA TOMBOL EDIT ---
        btnEdit.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String namaBaru = JOptionPane.showInputDialog(this,
                        "Edit Nama Barang:", tableModel.getValueAt(selectedRow, 0));
                String stokBaru = JOptionPane.showInputDialog(this,
                        "Edit Stok:", tableModel.getValueAt(selectedRow, 1));
                if (namaBaru != null && stokBaru != null) {
                    tableModel.setValueAt(namaBaru, selectedRow, 0);
                    tableModel.setValueAt(stokBaru, selectedRow, 1);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diedit!");
            }
        });

        actionPanel.add(btnTambah);
        actionPanel.add(btnEdit);
        actionPanel.add(btnHapus);
        add(actionPanel, BorderLayout.SOUTH);
    }

    public boolean kurangiStok(String namaBarangCari, int jumlahPinjam) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(namaBarangCari)) {
                int stokSekarang = Integer.parseInt(tableModel.getValueAt(i, 1).toString());
                if (stokSekarang >= jumlahPinjam) {
                    tableModel.setValueAt(String.valueOf(stokSekarang - jumlahPinjam), i, 1);
                    return true;
                }
            }
        }
        return false;
    }
}