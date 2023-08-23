package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.JurnalRepository;

public class JurnalListViewModel extends ViewModel {

    private static final String TAG = "JurnalListViewModel";
    private MutableLiveData<List<Jurnal>> mJurnalListLiveData;
    private JurnalRepository mJurnalRepository;

    public JurnalListViewModel() {
        mJurnalRepository = JurnalRepository.get();
        Log.d(TAG, "JurnalListViewModel instantiated");
    }

    public MutableLiveData<List<Jurnal>> getJurnals() {
        if (mJurnalListLiveData == null) {
            mJurnalListLiveData = mJurnalRepository.getJurnals();
            Log.d(TAG, "JurnalListViewModel.getJurnals() called");
        }
        return mJurnalListLiveData;
    }

    public void addJurnal(Jurnal jurnal) {
        mJurnalRepository.addJurnal(jurnal);
    }
}

