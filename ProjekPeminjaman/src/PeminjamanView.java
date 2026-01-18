import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PeminjamanView extends JPanel {
    private BarangView barangViewRef;
    private RiwayatView riwayatViewRef;
    private JTextField txtNamaPeminjam, txtNamaBarang, txtJumlah, txtTglPinjam, txtTglKembali;

    public PeminjamanView(BarangView barangView, RiwayatView riwayatView) {
        this.barangViewRef = barangView;
        this.riwayatViewRef = riwayatView;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblTitle = new JLabel("Formulir Peminjaman");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        formPanel.setBackground(Color.WHITE);

        txtNamaPeminjam = new JTextField();
        txtNamaBarang = new JTextField();
        txtJumlah = new JTextField();
        txtTglPinjam = new JTextField();
        txtTglKembali = new JTextField();

        // Logika Tombol Kalender Pinjam
        JButton btnCalPinjam = new JButton("📅");
        btnCalPinjam.addActionListener(e -> {
            txtTglPinjam.setText(new DatePicker((JFrame)SwingUtilities.getWindowAncestor(this)).setPickedDate());
        });

        // Logika Tombol Kalender Kembali
        JButton btnCalKembali = new JButton("📅");
        btnCalKembali.addActionListener(e -> {
            txtTglKembali.setText(new DatePicker((JFrame)SwingUtilities.getWindowAncestor(this)).setPickedDate());
        });

        JPanel pDatePinjam = new JPanel(new BorderLayout());
        pDatePinjam.add(txtTglPinjam, BorderLayout.CENTER);
        pDatePinjam.add(btnCalPinjam, BorderLayout.EAST);

        JPanel pDateKembali = new JPanel(new BorderLayout());
        pDateKembali.add(txtTglKembali, BorderLayout.CENTER);
        pDateKembali.add(btnCalKembali, BorderLayout.EAST);

        formPanel.add(new JLabel("Nama Peminjam:"));
        formPanel.add(txtNamaPeminjam);
        formPanel.add(new JLabel("Nama Barang:"));
        formPanel.add(txtNamaBarang);
        formPanel.add(new JLabel("Jumlah:"));
        formPanel.add(txtJumlah);
        formPanel.add(new JLabel("Tanggal Pinjam:"));
        formPanel.add(pDatePinjam);
        formPanel.add(new JLabel("Tanggal Kembali (Deadline):"));
        formPanel.add(pDateKembali);

        add(formPanel, BorderLayout.CENTER);

        JButton btnSubmit = new JButton("PROSES PEMINJAMAN");
        btnSubmit.setBackground(new Color(255, 182, 193));
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSubmit.setPreferredSize(new Dimension(0, 45));

        btnSubmit.addActionListener(e -> {
            try {
                String nama = txtNamaPeminjam.getText();
                String barang = txtNamaBarang.getText();
                int jml = Integer.parseInt(txtJumlah.getText());
                String tglP = txtTglPinjam.getText();
                String tglK = txtTglKembali.getText();

                if (barangViewRef.kurangiStok(barang, jml)) {
                    riwayatViewRef.tambahRiwayat(nama, barang, jml, tglP, tglK);
                    JOptionPane.showMessageDialog(this, "Peminjaman Berhasil!");
                    // Reset Form
                    txtNamaPeminjam.setText(""); txtNamaBarang.setText("");
                    txtJumlah.setText(""); txtTglPinjam.setText(""); txtTglKembali.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Stok Gagal!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Data Belum Lengkap!");
            }
        });
        add(btnSubmit, BorderLayout.SOUTH);
    }
}