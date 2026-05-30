package UAP;
import java.util.Scanner;
public class Main {
    private static GoDriveRentalSystem system = new GoDriveRentalSystem();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int pilihan = 0;
        do {
            System.out.println("\n====== MENU GO DRIVE RENTAL SYSTEM ======");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Daftar Armada");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Kembalikan Kendaraan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1:
                        menuTambahKendaraan();
                        break;
                    case 2:
                        system.tampilkanDaftarArmada();
                        break;
                    case 3:
                        menuSewaKendaraan();
                        break;
                    case 4:
                        menuKembalikanKendaraan();
                        break;
                    case 5:
                        System.out.println("Terima kasih telah menggunakan Go Drive!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Input harus berupa angka!");
            }
        } while (pilihan != 5);
    }
    private static void menuTambahKendaraan() {
        System.out.print("Masukkan jenis kendaraan (mobil/motor): ");
        String jenis = scanner.nextLine().trim();
        System.out.print("Masukkan kode kendaraan: ");
        String kode = scanner.nextLine().trim();
        System.out.print("Masukkan nama kendaraan: ");
        String nama = scanner.nextLine().trim();
        System.out.print("Masukkan harga sewa per hari: ");
        double harga = Double.parseDouble(scanner.nextLine());

        if (jenis.equalsIgnoreCase("mobil")) {
            System.out.print("Masukkan kapasitas kursi: ");
            int kursi = Integer.parseInt(scanner.nextLine());
            system.tambahKendaraan(new Mobil(kode, nama, harga, kursi));
        } else if (jenis.equalsIgnoreCase("motor")) {
            System.out.print("Masukkan jenis transmisi (Matik/Manual): ");
            String transmisi = scanner.nextLine().trim();
            system.tambahKendaraan(new Motor(kode, nama, harga, transmisi));
        } else {
            System.out.println("[ERROR] Jenis kendaraan tidak dikenal!");
        }
    }
    private static void menuSewaKendaraan() {
        System.out.print("Masukkan kode kendaraan yang ingin disewa: ");
        String kode = scanner.nextLine().trim();
        System.out.print("Masukkan durasi sewa (dalam hari): ");
        int durasi = Integer.parseInt(scanner.nextLine());
        System.out.print("Apakah Anda Member VIP? (y/n): ");
        String vipInput = scanner.nextLine().trim();
        boolean isVIP = vipInput.equalsIgnoreCase("y");

        try {
            system.sewaKendaraan(kode, durasi, isVIP);
        } catch (KendaraanTidakTersediaException e) {
            System.out.println("\nException in thread \"main\" " + e.getClass().getName() + ": " + e.getMessage());
            System.out.println("\tat GoDriveRentalSystem.sewaKendaraan(GoDriveRentalSystem.java:31)");
            System.out.println("\tat Main.menuSewaKendaraan(Main.java:75)");
            System.out.println("\tat Main.main(Main.java:27)");
        }
    }
    private static void menuKembalikanKendaraan() {
        System.out.print("Masukkan kode kendaraan yang ingin dikembalikan: ");
        String kode = scanner.nextLine().trim();
        system.kembalikanKendaraan(kode);
    }
}