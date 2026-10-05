/*
 * Main.java
 * Program utama sistem pemesanan Teaji.
 * Class ini mengatur alur program mulai dari login kasir, pemilihan cabang,
 * penampilan menu, pemesanan minuman, customisasi, member, pembayaran,
 * penyimpanan file, sampai menampilkan struk.
 * java.time dan Swing/AWT digunakan sebagai library tambahan karena
 * program membutuhkan tanggal/jam otomatis dan tampilan window/struk.
 */

import java.awt.BasicStroke;//library untuk mengatur ketebalan garis.
import java.awt.Color;//library untuk mengatur warna gambar.
import java.awt.Font;//library untuk mengatur jenis dan ukuran tulisan.
import java.awt.Graphics;//library untuk menggambar pada window.
import java.awt.Graphics2D;//library untuk menggambar dengan fitur 2D.
import java.awt.RenderingHints;//library java untuk memperhalus gambar.
import java.io.File;//library untuk mengecek/membuka file.
import java.io.FileNotFoundException;//library untu exception saat file tidak ditemukan.
import java.io.FileWriter;//library untuk menulis data ke file teks.
import java.io.IOException;//library exception untuk kesalahan operasi file.
import java.time.LocalDateTime;//library untuk mengambil tanggal dan waktu saat ini.
import java.time.format.DateTimeFormatter;//libraryuntuk mengatur format tanggal dan waktu.
import java.util.ArrayList;//library untuk menyimpan kumpulan object dengan ukuran dinamis.
import java.util.Scanner;//library untuk membaca input dari keyboard atau file teks.
import javax.swing.JFrame;//library untuk membuat window/tampilan GUI sederhana.
import javax.swing.JPanel;//library sebagai area gambar pada window.
import javax.swing.JScrollPane;// library agar isi tampilan dapat digulir.
import javax.swing.JTextArea;//library untuk menampilkan teks pada window.  

// Import class-class buatan sendiri dari package com.teaji.
// Kenapa? karena App.java tidak berada di package com.teaji, jadi class-nya harus diimpor dulu supaya bisa dipakai.
import com.teaji.Login;
import com.teaji.Member;
import com.teaji.MinumanUkuran;
import com.teaji.NonMember;
import com.teaji.Order;
import com.teaji.OrderItem;
import com.teaji.Reward;
import com.teaji.Seller;
import com.teaji.Topping;

// Mendefinisikan class utama App sesuai nama file.
// Class ini berisi method main dan semua method bantu untuk alur pemesanan Teaji.
// Semua method dibuat static supaya bisa langsung dipanggil dari main tanpa membuat objek App.
public class App {
    // Variabel private static ini digunakan untuk membaca input dari keyboard.
    // Diambil dari interface Login supaya seluruh program memakai satu Scanner yang sama.
    // Kenapa? membuat banyak Scanner untuk keyboard yang sama bisa bikin input bentrok.
    private static Scanner input = Login.INPUT;
    // Variabel ini digunakan untuk menghitung nomor antrean pesanan, dimulai dari 1.
    // Setiap ada pesanan baru nilainya ditambah 1. Dibuat static supaya nilainya tidak reset setiap pesanan.
    private static int queueCounter = 1;

    // Array ini menyimpan daftar nama cabang Teaji yang bisa dipilih kasir.
    // 'final' artinya isi variabel tidak boleh diganti, sedangkan huruf besar semua adalah kebiasaan untuk nilai tetap (konstanta).
    private static final String[] BRANCHES = {
        "Pasaraya Blok M - Jl. Iskandarsyah II No. 2, Kebayoran Baru",
        "Kota Kasablanka - Jl. Raya Casablanca, Tebet",
        "Pacific Place Mall (SCBD) - Basement 1, Jl. Jenderal Sudirman",
        "Senayan City - Lantai 5, Delicae Food Court, Jl. Asia Afrika",
        "Plaza Senayan - Basement, The FoodHall, Jl. Asia Afrika",
        "Gandaria City - Lantai 2, Jl. Sultan Iskandar Muda",
        "Pondok Indah Mall 3 (PIM 3) - Lantai 2, Jl. Metro Pondok Indah",
        "Kuningan City - Lower Ground, Jl. Prof. Dr. Satrio",
        "Lippo Mall Nusantara (Setiabudi) - Upper Ground, Jl. Jenderal Sudirman",
        "Kemayoran - K Mall - Jl. Kota Baru Bandar Kemayoran",
        "Thamrin - Agora Mall Thamrin Nine - Jl. MH Thamrin No. 10",
        "Gajah Mada Plaza - Jl. Gajah Mada No. 19-26",
        "Mall of Indonesia (Kelapa Gading) - Jl. Boulevard Barat Raya No. 6A",
        "Mall Kelapa Gading 2 - Jl. Kelapa Gading Boulevard",
        "Baywalk Mall (Muara Karang) - Jl. Pluit Karang Ayu",
        "By The Sea PIK 2 - Golf Island, Unit B38",
        "Batavia PIK - Jl. Pantai Indah Kapuk",
        "Aloha Pasir Putih (PIK) - Jl. Laksamana Yos Sudarso",
        "Indonesia Design District PIK 2 - Jl. MH Thamrin",
        "Puri Indah Mall - L2 depan XXI, Jl. Puri Agung No. 1",
        "Hublife Taman Anggrek - Lantai 1, Jl. Tanjung Duren Timur 2",
        "Gading Serpong - Springs Boulevard, Cihuni",
        "Alam Sutera - Mall @ Alam Sutera",
        "BSD - AEON Mall BSD City",
        "Summarecon Mall Bekasi - Jl. Boulevard Ahmad Yani",
        "Grand Galaxy Park Mall - Jl. Boulevard Raya Galaxy",
        "Botani Square Mall - Jl. Raya Pajajaran"
    };

    // Method main adalah titik awal program dijalankan.
    // Di sini alurnya: login kasir, pilih cabang, tampilkan menu, lalu melayani pesanan pelanggan berulang-ulang.
    public static void main(String[] args) {
        // Memastikan file sellers.txt, members.txt, dan orders.txt sudah ada sebelum program dipakai.
        ensureFiles();

        // Menampilkan judul program dan pilihan menu awal (Sign In, Sign Up, Keluar).
        System.out.println("====================================================");
        System.out.println("                 TEAJI ORDER SYSTEM");
        System.out.println("====================================================");
        System.out.println("1. Sign In");
        System.out.println("2. Sign Up");
        System.out.println("3. Keluar");

        // Membaca pilihan kasir. readInt dipakai supaya input pasti angka 1 sampai 3, kalau salah akan diminta ulang.
        int pilihanLogin = readInt("Pilih menu: ", 1, 3);

        // Membuat objek Seller (kasir) kosong yang nanti diisi lewat signUp() atau signIn().
        Seller seller = new Seller();

        // if ini memeriksa pilihan menu.
        // Kalau 2 (Sign Up), kasir daftar dulu lalu langsung sign in. Kalau 1, langsung sign in.
        // Selain itu (pilihan 3), program selesai dan main dihentikan dengan return.
        if (pilihanLogin == 2) {
            seller.signUp();
            seller.signIn();
        } else if (pilihanLogin == 1) {
            seller.signIn();
        } else {
            System.out.println("Program selesai.");
            return;
        }

        // Mengecek status login. Tanda ! artinya 'tidak'.
        // Kalau login gagal, program berhenti supaya orang yang tidak punya akun tidak bisa memakai sistem.
        if (!seller.isAuthenticated()) {
            System.out.println("Program berhenti karena login gagal.");
            return;
        }

        // Kasir memilih cabang tempat bekerja. Nama cabang disimpan untuk dicatat di setiap pesanan.
        String branch = pilihCabang();

        // Membuat daftar menu minuman dan topping satu kali di awal.
        // Daftar ini dipakai terus untuk semua pesanan selanjutnya.
        ArrayList<MinumanUkuran> menuMinuman = buatDaftarMinuman();
        ArrayList<Topping> menuTopping = buatDaftarTopping();

        // Menampilkan menu Teaji dalam window agar kasir dan pelanggan bisa melihat daftar menu.
        tampilkanMenuWindow(menuMinuman, menuTopping);

        // Variabel penanda apakah kasir masih mau melayani pelanggan berikutnya.
        // Selama true, loop pesanan terus berulang.
        boolean lanjut = true;

        // Perulangan while utama: satu putaran = satu pesanan pelanggan.
        // Berhenti kalau kasir memilih keluar (lanjut menjadi false).
        while (lanjut) {
            System.out.println("\n====================================================");
            System.out.println("                  PESANAN BARU");
            System.out.println("====================================================");

            // Meminta kasir mengetik nama pelanggan.
            System.out.print("Nama pelanggan: ");
            String namaPelanggan = input.nextLine();

            System.out.println("\nApakah pelanggan memiliki member?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            // Menanyakan apakah pelanggan punya member. 1 = Ya, 2 = Tidak.
            int pilihanMember = readInt("Pilihan: ", 1, 2);

            // Variabel member diisi null dulu, artinya belum ada data member.
            // Kalau nanti pelanggan ternyata member, isinya diganti dengan objek Member.
            Member member = null;

            // Kalau pelanggan punya member, minta nomor telepon lalu cari datanya di file members.txt.
            if (pilihanMember == 1) {
                System.out.print("Nomor telepon member: ");
                String nomorTelepon = input.nextLine();

                // cariMember mengembalikan objek Member kalau nomor ketemu, atau null kalau tidak ada.
                member = cariMember(nomorTelepon);

                // Nomor telepon tidak ditemukan, jadi kasir diberi dua pilihan:
                // daftarkan sebagai member baru atau lanjut sebagai non-member.
                if (member == null) {
                    System.out.println("Nomor telepon belum terdaftar.");
                    System.out.println("1. Daftarkan sebagai member");
                    System.out.println("2. Lanjut sebagai non-member");

                    int pilihanBaru = readInt("Pilihan: ", 1, 2);

                    // Kalau memilih daftar, dibuat objek Member baru dengan nama dan nomor telepon lalu disimpan ke file lewat simpanMember.
                    if (pilihanBaru == 1) {
                        // Membuat object menggunakan constructor dari suatu class.
                        member = new Member(namaPelanggan, nomorTelepon);
                        simpanMember(member);
                        System.out.println("Member baru berhasil dibuat.");
                    }
                } else {
                    // Kalau member ditemukan, nama diperbarui sesuai nama yang diketik kasir, lalu status member ditampilkan.
                    member.setNama(namaPelanggan);
                    System.out.println("\nData member ditemukan.");
                    tampilkanStatusMember(member);
                }
            }

            // Kalau sampai di sini member masih null, berarti pelanggan diproses sebagai Non Member.
            if (member == null) {
                // Membuat objek NonMember untuk pelanggan tanpa member.
                // Objek ini tidak disimpan ke variabel karena hanya dipakai sebagai penanda jenis pelanggan.
                new NonMember(namaPelanggan);
                System.out.println("Pelanggan diproses sebagai Non Member.");
            }

            // Membuat objek Order baru untuk menyimpan seluruh data satu pesanan (pelanggan, item, total, pembayaran).
            Order order = new Order();
            order.setNamaPelanggan(namaPelanggan);

            // Kalau pelanggan adalah member, datanya dimasukkan ke order beserta nomor teleponnya.
            if (member != null) {
                order.setMember(member);
                order.setNomor(member.getNomorTelepon());
            }

            // Memberi nomor antrean ke pesanan, lalu queueCounter ditambah 1 untuk pesanan berikutnya.
            order.setQueueNo(queueCounter);
            queueCounter++;

            // Mengambil tanggal dan jam saat ini secara otomatis, lalu diformat menjadi teks dd-MM-yyyy HH:mm:ss agar mudah dibaca di struk.
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String tanggalWaktu = now.format(formatter);

            order.setTanggal(tanggalWaktu);
            // Membuat nomor struk dan ID order dari waktu saat ini.
            // Kenapa? karena waktu selalu berbeda tiap pesanan, jadi nomornya hampir pasti unik.
            // ID order juga ditambah nomor antrean di belakangnya.
            order.setReceiptNo("RC-" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            order.setOrderID("ORD-" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                    + "-" + order.getQueueNo());
            // Menyimpan nama cabang ke order sebagai tempat pesanan diambil.
            order.setCollectedBy(branch);

            // Kasir memilih tipe pesanan, yaitu Dine In atau Takeaway, lalu disimpan ke order.
            String typeOrder = pilihTipeOrder();
            order.setTypeOrder(typeOrder);

            // Penanda apakah pelanggan masih mau menambah minuman lain dalam pesanan yang sama.
            boolean tambahMinuman = true;

            // Perulangan while ini terus menampilkan menu dan meminta minuman selama pelanggan masih mau menambah.
            while (tambahMinuman) {
                tampilkanMenuRingkas(menuMinuman);

                // Kasir memilih nomor minuman. Batasnya 1 sampai jumlah menu, lalu minuman diambil dari daftar (indeks list mulai dari 0, jadi dikurangi 1).
                int kodeMinuman = readInt("Pilih nomor minuman: ", 1, menuMinuman.size());
                MinumanUkuran minuman = menuMinuman.get(kodeMinuman - 1);

                System.out.println("\nAnda memilih: " + minuman.getNama());
                System.out.println("Kategori: " + minuman.getKategori());

                // Kalau minuman punya rekomendasi topping (tidak kosong), tampilkan sebagai saran untuk pelanggan.
                if (!minuman.getRekomendasiTopping().equals("")) {
                    System.out.println("Recommended topping: "
                            + minuman.getRekomendasiTopping());
                }

                // Menanyakan jumlah cup untuk minuman ini, dibatasi 1 sampai 20.
                int jumlah = readInt("Mau berapa cup menu ini? ", 1, 20);

                // Setiap cup dibuat sebagai OrderItem sendiri agar customisasi
                // dapat ditanyakan satu per satu.
// Perulangan for digunakan ketika jumlah pengulangan atau data yang diproses diketahui.
                for (int i = 1; i <= jumlah; i++) {
                    System.out.println("\n--- Customisasi cup ke-" + i + " ---");

                    // Untuk setiap cup, kasir memilih ukuran, tingkat gula atau madu, es, dan topping.
                    String ukuran = pilihUkuran(minuman);

                    // Sugar dan honey diberi kosong dulu. Hanya salah satu yang akan diisi tergantung jenis minumannya.
                    String sugarLevel = "";
                    String honeyLevel = "";

                    // Kalau nama minuman mengandung kata 'honey', yang ditanya adalah Honey Level. Kalau tidak, yang ditanya Sugar Level.
                    // toLowerCase() dipakai supaya huruf besar/kecil tidak berpengaruh.
                    if (minuman.getNama().toLowerCase().contains("honey")) {
                        honeyLevel = pilihHoney();
                    } else {
                        sugarLevel = pilihSugar();
                    }

                    // Kasir memilih tingkat es untuk cup ini.
                    String iceLevel = pilihIce();

                    // Membuat object OrderItem untuk menyimpan satu gelas minuman.
                    // Customisasi sekarang disimpan langsung sebagai ArrayList
                    // di dalam class OrderItem, sehingga tidak membutuhkan
                    // class Customisasi.java.
                    OrderItem item = new OrderItem();

                    // Menyimpan minuman yang dipilih ke dalam OrderItem.
                    item.setMinuman(minuman);

                    // Satu OrderItem pada perulangan ini mewakili satu gelas.
                    item.setJumlah(1);

                    // Menyimpan ukuran ke ArrayList customisasi.
                    item.addCustomisasi(ukuran);

                    // Menyimpan Sugar Level. Jika minuman menggunakan Honey,
                    // Sugar Level disimpan sebagai tanda '-'.
                    item.addCustomisasi(
                            sugarLevel.equals("") ? "-" : sugarLevel);

                    // Menyimpan Honey Level. Jika minuman tidak menggunakan Honey,
                    // Honey Level disimpan sebagai tanda '-'.
                    item.addCustomisasi(
                            honeyLevel.equals("") ? "-" : honeyLevel);

                    // Menyimpan Ice Level ke ArrayList customisasi.
                    item.addCustomisasi(iceLevel);

                    // Memilih topping untuk minuman.
                    Topping topping = pilihTopping(menuTopping);

                    // Jika pelanggan memilih topping, topping disimpan ke OrderItem.
                    if (topping != null) {
                        // false berarti topping bukan topping gratis dari reward.
                        item.addTopping(topping, false);
                    }

                    // Menambahkan OrderItem ke dalam Order.
                    order.addItem(item);

                    System.out.println("Cup ke-" + i + " berhasil ditambahkan.");
                }

                // Menanyakan apakah pelanggan mau menambah minuman lain. Kalau memilih 1 (Ya), loop diulang. Kalau 2, loop berhenti.
                System.out.println("\nApakah ingin menambah minuman lain?");
                System.out.println("1. Ya");
                System.out.println("2. Tidak");
                int tambah = readInt("Pilihan: ", 1, 2);
                tambahMinuman = tambah == 1;
            }

            // Setiap gelas yang dibeli memberikan satu stamp.
            // Stamp diproses sebelum pembayaran agar reward yang baru terbuka
            // dapat langsung digunakan pada transaksi yang sama.
            if (member != null) {
                prosesStampMember(member, order.getItemsArrayList().size(),
                        order, menuMinuman, menuTopping);
                simpanMember(member);
            }

            // Menghitung total pesanan (subtotal, potongan reward, dan total akhir) dari semua item.
            order.hitungTotal();

            System.out.println("\n====================================================");
            System.out.println("                  RINGKASAN ORDER");
            System.out.println("====================================================");
            // Menampilkan ringkasan order ke layar sebelum pembayaran.
            // Baris Reward hanya muncul kalau ada potongan (discount lebih dari 0).
            System.out.println("Customer : " + order.getNamaPelanggan());
            System.out.println("Order ID : " + order.getOrderID());
            System.out.println("Subtotal : Rp " + formatRupiah(order.getSubtotal()));
            if (order.getDiscount() > 0) {
                System.out.println("Reward   : -Rp " + formatRupiah(order.getDiscount()));
            }
            System.out.println("Total    : Rp " + formatRupiah(order.getTotal()));

            // Kasir memilih metode pembayaran (QRIS atau Cash), lalu disimpan ke order.
            String metodePembayaran = pilihPembayaran(order.getTotal());
            order.setMetodePembayaran(metodePembayaran);

            // Kalau Cash, kasir memasukkan uang tunai yang tidak boleh kurang dari total.
            // Kalau QRIS, ditampilkan QRIS simulasi dan pembayaran dianggap pas sama total.
            if (metodePembayaran.equals("Cash")) {
                double uang = readDoubleMin(
                        "Masukkan uang tunai: Rp ", order.getTotal());
                order.setPembayaran(uang);
            } else {
                tampilkanQRIS(order.getTotal());
                order.setPembayaran(order.getTotal());
            }

            System.out.println("\nPembayaran berhasil.");

            // Setelah bayar: cetak struk ke layar, simpan order ke file orders.txt, lalu tampilkan struk dalam window.
            order.cetak();
            order.simpanKeFile();
            order.paint();

            System.out.println("\nOrder selesai.");

            System.out.println("\nLayani pelanggan berikutnya?");
            System.out.println("1. Ya");
            System.out.println("2. Keluar");
            // Menanyakan apakah mau melayani pelanggan berikutnya. 1 = lanjut, 2 = keluar.
            int lagi = readInt("Pilihan: ", 1, 2);

            // Kalau memilih 1, lanjut menjadi true sehingga loop utama berulang. Kalau 2, menjadi false sehingga program selesai.
            lanjut = lagi == 1;

// if digunakan untuk membuat keputusan berdasarkan kondisi.
            if (lanjut) {
                tampilkanMenuWindow(menuMinuman, menuTopping);
            }
        }

        System.out.println("====================================================");
        System.out.println("              TEAJI SYSTEM SELESAI");
        System.out.println("====================================================");
    }

    // Method private static ini membuat file yang dibutuhkan kalau belum ada.
    // Dibuat private karena hanya dipakai di dalam class App.
    // Kenapa? supaya program tidak error saat membaca file yang belum dibuat.
    private static void ensureFiles() {
        // try dipakai karena membuat file bisa gagal.
        try {
            // Membuat objek File untuk sellers.txt. Kalau belum ada, dibuat dan diisi satu akun kasir bawaan
            // supaya ada akun yang bisa dipakai login pertama kali.
            File sellers = new File("sellers.txt");

            // if digunakan untuk membuat keputusan berdasarkan kondisi.
            if (!sellers.exists()) {
                // Membuat FileWriter untuk menulis data ke file teks.
                FileWriter writer = new FileWriter(sellers);
                writer.write("TEAji|tehpalingenak123|Kasir Teaji"
                        + System.lineSeparator());
                writer.close();
            }

            // Cek file members.txt (data member) dan orders.txt (data pesanan).
            // Kalau belum ada, dibuat file kosong dengan createNewFile().
            File members = new File("members.txt");
            // if digunakan untuk membuat keputusan berdasarkan kondisi.
            if (!members.exists()) {
                members.createNewFile();
            }

            // Membuat object File untuk mengakses atau memeriksa file.
            File orders = new File("orders.txt");
            // if digunakan untuk membuat keputusan berdasarkan kondisi.
            if (!orders.exists()) {
                orders.createNewFile();
            }
        // catch ini jalan kalau ada error saat menyiapkan file, lalu menampilkan pesan gagal.
        } catch (IOException e) {
            System.out.println("Gagal menyiapkan file sistem.");
        }
    }

    // Method ini menampilkan daftar cabang lalu mengembalikan nama cabang yang dipilih kasir.
    private static String pilihCabang() {
        System.out.println("\n====================================================");
        System.out.println("                  PILIH CABANG TEAJI");
        System.out.println("====================================================");

        // Perulangan for digunakan ketika jumlah pengulangan atau data yang diproses diketahui.
        for (int i = 0; i < BRANCHES.length; i++) {
            System.out.println((i + 1) + ". " + BRANCHES[i]);
        }

        // Nomor yang dipilih dikurangi 1 karena indeks array dimulai dari 0.
        int pilihan = readInt("Cabang: ", 1, BRANCHES.length);
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return BRANCHES[pilihan - 1];
    }

    // Method ini membuat daftar semua menu minuman Teaji dalam ArrayList.
    // ArrayList dipakai karena ukurannya fleksibel dan mudah ditambah menu baru.
    private static ArrayList<MinumanUkuran> buatDaftarMinuman() {
        // Membuat object menggunakan constructor dari suatu class.
        ArrayList<MinumanUkuran> list = new ArrayList<MinumanUkuran>();

        // Setiap list.add menambahkan satu minuman.
        // Constructor dengan 5 data berisi: nama, kategori, rekomendasi topping, harga Medium, dan harga Large.
        // Rekomendasi topping diisi teks kosong kalau tidak ada.
        list.add(new MinumanUkuran(
                "House Special Milk Tea", "Signature Selection",
                "Soya Bean Curd", 40000, 44000));

        list.add(new MinumanUkuran(
                "Deep Roast Oolong Milk Tea", "Signature Selection",
                "Soya Bean Curd", 28000, 34000));

        list.add(new MinumanUkuran(
                "Jasmine Green Pure Tea", "Signature Selection",
                "Grass Jelly", 21000, 23000));

        list.add(new MinumanUkuran(
                "Four Season Oolong Pure Tea", "Signature Selection",
                "Tea Jelly", 25000, 28000));

        list.add(new MinumanUkuran(
                "Deep Roast Oolong Pure Tea", "Deep Roast Oolong Tea",
                "", 21000, 23000));

        list.add(new MinumanUkuran(
                "Deep Roast Oolong Milk Tea", "Deep Roast Oolong Tea",
                "", 28000, 34000));

        list.add(new MinumanUkuran(
                "Four Seasons Oolong Pure Tea", "Four Seasons Oolong Tea",
                "", 25000, 28000));

        list.add(new MinumanUkuran(
                "Four Seasons Oolong Milk Tea", "Four Seasons Oolong Tea",
                "", 33000, 40000));

        // Constructor dengan 3 data (nama, kategori, harga) dipakai untuk minuman yang hanya punya satu ukuran (Single).
        list.add(new MinumanUkuran(
                "White Peach Four Seasons Oolong Milk Tea",
                "Fruit Tea Series", 44000));

        list.add(new MinumanUkuran(
                "White Peach Jasmine Green Milk Tea",
                "Fruit Tea Series", 44000));

        list.add(new MinumanUkuran(
                "Apple Green Tea", "Fruit Tea Series",
                "", 43000, 46000));

        list.add(new MinumanUkuran(
                "Passion Fruit Orange Green Tea", "Fruit Tea Series",
                "", 52000, 55000));

        list.add(new MinumanUkuran(
                "Lemon Green Tea", "Fruit Tea Series",
                "", 30000, 32000));

        list.add(new MinumanUkuran(
                "Deep Roast Oolong Soy Milk Tea",
                "Soy Milk Tea Series", 38000));

        list.add(new MinumanUkuran(
                "Jasmine Soy Green Milk Tea",
                "Soy Milk Tea Series", 38000));

        list.add(new MinumanUkuran(
                "Four Seasons Soy Milk Tea",
                "Soy Milk Tea Series", 42000));

        // Minuman Honey Series. Namanya mengandung kata 'honey' sehingga nanti yang dipilih adalah Honey Level, bukan Sugar Level.
        list.add(new MinumanUkuran(
                "Honey Oolong Milk Tea", "Honey Series",
                "", 41000, 46000));

        list.add(new MinumanUkuran(
                "Honey Lemonade", "Honey Series",
                "", 30000, 35000));

        list.add(new MinumanUkuran(
                "Honey Four Seasons Oolong Tea", "Honey Series",
                "", 30000, 35000));

        list.add(new MinumanUkuran(
                "Honey Jasmine Green Tea", "Honey Series",
                "", 27000, 32000));

        list.add(new MinumanUkuran(
                "Honey Deep Roast Oolong Tea", "Honey Series",
                "", 27000, 32000));

        list.add(new MinumanUkuran(
                "Honey Oolong Lemonade", "Honey Series",
                "", 40000, 45000));

        // Mengembalikan daftar minuman yang sudah jadi kepada pemanggilnya (main).
        return list;
    }

    // Method ini membuat daftar topping yang bisa dipilih. Semua topping harganya sama, yaitu Rp 8.000.
    private static ArrayList<Topping> buatDaftarTopping() {
        // Membuat object menggunakan constructor dari suatu class.
        ArrayList<Topping> list = new ArrayList<Topping>();

        // Setiap list.add menambahkan satu topping dengan nama dan harganya.
        list.add(new Topping("Tea Jelly", 8000));
        list.add(new Topping("Soya Bean Curd", 8000));
        list.add(new Topping("Grass Jelly", 8000));
        list.add(new Topping("Oats", 8000));
        list.add(new Topping("Pearl", 8000));
        list.add(new Topping("Coconut Jelly", 8000));
        list.add(new Topping("Konjac Jelly", 8000));

        // Mengembalikan daftar topping yang sudah jadi.
        return list;
    }

    // Method ini menampilkan menu minuman versi singkat (nomor, nama, harga) di layar console saat kasir memilih pesanan.
    private static void tampilkanMenuRingkas(ArrayList<MinumanUkuran> menu) {
        System.out.println("\n---------------- MENU TEAJI ----------------");

        // Perulangan for digunakan ketika jumlah pengulangan atau data yang diproses diketahui.
        for (int i = 0; i < menu.size(); i++) {
            MinumanUkuran m = menu.get(i);

            // Kalau harga satuan lebih dari 0, berarti minuman hanya punya satu ukuran, jadi cukup tampilkan satu harga.
            // Kalau tidak, tampilkan harga Medium (M) dan Large (L).
            if (m.getHargaSatuan() > 0) {
                System.out.println((i + 1) + ". " + m.getNama()
                        + " - Rp " + formatRupiah(m.getHargaSatuan()));
            } else {
                System.out.println((i + 1) + ". " + m.getNama()
                        + " - M Rp " + formatRupiah(m.getHargaMedium())
                        + " | L Rp " + formatRupiah(m.getHargaLarge()));
            }
        }

        System.out.println("--------------------------------------------");
    }

    // Method ini meminta kasir memilih ukuran minuman, lalu mengembalikan ukurannya sebagai teks (Single, Medium, atau Large).
    private static String pilihUkuran(MinumanUkuran minuman) {
        // Kalau minuman hanya punya satu ukuran, tidak perlu bertanya dan langsung mengembalikan 'Single'.
        if (minuman.getHargaSatuan() > 0) {
            System.out.println("Ukuran: Single size");
            // return mengembalikan nilai dari method kepada pemanggilnya.
            return "Single";
        }

        System.out.println("1. Medium - Rp "
                + formatRupiah(minuman.getHargaMedium()));
        System.out.println("2. Large  - Rp "
                + formatRupiah(minuman.getHargaLarge()));

        // Kasir memilih 1 untuk Medium atau 2 untuk Large.
        int pilihan = readInt("Pilih ukuran: ", 1, 2);

        // if digunakan untuk membuat keputusan berdasarkan kondisi.
        if (pilihan == 1) {
        // return mengembalikan nilai dari method kepada pemanggilnya.
            return "Medium";
        }

    // return mengembalikan nilai dari method kepada pemanggilnya.
        return "Large";
    }

    // Method ini menampilkan pilihan Sugar Level lalu mengembalikan levelnya dalam bentuk teks.
    private static String pilihSugar() {
        System.out.println("\nPilih Sugar Level:");
        System.out.println("1. 0%");
        System.out.println("2. 30%");
        System.out.println("3. 50%");
        System.out.println("4. 70%");
        System.out.println("5. 100%");

        int pilihan = readInt("Sugar: ", 1, 5);
        // Array ini menyimpan teks level sesuai urutan menu, jadi pilihan 1 sampai 5 diubah menjadi teksnya dengan data[pilihan - 1].
        String[] data = {"0%", "30%", "50%", "70%", "100%"};
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return data[pilihan - 1];
    }

    // Method ini sama seperti pilihSugar, tapi untuk minuman Honey Series (pilihan 30%, 50%, 70%, 100%).
    private static String pilihHoney() {
        System.out.println("\nPilih Honey Level:");
        System.out.println("1. 30%");
        System.out.println("2. 50%");
        System.out.println("3. 70%");
        System.out.println("4. 100%");

        int pilihan = readInt("Honey: ", 1, 4);
        // Array teks Honey Level, dipakai dengan cara yang sama seperti pada Sugar Level.
        String[] data = {"30%", "50%", "70%", "100%"};
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return data[pilihan - 1];
    }

    // Method ini menampilkan pilihan Ice Level lalu mengembalikan levelnya dalam bentuk teks.
    private static String pilihIce() {
        System.out.println("\nPilih Ice Level:");
        System.out.println("1. 0%");
        System.out.println("2. 30%");
        System.out.println("3. 70%");

        int pilihan = readInt("Ice: ", 1, 3);
        // Array teks Ice Level, dipakai dengan cara yang sama seperti pada Sugar Level.
        String[] data = {"0%", "30%", "70%"};
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return data[pilihan - 1];
    }

    // Method ini menampilkan daftar topping berbayar dan mengembalikan topping yang dipilih.
    // Kalau pelanggan tidak mau topping, method mengembalikan null.
    private static Topping pilihTopping(ArrayList<Topping> menuTopping) {
        System.out.println("\nTopping:");
        // Pilihan 0 disediakan untuk pelanggan yang tidak mau topping.
        System.out.println("0. Tanpa topping");

        // Perulangan for digunakan ketika jumlah pengulangan atau data yang diproses diketahui.
        for (int i = 0; i < menuTopping.size(); i++) {
            System.out.println((i + 1) + ". "
                    + menuTopping.get(i).getNama()
                    + " - Rp " + formatRupiah(menuTopping.get(i).getHarga()));
        }

        int pilihan = readInt("Pilih topping: ", 0, menuTopping.size());

        // Kalau memilih 0, kembalikan null yang artinya tidak ada topping.
        // Karena itu di main ada pengecekan 'topping != null' sebelum topping dimasukkan ke item.
        if (pilihan == 0) {
            return null;
        }

        // return mengembalikan nilai dari method kepada pemanggilnya.
        return menuTopping.get(pilihan - 1);
    }

    // Method ini menanyakan tipe pesanan lalu mengembalikan 'Dine In' atau 'Takeaway'.
    private static String pilihTipeOrder() {
        System.out.println("\nTipe Order:");
        System.out.println("1. Dine In");
        System.out.println("2. Takeaway");

        int pilihan = readInt("Pilihan: ", 1, 2);

        // if digunakan untuk membuat keputusan berdasarkan kondisi.
        if (pilihan == 1) {
        // return mengembalikan nilai dari method kepada pemanggilnya.
            return "Dine In";
        }

        // return mengembalikan nilai dari method kepada pemanggilnya.
        return "Takeaway";
    }

    // Method ini menampilkan total dan menanyakan metode pembayaran, lalu mengembalikan 'QRIS' atau 'Cash'.
    private static String pilihPembayaran(double total) {
        System.out.println("\n====================================================");
        System.out.println("                    PEMBAYARAN");
        System.out.println("====================================================");
        System.out.println("Total: Rp " + formatRupiah(total));
        System.out.println("1. QRIS");
        System.out.println("2. Cash");

        int pilihan = readInt("Metode pembayaran: ", 1, 2);

        // if digunakan untuk membuat keputusan berdasarkan kondisi.
        if (pilihan == 1) {
        // return mengembalikan nilai dari method kepada pemanggilnya.
            return "QRIS";
        }

        // return mengembalikan nilai dari method kepada pemanggilnya.
        return "Cash";
    }

    // Method ini mencari member di file members.txt berdasarkan nomor telepon.
    // Kalau ketemu, dikembalikan objek Member lengkap. Kalau tidak ketemu, dikembalikan null.
    private static Member cariMember(String nomorTelepon) {
        // try digunakan untuk mencoba operasi yang mungkin menghasilkan error.
        try {
            // Membuka file members.txt dengan File dan Scanner untuk dibaca baris demi baris.
            File myObj = new File("members.txt");
            // Membuat Scanner untuk membaca input atau isi file.
            Scanner reader = new Scanner(myObj);

            // Perulangan while ini membaca file sampai baris terakhir, satu baris satu member.
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                // Memecah baris menjadi beberapa bagian dengan pemisah |.
                // Urutannya: nomor telepon, nama, level, stamp, reward10, reward15, reward20.
                String[] str = data.split("\\|");

                // Kalau bagian pertama (nomor telepon) sama dengan yang dicari, berarti member ketemu.
                // Scanner ditutup dulu, lalu data dari file diubah ke tipe aslinya.
                if (str.length >= 4 && str[0].equals(nomorTelepon)) {
                    reader.close();

                    // Integer.parseInt mengubah teks menjadi angka int, karena semua isi file dibaca sebagai teks.
                    int level = Integer.parseInt(str[2]);
                    int stamp = Integer.parseInt(str[3]);

                    // Boolean.parseBoolean mengubah teks 'true' atau 'false' menjadi boolean.
                    // Pengecekan str.length dilakukan supaya data lama yang belum punya kolom reward tidak menyebabkan error.
                    boolean reward10 = str.length >= 5 && Boolean.parseBoolean(str[4]);
                    boolean reward15 = str.length >= 6 && Boolean.parseBoolean(str[5]);
                    boolean reward20 = str.length >= 7 && Boolean.parseBoolean(str[6]);

                    // Membuat objek Member dari data file lalu langsung dikembalikan ke pemanggil.
                    Member member = new Member(str[1], str[0], level, stamp);

                    // Status reward disimpan di objek Reward milik member.
                    Reward reward = member.getReward();
                    reward.setReward10Claimed(reward10);
                    reward.setReward15Claimed(reward15);
                    reward.setReward20Claimed(reward20);

                    return member;
                }
            }

            reader.close();
        // catch ini jalan kalau file members.txt tidak ada.
        } catch (FileNotFoundException e) {
            System.out.println("File member belum tersedia.");
        // catch ini jalan kalau level atau stamp di file bukan angka yang valid.
        } catch (NumberFormatException e) {
            System.out.println("Data level/stamp pada file member tidak valid.");
        }

        // Sampai baris ini berarti member tidak ditemukan, jadi mengembalikan null.
        return null;
    }

    // Method ini menyimpan data member ke members.txt.
    // Kalau member sudah ada, barisnya diperbarui. Kalau belum ada, ditambahkan sebagai baris baru.
    // Caranya: baca semua baris ke ArrayList dulu, ubah yang perlu, lalu tulis ulang seluruh file.
    private static void simpanMember(Member member) {
        // dataBaru menampung semua baris yang nanti ditulis ulang ke file.
        ArrayList<String> dataBaru = new ArrayList<String>();
        // Penanda apakah nomor telepon member ini sudah ada di file.
        boolean sudahAda = false;

        // try digunakan untuk mencoba operasi yang mungkin menghasilkan error.
        try {
        // Membuat object File untuk mengakses atau memeriksa file.
            File myObj = new File("members.txt");
            // Membuat Scanner untuk membaca input atau isi file.
            Scanner reader = new Scanner(myObj);

            // Perulangan while terus dilakukan selama kondisi bernilai true.
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] str = data.split("\\|");

                // Kalau nomor telepon cocok, baris lama diganti dengan data member terbaru (nama, level, stamp, status reward).
                // sudahAda menjadi true.
                if (str.length >= 4
                        && str[0].equals(member.getNomorTelepon())) {

                    data = member.getNomorTelepon() + "|"
                            + member.getNama() + "|"
                            + member.getReward().getLevel() + "|"
                            + member.getReward().getStamp() + "|"
                            + member.getReward().isReward10Claimed() + "|"
                            + member.getReward().isReward15Claimed() + "|"
                            + member.getReward().isReward20Claimed();

                    sudahAda = true;
                }

                // Setiap baris dimasukkan ke dataBaru, baik yang sudah diperbarui maupun yang tidak berubah.
                dataBaru.add(data);
            }

            reader.close();

            // Kalau sampai akhir nomor tidak ditemukan, berarti member baru, jadi ditambahkan di baris paling bawah.
            if (!sudahAda) {
                dataBaru.add(member.getNomorTelepon() + "|"
                        + member.getNama() + "|"
                        + member.getReward().getLevel() + "|"
                        + member.getReward().getStamp() + "|"
                        + member.getReward().isReward10Claimed() + "|"
                        + member.getReward().isReward15Claimed() + "|"
                        + member.getReward().isReward20Claimed());
            }

            // Membuka file untuk ditulis ulang. Tanpa parameter true, isi lama ditimpa oleh isi dataBaru.
            FileWriter writer = new FileWriter("members.txt");
            // Perulangan for-each ini menulis semua baris di dataBaru ke file, satu baris satu data.
            for (String data : dataBaru) {
                writer.write(data + System.lineSeparator());
            }
            writer.close();

        // catch ini jalan kalau ada error saat membaca atau menulis file member.
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data member.");
        }
    }

    // Method ini menampilkan data member: nama, nomor HP, level, stamp, dan daftar reward.
    private static void tampilkanStatusMember(Member member) {
        System.out.println("Nama  : " + member.getNama());
        System.out.println("No HP : " + member.getNomorTelepon());
        System.out.println("Level : " + member.getReward().getLevel());
        System.out.println("       " + member.getReward().getSloganLevel());
        System.out.println("Stamp : " + member.getReward().getStamp() + "/20");

        System.out.println("\nReward Level " + member.getReward().getLevel() + ":");
        System.out.println("10 stamp : " + member.getReward().getReward10());
        System.out.println("15 stamp : " + member.getReward().getReward15());
        System.out.println("20 stamp : " + member.getReward().getReward20());

        // Status reward hanya ditampilkan kalau stamp sudah cukup (10, 15, atau 20).
        // Statusnya SUDAH DITUKAR atau BELUM DITUKAR, dipilih dengan operator ternary (kondisi ? kalau_benar : kalau_salah).
        if (member.getReward().getStamp() >= 10) {
            System.out.println("10 stamp status: "
                    + (member.getReward().isReward10Claimed() ? "SUDAH DITUKAR" : "BELUM DITUKAR"));
        }
        // if digunakan untuk membuat keputusan berdasarkan kondisi.
        if (member.getReward().getStamp() >= 15) {
            System.out.println("15 stamp status: "
                    + (member.getReward().isReward15Claimed() ? "SUDAH DITUKAR" : "BELUM DITUKAR"));
        }                   
        // if digunakan untuk membuat keputusan berdasarkan kondisi.
        if (member.getReward().getStamp() >= 20) {
            System.out.println("20 stamp status: "
                    + (member.getReward().isReward20Claimed() ? "SUDAH DITUKAR" : "BELUM DITUKAR"));
        }
    }

    // Method private adalah method internal yang hanya digunakan oleh class ini.
    // Method ini menambahkan stamp setelah transaksi dibuat lalu memproses reward.
    private static void prosesStampMember(Member member, int jumlahMinuman,
            Order order, ArrayList<MinumanUkuran> menuMinuman,
            ArrayList<Topping> menuTopping) {

        // Menyimpan jumlah stamp sebelum ditambah supaya bisa ditampilkan sebagai perbandingan.
        int stampSebelum = member.getReward().getStamp();

        System.out.println("\n====================================================");
        System.out.println("                    MEMBER");
        System.out.println("====================================================");
        System.out.println("Nomor telepon : " + member.getNomorTelepon());
        System.out.println("Level sebelum : " + member.getReward().getLevel());
        System.out.println("Stamp sebelum : " + stampSebelum + "/20");
        System.out.println("Tambah stamp  : +" + jumlahMinuman);

        // Satu gelas minuman yang dibeli memberikan satu stamp.
        member.getReward().tambahStamp(jumlahMinuman);

        System.out.println("Stamp sekarang: " + member.getReward().getStamp() + "/20");
        System.out.println(member.getReward().getSloganLevel());

        // Reward dicek setelah stamp bertambah agar reward yang baru terbuka
        // dapat langsung ditawarkan kepada pelanggan.
        prosesPenukaranReward(member, order, menuMinuman, menuTopping);

        System.out.println("\nStatus member setelah reward:");
        tampilkanStatusMember(member);
    }

    // Method ini mengatur seluruh proses penukaran reward pada level member.
    private static void prosesPenukaranReward(Member member, Order order,
            ArrayList<MinumanUkuran> menuMinuman,
            ArrayList<Topping> menuTopping) {

        // Reward 10 stamp: topping gratis pada minuman yang sudah dipesan.
        // Mengecek apakah reward 10 stamp baru terbuka dan belum ditukar.
        if (member.getReward().reward10Terbuka()) {
            System.out.println("\n====================================================");
            System.out.println("              REWARD 10 STAMP TERBUKA");
            System.out.println("====================================================");
            System.out.println("Level  : " + member.getReward().getLevel());
            System.out.println("Reward : " + member.getReward().getReward10());
            System.out.println(member.getReward().getReward10Note());
            System.out.print("Mau menukar reward ini? (Y/N): ");

            // Kasir menjawab Y atau N. equalsIgnoreCase dipakai supaya huruf besar/kecil tidak masalah.
            String jawab = input.nextLine();

            if (jawab.equalsIgnoreCase("Y")) {
                // Level 1 mendapat 1 topping gratis, level lainnya 2 topping gratis (operator ternary).
                int jumlahToppingGratis = member.getReward().getLevel() == 1 ? 1 : 2;

                for (int i = 1; i <= jumlahToppingGratis; i++) {
                    System.out.println("\nPilih minuman yang mendapat topping gratis.");
                    tampilkanDaftarItemOrder(order);
                    int nomorItem = readInt("Nomor minuman: ", 1,
                            order.getItemsArrayList().size());

                    System.out.println("Pilih topping gratis ke-" + i + ":");
                    // Kasir memilih topping gratis dari daftar tanpa harga.
                    Topping topping = pilihToppingTanpaHarga(menuTopping);

                    if (topping != null) {
                        OrderItem item = order.getItemsArrayList().get(nomorItem - 1);
                        // Parameter true menandakan topping ini gratis dari reward, jadi tidak ikut dihitung sebagai harga.
                        item.addTopping(topping, true);
                    }
                }

                // Menandai reward 10 stamp sudah ditukar dan mencatat ringkasannya di order untuk ditampilkan di struk.
                member.getReward().tukarReward10();
                order.addRewardSummary("Level " + member.getReward().getLevel()
                        + " - " + member.getReward().getReward10());
                System.out.println("Reward topping berhasil ditambahkan ke pesanan.");
            } else {
                System.out.println("Reward 10 stamp belum ditukar.");
            }
        }

        // Reward 15 stamp: minuman gratis sesuai jenis reward pada level.
        // Mengecek apakah reward 15 stamp baru terbuka dan belum ditukar.
        if (member.getReward().reward15Terbuka()) {
            System.out.println("\n====================================================");
            System.out.println("              REWARD 15 STAMP TERBUKA");
            System.out.println("====================================================");
            System.out.println("Level  : " + member.getReward().getLevel());
            System.out.println("Reward : " + member.getReward().getReward15());
            System.out.println(member.getReward().getReward15Note());
            System.out.print("Mau menukar reward ini? (Y/N): ");

            String jawab = input.nextLine();

            if (jawab.equalsIgnoreCase("Y")) {
                // Jenis minuman gratis tergantung level member:
                // level 1 pilih Pure Tea, level 2 pilih Milk Tea, level 3 pilih Fruit Tea Series,
                // level selain itu (level 4) langsung House Special Milk Tea.
                MinumanUkuran rewardDrink;

                if (member.getReward().getLevel() == 1) {
                    rewardDrink = pilihPureTea(menuMinuman);
                } else if (member.getReward().getLevel() == 2) {
                    rewardDrink = pilihMinumanMengandung(menuMinuman, "Milk Tea");
                } else if (member.getReward().getLevel() == 3) {
                    rewardDrink = pilihMinumanKategori(menuMinuman, "Fruit Tea Series");
                } else {
                    rewardDrink = pilihMinumanExact(menuMinuman,
                            "House Special Milk Tea");
                }

                // Menambahkan minuman reward ke order. Parameter false artinya ukuran masih dipilih kasir.
                tambahMinumanReward(order, rewardDrink, menuTopping,
                        false, "Level " + member.getReward().getLevel() + " - "
                                + member.getReward().getReward15());

                String rewardText = "Level " + member.getReward().getLevel()
                        + " - " + member.getReward().getReward15();
                // Menandai reward 15 stamp sudah ditukar dan mencatat ringkasannya ke order.
                member.getReward().tukarReward15();
                order.addRewardSummary(rewardText);
                System.out.println("Reward 15 stamp berhasil ditukar.");
            } else {
                System.out.println("Reward 15 stamp belum ditukar.");
            }
        }

        // Reward 20 stamp membutuhkan minimal satu minuman berbayar.
        // Order utama sudah memiliki minimal satu minuman sebelum method ini dipanggil.
        // Mengecek apakah reward 20 stamp terbuka dan belum ditukar.
        if (member.getReward().reward20Terbuka()) {
            System.out.println("\n====================================================");
            System.out.println("              REWARD 20 STAMP TERBUKA");
            System.out.println("====================================================");
            System.out.println("Level  : " + member.getReward().getLevel());
            System.out.println("Reward : " + member.getReward().getReward20());
            System.out.println("Syarat : pembelian minimal 1 minuman.");
            System.out.println("Ukuran : Large gratis jika menu memiliki ukuran Large.");
            System.out.println("Topping tambahan pada reward tetap berbayar Rp 8.000.");
            System.out.print("Mau menukar reward ini? (Y/N): ");

            String jawab = input.nextLine();

            if (jawab.equalsIgnoreCase("Y")) {
                // Jumlah minuman gratis sama dengan level member (level 3 dapat 3 minuman gratis).
                // levelLama disimpan karena level akan berubah setelah reward ditukar.
                int jumlahFreeDrink = member.getReward().getLevel();
                int levelLama = member.getReward().getLevel();

                for (int i = 1; i <= jumlahFreeDrink; i++) {
                    System.out.println("\n--- Free Drink " + i + " dari "
                            + jumlahFreeDrink + " ---");
                    MinumanUkuran rewardDrink = pilihMinumanDuaUkuran(menuMinuman);
                    // Parameter true artinya ukuran otomatis Large gratis (kalau menu punya ukuran Large).
                    tambahMinumanReward(order, rewardDrink, menuTopping,
                            true, "Level " + levelLama + " - Free Drink");
                }

                // Menandai reward 20 stamp sudah ditukar. Setelah ini stamp kembali ke 0 dan level naik (level 4 kembali ke level 1).
                member.getReward().tukarReward20();

                // Setelah reward 20 ditukar, level berubah.
                // Level 1-3 naik satu level, sedangkan Level 4 kembali ke Level 1.
                member.getReward().resetLevel();

                order.addRewardSummary("Level " + levelLama + " - "
                        + (levelLama == 4 ? "Free 4 Drinks" : "Free "
                                + levelLama + " Drink(s)"));

                // Pesan yang ditampilkan dibedakan: level 4 kembali ke level 1, level lainnya naik satu level.
                if (levelLama == 4) {
                    System.out.println("Reward Level 4 berhasil ditukar.");
                    System.out.println("Member kembali ke Level 1.");
                } else {
                    System.out.println("Reward Level " + levelLama
                            + " berhasil ditukar.");
                    System.out.println("Member naik ke Level "
                            + member.getReward().getLevel() + ".");
                }
                System.out.println("Stamp kembali menjadi 0/20.");
                System.out.println(member.getReward().getSloganLevel());
            } else {
                System.out.println("Reward 20 stamp belum ditukar.");
                System.out.println("Stamp tetap 20/20.");
            }
        }
    }

    // Menampilkan item yang sudah dipesan untuk memilih target reward topping.
    private static void tampilkanDaftarItemOrder(Order order) {
        for (int i = 0; i < order.getItemsArrayList().size(); i++) {
            OrderItem item = order.getItemsArrayList().get(i);
            System.out.println((i + 1) + ". " + item.getMinuman().getNama());
        }
    }

    // Topping reward dipilih tanpa menambahkan harga.
    private static Topping pilihToppingTanpaHarga(ArrayList<Topping> menuTopping) {
        for (int i = 0; i < menuTopping.size(); i++) {
            System.out.println((i + 1) + ". " + menuTopping.get(i).getNama()
                    + " - FREE");
        }

        int pilihan = readInt("Pilih topping: ", 1, menuTopping.size());
        return menuTopping.get(pilihan - 1);
    }

    // Memilih pure tea yang tersedia untuk reward level 1.
    private static MinumanUkuran pilihPureTea(ArrayList<MinumanUkuran> menu) {
        // Membuat list sementara untuk menampung minuman yang boleh dipilih.
        ArrayList<MinumanUkuran> pilihan = new ArrayList<MinumanUkuran>();

        for (MinumanUkuran m : menu) {
            // Hanya minuman Pure Tea tertentu yang dimasukkan ke daftar pilihan reward level 1.
            if (m.getNama().equalsIgnoreCase("Jasmine Green Pure Tea")
                    || m.getNama().equalsIgnoreCase("Four Season Oolong Pure Tea")
                    || m.getNama().equalsIgnoreCase("Deep Roast Oolong Pure Tea")
                    || m.getNama().equalsIgnoreCase("Four Seasons Oolong Pure Tea")) {
                pilihan.add(m);
            }
        }

        System.out.println("\nPilihan Pure Tea:");
        for (int i = 0; i < pilihan.size(); i++) {
            String nama = pilihan.get(i).getNama()
                    // replace mengganti kata ' Pure Tea' menjadi ' Tea' supaya nama yang tampil lebih singkat.
                    .replace(" Pure Tea", " Tea");
            System.out.println((i + 1) + ". " + nama);
        }

        int nomor = readInt("Pilih Pure Tea: ", 1, pilihan.size());
        return pilihan.get(nomor - 1);
    }

    // Memilih minuman berdasarkan kata tertentu pada nama.
    private static MinumanUkuran pilihMinumanMengandung(
            ArrayList<MinumanUkuran> menu, String kata) {
        ArrayList<MinumanUkuran> pilihan = new ArrayList<MinumanUkuran>();

        for (MinumanUkuran m : menu) {
            // Memasukkan minuman yang namanya mengandung kata yang dicari, tanpa membedakan huruf besar atau kecil.
            if (m.getNama().toLowerCase().contains(kata.toLowerCase())) {
                pilihan.add(m);
            }
        }

        System.out.println("\nPilihan " + kata + ":");
        for (int i = 0; i < pilihan.size(); i++) {
            System.out.println((i + 1) + ". " + pilihan.get(i).getNama());
        }

        int nomor = readInt("Pilih minuman: ", 1, pilihan.size());
        return pilihan.get(nomor - 1);
    }

    // Memilih minuman berdasarkan kategori.
    private static MinumanUkuran pilihMinumanKategori(
            ArrayList<MinumanUkuran> menu, String kategori) {
        ArrayList<MinumanUkuran> pilihan = new ArrayList<MinumanUkuran>();

        for (MinumanUkuran m : menu) {
            // Memasukkan minuman yang kategorinya sama dengan kategori yang diminta.
            if (m.getKategori().equalsIgnoreCase(kategori)) {
                pilihan.add(m);
            }
        }

        System.out.println("\nPilihan " + kategori + ":");
        for (int i = 0; i < pilihan.size(); i++) {
            System.out.println((i + 1) + ". " + pilihan.get(i).getNama());
        }

        int nomor = readInt("Pilih minuman: ", 1, pilihan.size());
        return pilihan.get(nomor - 1);
    }

    // Memilih satu menu berdasarkan nama persis.
    private static MinumanUkuran pilihMinumanExact(
            ArrayList<MinumanUkuran> menu, String nama) {
        for (MinumanUkuran m : menu) {
            if (m.getNama().equalsIgnoreCase(nama)) {
                return m;
            }
        }
        // Kalau nama tidak ditemukan, dikembalikan menu pertama sebagai cadangan supaya program tidak error.
        return menu.get(0);
    }

    // Menampilkan seluruh menu Teaji untuk reward 20 stamp.
    // Pelanggan bebas memilih varian minuman apa saja dari menu.
    private static MinumanUkuran pilihMinumanDuaUkuran(
            ArrayList<MinumanUkuran> menu) {

        System.out.println("\n====================================================");
        System.out.println("          PILIH MENU UNTUK FREE DRINK");
        System.out.println("====================================================");
        System.out.println("Bebas memilih varian minuman dari menu Teaji.");

        // Menyimpan kategori terakhir yang sudah ditampilkan, supaya judul kategori hanya dicetak sekali.
        String kategoriTerakhir = "";

        // Loop digunakan untuk menampilkan menu berdasarkan kategori.
        for (int i = 0; i < menu.size(); i++) {
            MinumanUkuran m = menu.get(i);

            // Kalau kategori minuman berbeda dari sebelumnya, cetak judul kategori baru dalam tanda [ ].
            if (!m.getKategori().equalsIgnoreCase(kategoriTerakhir)) {
                kategoriTerakhir = m.getKategori();
                System.out.println("\n[" + kategoriTerakhir + "]");
            }

            String harga;
            if (m.getHargaSatuan() > 0) {
                harga = "Rp " + formatRupiah(m.getHargaSatuan());
            } else {
                harga = "M " + formatRupiah(m.getHargaMedium())
                        + " | L " + formatRupiah(m.getHargaLarge());
            }

            System.out.println((i + 1) + ". " + m.getNama() + " - " + harga);
        }

        int nomor = readInt("Pilih minuman free drink: ", 1, menu.size());
        return menu.get(nomor - 1);
    }

    // Menambahkan minuman reward beserta ukuran, sugar/honey, ice, dan topping.
    private static void tambahMinumanReward(Order order,
            MinumanUkuran minuman, ArrayList<Topping> menuTopping,
            boolean freeDrinkLarge, String labelReward) {

        System.out.println("\nAnda memilih reward: " + minuman.getNama());

        // Ukuran tidak diberi nilai dulu karena akan ditentukan di if di bawah:
        // reward 20 stamp otomatis Large, sedangkan reward lain dipilih kasir lewat pilihUkuran.
        String ukuran;
        if (freeDrinkLarge) {
            // Reward 20 menggunakan ukuran Large jika minuman menyediakan Large.
            // Jika menu hanya memiliki satu ukuran, ukuran tersebut digunakan.
            if (minuman.getHargaSatuan() > 0) {
                ukuran = "Single Size";
                System.out.println("Ukuran reward: Single Size (GRATIS)");
            } else {
                ukuran = "Large";
                System.out.println("Ukuran reward: Large (GRATIS)");
            }
        } else {
            ukuran = pilihUkuran(minuman);
        }

        String sugarLevel = "";
        String honeyLevel = "";

        if (minuman.getNama().toLowerCase().contains("honey")) {
            honeyLevel = pilihHoney();
        } else {
            sugarLevel = pilihSugar();
        }

        String iceLevel = pilihIce();

        // Membuat OrderItem untuk minuman reward.
        // Customisasi disimpan langsung di ArrayList yang berada
        // di dalam OrderItem, bukan menggunakan class Customisasi.
        OrderItem item = new OrderItem();

        // Menyimpan minuman reward.
        item.setMinuman(minuman);

        // Reward drink diberikan sebanyak satu gelas.
        item.setJumlah(1);

        // Menyimpan ukuran minuman reward.
        item.addCustomisasi(ukuran);

        // Menyimpan Sugar Level. Jika minuman Honey Series,
        // Sugar Level disimpan sebagai '-'.
        item.addCustomisasi(
                sugarLevel.equals("") ? "-" : sugarLevel);

        // Menyimpan Honey Level. Jika bukan minuman Honey Series,
        // Honey Level disimpan sebagai '-'.
        item.addCustomisasi(
                honeyLevel.equals("") ? "-" : honeyLevel);

        // Menyimpan Ice Level.
        item.addCustomisasi(iceLevel);

        System.out.println("\nApakah reward ingin ditambah topping?");
        System.out.println("0. Tidak");
        System.out.println("1. Ya, topping Rp 8.000");
        // Menanyakan apakah pelanggan mau menambah topping pada minuman reward. Topping ini tetap dibayar.
        int adaTopping = readInt("Pilihan: ", 0, 1);

        Topping topping = null;
        if (adaTopping == 1) {
            topping = pilihTopping(menuTopping);
        }

        // Jika pelanggan memilih topping tambahan, topping tersebut
        // tetap harus dibayar karena bukan bagian dari reward gratis.
        if (topping != null) {
            item.addTopping(topping, false);
        }

        // Harga dasar tetap dihitung, lalu dipotong oleh reward.
        item.setRewardItem(true);
        item.setRewardLabel(labelReward);
        order.addItem(item);

        // Mengambil harga minuman sesuai ukuran, lalu jumlah itu dijadikan potongan (discount).
        // Jadi minuman reward tetap tercatat di struk tapi harganya dipotong, sehingga jadi gratis.
        double hargaDasar = minuman.getHarga(ukuran);
        order.addDiscount(hargaDasar);

        System.out.println("Reward drink berhasil ditambahkan.");
        if (topping != null) {
            System.out.println("Topping reward dibayar terpisah: Rp "
                    + formatRupiah(topping.getHarga()));
        }
    }

    // Method ini menampilkan seluruh menu Teaji di sebuah window Swing.
    // Swing dipakai karena tampilan menu lebih rapi daripada console.
    private static void tampilkanMenuWindow(
            ArrayList<MinumanUkuran> menu,
            ArrayList<Topping> topping) {

        // JFrame adalah window utama. Parameternya adalah judul window, lalu ukuran diatur 900 x 700 piksel.
        JFrame frame = new JFrame("TEAJI - Menu");
        frame.setSize(900, 700);
        // DISPOSE_ON_CLOSE artinya kalau window ditutup, hanya window itu yang hilang dan seluruh program tidak ikut berhenti.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // JTextArea dipakai untuk menampilkan teks menu. Font Monospaced dipilih supaya huruf sama lebar dan tabel rapi.
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        // Teks dibuat tidak bisa diedit karena hanya untuk dibaca.
        area.setEditable(false);

        // Variabel teks dipakai untuk menyusun seluruh isi menu, sedikit demi sedikit, sebelum ditampilkan.
        String teks = "";
        teks = teks + "======================== TEAJI ========================\n";
        teks = teks + "                     MENU MINUMAN\n";
        teks = teks + "=========================================================\n\n";

        // Menyimpan kategori sebelumnya supaya judul kategori hanya ditulis saat kategori berganti.
        String kategoriSebelumnya = "";

        // Perulangan for digunakan ketika jumlah pengulangan atau data yang diproses diketahui.
        for (int i = 0; i < menu.size(); i++) {
            MinumanUkuran m = menu.get(i);

            // Kalau kategori berbeda dari sebelumnya, tambahkan judul kategori baru ke teks.
            if (!m.getKategori().equals(kategoriSebelumnya)) {
                teks = teks + "\n[" + m.getKategori() + "]\n";
                kategoriSebelumnya = m.getKategori();
            }

            // String.format mengatur format teks: %2d untuk nomor selebar 2 karakter dan %-45s untuk nama selebar 45 karakter rata kiri supaya harga sejajar.
            teks = teks + String.format("%2d. %-45s",
                    i + 1, m.getNama());

            // if digunakan untuk membuat keputusan berdasarkan kondisi.
            if (m.getHargaSatuan() > 0) {
                teks = teks + "Rp " + formatRupiah(m.getHargaSatuan()) + "\n";
            } else {
                teks = teks + "M Rp " + formatRupiah(m.getHargaMedium())
                        + " | L Rp " + formatRupiah(m.getHargaLarge()) + "\n";
            }

            // if digunakan untuk membuat keputusan berdasarkan kondisi.
            if (!m.getRekomendasiTopping().equals("")) {
                teks = teks + "    Recommended topping: "
                        + m.getRekomendasiTopping() + "\n";
            }
        }

        teks = teks + "\n=========================================================\n";
        teks = teks + "TOPPING - Rp 8.000\n";

        // Perulangan for digunakan ketika jumlah pengulangan atau data yang diproses diketahui.
        for (int i = 0; i < topping.size(); i++) {
            teks = teks + (i + 1) + ". " + topping.get(i).getNama() + "\n";
        }

        teks = teks + "\nSugar : 0%, 30%, 50%, 70%, 100%\n";
        teks = teks + "Honey : 30%, 50%, 70%, 100%\n";
        teks = teks + "Ice   : 0%, 30%, 70%\n";

        // Memasukkan seluruh teks menu ke JTextArea.
        area.setText(teks);

        // JScrollPane membuat isi window bisa digulir kalau menu lebih panjang dari layar, lalu ditambahkan ke window.
        frame.add(new JScrollPane(area));
        frame.setLocation(40, 40);
        // Menampilkan window ke layar. Tanpa baris ini window sudah dibuat tapi tidak kelihatan.
        frame.setVisible(true);
    }

    // Method ini menampilkan gambar QRIS simulasi di window baru. Ini hanya tampilan contoh, bukan pembayaran asli.
    private static void tampilkanQRIS(double total) {
        // Membuat window baru khusus QRIS dengan ukuran 450 x 520 piksel.
        JFrame frame = new JFrame("TEAJI - QRIS Simulasi");
        frame.setSize(450, 520);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Membuat panel gambar QRIS. Total dikirim supaya nominalnya ikut tertulis di gambar.
        QrisPanel panel = new QrisPanel(total);
        frame.add(panel);
        // Menaruh window tepat di tengah layar.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println("QRIS simulasi ditampilkan.");
        System.out.println("Silakan lanjutkan setelah tampilan QRIS muncul.");
    }

    // Method ini membaca angka dari keyboard yang harus berada di antara min dan max.
    // Kalau input salah, kasir diminta mengulang terus sampai benar.
    // Kenapa? supaya program tidak error kalau kasir mengetik huruf atau angka di luar pilihan.
    private static int readInt(String pesan, int min, int max) {
        // while (true) berjalan terus tanpa henti sampai ada return yang keluar dari method.
        while (true) {
            System.out.print(pesan);
            String value = input.nextLine();

            // try dipakai karena mengubah teks menjadi angka bisa gagal kalau isinya huruf.
            try {
                // Mengubah teks yang diketik menjadi angka int.
                int angka = Integer.parseInt(value);

                // Mengecek angka ada di dalam batas. Kalau ya, angka dikembalikan dan perulangan berhenti.
                if (angka >= min && angka <= max) {
                // return mengembalikan nilai dari method kepada pemanggilnya.
                    return angka;
                }

                System.out.println("Input harus antara "
                        + min + " dan " + max + ".");
            // catch ini jalan kalau input bukan angka, lalu program meminta input lagi.
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    // Method ini membaca angka desimal dari keyboard yang tidak boleh lebih kecil dari min.
    // Dipakai untuk uang tunai yang tidak boleh kurang dari total belanja.
    private static double readDoubleMin(String pesan, double min) {
        // Perulangan while terus dilakukan selama kondisi bernilai true.
        while (true) {
            System.out.print(pesan);
            String value = input.nextLine();

            // try digunakan untuk mencoba operasi yang mungkin menghasilkan error.
            try {
                // Mengubah teks menjadi angka double (bisa desimal).
                double angka = Double.parseDouble(value);

                // Kalau uang cukup (tidak kurang dari min), angka dikembalikan. Kalau kurang, tampil pesan dan diminta ulang.
                if (angka >= min) {
                // return mengembalikan nilai dari method kepada pemanggilnya.
                    return angka;
                }

                System.out.println("Uang tidak boleh kurang dari total.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    // Method ini mengubah angka menjadi teks berformat rupiah.
    // format %,.0f menghasilkan pemisah ribuan koma tanpa desimal, lalu koma diganti titik agar sesuai penulisan Indonesia (contoh: 40.000).
    private static String formatRupiah(double angka) {
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return String.format("%,.0f", angka).replace(',', '.');
    }

    // Panel QRIS ini hanya gambar simulasi, bukan QRIS pembayaran asli.
    // QrisPanel adalah class di dalam class App (nested class). 'extends JPanel' artinya panel ini area gambar Swing.
    // Dibuat static supaya bisa dipakai tanpa membuat objek App.
    static class QrisPanel extends JPanel {
        // Variabel private ini menyimpan total pembayaran yang akan ditulis di gambar QRIS.
        private double total;

        // Constructor ini membuat panel QRIS dan menerima total pembayaran.
        public QrisPanel(double total) {
            this.total = total;
        }

        // @Override menandakan method ini menimpa method bawaan JPanel.
        // paintComponent dipanggil otomatis oleh Swing setiap kali panel perlu digambar ulang.
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Mengubah Graphics menjadi Graphics2D supaya bisa memakai fitur gambar 2D yang lebih lengkap.
            Graphics2D g2 = (Graphics2D) g;
            // Mengaktifkan antialiasing supaya garis dan tulisan tampak lebih halus.
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("SansSerif", Font.BOLD, 28));
            g2.drawString("QRIS", 180, 45);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g2.drawString("SIMULASI - BUKAN PEMBAYARAN ASLI", 90, 75);

            // Menentukan posisi awal (x dan y) dan ukuran satu kotak kecil (cell) pada pola QR.
            int startX = 95;
            int startY = 105;
            int cell = 10;

            g2.setColor(Color.BLACK);

            // Pola kotak dibuat sendiri hanya sebagai tampilan QR simulasi.
            // Perulangan for digunakan ketika jumlah pengulangan atau data yang diproses diketahui.
            for (int row = 0; row < 21; row++) {
                // Perulangan for digunakan ketika jumlah pengulangan atau data yang diproses diketahui.
                for (int col = 0; col < 21; col++) {
                    // Rumus sederhana untuk menentukan kotak mana yang diwarnai hitam.
                    // Hasilnya hanya pola acak supaya mirip QR, bukan QR asli yang bisa discan.
                    boolean kotak = ((row * 7 + col * 11 + row * col) % 3 == 0);

                    // if digunakan untuk membuat keputusan berdasarkan kondisi.
                    if (kotak) {
                        g2.fillRect(
                                startX + col * cell,
                                startY + row * cell,
                                cell,
                                cell);
                    }
                }
            }

            // Tiga kotak sudut agar tampilannya mirip QR.
            g2.setStroke(new BasicStroke(4));
            gambarKotakQR(g2, startX, startY, cell);
            gambarKotakQR(g2, startX + 14 * cell, startY, cell);
            gambarKotakQR(g2, startX, startY + 14 * cell, cell);

            g2.setFont(new Font("SansSerif", Font.BOLD, 18));
            g2.drawString("Total: Rp " + formatRupiah(total), 125, 360);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g2.drawString("Pembayaran QRIS Teaji (SIMULASI)", 115, 395);
            g2.drawString("Tidak terhubung ke sistem pembayaran.", 95, 420);
        }

        // Method ini menggambar satu kotak penanda sudut QR: kotak berbingkai dengan kotak hitam di tengahnya.
        private void gambarKotakQR(
                Graphics2D g2, int x, int y, int cell) {

            g2.drawRect(x, y, 70, 70);
            g2.fillRect(x + 20, y + 20, 30, 30);
        }
    }
}