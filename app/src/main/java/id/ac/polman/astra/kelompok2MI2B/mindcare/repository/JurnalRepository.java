package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.JurnalService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JurnalRepository {

    private static final String TAG = "JurnalRepository";
    private static JurnalRepository INSTANCE;
    private JurnalService mJurnalService;

    private JurnalRepository(Context context){
        mJurnalService = ApiUtils.getJurnalService();
    }

    public static void initialize(Context context){
        if (INSTANCE == null){
            INSTANCE = new JurnalRepository(context);
        }
    }
    
    
    //untuk get data
    public static JurnalRepository get(){
        return INSTANCE;
    }
    
    
    //untuk get data secara list 
    public MutableLiveData<List<Jurnal>> getJurnals(){
        MutableLiveData<List<Jurnal>> jurnals = new MutableLiveData<>();

        Call<List<Jurnal>> call = mJurnalService.getJurnals();
        call.enqueue(new Callback<List<Jurnal>>() {
            @Override
            public void onResponse(Call<List<Jurnal>> call, Response<List<Jurnal>> response) {
                if (response.isSuccessful()){
                    jurnals.setValue(response.body());
                    Log.d(TAG, "getJurnals.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Jurnal>> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return jurnals;
    }

    //untuk get jurnal berdasarkan id
    public MutableLiveData<Jurnal> getJurnal(int jurnalId){
        MutableLiveData<Jurnal> jurnal = new MutableLiveData<>();

        Call<Jurnal> call = mJurnalService.getJurnalbyId(jurnalId);
        call.enqueue(new Callback<Jurnal>() {
            @Override
            public void onResponse(Call<Jurnal> call, Response<Jurnal> response) {
                if (response.isSuccessful()){
                    jurnal.setValue(response.body());
                    Log.d(TAG, "getJurnalById.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<Jurnal> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return jurnal;
    }

    //untuk save jurnal
    public void addJurnal(Jurnal jurnal){
        Log.i(TAG, "addJurnal: called");
        Call<Jurnal> call = mJurnalService.saveJurnal(jurnal);
        call.enqueue(new Callback<Jurnal>() {
            @Override
            public void onResponse(Call<Jurnal> call, Response<Jurnal> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "Jurnal added: "+jurnal.getNama_jurnal());
                }
            }

            @Override
            public void onFailure(Call<Jurnal> call, Throwable t) {
                Log.e("Error Api Call: ",t.getMessage() );
            }
        });
    }


    //untuk update jurnal
    public void updateJurnal(Jurnal jurnal){
        Log.i(TAG, "JurnalRepo.updateJurnal: called");
        Call<Jurnal> call = mJurnalService.updateJurnal(jurnal);
        call.enqueue(new Callback<Jurnal>() {
            @Override
            public void onResponse(Call<Jurnal> call, Response<Jurnal> response) {
                if (response.isSuccessful()){
                    Log.i(TAG, "Jurnal Updated "+ jurnal.getNama_jurnal());
                }
            }


            @Override
            public void onFailure(Call<Jurnal> call, Throwable t) {
                Log.e("Error Api call: ",t.getMessage() );
            }
        });
    }
    
    
}
