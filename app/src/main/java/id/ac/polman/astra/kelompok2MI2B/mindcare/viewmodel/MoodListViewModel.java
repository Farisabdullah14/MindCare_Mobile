package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Modifier;
import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.MoodRepository;

public class MoodListViewModel extends ViewModel {

    private static final String TAG = "MoodListViewModel";
    private MutableLiveData<List<Mood>> mMoodListMutableLiveData;
    private MoodRepository mMoodRepository;

    public MoodListViewModel(){
        mMoodRepository = MoodRepository.get();
        Log.d(TAG, "view model");
    }

    public MutableLiveData<List<Mood>> getMoods(){
        mMoodListMutableLiveData = mMoodRepository.getMoods();
        Log.d(TAG, "MoodListViewModel.getMoods() called = " +
                mMoodListMutableLiveData.toString());
        return mMoodListMutableLiveData;
    }

    public MutableLiveData<List<Mood>> getMoodsById(int id_user){
        mMoodListMutableLiveData = mMoodRepository.getMoodsById(id_user);
        Log.d(TAG, "MoodListViewModel.getMoods() called = " +
                mMoodListMutableLiveData.toString());
        return mMoodListMutableLiveData;
    }


}
