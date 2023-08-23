package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.RawService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//untuk login dengan api
public class RawRepository {

    private static final String TAG = "RawRepository";
    private static RawRepository INSTANCE;
    private RawService mRawService;

    private RawRepository(Context context) {
        mRawService = ApiUtils.getRawService();
    }

    public static void initialize(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RawRepository(context);
        }
    }

    public static RawRepository get() {
        return INSTANCE;
    }

    //yang kepake ini doang
    public MutableLiveData<List<Object[]>> getData(String query) {
        MutableLiveData<List<Object[]>> objList = new MutableLiveData<>();

        Call<List<Object[]>> call = mRawService.getData(query);

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if(response.isSuccessful()) {
                    objList.setValue(response.body());
                    Log.d(TAG, "Berhasil");
                }
                else {
                    Log.d(TAG, query);
                }
            }

            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {
                Log.e("Error API call : ",t.getMessage());
            }
        });
        return objList;
    }

    public MutableLiveData<List<Object[]>> getDataSingle(String query) {
        MutableLiveData<List<Object[]>> objList = new MutableLiveData<>();

        Call<List<Object[]>> call = mRawService.getDataSingle(query);

        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if(response.isSuccessful()) {
                    objList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {
                Log.e("Error API call : ",t.getMessage());
            }
        });
        return objList;
    }

    public MutableLiveData<Object[]> execute(String query) {
        MutableLiveData<Object[]> objList = new MutableLiveData<>();

        Call<Object[]> call = mRawService.execute(query);

        call.enqueue(new Callback<Object[]>() {
            @Override
            public void onResponse(Call<Object[]> call, Response<Object[]> response) {
                if(response.isSuccessful()) {
                    objList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Object[]> call, Throwable t) {
                Log.e("Error API call : ",t.getMessage());
            }
        });
        return objList;
    }

    // Metode untuk memanggil service getDataTotalWeekly
    public MutableLiveData<Integer> getDataTotalWeekly(int id_user) {
        MutableLiveData<Integer> totalMoodLiveData = new MutableLiveData<>();
        System.out.println("MASUK MEME");
        Call<Integer> call = mRawService.getDataTotalWeekly(id_user);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    totalMoodLiveData.setValue(response.body());
                    Log.d(TAG, "Berhasil");
                } else {
                    Log.d(TAG, "Gagal");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, "Error API call: " + t.getMessage());
            }
        });

        return totalMoodLiveData;
    }

    // Metode untuk memanggil service getDataTotal
    public MutableLiveData<Integer> getDataTotal(int id_user) {
        MutableLiveData<Integer> totalMoodLiveData1 = new MutableLiveData<>();

        Call<Integer> call = mRawService.getDataTotal(id_user);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    totalMoodLiveData1.setValue(response.body());
                    Log.d(TAG, "Berhasil");
                } else {
                    Log.d(TAG, "Gagal");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, "Error API call: " + t.getMessage());
            }
        });

        return totalMoodLiveData1;
    }


    public MutableLiveData<Integer> getDataTotalNotif(int id_user) {
        MutableLiveData<Integer> totalNotifLiveData1 = new MutableLiveData<>();

        Call<Integer> call = mRawService.getDataNotif(id_user);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    totalNotifLiveData1.setValue(response.body());
                    Log.d(TAG, "Berhasil");
                } else {
                    Log.d(TAG, "Gagal");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, "Error API call: " + t.getMessage());
            }
        });

        return totalNotifLiveData1;
    }

}
