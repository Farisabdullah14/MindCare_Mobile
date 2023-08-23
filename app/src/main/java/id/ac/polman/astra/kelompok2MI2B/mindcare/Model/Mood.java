package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;

import android.location.Location;

import androidx.room.ForeignKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Date;


public class Mood {
    @SerializedName("id_mood")
    @Expose
    private int id_mood;

    @SerializedName("id")
    @Expose
    private int id_user;

    @SerializedName("tanggal")
    @Expose
    private String  tanggal;

    @SerializedName("nilai")
    @Expose
    private int nilai;

    @SerializedName("perasaan")
    @Expose
    private String perasaan;

    public Mood(int id_mood,int id_user, String tanggal, int nilai, String perasaan) {
        this.id_mood = id_mood;
        this.id_user = id_user;
        this.tanggal = tanggal;
        this.nilai = nilai;
        this.perasaan = perasaan;
    }

    public Mood() {

    }

    public int getId_mood() {
        return id_mood;
    }

    public void setId_mood(int id_mood) {
        this.id_mood = id_mood;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public String getPerasaan() {
        return perasaan;
    }

    public void setPerasaan(String perasaan) {
        this.perasaan = perasaan;
    }


}
