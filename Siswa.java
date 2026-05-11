package LK07;

 /**
  * Model class untuk data Siswa
  * @author [Your Name]
  */
public class Siswa {
    private String nis;
    private String nama;
    private String alamat;

    /**
     * Konstruktor Siswa
     * @param nis Nomor Induk Siswa
     * @param nama Nama siswa
     * @param alamat Alamat siswa
     */
    public Siswa(String nis, String nama, String alamat) {
        this.nis = nis;
        this.nama = nama;
        this.alamat = alamat;
    }

    // Getter methods
    public String getNis() {
        return nis;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    /**
     * Konversi data ke format CSV
     * @return String dalam format "nis,nama,alamat"
     */
    public String toCsv() {
        return nis + "," + nama + "," + alamat;
    }

    /**
     * Override toString untuk tampilan di JTable
     */
    @Override
    public String toString() {
        return String.format("NIS: %s, Nama: %s, Alamat: %s", nis, nama, alamat);
    }
}

