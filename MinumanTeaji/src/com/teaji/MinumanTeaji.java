//package untuk mengetahui package tempat class/interface berada. 
// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

/*
 * MinumanTeaji.java
 * Class induk untuk data minuman Teaji.
 * Menyimpan nama, kategori, rekomendasi topping, dan harga.
 */
// Mendefinisikan class/interface utama sesuai nama file.
public class MinumanTeaji {
    // Variabel private ini digunakan untuk menyimpan data 
    // minuman Teaji, seperti nama, kategori, rekomendasi topping, dan harga. 
    // Variabel ini bersifat private karena supaaya hanya dapat diakses melalui method getter dan setter yang disediakan oleh class ini.
    private String nama;
    private String kategori;
    private String rekomendasiTopping;
    private double hargaMedium;
    private double hargaLarge;

    // Constructor public ini digunakan untuk membuat objek MinumanTeaji 
    // dengan nilai yang ditentukan untuk nama, kategori, rekomendasi topping, dan harga.
    public MinumanTeaji() {
        nama = "";
        kategori = "";
        rekomendasiTopping = "";
        hargaMedium = 0;
        hargaLarge = 0;
    }

    // Method ini digunakan untuk membuat objek MinumanTeaji dengan nilai yang ditentukan untuk nama, kategori, rekomendasi topping, dan harga.
    public MinumanTeaji(String nama, String kategori, String rekomendasiTopping,
                         double hargaMedium, double hargaLarge) {
        this.nama = nama;
        this.kategori = kategori;
        this.rekomendasiTopping = rekomendasiTopping;
        this.hargaMedium = hargaMedium;
        this.hargaLarge = hargaLarge;
    }

    // Setter digunakan untuk mengisi atau mengubah nilai attribute.
    // Method ini digunakan untuk mengatur nilai nama minuman kemudian disimpan ke dalam variabel nama.    
    // this.attribute mengacu pada variabel nama dari objek saat ini, sedangkan nama adalah parameter yang diteruskan ke method.
    // Dengan menggunakan this.attribute, kita dapat membedakan antara variabel instance dan parameter method yang memiliki nama yang sama.
    // Dengan demikian, this.(attribute) = attribute; berarti kita menetapkan nilai parameter nama ke variabel instance nama dari objek saat ini.
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public void setRekomendasiTopping(String rekomendasiTopping) {
        this.rekomendasiTopping = rekomendasiTopping;
    }
    public void setHargaMedium(double hargaMedium) {
        this.hargaMedium = hargaMedium;
    }
    public void setHargaLarge(double hargaLarge) {
        this.hargaLarge = hargaLarge;
    }

    // Getter digunakan untuk mengambil nilai attribute.
    //lalu return mengembalikan nilai dari method kepada pemanggilnya.
    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public String getRekomendasiTopping() {
        return rekomendasiTopping;
    }

    public double getHargaMedium() {
        return hargaMedium;
    }

    public double getHargaLarge() {
        return hargaLarge;
    }

    // Method ini digunakan untuk mengambil harga minuman berdasarkan ukuran yang dipilih.
    // Parameter ukuran adalah string yang menunjukkan ukuran minuman yang dipilih, misalnya "Medium" atau "Large".
    // Method ini mengembalikan harga minuman sesuai dengan ukuran yang dipilih.
    // Jika ukuran adalah "Medium", method ini mengembalikan hargaMedium,
    public double getHarga(String ukuran) {
        // if elsestatement digunakan untuk memeriksa ukuran minuman yang dipilih.
        // lalu return mengembalikan nilai dari method kepada pemanggilnya.
        if (ukuran.equalsIgnoreCase("Medium")) {
            return hargaMedium;
        } else {
            return hargaLarge;
        }
    }
}
