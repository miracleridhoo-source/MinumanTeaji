//package ini agar class/interface berada dalam package com.teaji. 
// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

/*
 * Person.java
 * Class induk yang menyimpan data umum berupa nama.
 * Seller dan Pelanggan dapat menggunakan inheritance dari class ini.
 */
// Mendefinisikan class/interface utama sesuai nama file.
public class Person {
    // Variabel private ini digunakan untuk menyimpan nama orang.
    // Variabel ini diatur private agar hanya dapat diakses melalui method getter dan setter yang disediakan oleh class ini.
    private String nama;

    // methode constructor public ini digunakan untuk membuat objek Person dengan nilai default untuk nama.
    // Method ini dipanggil saat objek Person dibuat tanpa parameter.
    // kenapa? karena constructor ini tidak memiliki parameter, 
    // sehingga saat objek Person dibuat tanpa memberikan nilai untuk nama, constructor ini akan dipanggil dan menetapkan nilai default (string kosong) ke variabel nama.
    public Person() {
        nama = "";
    }

    // Method public ini adalah constructor yang digunakan untuk membuat objek Person dengan nilai yang ditentukan untuk nama.
    // Method ini dipanggil saat objek Person dibuat dengan memberikan nilai untuk nama.
    public Person(String nama) {
        this.nama = nama;
    }

    // Setter ini digunakan untuk mengisi atau mengubah nilai nama orang.
    // Method ini menerima parameter nama dan menetapkan nilainya ke variabel nama.
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter ini digunakan untuk mengambil nilai nama orang.
    // kemudian mengembalikan nilai dari variabel nama.
    public String getNama() {
        return nama;
    }
}
