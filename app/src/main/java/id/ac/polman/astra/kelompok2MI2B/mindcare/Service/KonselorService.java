package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Konselor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KonselorService {

    @GET("/api/konselor/data")
    Call<Konselor> getKonselorbyId(@Query("id") int id);

    @GET("/api/konselor/alldata")
    Call<List<Konselor>> getKonselors();
}
