package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RawService {

    @GET("/api/raw/getdata")
    Call<List<Object[]>> getData(@Query("query")String query);

    @GET("/api/raw/getdatasingle")
    Call<List<Object[]>> getDataSingle(@Query("query")String query);

    @GET("/api/raw/execute")
    Call<Object[]> execute(@Query("query")String query);

    @GET("/api/raw/totalMoodMingguan")
    Call<Integer> getDataTotalWeekly(@Query("id_user")int id_user);

    @GET("/api/raw/totalMood")
    Call<Integer> getDataTotal(@Query("id_user")int id_user);

    @GET("/api/raw/totalNotif")
    Call<Integer> getDataNotif(@Query("id_user")int id_user);

}
