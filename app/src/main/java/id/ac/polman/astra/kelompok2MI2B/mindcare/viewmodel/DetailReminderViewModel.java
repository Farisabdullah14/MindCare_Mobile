package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Reminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.ReminderRepository;

public class DetailReminderViewModel extends ViewModel {
    private static final String TAG = "ReminderDetailViewModel";
    private LiveData<Reminder> mReminderLiveData;
    private LiveData<Pengguna> mUserLiveData;
    private ReminderRepository mReminderRepository;
    private MutableLiveData<Integer> mIdMutableLiveData;

    public DetailReminderViewModel() {
        mReminderRepository = ReminderRepository.get();

        mIdMutableLiveData = new MutableLiveData<>();
        mReminderLiveData = Transformations.switchMap(mIdMutableLiveData, reminderId ->
                mReminderRepository.getReminder(reminderId));
//        mIdMutableLiveData = new MutableLiveData<Integer>();
//        mReminderLiveData = Transformations.switchMap(mIdMutableLiveData, pendaftaranId -> mReminderRepository.getReminder(pendaftaranId));
    }

    public void loadReminder(int reminderId) {
        Log.i(TAG, "loadReminder() called");
        mIdMutableLiveData.setValue(reminderId);
    }

    public LiveData<Reminder> getReminderLiveData() {
        Log.i(TAG, "getReminderLiveData() called");
        //   System.out.println(mReminderLiveData.getValue());
        return mReminderLiveData;
    }

//    public void saveReminder(Reminder pendaftaran){
//        mReminderRepository.updateReminder(pendaftaran);
//    }
//
//    public void deleteReminder(int pendaftaranID){
//        mReminderRepository.deleteReminder(pendaftaranID);
//    }



}
