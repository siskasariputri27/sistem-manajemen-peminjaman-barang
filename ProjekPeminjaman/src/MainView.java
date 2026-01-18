import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame {

    // Warna Soft Pink
    private final Color HEADER_COLOR = new Color(255, 182, 193);
    private final Color SIDEBAR_COLOR = new Color(245, 246, 250);

    private JPanel mainContentPanel;
    private CardLayout cardLayout;

    public MainView() {
        setTitle("Sistem Manajemen Peminjaman Barang");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        topPanel.setBackground(HEADER_COLOR);
        JLabel titleLabel = new JLabel("Sistem Manajemen Peminjaman Barang");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(SIDEBAR_COLOR);
        sidebarPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        sidebarPanel.setPreferredSize(new Dimension(220, getHeight()));

        JButton btnDataBarang = createSidebarButton("Data Barang");
        JButton btnPeminjaman = createSidebarButton("Peminjaman");
        JButton btnRiwayat = createSidebarButton("Riwayat");
        JButton btnKeluar = createSidebarButton("Keluar");
        btnKeluar.setBackground(new Color(44, 62, 80));
        btnKeluar.setForeground(Color.WHITE);

        sidebarPanel.add(btnDataBarang);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(btnPeminjaman);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(btnRiwayat);
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(btnKeluar);

        add(sidebarPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        BarangView viewBarang = new BarangView();
        RiwayatView viewRiwayat = new RiwayatView();
        PeminjamanView viewPeminjaman = new PeminjamanView(viewBarang, viewRiwayat);

        mainContentPanel.add(viewBarang, "BARANG_VIEW");
        mainContentPanel.add(viewPeminjaman, "PEMINJAMAN_VIEW");
        mainContentPanel.add(viewRiwayat, "RIWAYAT_VIEW");

        add(mainContentPanel, BorderLayout.CENTER);

        // Navigasi
        btnDataBarang.addActionListener(e -> cardLayout.show(mainContentPanel, "BARANG_VIEW"));
        btnPeminjaman.addActionListener(e -> cardLayout.show(mainContentPanel, "PEMINJAMAN_VIEW"));
        btnRiwayat.addActionListener(e -> cardLayout.show(mainContentPanel, "RIWAYAT_VIEW"));
        btnKeluar.addActionListener(e -> System.exit(0));
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBackground(SIDEBAR_COLOR);
        btn.setForeground(Color.DARK_GRAY);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }
}