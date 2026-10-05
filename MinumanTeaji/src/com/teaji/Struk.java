// package ini agar interface berada dalam package com.teaji.
// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

// Interface Struk adalah 'aturan' yang harus diikuti class yang mau jadi struk (misalnya Order).
// Interface hanya berisi nama method tanpa isi, jadi class yang mengimplementasikannya wajib menulis isi method-nya sendiri.
// Kenapa? supaya semua class struk pasti punya method yang sama (getOrder, setOrder, cetak, paint).
public interface Struk {
    // Method ini dipakai untuk mengambil data Order yang ada di struk.
    // Tidak ada isinya karena hanya deklarasi, isinya ditulis di class yang mengimplementasikan.
    Order getOrder();
    // Method ini dipakai untuk mengisi atau mengganti data Order pada struk.
    // Parameter order adalah pesanan yang mau dimasukkan ke struk.
    void setOrder(Order order);
    // Method ini dipakai untuk mencetak struk ke layar (console).
    // void artinya method ini tidak mengembalikan nilai apa-apa.
    void cetak();
    // Method ini dipakai untuk menampilkan struk dalam bentuk window (tampilan gambar/GUI).
    void paint();
}