package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.MoodService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoodRepository {
    private static final String TAG = "MoodRepository";
    private static MoodRepository INSTANCE;
    private MoodService mMoodService;

    private MoodRepository(Context context){
        mMoodService = ApiUtils.getMoodService();
    }

    //untuk initialize repository
    public static void initialize(Context context){
        if (INSTANCE == null){
            INSTANCE = new MoodRepository(context);
        }
    }

    //untuk get data
    public static MoodRepository get(){
        return INSTANCE;
    }

    //untuk get data secara list 
    public MutableLiveData<List<Mood>> getMoods(){
        MutableLiveData<List<Mood>> moods = new MutableLiveData<>();

        Call<List<Mood>> call = mMoodService.getMoods();
        call.enqueue(new Callback<List<Mood>>() {
            @Override
            public void onResponse(Call<List<Mood>> call, Response<List<Mood>> response) {
                if (response.isSuccessful()){
                    moods.setValue(response.body());
                    Log.d(TAG, "getMoods.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Mood>> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return moods;
    }

    //untuk get mood berdasarkan id
    public MutableLiveData<Mood> getMood(int moodId){
        MutableLiveData<Mood> mood = new MutableLiveData<>();

        Call<Mood> call = mMoodService.getMoodbyId(moodId);
        call.enqueue(new Callback<Mood>() {
            @Override
            public void onResponse(Call<Mood> call, Response<Mood> response) {
                if (response.isSuccessful()){
                    mood.setValue(response.body());
                    Log.d(TAG, "getMoodById.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<Mood> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return mood;
    }


    public MutableLiveData<List<Mood>> getMoodsById(int id_user){
        MutableLiveData<List<Mood>> moods = new MutableLiveData<>();

        Call<List<Mood>> call = mMoodService.getMoodsById(id_user);
        call.enqueue(new Callback<List<Mood>>() {
            @Override
            public void onResponse(Call<List<Mood>> call, Response<List<Mood>> response) {
                if (response.isSuccessful()){
                    moods.setValue(response.body());
                    Log.d(TAG, "getMoods.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Mood>> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return moods;
    }


    public MutableLiveData<List<Object[]>> countMoodByIdUser(int id_user) {
        MutableLiveData<List<Object[]>> moodCounts = new MutableLiveData<>();

        Call<List<Object[]>> call = mMoodService.countMoodByIdUser(id_user);
        call.enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if (response.isSuccessful()) {
                    moodCounts.setValue(response.body());
                    Log.d(TAG, "countMoodByIdUser.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {
                Log.e("Error API call: ", t.getMessage());
            }
        });

        return moodCounts;
    }


    //untuk save mood
    public void addMood(Mood mood){
        System.out.println("rian");
        Log.i(TAG, "addMood: called");
        Call<Mood> call = mMoodService.saveMood(mood);
        call.enqueue(new Callback<Mood>() {
            @Override
            public void onResponse(Call<Mood> call, Response<Mood> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "Mood added: "+mood.getNilai());
                }
            }

            @Override
            public void onFailure(Call<Mood> call, Throwable t) {
                Log.e("Error Api Call: ",t.getMessage() );
            }
        });
    }
}
