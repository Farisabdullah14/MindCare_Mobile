package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface JurnalService {

    @GET("/api/jurnal/jurnal")
    Call<Jurnal> getJurnalbyId(@Query("id") int id);

    @GET("/api/jurnal/jurnals")
    Call<List<Jurnal>> getJurnals();

    @GET("/api/jurnal/jurnalsbyjenis")
    Call<Jurnal> getJurnalsbyJenis(@Query("jenis_jurnal") String jenis_jurnal);


    @POST("/api/jurnal/jurnal")
    Call<Jurnal> saveJurnal(@Body Jurnal jurnal);


    @PUT("/api/jurnal/jurnal")
    Call<Jurnal> updateJurnal(@Body Jurnal jurnal);

    @DELETE("/api/jurnal/jurnal")
    Call<Jurnal> deleteJurnalById(@Query("id_jurnal") int id);




}
