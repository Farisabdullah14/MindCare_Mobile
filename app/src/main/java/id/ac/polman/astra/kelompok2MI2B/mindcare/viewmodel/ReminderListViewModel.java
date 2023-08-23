package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Reminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.ReminderRepository;

public class ReminderListViewModel extends ViewModel {

    private static final String TAG = "ReminderListViewModel";
    private MutableLiveData<List<Reminder>> mReminderListLiveData;
    private ReminderRepository mReminderRepository;

    public ReminderListViewModel() {
        mReminderRepository = ReminderRepository.get();
        Log.d(TAG, "ReminderListViewModel instantiated");
    }

    public MutableLiveData<List<Reminder>> getReminders() {
        if (mReminderListLiveData == null) {
            mReminderListLiveData = mReminderRepository.getReminders();
            Log.d(TAG, "ReminderListViewModel.getReminders() called");
        }
        return mReminderListLiveData;
    }


}

