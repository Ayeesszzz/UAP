package UAP;
import java.util.ArrayList;
public class GoDriveRentalSystem {
    private ArrayList<Kendaraan> daftarKendaraan;
    public GoDriveRentalSystem() {
        daftarKendaraan = new ArrayList<>();
        daftarKendaraan.add(new Mobil("MBL01", "Toyota Avanza", 350000, 7));
        daftarKendaraan.add(new Mobil("MBL02", "Daihatsu Sigra", 300000, 7));
        daftarKendaraan.add(new Mobil("MBL03", "Honda Brio", 280000, 5));
        daftarKendaraan.add(new Motor("MTR01", "Honda Vario", 80000, "Matik"));
        daftarKendaraan.add(new Motor("MTR02", "Yamaha NMAX", 100000, "Matik"));
        daftarKendaraan.add(new Motor("MTR03", "Kawasaki KLX", 90000, "Manual"));
    }
    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
        System.out.println("[INFO] Kendaraan berhasil ditambahkan: " + k.getNamaKendaraan() + " (" + k.getKodeKendaraan() + ")");
    }
    public void tampilkanDaftarArmada() {
        System.out.println("\n=== DAFTAR ARMADA GODRIVE ===");
        int i = 1;
        for (Kendaraan k : daftarKendaraan) {
            System.out.print(i + ". ");
            k.tampilInfo();
            i++;
        }
    }
    public void sewaKendaraan(String kode, int lamaSewa, boolean isVIP) throws KendaraanTidakTersediaException {
        Kendaraan kendaraan = null;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                kendaraan = k;
                break;
            }
        }
        if (kendaraan == null || !kendaraan.isTersedia()) {
            throw new KendaraanTidakTersediaException("Kendaraan dengan kode " + kode + " gagal disewa. Alasan: Kendaraan sedang disewa atau tidak ditemukan!");
        }
        kendaraan.setTersedia(false);
        double biayaDasar = kendaraan.hitungBiayaDasar(lamaSewa);
        double tambahan = 0;
        String labelTambahan = "";

        if (kendaraan instanceof Mobil) {
            Mobil m = (Mobil) kendaraan;
            if (m.getJumlahKursi() > 5) {
                tambahan = 50000;
                labelTambahan = "Tambahan Kursi (>5)";
            }
        } else if (kendaraan instanceof Motor) {
            Motor mot = (Motor) kendaraan;
            if (mot.getJenisTransmisi().equalsIgnoreCase("Matik")) {
                tambahan = 10000 * lamaSewa;
                labelTambahan = "Asuransi Matik";
            }
        }
        double totalSebelumDiskon = biayaDasar + tambahan;
        double diskonVIP = 0;
        if (isVIP) {
            diskonVIP = 0.10 * totalSebelumDiskon;
        }
        double totalBiayaAkhir = totalSebelumDiskon - diskonVIP;

        System.out.println("\n=== TRANSAKSI SEWA GODRIVE ===");
        System.out.println("Kendaraan Berhasil Disewa!");
        System.out.printf("%-20s: %s (%s)%n", "Unit", kendaraan.getNamaKendaraan(), kendaraan.getKodeKendaraan());
        System.out.printf("%-20s: %d hari%n", "Lama Sewa", lamaSewa);
        System.out.printf("%-20s: Rp %,.0f%n", "Biaya Dasar Harian", biayaDasar);
        if (tambahan > 0) {
            System.out.printf("%-20s: Rp %,.0f%n", labelTambahan, tambahan);
        }
        if (isVIP) {
            System.out.printf("%-20s: -Rp %,.0f%n", "Diskon Member VIP (10%)", diskonVIP);
        }
        System.out.println("==============================================");
        System.out.printf("%-20s: Rp %,.0f%n", "TOTAL BIAYA AKHIR", totalBiayaAkhir);
    }
    public void kembalikanKendaraan(String kode) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                k.setTersedia(true);
                System.out.println("[INFO] Kendaraan " + k.getNamaKendaraan() + " (" + k.getKodeKendaraan() + ") berhasil dikembalikan. Status: Tersedia.");
                return;
            }
        }
        System.out.println("[ERROR] Kendaraan dengan kode " + kode + " tidak ditemukan!");
    }
}