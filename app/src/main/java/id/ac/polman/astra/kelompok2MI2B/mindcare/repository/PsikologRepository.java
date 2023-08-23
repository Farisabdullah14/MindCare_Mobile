package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Psikolog;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.PsikologService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PsikologRepository {

    private static final String TAG = "PsikologRepository";
    private static PsikologRepository INSTANCE;
    private PsikologService mPsikologService;

    private PsikologRepository(Context context){
        mPsikologService = ApiUtils.getPsikologService();
    }


    //untuk initialize repository
    public static void initialize(Context context){
        if (INSTANCE == null){
            INSTANCE = new PsikologRepository(context);
        }
    }


    //untuk get data
    public static PsikologRepository get(){
        return INSTANCE;
    }


    //untuk get data secara list 
    public MutableLiveData<List<Psikolog>> getPsikologs(){
        MutableLiveData<List<Psikolog>> psikologs = new MutableLiveData<>();

        Call<List<Psikolog>> call = mPsikologService.getPsikologs();
        call.enqueue(new Callback<List<Psikolog>>() {
            @Override
            public void onResponse(Call<List<Psikolog>> call, Response<List<Psikolog>> response) {
                if (response.isSuccessful()){
                    psikologs.setValue(response.body());
                    Log.d(TAG, "getPsikologs.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Psikolog>> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return psikologs;
    }

    //untuk get psikolog berdasarkan id
    public MutableLiveData<Psikolog> getPsikolog(int psikologId){
        MutableLiveData<Psikolog> psikolog = new MutableLiveData<>();

        Call<Psikolog> call = mPsikologService.getPsikologbyId(psikologId);
        call.enqueue(new Callback<Psikolog>() {
            @Override
            public void onResponse(Call<Psikolog> call, Response<Psikolog> response) {
                if (response.isSuccessful()){
                    psikolog.setValue(response.body());
                    Log.d(TAG, "getPsikologById.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<Psikolog> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return psikolog;
    }

    //untuk save psikolog
//    public void addPsikolog(Psikolog psikolog){
//        Log.i(TAG, "addPsikolog: called");
//        Call<Psikolog> call = mPsikologService.savePsikolog(psikolog);
//        call.enqueue(new Callback<Psikolog>() {
//            @Override
//            public void onResponse(Call<Psikolog> call, Response<Psikolog> response) {
//                if(response.isSuccessful()){
//                    Log.i(TAG, "Psikolog added: "+psikolog.getNama_psikolog());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Psikolog> call, Throwable t) {
//                Log.e("Error Api Call: ",t.getMessage() );
//            }
//        });
//    }


//    //untuk update psikolog
//    public void updatePsikolog(Psikolog psikolog){
//        Log.i(TAG, "PsikologRepo.updatePsikolog: called");
//        Call<Psikolog> call = mPsikologService.updatePsikolog(psikolog);
//        call.enqueue(new Callback<Psikolog>() {
//            @Override
//            public void onResponse(Call<Psikolog> call, Response<Psikolog> response) {
//                if (response.isSuccessful()){
//                    Log.i(TAG, "Psikolog Updated "+ psikolog.getNama_psikolog());
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<Psikolog> call, Throwable t) {
//                Log.e("Error Api call: ",t.getMessage() );
//            }
//        });
//    }


}
