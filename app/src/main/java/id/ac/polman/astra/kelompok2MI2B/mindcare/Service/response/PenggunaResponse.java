package id.ac.polman.astra.kelompok2MI2B.mindcare.Service.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;

public class PenggunaResponse {
    @SerializedName("data")
    @Expose
    private Pengguna mPengguna;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private int status;

    public Pengguna getPengguna() {
        return mPengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        mPengguna = pengguna;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "mTrsBooking=" + mPengguna +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
