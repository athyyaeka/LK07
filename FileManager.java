package LK07;

import java.io.*;
import java.util.*;


/**
 * Kelas untuk mengelola operasi file CSV siswa
 */
public class FileManager {
    private static final String FILE_NAME = "siswa.csv";

    /**
     * Membaca data dari file siswa.csv
     * @return List Siswa, empty jika file tidak ada
     */
    public static List<Siswa> loadFromFile() {
        List<Siswa> siswaList = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        // Jika file tidak ada, return empty list
        if (!file.exists()) {
            return siswaList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true; // Skip header line
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] parts = line.split(",", 3); // Max 3 parts
                if (parts.length == 3) {
                    Siswa siswa = new Siswa(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    siswaList.add(siswa);
                }
            }
        } catch (IOException e) {
            System.err.println("Error membaca file: " + e.getMessage());
        }
        return siswaList;
    }

    /**
     * Menyimpan seluruh list ke file siswa.csv
     * @param siswaList List data siswa
     */
    public static void saveToFile(List<Siswa> siswaList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Tulis header
            writer.println("NIS,Nama,Alamat");
            
            // Tulis data
            for (Siswa siswa : siswaList) {
                writer.println(siswa.toCsv());
            }
        } catch (IOException e) {
            System.err.println("Error menyimpan file: " + e.getMessage());
        }
    }

    /**
     * Cek apakah NIS sudah ada (load full list untuk check)
     * @param nis NIS yang dicek
     * @return true jika sudah ada
     */
    public static boolean isNisExists(String nis) {
        List<Siswa> list = loadFromFile();
        for (Siswa s : list) {
            if (s.getNis().equals(nis)) {
                return true;
            }
        }
        return false;
    }
}

