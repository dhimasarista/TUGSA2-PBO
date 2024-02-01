import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class InputFormKaryawan extends JFrame {

    private JTextField tfNama, tfAlamat, tfTelepon;
    private JButton btnSimpan, btnTampilkan;

    public InputFormKaryawan() {
        // Pengaturan frame
        setTitle("Form Karyawan");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat komponen-komponen GUI
        JLabel lblNama = new JLabel("Nama:");
        JLabel lblAlamat = new JLabel("Alamat:");
        JLabel lblTelepon = new JLabel("Telepon:");

        tfNama = new JTextField(20);
        tfAlamat = new JTextField(20);
        tfTelepon = new JTextField(20);

        btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });

        btnTampilkan = new JButton("Tampilkan Data");
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanData();
            }
        });

        // Menambahkan komponen-komponen ke dalam frame
        setLayout(new GridLayout(5, 2));
        add(lblNama);
        add(tfNama);
        add(lblAlamat);
        add(tfAlamat);
        add(lblTelepon);
        add(tfTelepon);
        add(btnSimpan);
        add(new JLabel()); // placeholder
        add(btnTampilkan);
    }

    // Metode untuk menyimpan data ke dalam file
    private void simpanData() {
        String nama = tfNama.getText();
        String alamat = tfAlamat.getText();
        String telepon = tfTelepon.getText();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("karyawan.txt", true))) {
            // Menulis data ke dalam file
            writer.write("Nama: " + nama + ", Alamat: " + alamat + ", Telepon: " + telepon);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            clearForm();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data.");
        }
    }

    // Metode untuk mengosongkan formulir setelah penyimpanan data
    private void clearForm() {
        tfNama.setText("");
        tfAlamat.setText("");
        tfTelepon.setText("");
    }

    // Metode untuk menampilkan data dari file
    private void tampilkanData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("karyawan.txt"))) {
            StringBuilder data = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            JOptionPane.showMessageDialog(this, data.toString(), "Data Karyawan", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal membaca data.");
        }
    }

    public static void main(String[] args) {
        // Menjalankan aplikasi GUI di thread event dispatch
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InputFormKaryawan().setVisible(true);
            }
        });
    }
}
