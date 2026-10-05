// Package ini agar class/interface berada dalam package com.teaji.
// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

/*
 * Order.java
 * Class yang menyimpan satu transaksi/order.
 * Menggunakan ArrayList<OrderItem> agar satu order dapat berisi banyak minuman.
 * Class ini juga mengimplementasikan interface Struk untuk mencetak
 * dan menampilkan struk.
 */

import java.awt.Font; // library java untuk mengatur font pada GUI.
import java.io.FileWriter; // library java untuk menulis ke file.
import java.io.IOException; // library java untuk menangani kesalahan input/output.
import java.util.ArrayList; // library java untuk menyimpan banyak item dalam satu order.
import javax.swing.JFrame; // library java untuk membuat window GUI.
import javax.swing.JScrollPane; // library java untuk membuat scrollable area pada GUI.
import javax.swing.JTextArea; // library java untuk menampilkan teks dalam GUI.

// Mendefinisikan class utama sesuai nama file.
// Kata implements Struk berarti class Order wajib menyediakan semua method yang ada pada interface Struk.
public class Order implements Struk {

    // Variabel private ini digunakan untuk menyimpan data transaksi/order.
    // Variabel ini diatur private agar hanya dapat diakses melalui
    // method getter dan setter yang disediakan oleh class ini.

    // Menyimpan daftar item minuman dalam order.
    private ArrayList<OrderItem> itemsArrayList;
    // Menyimpan objek member jika pelanggan adalah member.
    private Member member;
    // Menyimpan nama pelanggan.
    private String namaPelanggan;
    // Menyimpan nomor telepon pelanggan.
    private String nomor;
    // Menyimpan nomor antrean.
    private int queueNo;
    // Menyimpan tanggal transaksi.
    private String tanggal;
    // Menyimpan nomor struk.
    private String receiptNo;
    // Menyimpan ID order.
    private String orderID;
    // Menyimpan informasi cabang pengambilan pesanan.
    private String collectedBy;
    // Menyimpan tipe order.
    private String typeOrder;
    // Menyimpan jumlah harga seluruh item sebelum potongan.
    private double subtotal;
    // Menyimpan jumlah harga akhir setelah potongan.
    private double total;
    // Menyimpan jumlah potongan dari reward.
    private double discount;
    // Menyimpan keterangan reward yang digunakan.
    private String rewardSummary;
    // Menyimpan jumlah uang yang dibayarkan.
    private double pembayaran;
    // Menyimpan metode pembayaran.
    private String metodePembayaran;


    // Method constructor public ini digunakan untuk membuat objek Order dengan nilai default.
    // Method ini dipanggil saat objek Order dibuat tanpa parameter.
    // kenapa? karena constructor ini tidak memiliki parameter,
    // sehingga saat objek Order dibuat tanpa memberikan nilai apa pun, constructor ini akan dipanggil
    // dan menetapkan nilai awal pada setiap variabel.
    public Order() {

        // Membuat ArrayList baru agar dapat menyimpan item pesanan.
        // kenapa? agar itemsArrayList tidak bernilai null saat addItem dipanggil.
        itemsArrayList = new ArrayList<OrderItem>();

        // Nilai awal member adalah null karena pelanggan
        // belum tentu merupakan member.
        member = null;

        // Memberikan nilai awal berupa string kosong atau 0 untuk data transaksi
        // karena data tersebut belum diisi.
        namaPelanggan = "";
        nomor = "";
        queueNo = 0;
        tanggal = "";
        receiptNo = "";
        orderID = "";
        collectedBy = "";
        typeOrder = "";

        // Nilai awal perhitungan harga ditetapkan 0 karena belum ada item yang dihitung.
        subtotal = 0;
        total = 0;
        discount = 0;

        // Nilai awal keterangan reward ditetapkan string kosong karena belum ada reward yang digunakan.
        rewardSummary = "";

        // Nilai awal pembayaran ditetapkan 0 dan metode pembayaran string kosong karena belum ada pembayaran.
        pembayaran = 0;
        metodePembayaran = "";
    }


    // Method public ini adalah constructor yang digunakan untuk membuat objek Order dengan nilai yang ditentukan
    // untuk daftar item dan member.
    // Method ini dipanggil saat objek Order dibuat dengan memberikan kedua nilai tersebut.
    public Order(ArrayList<OrderItem> items, Member member) {

        // Menyimpan ArrayList item yang diberikan ke variabel itemsArrayList.
        itemsArrayList = items;

        // Menyimpan data member yang diberikan ke variabel member milik objek.
        this.member = member;
    }


    // Setter ini digunakan untuk mengisi atau mengubah data member.
    // Method ini menerima parameter member dan menetapkan nilainya ke variabel member.
    public void setMember(Member member) {
        this.member = member;
    }

    // Setter ini digunakan untuk mengisi atau mengubah nama pelanggan.
    // Method ini menerima parameter namaPelanggan dan menetapkan nilainya ke variabel namaPelanggan.
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    // Setter ini digunakan untuk mengisi atau mengubah nomor telepon pelanggan.
    // Method ini menerima parameter nomor dan menetapkan nilainya ke variabel nomor.
    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    // Setter ini digunakan untuk mengisi atau mengubah nomor antrean.
    // Method ini menerima parameter queueNo dan menetapkan nilainya ke variabel queueNo.
    public void setQueueNo(int queueNo) {
        this.queueNo = queueNo;
    }

    // Setter ini digunakan untuk mengisi atau mengubah tanggal transaksi.
    // Method ini menerima parameter tanggal dan menetapkan nilainya ke variabel tanggal.
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    // Setter ini digunakan untuk mengisi atau mengubah nomor struk.
    // Method ini menerima parameter receiptNo dan menetapkan nilainya ke variabel receiptNo.
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    // Setter ini digunakan untuk mengisi atau mengubah ID order.
    // Method ini menerima parameter orderID dan menetapkan nilainya ke variabel orderID.
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    // Setter ini digunakan untuk mengisi atau mengubah informasi cabang pengambilan pesanan.
    // Method ini menerima parameter collectedBy dan menetapkan nilainya ke variabel collectedBy.
    public void setCollectedBy(String collectedBy) {
        this.collectedBy = collectedBy;
    }

    // Setter ini digunakan untuk mengisi atau mengubah tipe order.
    // Method ini menerima parameter typeOrder dan menetapkan nilainya ke variabel typeOrder.
    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }

    // Setter ini digunakan untuk mengisi atau mengubah nilai potongan reward.
    // Method ini menerima parameter discount dan menetapkan nilainya ke variabel discount.
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // Method public ini digunakan untuk menambahkan potongan reward ke potongan yang sudah ada.
    // kenapa? karena satu order dapat memakai lebih dari satu reward,
    // sehingga nilai discount lama dijumlahkan dengan discount baru, bukan diganti.
    public void addDiscount(double discount) {
        this.discount = this.discount + discount;
    }

    // Setter ini digunakan untuk mengisi atau mengubah keterangan reward.
    // Method ini menerima parameter rewardSummary dan menetapkan nilainya ke variabel rewardSummary.
    public void setRewardSummary(String rewardSummary) {
        this.rewardSummary = rewardSummary;
    }


    // Method public ini digunakan untuk menambahkan keterangan reward
    // ke dalam ringkasan reward.
    // Method ini menerima parameter rewardSummary, yaitu keterangan reward yang baru.
    public void addRewardSummary(String rewardSummary) {

        // Jika belum ada keterangan reward,
        // langsung simpan keterangan tersebut.
        if (this.rewardSummary.equals("")) {

            this.rewardSummary = rewardSummary;

        } else {

            // Jika sudah ada reward,
            // tambahkan reward baru menggunakan pemisah.
            // Tanda | digunakan agar setiap reward mudah dibedakan saat ditampilkan.
            this.rewardSummary = this.rewardSummary
                    + " | " + rewardSummary;
        }
    }


    // Getter ini digunakan untuk mengambil nilai potongan reward.
    // kemudian mengembalikan nilai dari variabel discount.
    public double getDiscount() {
        return discount;
    }

    // Getter ini digunakan untuk mengambil keterangan reward.
    // kemudian mengembalikan nilai dari variabel rewardSummary.
    public String getRewardSummary() {
        return rewardSummary;
    }

    // Setter ini digunakan untuk mengisi atau mengubah jumlah pembayaran.
    // Method ini menerima parameter pembayaran dan menetapkan nilainya ke variabel pembayaran.
    public void setPembayaran(double pembayaran) {
        this.pembayaran = pembayaran;
    }

    // Setter ini digunakan untuk mengisi atau mengubah metode pembayaran.
    // Method ini menerima parameter metodePembayaran dan menetapkan nilainya ke variabel metodePembayaran.
    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    // Getter ini digunakan untuk mengambil daftar item dalam order.
    // kemudian mengembalikan nilai dari variabel itemsArrayList.
    public ArrayList<OrderItem> getItemsArrayList() {
        return itemsArrayList;
    }

    // Getter ini digunakan untuk mengambil objek member.
    // kemudian mengembalikan nilai dari variabel member.
    public Member getMember() {
        return member;
    }

    // Getter ini digunakan untuk mengambil nama pelanggan.
    // kemudian mengembalikan nilai dari variabel namaPelanggan.
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    // Getter ini digunakan untuk mengambil nomor telepon pelanggan.
    // kemudian mengembalikan nilai dari variabel nomor.
    public String getNomor() {
        return nomor;
    }

    // Getter ini digunakan untuk mengambil nomor antrean.
    // kemudian mengembalikan nilai dari variabel queueNo.
    public int getQueueNo() {
        return queueNo;
    }

    // Getter ini digunakan untuk mengambil tanggal transaksi.
    // kemudian mengembalikan nilai dari variabel tanggal.
    public String getTanggal() {
        return tanggal;
    }

    // Getter ini digunakan untuk mengambil nomor struk.
    // kemudian mengembalikan nilai dari variabel receiptNo.
    public String getReceiptNo() {
        return receiptNo;
    }

    // Getter ini digunakan untuk mengambil ID order.
    // kemudian mengembalikan nilai dari variabel orderID.
    public String getOrderID() {
        return orderID;
    }

    // Getter ini digunakan untuk mengambil informasi cabang pengambilan pesanan.
    // kemudian mengembalikan nilai dari variabel collectedBy.
    public String getCollectedBy() {
        return collectedBy;
    }

    // Getter ini digunakan untuk mengambil tipe order.
    // kemudian mengembalikan nilai dari variabel typeOrder.
    public String getTypeOrder() {
        return typeOrder;
    }

    // Getter ini digunakan untuk mengambil nilai subtotal.
    // kemudian mengembalikan nilai dari variabel subtotal.
    public double getSubtotal() {
        return subtotal;
    }

    // Getter ini digunakan untuk mengambil nilai total.
    // kemudian mengembalikan nilai dari variabel total.
    public double getTotal() {
        return total;
    }

    // Getter ini digunakan untuk mengambil jumlah pembayaran.
    // kemudian mengembalikan nilai dari variabel pembayaran.
    public double getPembayaran() {
        return pembayaran;
    }

    // Getter ini digunakan untuk mengambil metode pembayaran.
    // kemudian mengembalikan nilai dari variabel metodePembayaran.
    public String getMetodePembayaran() {
        return metodePembayaran;
    }


    // Method public ini digunakan untuk menambahkan satu item ke dalam order.
    // Method ini menerima parameter item, yaitu objek OrderItem yang akan ditambahkan.
    public void addItem(OrderItem item) {

        // Memastikan item tidak kosong sebelum ditambahkan.
        // kenapa? agar tidak ada nilai null di dalam ArrayList yang dapat menimbulkan error saat dihitung atau dicetak.
        if (item != null) {

            itemsArrayList.add(item);
        }
    }


    // Method public ini digunakan untuk menghitung subtotal dari semua item dalam order.
    // Hasil perhitungan disimpan ke variabel subtotal.
    public void hitungSubtotal() {

        // Mengatur subtotal kembali menjadi 0 sebelum menghitung.
        // kenapa? agar hasil perhitungan tidak menumpuk jika method ini dipanggil berulang kali.
        subtotal = 0;

        // Mengambil setiap OrderItem dari ArrayList menggunakan perulangan for-each.
        for (OrderItem item : itemsArrayList) {

            // Menambahkan harga setiap item ke subtotal.
            subtotal = subtotal + item.getHarga();
        }
    }


    // Method public ini digunakan untuk menghitung total pembayaran.
    // Hasil perhitungan disimpan ke variabel total.
    public void hitungTotal() {

        // Menghitung subtotal terlebih dahulu.
        // kenapa? karena total dihitung dari subtotal yang terbaru.
        hitungSubtotal();

        // Total = subtotal dikurangi potongan reward.
        total = subtotal - discount;

        // Jika potongan lebih besar dari subtotal,
        // total tidak boleh menjadi negatif.
        if (total < 0) {

            total = 0;
        }
    }


    // Method public ini digunakan untuk menghitung jumlah kembalian.
    // Method ini mengembalikan nilai double berupa selisih pembayaran dan total.
    public double hitungJumlahKembalian() {

        // Kembalian = uang pembayaran dikurangi total.
        return pembayaran - total;
    }


    // Method public ini digunakan untuk menghitung jumlah item yang ada dalam order.
    // Method ini mengembalikan jumlah item dalam bentuk String.
    public String hitungJumlahItem() {

        // String.valueOf digunakan untuk mengubah angka ukuran ArrayList menjadi String.
        return String.valueOf(itemsArrayList.size());
    }


    // Method public ini digunakan untuk menampilkan informasi member dalam bentuk teks.
    // Method ini mengembalikan nilai berupa String.
    public String getMemberText() {

        // Jika member null berarti pelanggan bukan member.
        if (member == null) {

            return "Non Member";
        }

        /*
         * Level dan stamp sekarang berada di dalam objek Reward.
         *
         * Jadi tidak lagi:
         * member.getLevel()
         * member.getStamp()
         *
         * Tetapi:
         * member.getReward().getLevel()
         * member.getReward().getStamp()
         */

        // Menggabungkan nama, nomor telepon, level, dan stamp member menjadi satu teks.
        return member.getNama()
                + " | " + member.getNomorTelepon()
                + " | Level " + member.getReward().getLevel()
                + " | Stamp " + member.getReward().getStamp();
    }


    // Method public ini digunakan untuk membuat teks struk.
    // Method ini mengembalikan seluruh isi struk dalam bentuk String.
    public String getReceiptText() {

        // Variabel untuk menyimpan seluruh teks struk.
        // Teks ditambahkan sedikit demi sedikit ke variabel ini.
        String result = "";

        // Bagian ini menambahkan garis pembuka dan judul struk.
        result = result
                + "====================================================\n";

        result = result
                + "                    TEAJI\n";

        result = result
                + "====================================================\n";

        // Bagian ini menambahkan informasi utama transaksi.
        result = result
                + "Collected by : " + collectedBy + "\n";

        result = result
                + "Tanggal      : " + tanggal + "\n";

        result = result
                + "Queue No     : " + queueNo + "\n";

        result = result
                + "Receipt No   : " + receiptNo + "\n";

        result = result
                + "Order ID     : " + orderID + "\n";

        result = result
                + "Customer     : " + namaPelanggan + "\n";

        result = result
                + "Member       : " + getMemberText() + "\n";

        result = result
                + "Order Type   : " + typeOrder + "\n";

        result = result
                + "----------------------------------------------------\n";

        result = result
                + "ORDER\n";


        // Variabel nomor item untuk memberi nomor
        // pada setiap pesanan.
        int nomorItem = 1;


        // Menampilkan semua item dalam order menggunakan perulangan for-each.
        for (OrderItem item : itemsArrayList) {

            // Menampilkan detail item.
            result = result
                    + nomorItem + ". "
                    + item.getDetail() + "\n";

            // Menampilkan harga item dalam format Rupiah.
            result = result
                    + "   Rp "
                    + formatRupiah(getHargaItem(item))
                    + "\n";

            // Menambah nomor item agar item berikutnya memiliki nomor urut yang benar.
            nomorItem++;
        }


        result = result
                + "----------------------------------------------------\n";


        // Menampilkan subtotal.
        result = result
                + "Subtotal     : Rp "
                + formatRupiah(subtotal)
                + "\n";


        // Jika ada discount reward,
        // tampilkan sebagai pengurangan.
        // kenapa? agar baris potongan tidak muncul di struk jika tidak ada reward yang digunakan.
        if (discount > 0) {

            result = result
                    + "Reward/Disc  : -Rp "
                    + formatRupiah(discount)
                    + "\n";
        }


        // Jika ada reward yang digunakan,
        // tampilkan keterangannya.
        if (!rewardSummary.equals("")) {

            result = result
                    + "Claim Reward : "
                    + rewardSummary
                    + "\n";
        }


        // Menampilkan total.
        result = result
                + "Total        : Rp "
                + formatRupiah(total)
                + "\n";


        // Menampilkan metode pembayaran.
        result = result
                + "Payment      : "
                + metodePembayaran
                + "\n";


        // Menampilkan jumlah uang yang dibayarkan.
        result = result
                + "Paid         : Rp "
                + formatRupiah(pembayaran)
                + "\n";


        // Kembalian hanya ditampilkan jika pembayaran Cash.
        // equalsIgnoreCase digunakan agar huruf besar dan kecil tidak mempengaruhi pengecekan.
        if (metodePembayaran.equalsIgnoreCase("Cash")) {

            result = result
                    + "Change       : Rp "
                    + formatRupiah(hitungJumlahKembalian())
                    + "\n";
        }


        // Jika pelanggan merupakan member,
        // tampilkan informasi member.
        if (member != null) {

            result = result
                    + "----------------------------------------------------\n";

            result = result
                    + "MEMBER\n";

            // Level sekarang diambil dari Reward.
            result = result
                    + "Level        : "
                    + member.getReward().getLevel()
                    + "\n";

            // Stamp sekarang diambil dari Reward.
            result = result
                    + "Stamp        : "
                    + member.getReward().getStamp()
                    + "\n";
        }


        // Bagian ini menambahkan garis penutup dan ucapan terima kasih.
        result = result
                + "====================================================\n";

        result = result
                + "              Thank you for ordering!\n";

        result = result
                + "====================================================\n";


        // Mengembalikan teks struk.
        return result;
    }


    /*
     * Method getHargaItem digunakan untuk mengambil
     * harga satu OrderItem.
     *
     * Method ini dibuat agar getReceiptText()
     * tidak perlu menghitung harga item sendiri.
     */
    // Method ini diatur private karena hanya digunakan di dalam class Order.
    private double getHargaItem(OrderItem item) {

        // Jika item kosong, harga dikembalikan 0.
        if (item == null) {

            return 0;
        }

        // Mengambil harga dari method getHarga()
        // yang ada di class OrderItem.
        return item.getHarga();
    }


    // Method private ini digunakan untuk mengubah angka
    // menjadi format Rupiah.
    // Method ini menerima parameter angka dan mengembalikan String.
    private String formatRupiah(double angka) {

        // String.format digunakan untuk memberikan
        // pemisah angka.
        // Tanda koma hasil format diganti menjadi titik
        // agar sesuai dengan penulisan Rupiah di Indonesia.
        return String.format("%,.0f", angka)
                .replace(',', '.');
    }


    // Implementasi method getOrder()
    // dari interface Struk.
    // Anotasi @Override menandakan bahwa method ini menggantikan method milik interface.
    @Override
    public Order getOrder() {

        // Mengembalikan object Order ini sendiri.
        return this;
    }


    // Implementasi method setOrder()
    // dari interface Struk.
    // Method ini menerima parameter order, yaitu objek Order yang datanya akan disalin.
    @Override
    public void setOrder(Order order) {

        // Mengecek agar object order tidak kosong.
        // kenapa? agar tidak terjadi error NullPointerException saat menyalin data.
        if (order != null) {

            // Menyalin semua data order ke objek ini.
            itemsArrayList = order.itemsArrayList;
            member = order.member;
            namaPelanggan = order.namaPelanggan;
            nomor = order.nomor;
            queueNo = order.queueNo;
            tanggal = order.tanggal;
            receiptNo = order.receiptNo;
            orderID = order.orderID;
            collectedBy = order.collectedBy;
            typeOrder = order.typeOrder;
            subtotal = order.subtotal;
            total = order.total;
            discount = order.discount;
            rewardSummary = order.rewardSummary;
            pembayaran = order.pembayaran;
            metodePembayaran = order.metodePembayaran;
        }
    }


    // Implementasi method cetak()
    // dari interface Struk.
    @Override
    public void cetak() {

        // Menampilkan teks struk ke console.
        System.out.println(getReceiptText());
    }


    /*
     * Method paint() digunakan untuk menampilkan
     * struk dalam bentuk window GUI.
     */
    // Implementasi method paint() dari interface Struk.
    @Override
    public void paint() {

        // Membuat JFrame sebagai window struk.
        JFrame frame = new JFrame("Teaji - Struk");

        // Mengatur ukuran window.
        frame.setSize(620, 720);

        // Window hanya ditutup jika tombol close ditekan.
        // DISPOSE_ON_CLOSE menutup window struk saja tanpa menghentikan seluruh program.
        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // JTextArea digunakan untuk menampilkan teks struk.
        JTextArea area = new JTextArea(
                getReceiptText()
        );

        // Menggunakan font Monospaced agar struk terlihat rapi.
        // kenapa? karena setiap huruf pada font Monospaced memiliki lebar yang sama sehingga kolom teks sejajar.
        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        // Struk tidak dapat diedit.
        area.setEditable(false);

        // JScrollPane membuat isi struk dapat digulir.
        frame.add(
                new JScrollPane(area)
        );

        // Window diletakkan di tengah layar.
        frame.setLocationRelativeTo(null);

        // Menampilkan window.
        frame.setVisible(true);
    }


    // Method public ini digunakan untuk menyimpan data transaksi dan detail pesanan
    // ke dalam file orders.txt.
    public void simpanKeFile() {

        // try digunakan untuk menangani kemungkinan kesalahan file.
        try {

            // Membuka file dalam mode append agar data transaksi lama
            // tidak terhapus ketika transaksi baru disimpan.
            FileWriter writer = new FileWriter("orders.txt", true);

            // Membuat garis pemisah antar transaksi.
            writer.write("============================================================"
                    + System.lineSeparator());

            // Menampilkan informasi utama transaksi.
            writer.write(
                    "ORDER ID     : " + orderID
                    + System.lineSeparator()
            );

            writer.write(
                    "Tanggal      : " + tanggal
                    + System.lineSeparator()
            );

            writer.write(
                    "Customer     : " + namaPelanggan
                    + System.lineSeparator()
            );

            writer.write(
                    "Payment      : " + metodePembayaran
                    + System.lineSeparator()
            );

            writer.write("------------------------------------------------------------"
                    + System.lineSeparator());

            writer.write("PESANAN"
                    + System.lineSeparator());

            writer.write("------------------------------------------------------------"
                    + System.lineSeparator());

            // Header tabel pesanan.
            // Format %-4s, %-35s, %-8s, dan %-15s mengatur lebar kolom dengan rata kiri.
            writer.write(
                    String.format(
                            "%-4s %-35s %-8s %-15s",
                            "No",
                            "Menu",
                            "Qty",
                            "Harga"
                    )
                    + System.lineSeparator()
            );

            writer.write("------------------------------------------------------------"
                    + System.lineSeparator());


            // Menampilkan semua item pesanan menggunakan perulangan for berdasarkan indeks.
            for (int i = 0; i < itemsArrayList.size(); i++) {

                // Mengambil item dari ArrayList sesuai indeks i.
                OrderItem item = itemsArrayList.get(i);

                // Menampilkan nomor item.
                writer.write(
                        String.format(
                                "%-4s ",
                                (i + 1) + "."
                        )
                );

                // Menampilkan detail pesanan.
                writer.write(item.getDetail());

                // Pindah ke baris berikutnya.
                writer.write(System.lineSeparator());

                // Menampilkan harga item di baris berikutnya.
                writer.write(
                        "     Harga: Rp "
                        + formatRupiah(getHargaItem(item))
                        + System.lineSeparator()
                );

                // Memberikan jarak antar pesanan.
                writer.write(System.lineSeparator());
            }


            writer.write("------------------------------------------------------------"
                    + System.lineSeparator());

            // Menampilkan total transaksi.
            writer.write(
                    "TOTAL        : Rp "
                    + formatRupiah(total)
                    + System.lineSeparator()
            );

            writer.write("============================================================"
                    + System.lineSeparator());

            // Memberikan satu baris kosong setelah transaksi.
            writer.write(System.lineSeparator());

            // Menutup file setelah selesai digunakan.
            // kenapa? agar data benar-benar tersimpan dan file tidak terkunci oleh program.
            writer.close();

        // catch menangkap IOException jika terjadi kesalahan saat menulis file.
        } catch (IOException e) {

            // Menampilkan pesan jika terjadi kesalahan saat menyimpan file.
            System.out.println("Gagal menyimpan order ke file.");
            }
        }
    }