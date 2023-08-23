package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Psikolog;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PsikologService {


    @GET("/api/psikolog/data")
    Call<Psikolog> getPsikologbyId(@Query("id_psikolog") int id);

    @GET("/api/psikolog/alldata")
    Call<List<Psikolog>> getPsikologs();




}
