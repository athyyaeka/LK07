package LK07;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SiswaApp extends JFrame {
    private List<Siswa> dataList;
    private DefaultTableModel tableModel;
    private JTextField nisField, namaField, alamatField;
    private JTable table;

    public SiswaApp() {
        setTitle("Manajemen Data Siswa Perpustakaan SMP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        dataList = new ArrayList<>();
        initComponents();
        loadData();
    }

    private void initComponents() {
        getContentPane().setBackground(new Color(248, 249, 250)); // Soft gray-white
        
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(230, 240, 250)); // Very light blue-gray
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JLabel judulLabel = new JLabel("📖 Manajemen Data Siswa", JLabel.CENTER);
        judulLabel.setFont(new Font("Arial", Font.BOLD, 20));
        judulLabel.setForeground(new Color(70, 90, 120)); // Soft dark blue
        titlePanel.add(judulLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 12, 12));
        formPanel.setBackground(new Color(245, 240, 255)); // Lavender mist
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 190, 220), 1), "Data Siswa", 0, 0, new Font("Arial", Font.PLAIN, 12), new Color(120, 110, 160)));

        JLabel lblNis = new JLabel("NIS:", JLabel.RIGHT);
        lblNis.setForeground(new Color(80, 80, 100));
        nisField = new JTextField();
        nisField.setBackground(new Color(255, 255, 255));
        nisField.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        formPanel.add(lblNis);
        formPanel.add(nisField);

        JLabel lblNama = new JLabel("Nama:", JLabel.RIGHT);
        lblNama.setForeground(new Color(80, 80, 100));
        namaField = new JTextField();
        namaField.setBackground(new Color(255, 255, 255));
        namaField.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        formPanel.add(lblNama);
        formPanel.add(namaField);

        JLabel lblAlamat = new JLabel("Alamat:", JLabel.RIGHT);
        lblAlamat.setForeground(new Color(80, 80, 100));
        alamatField = new JTextField();
        alamatField.setBackground(new Color(255, 255, 255));
        alamatField.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 230), 1));
        formPanel.add(lblAlamat);
        formPanel.add(alamatField);

        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[] {"NIS", "Nama", "Alamat"}, 0);
        table = new JTable(tableModel);
        table.setBackground(new Color(250, 252, 250)); // Mint cream
        table.setGridColor(new Color(230, 235, 235)); // Soft gray grid
        table.getTableHeader().setBackground(new Color(225, 235, 245)); // Soft blue header
        table.getTableHeader().setForeground(new Color(60, 70, 90));
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(248, 250, 252));
        scrollPane.getViewport().setBackground(new Color(250, 252, 250));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(225, 230, 235), 1));
        add(scrollPane, BorderLayout.CENTER);

        JPanel tombolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        tombolPanel.setBackground(new Color(252, 248, 250)); // Rose white
        tombolPanel.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        JButton tambahBtn = new JButton("Tambah");
        styleButton(tambahBtn, new Color(240, 250, 240), new Color(200, 230, 200)); // Mint soft
        
        JButton updateBtn = new JButton("Update");
        styleButton(updateBtn, new Color(250, 245, 255), new Color(210, 190, 230)); // Lavender soft
        
        JButton hapusBtn = new JButton("Hapus");
        styleButton(hapusBtn, new Color(255, 245, 245), new Color(240, 200, 200)); // Rose soft

        tambahBtn.addActionListener(e -> tambahData());
        updateBtn.addActionListener(e -> updateData());
        hapusBtn.addActionListener(e -> hapusData());

        tombolPanel.add(tambahBtn);
        tombolPanel.add(updateBtn);
        tombolPanel.add(hapusBtn);
        add(tombolPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton btn, Color bg, Color hoverBg) {
        btn.setBackground(bg);
        btn.setForeground(new Color(60, 60, 80));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(hoverBg);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });
    }

    private void loadData() {
        dataList = FileManager.loadFromFile();
        tampilkanTabel();
    }

    private void tampilkanTabel() {
        tableModel.setRowCount(0);
        for (Siswa s : dataList) {
            tableModel.addRow(new String[] {s.getNis(), s.getNama(), s.getAlamat()});
        }
    }

    private void tambahData() {
        String nis = nisField.getText().trim();
        String nama = namaField.getText().trim();
        String alamat = alamatField.getText().trim();

        if (nis.equals("") || nama.equals("") || alamat.equals("")) {
            JOptionPane.showMessageDialog(this, "Lengkapi semua data!");
            return;
        }

        if (FileManager.isNisExists(nis)) {
            try {
                throw new DuplicateNisException("NIS " + nis + " sudah ada!");
            } catch (DuplicateNisException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                return;
            }
        }

        Siswa siswaBaru = new Siswa(nis, nama, alamat);
        dataList.add(siswaBaru);
        FileManager.saveToFile(dataList);
        tampilkanTabel();
        bersihkanForm();
        JOptionPane.showMessageDialog(this, "Data ditambahkan!");
    }

    private void updateData() {
        int baris = table.getSelectedRow();
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris di tabel!");
            return;
        }

        String nama = namaField.getText().trim();
        String alamat = alamatField.getText().trim();
        if (nama.equals("") || alamat.equals("")) {
            JOptionPane.showMessageDialog(this, "Isi nama dan alamat!");
            return;
        }

        Siswa siswa = dataList.get(baris);
        String nisLama = siswa.getNis();
        String nisBaru = nisField.getText().trim();
        if (!nisBaru.equals("") && FileManager.isNisExists(nisBaru) && !nisBaru.equals(nisLama)) {
            JOptionPane.showMessageDialog(this, "NIS sudah digunakan!");
            return;
        }

        dataList.set(baris, new Siswa(nisBaru.equals("") ? nisLama : nisBaru, nama, alamat));
        FileManager.saveToFile(dataList);
        tampilkanTabel();
        bersihkanForm();
        JOptionPane.showMessageDialog(this, "Data diupdate!");
    }

    private void hapusData() {
        int baris = table.getSelectedRow();
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk hapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Hapus data?");
        if (konfirmasi == JOptionPane.YES_OPTION) {
            dataList.remove(baris);
            FileManager.saveToFile(dataList);  // Update CSV
            tampilkanTabel();
            bersihkanForm();
            JOptionPane.showMessageDialog(this, "Data dihapus!");
        }
    }

    private void bersihkanForm() {
        nisField.setText("");
        namaField.setText("");
        alamatField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SiswaApp().setVisible(true);
            }
        });
    }
}

