package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.MoodRepository;

public class MoodDetailViewModel extends ViewModel {

    private static final String TAG = "MoodDetailViewModel";

    private LiveData<Mood> mMoodLiveData;
    private MoodRepository mMoodRepository;
    private MutableLiveData<Integer> mIdMutableLiveData;



    public MoodDetailViewModel() {
        mMoodRepository = MoodRepository.get();
        mIdMutableLiveData = new MutableLiveData<>();
        mMoodLiveData = Transformations.switchMap(mIdMutableLiveData, moodId ->
                mMoodRepository.getMood(moodId));

    }

    public void loadMood(int moodId) {
        Log.i(TAG, "loadMood: called");
        mIdMutableLiveData.setValue(moodId);
    }

    public LiveData<Mood> getMoodLiveData() {
        Log.i(TAG, "getMoodLiveData: called");
        return mMoodLiveData;
    }


}
