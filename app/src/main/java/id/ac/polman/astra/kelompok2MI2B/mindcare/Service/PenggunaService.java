package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;



import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.response.PenggunaResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PenggunaService {

    @GET("restuser")
    Call<Pengguna> getPenggunaById(@Query("id") int id);

    @GET("restuserbynim")
    Call<Pengguna> getPenggunaByNim(@Query("nim") String nim);

    @POST("restuser")
    Call<Pengguna> savePengguna(@Query("nim") String nim,@Query("nama") String nama);

    @PUT("restuser")
    Call<Pengguna> updatePengguna(@Body Pengguna user);

}
