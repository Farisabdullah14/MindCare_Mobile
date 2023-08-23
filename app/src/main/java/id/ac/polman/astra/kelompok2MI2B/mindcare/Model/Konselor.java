package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Konselor {

    @SerializedName("id_konselor")
    @Expose
    private int id_konselor;

    @SerializedName("nama_konselor")
    @Expose
    private String nama_konselor;

    @SerializedName("kontak_konselor")
    @Expose
    private String kontak_konselor;

    @SerializedName("link_kontak")
    @Expose
    private String link_kontak;


    public Konselor(int id_konselor, String nama_konselor, String kontak_konselor, String link_kontak) {
        this.id_konselor = id_konselor;
        this.nama_konselor = nama_konselor;
        this.kontak_konselor = kontak_konselor;
        this.link_kontak = link_kontak;
    }

    public int getId_konselor() {
        return id_konselor;
    }

    public void setId_konselor(int id_konselor) {
        this.id_konselor = id_konselor;
    }

    public String getNama_konselor() {
        return nama_konselor;
    }

    public void setNama_konselor(String nama_konselor) {
        this.nama_konselor = nama_konselor;
    }

    public String getKontak_konselor() {
        return kontak_konselor;
    }

    public void setKontak_konselor(String kontak_konselor) {
        this.kontak_konselor = kontak_konselor;
    }

    public String getLink_kontak() {
        return link_kontak;
    }

    public void setLink_kontak(String link_kontak) {
        this.link_kontak = link_kontak;
    }
}
