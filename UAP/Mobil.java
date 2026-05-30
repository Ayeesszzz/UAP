package UAP;
public class Mobil extends Kendaraan {
    private int jumlahKursi;
    public Mobil(String kodeKendaraan, String namaKendaraan, double hargaSewaPerHari, int jumlahKursi) {
        super(kodeKendaraan, namaKendaraan, hargaSewaPerHari);
        this.jumlahKursi = jumlahKursi;
    }
    public int getJumlahKursi() {
        return jumlahKursi;
    }
    public void setJumlahKursi(int jumlahKursi) {
        this.jumlahKursi = jumlahKursi;
    }
    @Override
    public void tampilInfo() {
        String status = isTersedia() ? "Tersedia" : "Tidak Tersedia";
        System.out.printf("[MOBIL] Kode: %-5s | Nama: %-15s | Kursi: %-1d | Tarif: Rp%,-11.0f | Status: %s%n",
                getKodeKendaraan(), getNamaKendaraan(), jumlahKursi, getHargaSewaPerHari(), status);
    }
    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        return lamaSewa * getHargaSewaPerHari();
    }
}