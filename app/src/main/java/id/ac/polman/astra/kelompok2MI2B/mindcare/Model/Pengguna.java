package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pengguna {
    @SerializedName("id")
    @Expose
    private int mid_user;

    @SerializedName("nim")
    @Expose
    private String nim;

    @SerializedName("nama")
    @Expose
    private String mNama;


    @SerializedName("jenis_kelamin")
    @Expose
    private String mjenis_kelamin;


    @SerializedName("alamat")
    @Expose
    private String mAlamat;

    @SerializedName("password")
    @Expose
    private String mPassword;

    @SerializedName("status")
    @Expose
    private String status;


    public Pengguna() {

    }

    public Pengguna(int mid_user, String nim, String nama, String password, String alamat, String mjenis_kelamin, String status) {
        this.mid_user = mid_user;
        this.nim = nim;
        this.mNama = nama;

        this.mPassword = password;
        this.mAlamat = alamat;
        this.mjenis_kelamin = mjenis_kelamin;
        this.status = status;
    }

    public int getMid_user() {
        return mid_user;
    }

    public void setMid_user(int mid_user) {
        this.mid_user = mid_user;
    }

    public String getMjenis_kelamin() {
        return mjenis_kelamin;
    }

    public void setMjenis_kelamin(String mjenis_kelamin) {
        this.mjenis_kelamin = mjenis_kelamin;
    }



    public String getNama() {
        return mNama;
    }

    public void setNama(String nama) {
        mNama = nama;
    }

//    public String getUsername() {
//        return mUsername;
//    }
//
//    public void setUsername(String username) {
//        mUsername = username;
//    }


    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getAlamat() {
        return mAlamat;
    }

    public void setAlamat(String alamat) {
        mAlamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
