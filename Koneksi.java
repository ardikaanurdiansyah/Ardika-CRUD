package MainCRUD;

/**
 *
 * @author ardika
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    public static Connection connect() {
        Connection conn = null;
        try {
            // Sesuaikan path ke lokasi file .accdb kamu
            String url = "jdbc:ucanaccess://D:/db_mahasiswa.accdb";
            conn = DriverManager.getConnection(url);
            System.out.println("Koneksi Berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal! " + e.getMessage());
        }
        return conn;
    }
}
