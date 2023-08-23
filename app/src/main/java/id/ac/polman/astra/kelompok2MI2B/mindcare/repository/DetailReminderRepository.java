package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.DetailReminder;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.DetailReminderService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailReminderRepository {
    private static final String TAG = "DReminderRepository";
    private static DetailReminderRepository INSTANCE;
    private DetailReminderService mDetailReminderService;

    private DetailReminderRepository(Context context){
        mDetailReminderService = ApiUtils.getDetailReminderService();
    }


    //untuk initialize repository
    public static void initialize(Context context){
        if (INSTANCE == null){
            INSTANCE = new DetailReminderRepository(context);
        }
    }


    //untuk get data
    public static DetailReminderRepository get(){
        return INSTANCE;
    }


    public MutableLiveData<List<DetailReminder>> getDetailReminderByIdPengguna(int id){
        MutableLiveData<List<DetailReminder>> pendaftarans = new MutableLiveData<>();

        Call<List<DetailReminder>> call = mDetailReminderService.getDetailReminderById(id);
        call.enqueue(new Callback<List<DetailReminder>>() {
            @Override
            public void onResponse(Call<List<DetailReminder>> call,
                                   Response<List<DetailReminder>> response) {
                if (response.isSuccessful()){
                    pendaftarans.setValue(response.body());
                    Log.d(TAG, "getDetailReminders.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<DetailReminder>> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());

            }
        });
        return pendaftarans;
    }


    public void deleteNotif(int id_detail){
        System.out.println("rian");
        Log.i(TAG, "addDetailReminder: called");
        Call<DetailReminder> call = mDetailReminderService.deleteDetailReminderById_detail(id_detail);
        call.enqueue(new Callback<DetailReminder>() {
            @Override
            public void onResponse(Call<DetailReminder> call, Response<DetailReminder> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "notif delete success: ");
                }
            }

            @Override
            public void onFailure(Call<DetailReminder> call, Throwable t) {
                Log.e("Error Api Call: ",t.getMessage() );
            }
        });
    }


    






    


}
