// Package ini agar class/interface berada dalam package com.teaji.
// Package digunakan untuk mengelompokkan class/interface yang terkait.
package com.teaji;

/*
 * Reward.java
 * Class yang menyimpan dan mengatur data reward member berupa level, stamp,
 * dan status klaim reward pada 10, 15, dan 20 stamp.
 * Class ini juga menentukan isi reward serta slogan sesuai level member.
 */
// Mendefinisikan class/interface utama sesuai nama file.
public class Reward {

    // Variabel private ini digunakan untuk menyimpan level member.
    // Variabel ini diatur private agar hanya dapat diakses melalui method getter dan setter yang disediakan oleh class ini.
    private int level;

    // Variabel private ini digunakan untuk menyimpan jumlah stamp yang dimiliki member saat ini.
    // Nilainya bertambah melalui method tambahStamp dan kembali ke 0 saat level berpindah.
    private int stamp;

    // Variabel private ini digunakan untuk menyimpan jumlah stamp yang dibutuhkan.
    // Nilainya diisi melalui constructor atau setter.
    private int stampDibutuhkan;

    // Variabel private ini digunakan untuk menyimpan deskripsi reward.
    private String deskripsi;

    // Variabel private ini digunakan untuk menyimpan status reward 10 stamp.
    // true  = sudah diklaim.
    // false = belum diklaim.
    private boolean reward10Claimed;

    // Variabel private ini digunakan untuk menyimpan status reward 15 stamp.
    // Nilai true berarti sudah diklaim dan false berarti belum diklaim.
    private boolean reward15Claimed;

    // Variabel private ini digunakan untuk menyimpan status reward 20 stamp.
    // Nilai true berarti sudah diklaim dan false berarti belum diklaim.
    private boolean reward20Claimed;


    // Method constructor public ini digunakan untuk membuat objek Reward dengan nilai default.
    // Method ini dipanggil saat objek Reward dibuat tanpa parameter.
    // kenapa? karena constructor ini tidak memiliki parameter,
    // sehingga saat objek Reward dibuat tanpa memberikan nilai apa pun, constructor ini akan dipanggil
    // dan menetapkan nilai awal pada setiap variabel.
    public Reward() {

        // Level awal member ditetapkan 1 karena member baru selalu memulai dari level paling dasar.
        level = 1;

        // Stamp awal ditetapkan 0 karena member baru belum memiliki stamp.
        stamp = 0;

        // Nilai awal stampDibutuhkan ditetapkan 0 karena belum ditentukan.
        stampDibutuhkan = 0;

        // Deskripsi awal ditetapkan string kosong karena belum ada keterangan reward.
        deskripsi = "";

        // Semua reward ditetapkan false karena belum ada reward yang diklaim.
        reward10Claimed = false;
        reward15Claimed = false;
        reward20Claimed = false;
    }


    // Method public ini adalah constructor yang digunakan untuk membuat objek Reward dengan nilai yang ditentukan
    // untuk level, stampDibutuhkan, dan deskripsi.
    // Method ini dipanggil saat objek Reward dibuat dengan memberikan ketiga nilai tersebut.
    public Reward(
            int level,
            int stampDibutuhkan,
            String deskripsi) {

        // Menyimpan nilai parameter level ke variabel level milik objek.
        this.level = level;

        // Menyimpan nilai parameter stampDibutuhkan ke variabel stampDibutuhkan milik objek.
        this.stampDibutuhkan = stampDibutuhkan;

        // Menyimpan nilai parameter deskripsi ke variabel deskripsi milik objek.
        this.deskripsi = deskripsi;

        // Stamp awal ditetapkan 0 karena parameter constructor ini tidak memuat jumlah stamp.
        this.stamp = 0;

        // Semua reward ditetapkan false karena belum ada reward yang diklaim.
        this.reward10Claimed = false;
        this.reward15Claimed = false;
        this.reward20Claimed = false;
    }


    // Setter ini digunakan untuk mengisi atau mengubah nilai level member.
    // Method ini menerima parameter level dan menetapkan nilainya ke variabel level.
    public void setLevel(int level) {
        this.level = level;
    }

    // Setter ini digunakan untuk mengisi atau mengubah jumlah stamp member.
    // Method ini menerima parameter stamp dan menetapkan nilainya ke variabel stamp.
    public void setStamp(int stamp) {
        this.stamp = stamp;
    }

    // Setter ini digunakan untuk mengisi atau mengubah jumlah stamp yang dibutuhkan.
    // Method ini menerima parameter stampDibutuhkan dan menetapkan nilainya ke variabel stampDibutuhkan.
    public void setStampDibutuhkan(int stampDibutuhkan) {
        this.stampDibutuhkan = stampDibutuhkan;
    }

    // Setter ini digunakan untuk mengisi atau mengubah deskripsi reward.
    // Method ini menerima parameter deskripsi dan menetapkan nilainya ke variabel deskripsi.
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    // Setter ini digunakan untuk mengisi atau mengubah status klaim reward 10 stamp.
    // Method ini menerima parameter reward10Claimed dan menetapkan nilainya ke variabel reward10Claimed.
    public void setReward10Claimed(boolean reward10Claimed) {
        this.reward10Claimed = reward10Claimed;
    }

    // Setter ini digunakan untuk mengisi atau mengubah status klaim reward 15 stamp.
    // Method ini menerima parameter reward15Claimed dan menetapkan nilainya ke variabel reward15Claimed.
    public void setReward15Claimed(boolean reward15Claimed) {
        this.reward15Claimed = reward15Claimed;
    }

    // Setter ini digunakan untuk mengisi atau mengubah status klaim reward 20 stamp.
    // Method ini menerima parameter reward20Claimed dan menetapkan nilainya ke variabel reward20Claimed.
    public void setReward20Claimed(boolean reward20Claimed) {
        this.reward20Claimed = reward20Claimed;
    }


    // Getter ini digunakan untuk mengambil nilai level member.
    // kemudian mengembalikan nilai dari variabel level.
    public int getLevel() {
        return level;
    }

    // Getter ini digunakan untuk mengambil jumlah stamp yang dimiliki member.
    // kemudian mengembalikan nilai dari variabel stamp.
    public int getStamp() {
        return stamp;
    }

    // Getter ini digunakan untuk mengambil jumlah stamp yang dibutuhkan.
    // kemudian mengembalikan nilai dari variabel stampDibutuhkan.
    public int getStampDibutuhkan() {
        return stampDibutuhkan;
    }

    // Getter ini digunakan untuk mengambil deskripsi reward.
    // kemudian mengembalikan nilai dari variabel deskripsi.
    public String getDeskripsi() {
        return deskripsi;
    }

    // Getter ini digunakan untuk mengambil status klaim reward 10 stamp.
    // kemudian mengembalikan nilai dari variabel reward10Claimed.
    // Penamaan method diawali "is" karena tipe datanya boolean.
    public boolean isReward10Claimed() {
        return reward10Claimed;
    }

    // Getter ini digunakan untuk mengambil status klaim reward 15 stamp.
    // kemudian mengembalikan nilai dari variabel reward15Claimed.
    public boolean isReward15Claimed() {
        return reward15Claimed;
    }

    // Getter ini digunakan untuk mengambil status klaim reward 20 stamp.
    // kemudian mengembalikan nilai dari variabel reward20Claimed.
    public boolean isReward20Claimed() {
        return reward20Claimed;
    }


    // Method public ini digunakan untuk mendapatkan slogan sesuai dengan level member.
    // Method ini mengembalikan nilai berupa String yang berbeda untuk setiap level.
    public String getSloganLevel() {

        // Jika level member adalah 1, maka slogan untuk pemula dikembalikan.
        if (level == 1) {

            return "keep drinking, rookie!";

        // Jika level member adalah 2, maka slogan untuk level dua dikembalikan.
        } else if (level == 2) {

            return "welcome to the addict club!";

        // Jika level member adalah 3, maka slogan untuk level tiga dikembalikan.
        } else if (level == 3) {

            return "wow! you're a fanatic";

        // Jika level bukan 1, 2, atau 3 (yaitu level 4), maka slogan tertinggi dikembalikan.
        } else {

            return "certified as a trendsetter";
        }
    }


    // Method public ini digunakan untuk mendapatkan isi reward 10 stamp sesuai dengan level member.
    // Method ini mengembalikan nilai berupa String yang berbeda untuk setiap level.
    public String getReward10() {

        // Jika level member adalah 1, maka reward berupa satu topping gratis.
        if (level == 1) {

            return "Free topping";

        // Jika level member adalah 2, maka reward berupa dua topping gratis.
        } else if (level == 2) {

            return "Free 2 toppings";

        // Jika level member adalah 3, maka reward berupa minuman kejutan.
        } else if (level == 3) {

            return "Mystery drink";

        // Jika level bukan 1, 2, atau 3 (yaitu level 4), maka reward berupa Honey Series.
        } else {

            return "Honey Series";
        }
    }


    // Method public ini digunakan untuk mendapatkan isi reward 15 stamp sesuai dengan level member.
    // Method ini mengembalikan nilai berupa String yang berbeda untuk setiap level.
    public String getReward15() {

        // Jika level member adalah 1, maka reward berupa Pure Tea gratis.
        if (level == 1) {

            return "Free Pure Tea";

        // Jika level member adalah 2, maka reward berupa Milk Tea gratis.
        } else if (level == 2) {

            return "Free Milk Tea";

        // Jika level member adalah 3, maka reward berupa Fruit Tea.
        } else if (level == 3) {

            return "Fruit Tea";

        // Jika level bukan 1, 2, atau 3 (yaitu level 4), maka reward berupa House Special Milk Tea.
        } else {

            return "House Special Milk Tea";
        }
    }


    // Method public ini digunakan untuk mendapatkan isi reward 20 stamp sesuai dengan level member.
    // Method ini mengembalikan nilai berupa String yang berbeda untuk setiap level.
    public String getReward20() {

        // Jika level member adalah 1, maka reward berupa 1 minuman gratis.
        if (level == 1) {

            return "Free 1 drink";

        // Jika level member adalah 2, maka reward berupa 2 minuman gratis.
        } else if (level == 2) {

            return "Free 2 drinks";

        // Jika level member adalah 3, maka reward berupa 3 minuman gratis.
        } else if (level == 3) {

            return "Free 3 drinks";

        // Jika level bukan 1, 2, atau 3 (yaitu level 4), maka reward berupa 4 minuman gratis.
        } else {

            return "Free 4 drinks";
        }
    }


    // Method public ini digunakan untuk mendapatkan catatan reward 10 stamp.
    // Method ini menggabungkan teks keterangan dengan hasil dari method getReward10.
    public String getReward10Note() {

        return "Reward 10 stamp: " + getReward10();
    }

    // Method public ini digunakan untuk mendapatkan catatan reward 15 stamp.
    // Method ini menggabungkan teks keterangan dengan hasil dari method getReward15.
    public String getReward15Note() {

        return "Reward 15 stamp: " + getReward15();
    }

    // Method public ini digunakan untuk mendapatkan catatan reward 20 stamp.
    // Method ini menggabungkan teks keterangan dengan hasil dari method getReward20.
    public String getReward20Note() {

        return "Reward 20 stamp: " + getReward20();
    }


    // Method public ini digunakan untuk mengecek apakah reward 10 stamp sudah terbuka.
    // Method ini mengembalikan nilai boolean, true jika reward dapat diklaim dan false jika tidak.
    public boolean reward10Terbuka() {

        // Reward 10 terbuka jika stamp sudah minimal 10
        // dan reward belum pernah diklaim.
        // Operator && digunakan agar kedua syarat harus terpenuhi sekaligus.
        return stamp >= 10 && !reward10Claimed;
    }


    // Method public ini digunakan untuk mengecek apakah reward 15 stamp sudah terbuka.
    // Method ini mengembalikan nilai boolean, true jika reward dapat diklaim dan false jika tidak.
    public boolean reward15Terbuka() {

        // Reward 15 terbuka jika stamp sudah minimal 15
        // dan reward belum pernah diklaim.
        return stamp >= 15 && !reward15Claimed;
    }


    // Method public ini digunakan untuk mengecek apakah reward 20 stamp sudah terbuka.
    // Method ini mengembalikan nilai boolean, true jika reward dapat diklaim dan false jika tidak.
    public boolean reward20Terbuka() {

        // Reward 20 terbuka jika stamp sudah minimal 20
        // dan reward belum pernah diklaim.
        return stamp >= 20 && !reward20Claimed;
    }


    // Method public ini digunakan untuk menambah jumlah stamp member.
    // Method ini menerima parameter jumlah, yaitu banyaknya stamp yang akan ditambahkan.
    public void tambahStamp(int jumlah) {

        // Stamp hanya ditambah jika jumlah lebih dari 0.
        // kenapa? agar stamp member tidak berkurang atau tidak berubah akibat nilai nol atau negatif.
        if (jumlah > 0) {

            stamp = stamp + jumlah;
        }
    }


    // Method public ini digunakan untuk mengecek apakah ada reward yang sudah terbuka.
    // Method ini mengembalikan objek Reward jika ada reward yang terbuka, dan null jika belum ada.
    public Reward cekReward() {

        // Mengecek apakah salah satu reward
        // sudah terbuka.
        // Operator || digunakan agar cukup satu reward yang terbuka untuk memenuhi syarat.
        if (reward10Terbuka()
                || reward15Terbuka()
                || reward20Terbuka()) {

            // Mengembalikan objek Reward ini.
            return this;
        }

        // Jika belum ada reward yang terbuka, maka dikembalikan null.
        return null;
    }


    // Method public ini digunakan untuk mengecek apakah ada reward yang bisa diklaim.
    // Method ini mengembalikan nilai boolean, true jika salah satu reward terbuka dan false jika tidak ada.
    public boolean bisaKlaimReward() {

        return reward10Terbuka()
                || reward15Terbuka()
                || reward20Terbuka();
    }


    // Method public ini digunakan untuk menukar reward 10 stamp.
    // Method ini hanya mengubah status klaim jika reward 10 memang sudah terbuka.
    public void tukarReward10() {

        if (reward10Terbuka()) {

            // Menandai reward 10 sudah digunakan.
            reward10Claimed = true;
        }
    }


    // Method public ini digunakan untuk menukar reward 15 stamp.
    // Method ini hanya mengubah status klaim jika reward 15 memang sudah terbuka.
    public void tukarReward15() {

        if (reward15Terbuka()) {

            // Menandai reward 15 sudah digunakan.
            reward15Claimed = true;
        }
    }


    // Method public ini digunakan untuk menukar reward 20 stamp.
    // Method ini hanya mengubah status klaim jika reward 20 memang sudah terbuka.
    public void tukarReward20() {

        if (reward20Terbuka()) {

            // Menandai reward 20 sudah digunakan.
            reward20Claimed = true;
        }
    }


    // Method public ini digunakan untuk mengklaim satu reward berdasarkan urutan prioritas.
    // Prioritas dimulai dari reward 20, kemudian reward 15, dan terakhir reward 10.
    // kenapa? karena reward dengan stamp lebih besar memiliki nilai yang lebih tinggi,
    // sehingga diklaim lebih dahulu dan hanya satu reward yang diklaim setiap method ini dipanggil.
    public void claimReward() {

        // Jika reward 20 tersedia,
        // reward 20 yang digunakan.
        if (reward20Terbuka()) {

            tukarReward20();

        // Jika reward 20 tidak tersedia,
        // cek reward 15.
        } else if (reward15Terbuka()) {

            tukarReward15();

        // Jika reward 15 juga tidak tersedia,
        // cek reward 10.
        } else if (reward10Terbuka()) {

            tukarReward10();
        }
    }


    // Method public ini digunakan untuk mereset stamp dan memindahkan level member.
    // Method ini hanya berjalan jika stamp member sudah mencapai minimal 20.
    public void resetLevel() {

        // Level hanya berubah setelah mencapai 20 stamp.
        if (stamp >= 20) {

            // Jika belum level 4,
            // naik satu level.
            if (level < 4) {

                level = level + 1;

            } else {

                // Jika sudah level 4,
                // kembali ke level 1.
                level = 1;
            }

            // Stamp dimulai kembali dari 0.
            stamp = 0;

            // Status reward untuk level baru
            // dikembalikan menjadi belum diklaim.
            reward10Claimed = false;
            reward15Claimed = false;
            reward20Claimed = false;
        }
    }
}