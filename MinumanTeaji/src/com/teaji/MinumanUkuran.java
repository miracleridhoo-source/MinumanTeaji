//package tempat class berada, sesuai struktur folder.
package com.teaji;

/*
 * MinumanUkuran.java
 * Turunan dari MinumanTeaji untuk minuman yang memiliki ukuran/harga.
 * Menggunakan constructor overloading untuk minuman dua ukuran dan single size.
 * Method getHarga() dioverride untuk menyesuaikan perhitungan harga.
 */
// Mendefinisikan class/interface utama sesuai nama file.
public class MinumanUkuran extends MinumanTeaji {
    // Variabel private unttuk menyimpan harga minuman berdasarkan ukuran. 
    // Variabel ini bersifat private karena hanya dapat diakses melalui method getter dan setter 
    // yang disediakan oleh class ini.
    private double hargaMedium;
    private double hargaLarge;
    private double hargaSatuan;

    // Method constructor public ini digunakan untuk membuat objek MinumanUkuran dengan nilai default untuk harga minuman.
    // Method ini memanggil constructor dari class induk MinumanTeaji menggunakan super()
    public MinumanUkuran() {
        super();
        hargaMedium = 0;
        hargaLarge = 0;
        hargaSatuan = 0;
    }

    // Untuk minuman yang memiliki ukuran Medium dan Large.
    // Method public menjalankan satu tugas tertentu sesuai tanggung jawab class.
    public MinumanUkuran(String nama, String kategori, String rekomendasiTopping,
                         double hargaMedium, double hargaLarge) {
        super(nama, kategori, rekomendasiTopping, hargaMedium, hargaLarge);
        this.hargaMedium = hargaMedium;
        this.hargaLarge = hargaLarge;
        hargaSatuan = 0;
    }

    // Untuk minuman yang hanya memiliki satu ukuran.
    // Method public menjalankan satu tugas tertentu sesuai tanggung jawab class.
    public MinumanUkuran(String nama, String kategori, double hargaSatuan) {
        super(nama, kategori, "", 0, 0);
        this.hargaMedium = 0;
        this.hargaLarge = 0;
        this.hargaSatuan = hargaSatuan;
    }

    // Setter dibuat untuk mengatur nilai harga berdasarkan ukuran.
    public void setHargaMedium(double hargaMedium) {
        this.hargaMedium = hargaMedium;
        super.setHargaMedium(hargaMedium);
    }
    public void setHargaLarge(double hargaLarge) {
        this.hargaLarge = hargaLarge;
        super.setHargaLarge(hargaLarge);
    }
    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    // Getter untuk mengambil nilai harga berdasarkan ukuran.
    //lalu return mengembalikan nilai dari method kepada pemanggilnya.
    public double getHargaMedium() {
        return hargaMedium;
    }
    public double getHargaLarge() {
        return hargaLarge;
    }
    public double getHargaSatuan() {
        return hargaSatuan;
    }

    @Override
    // Getter utk mengambil nilai harga berdasarkan ukuran yang dipilih.
    // Parameter ukuran adalah string yang menunjukkan ukuran minuman yang dipilih, misalnya "Medium
    // atau "Large". Method ini mengembalikan harga minuman sesuai dengan ukuran yang dipilih.
    public double getHarga(String ukuran) {
        // if statement digunakan untuk memeriksa ukuran minuman yang dipilih.
        //lalu return mengembalikan nilai dari method kepada pemanggilnya.
        if (hargaSatuan > 0) {
            return hargaSatuan;
        }

        // if statement untuk membuat keputusan berdasarkan kondisi ukuran minuman yang dipilih.
        if (ukuran.equalsIgnoreCase("Medium")) {
            return hargaMedium;
        }

        // return untuk mengembalikan nilai hargaLarge jika ukuran bukan "Medium".
        return hargaLarge;
    }
}
