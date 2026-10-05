// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;
/*
 * Topping.java
 * Class topping yang merupakan turunan dari MinumanTeaji.
 * Topping memiliki nama dan harga sendiri.
 */
// Mendefinisikan class Topping sesuai nama file.
// 'extends MinumanTeaji' artinya Topping adalah turunan (inheritance) dari MinumanTeaji,
// jadi Topping otomatis punya nama, kategori, dan method lain milik MinumanTeaji.
public class Topping extends MinumanTeaji {
    // Variabel private ini digunakan untuk menyimpan harga topping.
    // Diatur private agar tidak bisa diubah langsung dari luar class, hanya lewat getter dan setter (encapsulation).
    // Tipe double dipakai karena harga bisa saja berupa angka desimal.
    private double harga;

    // Constructor public ini digunakan untuk membuat objek Topping tanpa parameter.
    // Kenapa? supaya objek Topping tetap bisa dibuat dengan nilai awal default, yaitu harga 0.
    public Topping() {
        // super() memanggil constructor kosong milik class induk (MinumanTeaji)
        // supaya bagian data yang diwarisi dari induk ikut disiapkan dulu.
        super();
        harga = 0;
    }

    // Constructor public ini digunakan untuk membuat objek Topping dengan nama dan harga yang langsung ditentukan.
    // Contohnya: new Topping("Pearl", 8000).
    public Topping(String nama, double harga) {
        // super(...) mengirim data ke constructor class induk (MinumanTeaji).
        // Nama diisi sesuai parameter, kategori diisi 'Topping', sisanya dikosongkan/0
        // karena harga topping disimpan sendiri di class ini, bukan di class induk.
        super(nama, "Topping", "", 0, 0);
        // this.harga adalah variabel milik objek, sedangkan harga di kanan adalah parameter.
        // Jadi baris ini menyimpan harga dari parameter ke dalam objek.
        this.harga = harga;
    }

    // Setter ini digunakan untuk mengisi atau mengubah harga topping.
    // Method ini menerima parameter harga lalu menyimpannya ke variabel harga.
    public void setHarga(double harga) {
        this.harga = harga;
    }

    // Getter ini digunakan untuk mengambil harga topping.
    // Method ini mengembalikan nilai dari variabel harga kepada yang memanggilnya.
    public double getHarga() {
        // return mengembalikan nilai dari method kepada pemanggilnya.
        return harga;
    }
}