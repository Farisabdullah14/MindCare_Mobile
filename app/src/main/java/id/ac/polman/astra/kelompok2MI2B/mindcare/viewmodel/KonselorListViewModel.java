package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Konselor;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.KonselorRepository;

public class KonselorListViewModel extends ViewModel {

    private static final String TAG = "KonselorListViewModel";
    private MutableLiveData<List<Konselor>> mKonselorListMutableLiveData;
    private KonselorRepository mKonselorRepository;

    public KonselorListViewModel(){
        mKonselorRepository = KonselorRepository.get();
        Log.d(TAG, "view model");
    }

    public MutableLiveData<List<Konselor>> getKonselors(){
        mKonselorListMutableLiveData = mKonselorRepository.getKonselors();
        Log.d(TAG, "KonselorListViewModel.getKonselors() called = " +
                mKonselorListMutableLiveData.toString());
        return mKonselorListMutableLiveData;
    }


}
