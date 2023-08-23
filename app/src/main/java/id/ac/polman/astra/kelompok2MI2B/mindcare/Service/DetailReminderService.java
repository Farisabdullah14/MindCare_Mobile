package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.DetailReminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DetailReminderService {

    @GET("/api/detailreminder/detailReminderId")
    Call<List<DetailReminder>> getDetailReminderById(@Query("id") int id);

    @DELETE("/api/detailreminder/hapusNotif")
    Call<DetailReminder> deleteDetailReminderById_detail(@Query("id_detail") int id_detail);

}
