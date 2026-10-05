//package ini supaya class/interface berada dalam package com.teaji. Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

/*
 * Pelanggan.java
 * Class pelanggan yang mewarisi Person dan mengimplementasikan Login.
 * Class ini disiapkan untuk proses sign up/sign in pelanggan menggunakan
 * nomor telepon.
 */
import java.io.File;//library java untuk mengakses atau memeriksa file.
import java.io.FileNotFoundException;//library java untuk menangani kesalahan saat file tidak ditemukan.
import java.io.FileWriter;//library java untuk menulis data ke file teks.
import java.io.IOException;//library java untuk menangani kesalahan input/output.
import java.util.Scanner;//library java untuk membaca input dari pengguna.

// Mendefinisikan class/interface utama sesuai nama file.
public class Pelanggan extends Person implements Login {
    // Variabel private digunakan untuk encapsulation sehingga data tidak diakses langsung dari luar class.
    private String nomorTelepon;

    // Method dan constructor public menjalankan satu tugas tertentu sesuai tanggung jawab class.
    // Constructor public ini digunakan untuk membuat objek Pelanggan dengan nilai default untuk nama dan nomor telepon.
    // Method ini dipanggil saat objek Pelanggan dibuat tanpa parameter.
    public Pelanggan() {
        super("");
        nomorTelepon = "";
    }

    // Method public menjalankan satu tugas tertentu sesuai tanggung jawab class.
    public Pelanggan(String nama, String nomorTelepon) {
        super(nama);
        this.nomorTelepon = nomorTelepon;
    }

    // Setter untuk mengisi atau mengubah nilai nomor telepon pelanggan. 
    // Method ini menerima parameter nomorTelepon dan menetapkan nilainya ke variabel nomorTelepon.
    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    // Getter digunakan untuk mengambil nilai attribute.
    // lalu mengembalikan nilai dari method kepada pemanggilnya
    public String getNomorTelepon() {
        return nomorTelepon;
    }

    @Override
    // penerapan method override ini untuk proses sign up pelanggan menggunakan nama dan nomor telepon.
    // Method ini membaca input dari pengguna dan menyimpan data pelanggan ke dalam file members.txt.
    public void signUp() {
        Scanner input = Login.INPUT;

        System.out.println("\n===== SIGN UP PELANGGAN =====");
        System.out.print("Nama         : ");
        setNama(input.nextLine());
        System.out.print("Nomor telepon: ");
        setNomorTelepon(input.nextLine());

        // try digunakan untuk mencoba operasi yang mungkin menghasilkan error.
        try {
            // Membuat FileWriter untuk menulis data ke file teks.
            FileWriter writer = new FileWriter("members.txt", true);
            writer.write(getNomorTelepon() + "|" + getNama() + "|1|0" + System.lineSeparator());
            writer.close();
            System.out.println("Data pelanggan berhasil disimpan.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data pelanggan.");
        }
    }

    @Override
    // Method public ini untuk proses sign in pelanggan menggunakan nomor telepon.
    // Method ini membaca data dari file members.txt dan mencari data pelanggan berdasarkan nomor telepon.
    public void signIn() {
        Scanner input = Login.INPUT;

        System.out.println("\n===== SIGN IN PELANGGAN =====");
        System.out.print("Nomor telepon: ");
        String phone = input.nextLine();

        // try digunakan untuk mencoba operasi yang mungkin menghasilkan error.
        try {
            // Membuat object File untuk mengakses atau memeriksa file.
            File myObj = new File("members.txt");
            // Membuat Scanner untuk membaca input atau isi file.
            Scanner reader = new Scanner(myObj);
            boolean found = false;

            // Perulangan while terus dilakukan selama kondisi bernilai true 
            // agar dapat membaca setiap baris dalam file members.txt.
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] str = data.split("\\|");

                // if untuk membuat keputusan berdasarkan kondisi.
                if (str.length >= 4 && str[0].equals(phone)) {
                    setNomorTelepon(str[0]);
                    setNama(str[1]);
                    found = true;
                    break;
                }
            }
            // Menutup Scanner setelah selesai membaca file.
            reader.close();

            // if  untuk membuat keputusan berdasarkan kondisi data pelanggan ditemukan atau tidak.
            if (found) {
                System.out.println("Data pelanggan ditemukan.");
            } else {
                System.out.println("Data pelanggan tidak ditemukan.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File member belum tersedia.");
        }
    }
}
