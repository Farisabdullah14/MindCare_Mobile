package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reminder {
    @SerializedName("id_reminder")
    @Expose
    private int id_reminder;

    @SerializedName("reminder_judul")
    @Expose
    private String reminder_judul;

    @SerializedName("reminder_konten")
    @Expose
    private String reminder_konten;

    @SerializedName("reminder_tipe")
    @Expose
    private String reminder_tipe;

    @SerializedName("reminder_deskripsi")
    @Expose
    private String reminder_deskripsi;

    @SerializedName("reminder_genre")
    @Expose
    private String reminder_genre;

    @SerializedName("reminder_gambar")
    @Expose
    private String reminder_gambar;



    @SerializedName("reminder_status")
    @Expose
    private String reminder_status;

    public Reminder(int id_reminder, String reminder_judul, String reminder_konten, String reminder_tipe, String reminder_deskripsi, String reminder_genre, String reminder_gambar, String reminder_status) {
        this.id_reminder = id_reminder;
        this.reminder_judul = reminder_judul;
        this.reminder_konten = reminder_konten;
        this.reminder_tipe = reminder_tipe;
        this.reminder_deskripsi = reminder_deskripsi;
        this.reminder_genre = reminder_genre;
        this.reminder_gambar = reminder_gambar;
        this.reminder_status = reminder_status;
    }

    public int getId_reminder() {
        return id_reminder;
    }

    public void setId_reminder(int id_reminder) {
        this.id_reminder = id_reminder;
    }

    public String getReminder_judul() {
        return reminder_judul;
    }

    public void setReminder_judul(String reminder_judul) {
        this.reminder_judul = reminder_judul;
    }

    public String getReminder_konten() {
        return reminder_konten;
    }

    public void setReminder_konten(String reminder_konten) {
        this.reminder_konten = reminder_konten;
    }

    public String getReminder_tipe() {
        return reminder_tipe;
    }

    public void setReminder_tipe(String reminder_tipe) {
        this.reminder_tipe = reminder_tipe;
    }

    public String getReminder_deskripsi() {
        return reminder_deskripsi;
    }

    public void setReminder_deskripsi(String reminder_deskripsi) {
        this.reminder_deskripsi = reminder_deskripsi;
    }

    public String getReminder_genre() {
        return reminder_genre;
    }

    public void setReminder_genre(String reminder_genre) {
        this.reminder_genre = reminder_genre;
    }

    public String getReminder_status() {
        return reminder_status;
    }

    public void setReminder_status(String reminder_status) {
        this.reminder_status = reminder_status;
    }

    public String getReminder_gambar() {
        return reminder_gambar;
    }

    public void setReminder_gambar(String reminder_gambar) {
        this.reminder_gambar = reminder_gambar;
    }
}
