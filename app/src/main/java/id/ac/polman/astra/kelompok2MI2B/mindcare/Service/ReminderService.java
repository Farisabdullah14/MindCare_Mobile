package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Reminder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReminderService {
    @GET("/api/reminder/data")
    Call<Reminder> getReminderbyId(@Query("id") int id);

    @GET("/api/reminder/alldata")
    Call<List<Reminder>> getReminders();



}
