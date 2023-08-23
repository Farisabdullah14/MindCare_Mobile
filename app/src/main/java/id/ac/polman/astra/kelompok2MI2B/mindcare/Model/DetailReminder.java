package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;



import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class DetailReminder {


    @SerializedName("id_detail")
    @Expose
    private int id_detail;



    @SerializedName("id")
    @Expose
    private Pengguna mPengguna;


    @SerializedName("id_reminder")
    @Expose
    private Reminder mReminder;

    private int status;

    public DetailReminder(int id_detail, Pengguna pengguna, Reminder reminder, int status) {
        this.id_detail = id_detail;
        this.mPengguna = pengguna;
        this.mReminder = reminder;
        this.status = status;
    }


    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public Pengguna getPengguna() {
        return mPengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        mPengguna = pengguna;
    }

    public Reminder getReminder() {
        return mReminder;
    }

    public void setReminder(Reminder reminder) {
        mReminder = reminder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
