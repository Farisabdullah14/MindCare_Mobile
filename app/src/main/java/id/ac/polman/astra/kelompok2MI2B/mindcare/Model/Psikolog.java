package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Psikolog {
    @SerializedName("id_psikolog")
    @Expose
    private int id_psikolog;

    @SerializedName("nama_psi")
    @Expose
    private String nama_psi;

    @SerializedName("lokasi")
    @Expose
    private String lokasi;

    @SerializedName("alamat_psi")
    @Expose
    private String alamat_psi;

    public Psikolog(int id_psikolog, String nama_psi, String lokasi, String alamat_psi) {
        this.id_psikolog = id_psikolog;
        this.nama_psi = nama_psi;
        this.lokasi = lokasi;
        this.alamat_psi = alamat_psi;
    }

    public int getId_psikolog() {
        return id_psikolog;
    }

    public void setId_psikolog(int id_psikolog) {
        this.id_psikolog = id_psikolog;
    }

    public String getNama_psi() {
        return nama_psi;
    }

    public void setNama_psi(String nama_psi) {
        this.nama_psi = nama_psi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getAlamat_psi() {
        return alamat_psi;
    }

    public void setAlamat_psi(String alamat_psi) {
        this.alamat_psi = alamat_psi;
    }
}
