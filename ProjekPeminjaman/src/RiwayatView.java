import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RiwayatView extends JPanel {
    private DefaultTableModel tableModel;

    public RiwayatView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Riwayat Transaksi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        // PASTIKAN ADA 6 KOLOM DISINI
        String[] columnNames = {"Peminjam", "Barang", "Jml", "Tgl Pinjam", "Tgl Kembali", "Status"};
        tableModel = new DefaultTableModel(new Object[][]{}, columnNames);

        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Fungsi ini harus menerima 5 data agar bisa mengisi 6 kolom (kolom ke-6 diisi manual "Sedang Dipinjam")
    public void tambahRiwayat(String nama, String barang, int jumlah, String tglP, String tglK) {
        tableModel.addRow(new Object[]{nama, barang, jumlah, tglP, tglK, "Sedang Dipinjam"});
    }
}