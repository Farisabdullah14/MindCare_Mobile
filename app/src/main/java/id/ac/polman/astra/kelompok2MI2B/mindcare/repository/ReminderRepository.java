package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Reminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.ReminderService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReminderRepository {
    private static final String TAG = "ReminderRepository";
    private static ReminderRepository INSTANCE;
    private ReminderService mReminderService;

    private ReminderRepository(Context context){
        mReminderService = ApiUtils.getReminderService();
    }


    //untuk initialize repository
    public static void initialize(Context context){
        if (INSTANCE == null){
            INSTANCE = new ReminderRepository(context);
        }
    }


    //untuk get data
    public static ReminderRepository get(){
        return INSTANCE;
    }


    //untuk get data secara list 
    public MutableLiveData<List<Reminder>> getReminders(){
        MutableLiveData<List<Reminder>> psikologs = new MutableLiveData<>();

        Call<List<Reminder>> call = mReminderService.getReminders();
        call.enqueue(new Callback<List<Reminder>>() {
            @Override
            public void onResponse(Call<List<Reminder>> call, Response<List<Reminder>> response) {
                if (response.isSuccessful()){
                    psikologs.setValue(response.body());
                    Log.d(TAG, "getReminders.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Reminder>> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return psikologs;
    }

    //untuk get psikolog berdasarkan id
    public MutableLiveData<Reminder> getReminder(int psikologId){
        MutableLiveData<Reminder> psikolog = new MutableLiveData<>();

        Call<Reminder> call = mReminderService.getReminderbyId(psikologId);
        call.enqueue(new Callback<Reminder>() {
            @Override
            public void onResponse(Call<Reminder> call, Response<Reminder> response) {
                if (response.isSuccessful()){
                    psikolog.setValue(response.body());
                    Log.d(TAG, "getReminderById.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<Reminder> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return psikolog;
    }

    //untuk save psikolog
//    public void addReminder(Reminder psikolog){
//        Log.i(TAG, "addReminder: called");
//        Call<Reminder> call = mReminderService.saveReminder(psikolog);
//        call.enqueue(new Callback<Reminder>() {
//            @Override
//            public void onResponse(Call<Reminder> call, Response<Reminder> response) {
//                if(response.isSuccessful()){
//                    Log.i(TAG, "Reminder added: "+psikolog.getNama_psikolog());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Reminder> call, Throwable t) {
//                Log.e("Error Api Call: ",t.getMessage() );
//            }
//        });
//    }


//    //untuk update psikolog
//    public void updateReminder(Reminder psikolog){
//        Log.i(TAG, "ReminderRepo.updateReminder: called");
//        Call<Reminder> call = mReminderService.updateReminder(psikolog);
//        call.enqueue(new Callback<Reminder>() {
//            @Override
//            public void onResponse(Call<Reminder> call, Response<Reminder> response) {
//                if (response.isSuccessful()){
//                    Log.i(TAG, "Reminder Updated "+ psikolog.getNama_psikolog());
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<Reminder> call, Throwable t) {
//                Log.e("Error Api call: ",t.getMessage() );
//            }
//        });
//    }
}
