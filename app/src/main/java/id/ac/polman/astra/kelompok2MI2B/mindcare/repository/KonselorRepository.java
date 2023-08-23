package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Konselor;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.KonselorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonselorRepository {

    private static final String TAG = "KonselorRepository";
    private static KonselorRepository INSTANCE;
    private KonselorService mKonselorService;

    private KonselorRepository(Context context){
        mKonselorService = ApiUtils.getKonselorService();
    }


    //untuk initialize repository
    public static void initialize(Context context){
        if (INSTANCE == null){
            INSTANCE = new KonselorRepository(context);
        }
    }


    //untuk get data
    public static KonselorRepository get(){
        return INSTANCE;
    }


    //untuk get data secara list 
    public MutableLiveData<List<Konselor>> getKonselors(){
        MutableLiveData<List<Konselor>> konselors = new MutableLiveData<>();

        Call<List<Konselor>> call = mKonselorService.getKonselors();
        call.enqueue(new Callback<List<Konselor>>() {
            @Override
            public void onResponse(Call<List<Konselor>> call, Response<List<Konselor>> response) {
                if (response.isSuccessful()){
                    konselors.setValue(response.body());
                    Log.d(TAG, "getKonselors.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Konselor>> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return konselors;
    }

    //untuk get konselor berdasarkan id
    public MutableLiveData<Konselor> getKonselor(int konselorId){
        MutableLiveData<Konselor> konselor = new MutableLiveData<>();

        Call<Konselor> call = mKonselorService.getKonselorbyId(konselorId);
        call.enqueue(new Callback<Konselor>() {
            @Override
            public void onResponse(Call<Konselor> call, Response<Konselor> response) {
                if (response.isSuccessful()){
                    konselor.setValue(response.body());
                    Log.d(TAG, "getKonselorById.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<Konselor> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return konselor;
    }
}
