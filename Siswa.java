package LK07;

public class Siswa {
    private String nis;
    private String nama;
    private String alamat;

    public Siswa(String nis, String nama, String alamat) {
        this.nis = nis;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getNis() {
        return nis;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String toCsv() {
        return nis + "," + nama + "," + alamat;
    }

    @Override
    public String toString() {
        return String.format("NIS: %s, Nama: %s, Alamat: %s", nis, nama, alamat);
    }
}

