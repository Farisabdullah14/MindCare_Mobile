package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.DetailReminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.DetailReminderRepository;

public class DetailReminderListViewModel extends ViewModel {

    private MutableLiveData<List<DetailReminder>> mDetailReminderListMutableLiveData;
    private DetailReminderRepository mDetailReminderRepository;

    public DetailReminderListViewModel(){
        Log.d(TAG, "DetailReminderListViewModel constructor called");
        mDetailReminderRepository = DetailReminderRepository.get();
    }


    public MutableLiveData<List<DetailReminder>> getDetailReminderByIdPengguna(int id){
        mDetailReminderListMutableLiveData = mDetailReminderRepository.getDetailReminderByIdPengguna(id);
        Log.d(TAG, "ReminderListViewModel.getLombas() called = " +
                mDetailReminderListMutableLiveData.toString());
        return mDetailReminderListMutableLiveData;
    }


//    public MutableLiveData<List<Mood>> getMoodsById(int id_user){
//        mMoodListMutableLiveData = mMoodRepository.getMoodsById(id_user);
//        Log.d(TAG, "MoodListViewModel.getMoods() called = " +
//                mMoodListMutableLiveData.toString());
//        return mMoodListMutableLiveData;
//    }
}
