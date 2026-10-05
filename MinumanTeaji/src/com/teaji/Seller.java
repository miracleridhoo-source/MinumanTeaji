// package ini agar class berada dalam package com.teaji.
// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

/*
 * Seller.java
 * Class untuk kasir Teaji.
 *
 * Seller mewarisi nama dari Person dan mengimplementasikan interface Login.
 * Sign Up menyimpan username/password ke file, sedangkan Sign In membaca
 * file untuk melakukan verifikasi login.
 */
import java.io.File;//library java untuk mengecek/membuka file.
import java.io.FileWriter;//library java untuk menulis data ke file teks.
import java.io.FileNotFoundException;//library java untuk Exception saat file tidak ditemukan.
import java.io.IOException;//library java untuk Exception untuk kesalahan operasi file.
import java.util.Scanner;//library java untuk membaca input dari keyboard atau file teks.


// Mendefinisikan class Seller (kasir) sesuai nama file.
// 'extends Person' artinya Seller mewarisi data nama dari class Person (inheritance).
// 'implements Login' artinya Seller wajib punya method signUp() dan signIn() sesuai aturan interface Login.
public class Seller extends Person implements Login {
    // Variabel private ini digunakan untuk menyimpan username kasir.
    // Diatur private agar hanya bisa diakses lewat getter dan setter (encapsulation).
    private String username;
    // Variabel private ini digunakan untuk menyimpan password kasir.
    // Diatur private supaya password tidak bisa diubah/dibaca sembarangan dari luar class.
    private String password;
    // Variabel private ini digunakan untuk menandai apakah kasir sudah berhasil login atau belum.
    // Nilai true artinya sudah login, false artinya belum login.
    private boolean authenticated;

    // Constructor public ini digunakan untuk membuat objek Seller tanpa parameter.
    // Dipanggil saat objek Seller dibuat kosong, misalnya di App.java sebelum kasir sign in.
    public Seller() {
        // super(...) memanggil constructor class induk (Person)
        // untuk mengisi nama dengan nilai default 'Kasir Teaji'.
        super("Kasir Teaji");
        // Username dan password diberi string kosong dulu sebagai nilai awal,
        // authenticated diberi false karena kasir belum login.
        username = "";
        password = "";
        authenticated = false;
    }

    // Constructor public ini digunakan untuk membuat objek Seller dengan nama, username, dan password yang sudah ditentukan.
    // Dipanggil saat data kasir sudah diketahui sejak awal.
    public Seller(String nama, String username, String password) {
        // super(nama) mengirim nama ke constructor class induk (Person) supaya nama tersimpan di sana.
        super(nama);
        this.username = username;
        this.password = password;
        authenticated = false;
    }

    // Setter ini digunakan untuk mengisi atau mengubah username kasir.
    // this.username adalah variabel milik objek, username di kanan adalah parameter.
    public void setUsername(String username) {
        this.username = username;
    }

    // Setter ini digunakan untuk mengisi atau mengubah password kasir.
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter ini digunakan untuk mengambil username kasir.
    // Method ini mengembalikan nilai dari variabel username.
    public String getUsername() {
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return username;
    }

    // Getter ini digunakan untuk mengambil password kasir.
    // Method ini mengembalikan nilai dari variabel password.
    public String getPassword() {
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return password;
    }

    // Getter ini digunakan untuk mengecek status login kasir.
    // Mengembalikan true jika sudah login dan false jika belum.
    // Dipakai di App.java untuk menentukan apakah program boleh lanjut.
    public boolean isAuthenticated() {
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return authenticated;
    }

    // Sign up kasir dan simpan akun ke file.
    // @Override menandakan method ini menjalankan aturan dari interface Login.
    // Method signUp() dipakai untuk mendaftarkan akun kasir baru.
    @Override
    public void signUp() {
        // Mengambil Scanner yang sudah disediakan di interface Login.
        // Kenapa? supaya seluruh program memakai satu Scanner yang sama untuk membaca keyboard.
        Scanner input = Login.INPUT;

        // Menampilkan judul lalu meminta kasir mengisi nama, username, dan password.
        // nextLine() membaca satu baris ketikan sampai tombol Enter ditekan.
        System.out.println("\n===== SIGN UP KASIR =====");
        System.out.print("Nama kasir     : ");
        String nama = input.nextLine();
        System.out.print("Username       : ");
        String user = input.nextLine();
        System.out.print("Password       : ");
        String pass = input.nextLine();

        // try dipakai karena menulis ke file bisa saja gagal (misalnya file tidak bisa dibuka).
        // Kalau gagal, program tidak langsung berhenti tapi pindah ke catch.
        try {
            // Membuat FileWriter untuk menulis ke file sellers.txt.
            // Parameter true artinya mode append: data baru ditambahkan di bagian bawah, data lama tidak terhapus.
            FileWriter writer = new FileWriter("sellers.txt", true);
            // Menulis satu akun ke file dengan format username|password|nama, lalu pindah baris.
            // Tanda | dipakai sebagai pemisah supaya nanti mudah dipecah lagi saat sign in.
            writer.write(user + "|" + pass + "|" + nama + System.lineSeparator());
            // Menutup file setelah selesai menulis supaya data benar-benar tersimpan dan file tidak terkunci.
            writer.close();

            // Menyimpan data akun yang baru dibuat ke objek Seller ini juga.
            // setNama() berasal dari class Person, sedangkan setUsername() dan setPassword() milik Seller.
            setNama(nama);
            setUsername(user);
            setPassword(pass);

            System.out.println("Akun kasir berhasil dibuat.");
        // catch ini jalan kalau terjadi error saat menulis file, lalu menampilkan pesan gagal.
        } catch (IOException e) {
            System.out.println("Gagal menyimpan akun kasir.");
        }
    }

    // Sign in kasir dengan membaca file akun.
    // @Override menandakan method ini menjalankan aturan dari interface Login.
    // Method signIn() dipakai kasir untuk masuk dengan mencocokkan data di file.
    @Override
    public void signIn() {
        // Memakai Scanner bersama dari interface Login untuk membaca input username dan password.
        Scanner input = Login.INPUT;

        System.out.println("\n===== SIGN IN KASIR =====");
        System.out.print("Username : ");
        String user = input.nextLine();
        System.out.print("Password : ");
        String pass = input.nextLine();

        // Status login direset ke false dulu.
        // Kenapa? supaya kalau login sebelumnya berhasil lalu sign in ulang gagal, statusnya tidak tertinggal true.
        authenticated = false;

        // try dipakai karena membaca file bisa gagal, misalnya file sellers.txt belum ada.
        try {
            // Membuat objek File yang menunjuk ke sellers.txt (tempat akun kasir disimpan).
            File myObj = new File("sellers.txt");
            // Membuat Scanner untuk membaca isi file baris demi baris.
            Scanner reader = new Scanner(myObj);

            // Perulangan while ini terus berjalan selama file masih punya baris yang belum dibaca.
            // Setiap putaran, satu baris akun diperiksa.
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                // Memecah satu baris menjadi 3 bagian berdasarkan tanda |.
                // str[0] = username, str[1] = password, str[2] = nama kasir.
                // Tanda \\ dipakai karena | punya arti khusus di regex, jadi harus di-escape.
                String[] str = data.split("\\|");

                // Mengecek apakah baris punya minimal 3 bagian dan username serta password-nya sama dengan yang diketik.
                // equals() dipakai untuk membandingkan isi String (bukan ==).
                if (str.length >= 3 && str[0].equals(user) && str[1].equals(pass)) {
                    username = str[0];
                    password = str[1];
                    // Kalau cocok, data akun diambil dari file dan disimpan ke objek,
                    // lalu authenticated diubah true sebagai tanda login berhasil.
                    setNama(str[2]);
                    authenticated = true;
                    // break menghentikan perulangan karena akun sudah ketemu, jadi tidak perlu membaca baris berikutnya.
                    break;
                }
            }

            // Menutup Scanner setelah selesai membaca file.
            reader.close();
        // catch ini jalan kalau file sellers.txt tidak ditemukan, lalu menampilkan pesan.
        } catch (FileNotFoundException e) {
            System.out.println("File akun kasir belum tersedia.");
        }

        // Mengecek hasil login: kalau berhasil tampilkan sambutan dengan nama kasir, kalau tidak tampilkan pesan salah.
        if (authenticated) {
            System.out.println("Login berhasil. Selamat datang, " + getNama() + "!");
        } else {
            System.out.println("Username atau password salah.");
        }
    }
}