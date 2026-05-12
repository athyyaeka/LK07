package LK07;

import java.io.*;
import java.util.*;

public class FileManager {
    private static final String FILE_NAME = "siswa.csv";

    public static List<Siswa> loadFromFile() {
        List<Siswa> siswaList = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return siswaList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true; 
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] parts = line.split(",", 3); 
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

