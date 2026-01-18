import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DatePicker {
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JDialog d;
    JButton[] button = new JButton[49];

    public DatePicker(JFrame parent) {
        d = new JDialog();
        d.setModal(true);
        String[] header = { "Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab" };
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(430, 120));

        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.WHITE);
            if (x > 6)
                button[x].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        d.dispose();
                    }
                });
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.RED);
            }
            p1.add(button[x]);
        }
        JPanel p2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<< Sebelumnya");
        previous.addActionListener(ae -> { month--; displayDate(); });
        p2.add(previous);
        p2.add(l);
        JButton next = new JButton("Berikutnya >>");
        next.addActionListener(ae -> { month++; displayDate(); });
        p2.add(next);

        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        d.setLocationRelativeTo(parent);
        displayDate();
        d.setVisible(true);
    }

    public void displayDate() {
        for (int x = 7; x < button.length; x++) {
            button[x].setText("");
            button[x].setActionCommand("");
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy",
                new java.util.Locale("id", "ID"));
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, d = 1; d <= daysInMonth; x++, d++) {
            button[x].setText("" + d);
            button[x].setActionCommand("" + d); // PENTING: Agar angka terbaca
        }
        l.setText(sdf.format(cal.getTime()));
    }

    public String setPickedDate() {
        if (day.equals("")) return "";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
}