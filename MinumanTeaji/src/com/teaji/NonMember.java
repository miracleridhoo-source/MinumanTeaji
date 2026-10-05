//package com.teaji tempat class/interface berada. 
// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;
/*
 * NonMember.java
 * Class dasar untuk pelanggan yang belum menjadi member.
 * Menyimpan nama pelanggan dan menyediakan setter/getter.
 */
// Mendefinisikan class/interface utama sesuai nama file.   
public class NonMember {
    // Variabel private ini tuk untuk menyimpan nama pelanggan. 
    // Variabel ini diatur private karena hanya dapat diakses melalui method getter dan setter yang disediakan oleh class ini.
    private String nama;

    // constructor public ini digunakan untuk membuat objek NonMember dengan nilai default untuk nama pelanggan.
    // Method ini dipanggil saat objek NonMember dibuat tanpa parameter.
    public NonMember() {
        nama = "";
    }

    // Method ini digunakan untuk mengatur nilai nama pelanggan kemudian disimpan ke dalam variabel nama
    public NonMember(String nama) {
        this.nama = nama;
    }

    // Setter ini tuk untuk mengatur nilai nama pelanggan. 
    // Method ini menerima parameter nama dan menetapkan nilainya ke variabel nama.
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter utk mengambil nilai nama pelanggan. 
    // Method ini mengembalikan nilai dari variabel nama.
    public String getNama() {
        return nama;
    }
}
