package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Jurnal {

    @SerializedName("id_jurnal")
    @Expose
    private int id_jurnal;



    @SerializedName("nama_jurnal")
    @Expose
    private String nama_jurnal;

    @SerializedName("tanggal_jurnal")
    @Expose
    private Date tanggal_jurnal;

    @SerializedName("jenis_jurnal")
    @Expose
    private String jenis_jurnal;


    @SerializedName("sumber_jurnal")
    @Expose
    private String sumber_jurnal;

    @SerializedName("gambar_jurnal")
    @Expose
    private String gambar_jurnal;

    @SerializedName("konten")
    @Expose
    private String konten;

    @SerializedName("status")
    @Expose
    private String status;

    public Jurnal(int id_jurnal, String nama_jurnal, Date tanggal_jurnal, String jenis_jurnal, String sumber_jurnal, String gambar_jurnal, String konten, String status) {
        this.id_jurnal = id_jurnal;
        this.nama_jurnal = nama_jurnal;
        this.tanggal_jurnal = tanggal_jurnal;
        this.jenis_jurnal = jenis_jurnal;
        this.sumber_jurnal = sumber_jurnal;
        this.gambar_jurnal = gambar_jurnal;
        this.konten = konten;
        this.status = status;
    }

    public Jurnal() {
    }

    public String getGambar_jurnal() {
        return gambar_jurnal;
    }

    public void setGambar_jurnal(String gambar_jurnal) {
        this.gambar_jurnal = gambar_jurnal;
    }

    public String getJenis_jurnal() {
        return jenis_jurnal;
    }

    public void setJenis_jurnal(String jenis_jurnal) {
        this.jenis_jurnal = jenis_jurnal;
    }

    public String getSumber_jurnal() {
        return sumber_jurnal;
    }

    public void setSumber_jurnal(String sumber_jurnal) {
        this.sumber_jurnal = sumber_jurnal;
    }

    public int getId_jurnal() {
        return id_jurnal;
    }

    public void setId_jurnal(int id_jurnal) {
        this.id_jurnal = id_jurnal;
    }


    public String getNama_jurnal() {
        return nama_jurnal;
    }

    public void setNama_jurnal(String nama_jurnal) {
        this.nama_jurnal = nama_jurnal;
    }

    public Date getTanggal_jurnal() {
        return tanggal_jurnal;
    }

    public void setTanggal_jurnal(Date tanggal_jurnal) {
        this.tanggal_jurnal = tanggal_jurnal;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}