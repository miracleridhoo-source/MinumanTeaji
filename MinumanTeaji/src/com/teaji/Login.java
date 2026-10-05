//package untuk tau tempat class/interface berada. Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;
/*
Login.java merupakan interface yang mendefinisikan metode untuk proses login dan pendaftaran pengguna. 
Interface ini menyediakan kontrak bagi kelas yang mengimplementasikannya 
untuk menyediakan fungsional signUp() dan signIn().
*/
// Library java untuk membaca input dari pengguna
import java.util.Scanner;

// Nama interface Login yang mendefinisikan metode untuk proses login dan pendaftaran pengguna.
public interface Login {
    // Interface dapat memiliki variabel, tetapi variabel tersebut bersifat akhir (final) dan statis (static) secara default. 
    // Variabel INPUT adalah sebuah Scanner yang digunakan untuk membaca input dari pengguna.
    // Membuat Scanner untuk membaca input atau isi file.
    Scanner INPUT = new Scanner(System.in);

    //void signUp() dan void signIn() adalah metode abstrak yang harus diimplementasikan oleh kelas yang mengimplementasikan interface Login.
    void signUp();
    void signIn(); 
}
