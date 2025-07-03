package MainCRUD;

import java.sql.*;
import java.util.Scanner;

public class MainCRUD {
    static Connection conn = Koneksi.connect();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("\n=== MENU MAHASISWA ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Data");
            System.out.println("3. Ubah Data");
            System.out.println("4. Hapus Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            pilih = input.nextInt();
            input.nextLine(); // flush newline

            switch (pilih) {
                case 1 -> tambahData(input);
                case 2 -> tampilData();
                case 3 -> ubahData(input);
                case 4 -> hapusData(input);
                case 5 -> System.out.println("Keluar...");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 5);
    }

    static void tambahData(Scanner input) {
        try {
            System.out.print("NIM: ");
            String nim = input.nextLine();
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Jurusan: ");
            String jurusan = input.nextLine();

            String sql = "INSERT INTO mahasiswa (NIM, Nama, Jurusan) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            pst.setString(2, nama);
            pst.setString(3, jurusan);
            pst.executeUpdate();
            System.out.println("Data berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal menambah data: " + e.getMessage());
        }
    }

    static void tampilData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mahasiswa");

            System.out.println("\n=== DATA MAHASISWA ===");
            while (rs.next()) {
                System.out.println("NIM     : " + rs.getString("NIM"));
                System.out.println("Nama    : " + rs.getString("Nama"));
                System.out.println("Jurusan : " + rs.getString("Jurusan"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
    }

    static void ubahData(Scanner input) {
        try {
            System.out.print("Masukkan NIM yang ingin diubah: ");
            String nim = input.nextLine();

            System.out.print("Nama baru: ");
            String nama = input.nextLine();
            System.out.print("Jurusan baru: ");
            String jurusan = input.nextLine();

            String sql = "UPDATE mahasiswa SET Nama=?, Jurusan=? WHERE NIM=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, jurusan);
            pst.setString(3, nim);
            int hasil = pst.executeUpdate();

            if (hasil > 0)
                System.out.println("Data berhasil diubah!");
            else
                System.out.println("Data tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal mengubah data: " + e.getMessage());
        }
    }

    static void hapusData(Scanner input) {
        try {
            System.out.print("Masukkan NIM yang ingin dihapus: ");
            String nim = input.nextLine();

            String sql = "DELETE FROM mahasiswa WHERE NIM=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            int hasil = pst.executeUpdate();

            if (hasil > 0)
                System.out.println("Data berhasil dihapus!");
            else
                System.out.println("Data tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }
}




