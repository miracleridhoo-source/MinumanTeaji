//package untuk mendefinisikan package tempat class/interface berada. Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

/*
 * OrderItem.java
 * Class untuk menyimpan satu item pesanan.
 *
 * Customisasi tidak menggunakan class Customisasi.java.
 * Customisasi disimpan langsung menggunakan ArrayList<String>.
 */

import java.util.ArrayList;//library java untuk menggunakan ArrayList.

public class OrderItem {// Mendefinisikan class/interface utama sesuai nama file.

    // Menyimpan object minuman yang dipesan.
    private MinumanUkuran minuman;

    // Menyimpan jumlah minuman.
    private int jumlah;

    // ArrayList untuk menyimpan customisasi minuman.
    // Urutan data:
    // index 0 = ukuran
    // index 1 = sugar level
    // index 2 = honey level
    // index 3 = ice level
    private ArrayList<String> customisasi;

    // ArrayList untuk menyimpan topping.
    private ArrayList<Topping> toppingList;

    // ArrayList untuk menyimpan status topping.
    // true  = topping gratis
    // false = topping berbayar
    private ArrayList<Boolean> toppingGratisList;

    // Menyimpan status apakah item merupakan reward.
    private boolean rewardItem;

    // Menyimpan keterangan reward.
    private String rewardLabel;


    // Constructor.
    public OrderItem() {

        // Membuat ArrayList customisasi.
        customisasi = new ArrayList<String>();

        // Membuat ArrayList topping.
        toppingList = new ArrayList<Topping>();

        // Membuat ArrayList status topping gratis.
        toppingGratisList = new ArrayList<Boolean>();

        // Nilai awal item bukan reward.
        rewardItem = false;

        // Nilai awal keterangan reward kosong.
        rewardLabel = "";
    }


    // Setter untuk menyimpan minuman.
    public void setMinuman(MinumanUkuran minuman) {
        this.minuman = minuman;
    }


    // Getter untuk mengambil minuman.
    public MinumanUkuran getMinuman() {
        return minuman;
    }


    // Setter untuk menyimpan jumlah minuman.
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }


    // Getter untuk mengambil jumlah minuman.
    public int getJumlah() {
        return jumlah;
    }


    // Getter untuk mengambil ArrayList customisasi.
    public ArrayList<String> getCustomisasi() {
        return customisasi;
    }


    // Method untuk menambahkan satu customisasi
    // ke dalam ArrayList customisasi.
    public void addCustomisasi(String data) {
        customisasi.add(data);
    }


    // Method untuk menambahkan topping.
    //
    // Parameter gratis digunakan untuk menentukan
    // apakah topping tersebut merupakan reward gratis.
    public void addTopping(Topping topping, boolean gratis) {

        // Jika topping tidak kosong, topping disimpan.
        if (topping != null) {

            // Menambahkan topping ke ArrayList.
            toppingList.add(topping);

            // Menyimpan status gratis/berbayar.
            toppingGratisList.add(gratis);
        }
    }


    // Getter untuk mengambil daftar topping.
    public ArrayList<Topping> getToppingList() {
        return toppingList;
    }


    // Method untuk menentukan apakah item merupakan reward.
    public void setRewardItem(boolean rewardItem) {
        this.rewardItem = rewardItem;
    }


    // Getter untuk mengambil status reward.
    public boolean isRewardItem() {
        return rewardItem;
    }


    // Setter untuk menyimpan keterangan reward.
    public void setRewardLabel(String rewardLabel) {
        this.rewardLabel = rewardLabel;
    }


    // Getter untuk mengambil keterangan reward.
    public String getRewardLabel() {
        return rewardLabel;
    }


    /*
     * Method getHarga digunakan oleh class Order
     * untuk menghitung subtotal.
     *
     * Harga minuman ditentukan berdasarkan ukuran
     * yang tersimpan pada ArrayList customisasi.
     */
    public double getHarga() {

        // Jika minuman belum dipilih,
        // harga item adalah 0.
        if (minuman == null) {
            return 0;
        }

        // Jika customisasi belum memiliki ukuran,
        // harga item adalah 0.
        if (customisasi.size() == 0) {
            return 0;
        }

        // Ukuran disimpan pada index pertama.
        String ukuran = customisasi.get(0);

        // Mengambil harga minuman berdasarkan ukuran.
        double hargaMinuman = minuman.getHarga(ukuran);

        // Menghitung harga minuman berdasarkan jumlah.
        double totalHarga = hargaMinuman * jumlah;

        // Menghitung harga setiap topping.
        for (int i = 0; i < toppingList.size(); i++) {

            // Topping hanya ditambahkan ke harga
            // jika topping tersebut bukan reward gratis.
            if (!toppingGratisList.get(i)) {

                totalHarga = totalHarga
                        + toppingList.get(i).getHarga();
            }
        }

        // Mengembalikan total harga item.
        //
        // Jika item merupakan reward, harga dasar
        // tetap dikembalikan karena class Order
        // akan mengurangi harga reward menggunakan discount.
        return totalHarga;
    }


    /*
     * Method getDetail digunakan untuk membuat
     * informasi satu item pada struk.
     */
    public String getDetail() {

        // Jika minuman belum dipilih,
        // kembalikan teks kosong.
        if (minuman == null) {
            return "";
        }

        // Membuat String untuk menyimpan detail item.
        String detail = "";

        // Menambahkan nama minuman.
        detail = detail + minuman.getNama();

        // Menambahkan ukuran jika tersedia.
        if (customisasi.size() > 0) {
            detail = detail + " | Size: " + customisasi.get(0);
        }

        // Menambahkan Sugar Level jika tersedia.
        if (customisasi.size() > 1) {

            String sugar = customisasi.get(1);

            if (!sugar.equals("-") && !sugar.equals("")) {
                detail = detail + " | Sugar: " + sugar;
            }
        }

        // Menambahkan Honey Level jika tersedia.
        if (customisasi.size() > 2) {

            String honey = customisasi.get(2);

            if (!honey.equals("-") && !honey.equals("")) {
                detail = detail + " | Honey: " + honey;
            }
        }

        // Menambahkan Ice Level jika tersedia.
        if (customisasi.size() > 3) {
            detail = detail + " | Ice: " + customisasi.get(3);
        }

        // Menambahkan jumlah.
        detail = detail + " | Qty: " + jumlah;

        // Jika terdapat topping,
        // tampilkan semua topping yang dipilih.
        if (toppingList.size() > 0) {

            detail = detail + " | Topping: ";

            for (int i = 0; i < toppingList.size(); i++) {

                // Menambahkan nama topping.
                detail = detail + toppingList.get(i).getNama();

                // Menampilkan keterangan FREE
                // jika topping berasal dari reward.
                if (toppingGratisList.get(i)) {
                    detail = detail + " (FREE)";
                }

                // Memberikan tanda koma jika masih ada
                // topping berikutnya.
                if (i < toppingList.size() - 1) {
                    detail = detail + ", ";
                }
            }
        }

        // Jika item merupakan reward,
        // tambahkan keterangan reward.
        if (rewardItem && !rewardLabel.equals("")) {
            detail = detail + " | Reward: " + rewardLabel;
        }

        // Mengembalikan detail item.
        return detail;
    }
}
