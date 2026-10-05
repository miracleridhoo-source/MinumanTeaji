// package ini agar class/interface berada dalam package com.teaji.
// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

/*
 * Member.java
 * Class yang menyimpan data pelanggan yang terdaftar sebagai member,
 * berupa nama, nomor telepon, dan data reward (level dan stamp).
 * Class ini memakai komposisi dengan class Reward, yaitu objek Reward menjadi bagian dari objek Member.
 */
// Mendefinisikan class/interface utama sesuai nama file.
public class Member {

    // Variabel private ini digunakan untuk menyimpan nama member.
    // Variabel ini diatur private agar hanya dapat diakses melalui method getter dan setter yang disediakan oleh class ini.
    private String nama;

    // Variabel private ini digunakan untuk menyimpan nomor telepon member.
    // Tipe datanya String agar angka 0 di depan nomor tidak hilang dan nomor dapat diawali tanda plus (+).
    // Variabel ini diatur private agar hanya dapat diakses melalui method getter dan setter yang disediakan oleh class ini.
    private String nomorTelepon;

    // Variabel private ini digunakan untuk menyimpan data reward milik member.
    // Tipe datanya adalah objek dari class Reward, sehingga member memiliki (has-a) sebuah reward.
    // Variabel ini diatur private agar hanya dapat diakses melalui method getter yang disediakan oleh class ini.
    private Reward reward;

    // Method constructor public ini digunakan untuk membuat objek Member dengan nilai default.
    // Method ini dipanggil saat objek Member dibuat tanpa parameter.
    // kenapa? karena constructor ini tidak memiliki parameter,
    // sehingga nama dan nomor telepon diberi nilai default (string kosong),
    // dan reward dibuat sebagai objek Reward baru agar tidak bernilai null saat digunakan.
    public Member() {
        nama = "";
        nomorTelepon = "";
        reward = new Reward();
    }

    // Method public ini adalah constructor yang digunakan untuk membuat objek Member dengan nilai nama dan nomor telepon yang ditentukan.
    // Method ini dipanggil saat objek Member dibuat dengan memberikan nilai untuk nama dan nomor telepon.
    // Keyword this digunakan untuk membedakan variabel milik objek dengan parameter yang memiliki nama sama.
    // Reward dibuat sebagai objek Reward baru dengan nilai awalnya, karena member baru belum memiliki riwayat reward.
    public Member(String nama, String nomorTelepon) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        reward = new Reward();
    }

    // Method public ini adalah constructor yang digunakan untuk membuat objek Member lengkap dengan data reward.
    // Method ini dipanggil saat objek Member dibuat dengan memberikan nama, nomor telepon, level, dan stamp.
    // Constructor ini berguna untuk member yang sudah pernah memiliki reward sebelumnya.
    public Member(String nama, String nomorTelepon, int level, int stamp) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;

        // Membuat objek Reward baru, kemudian mengisi level dan stamp-nya
        // melalui setter setLevel dan setStamp dengan nilai dari parameter constructor.
        reward = new Reward();
        reward.setLevel(level);
        reward.setStamp(stamp);
    }

    // Setter ini digunakan untuk mengisi atau mengubah nilai nama member.
    // Method ini menerima parameter nama dan menetapkan nilainya ke variabel nama.
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter ini digunakan untuk mengambil nilai nama member.
    // kemudian mengembalikan nilai dari variabel nama.
    public String getNama() {
        return nama;
    }

    // Setter ini digunakan untuk mengisi atau mengubah nilai nomor telepon member.
    // Method ini menerima parameter nomorTelepon dan menetapkan nilainya ke variabel nomorTelepon.
    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    // Getter ini digunakan untuk mengambil nilai nomor telepon member.
    // kemudian mengembalikan nilai dari variabel nomorTelepon.
    public String getNomorTelepon() {
        return nomorTelepon;
    }

    // Getter ini digunakan untuk mengambil objek reward milik member.
    // kemudian mengembalikan objek Reward dari variabel reward,
    // sehingga level dan stamp dapat diakses atau diubah melalui method milik class Reward.
    public Reward getReward() {
        return reward;
    }
}