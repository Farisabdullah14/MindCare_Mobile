package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;



import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.PenggunaService;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.response.PenggunaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenggunaRepository {

    private static final String TAG = "PenggunaRepository";
    private static PenggunaRepository INSTANCE;
    private PenggunaService mPenggunaService;
    private PenggunaRepository(Context context){
        mPenggunaService = ApiUtils.getPenggunaService();
    }

    public static void initialize(Context context){
        if(INSTANCE == null){
            INSTANCE = new PenggunaRepository(context);
        }
    }

    public static PenggunaRepository get(){
        return INSTANCE;
    }

    public MutableLiveData<Pengguna> getPengguna(int penggunaId){
        MutableLiveData<Pengguna> pengguna = new MutableLiveData<>();

        Call<Pengguna> call = mPenggunaService.getPenggunaById(penggunaId);
        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call,
                                   Response<Pengguna> response) {
                if(response.isSuccessful()){
                    pengguna.setValue(response.body());
                    Log.d(TAG, "getPenggunaById.onResponse() called" + response.body());
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return pengguna;
    }

    //untuk save pengguna
    public void savePengguna( String nim, String nama){
        Log.i(TAG, "addPengguna() called");
        Call<Pengguna> call = mPenggunaService.savePengguna(nim,nama);
        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "Pengguna : "+  nama + " added "  );
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {

            }
        });
    }

    public void updatePengguna(Pengguna pengguna){
        Log.i(TAG, "UpdatePengguna() called");
        Call<Pengguna> call = mPenggunaService.updatePengguna(pengguna);
        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "Pengguna : "+  pengguna.getNama() + " added "  );
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Pengguna> getPenggunaByNim(String nim){
        MutableLiveData<Pengguna> pengguna = new MutableLiveData<>();

        Call<Pengguna> call = mPenggunaService.getPenggunaByNim(nim);
        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call,
                                   Response<Pengguna> response) {
                if (response.isSuccessful()){
                    pengguna.setValue(response.body());
                    Log.d(TAG, "getDetailPendaftarans.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());

            }
        });
        return pengguna;
    }
}
