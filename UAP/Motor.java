package UAP;
public class Motor extends Kendaraan {
    private String jenisTransmisi;
    public Motor(String kodeKendaraan, String namaKendaraan, double hargaSewaPerHari, String jenisTransmisi) {
        super(kodeKendaraan, namaKendaraan, hargaSewaPerHari);
        this.jenisTransmisi = jenisTransmisi;
    }
    public String getJenisTransmisi() {
        return jenisTransmisi;
    }
    public void setJenisTransmisi(String jenisTransmisi) {
        this.jenisTransmisi = jenisTransmisi;
    }
    @Override
    public void tampilInfo() {
        String status = isTersedia() ? "Tersedia" : "Tidak Tersedia";
        System.out.printf("[MOTOR] Kode: %-5s | Nama: %-15s | Transmisi: %-6s | Tarif: Rp%,-11.0f | Status: %s%n",
                getKodeKendaraan(), getNamaKendaraan(), jenisTransmisi, getHargaSewaPerHari(), status);
    }
    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        return lamaSewa * getHargaSewaPerHari();
    }
}