package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.JurnalRepository;

public class JurnalDetailViewModel extends ViewModel{

    private static final String TAG = "JurnalDetailViewModel";

    private LiveData<Jurnal> mJurnalLiveData;
    private JurnalRepository mJurnalRepository;
    private MutableLiveData<Integer> mIdMutableLiveData;
    


    public JurnalDetailViewModel() {
        mJurnalRepository = JurnalRepository.get();
        mIdMutableLiveData = new MutableLiveData<>();
        mJurnalLiveData = Transformations.switchMap(mIdMutableLiveData, jurnalId ->
                mJurnalRepository.getJurnal(jurnalId));

    }

    public void loadJurnal(int jurnalId) {
        Log.i(TAG, "loadJurnal: called");
        mIdMutableLiveData.setValue(jurnalId);
    }

    public LiveData<Jurnal> getJurnalLiveData() {
        Log.i(TAG, "getJurnalLiveData: called");
        return mJurnalLiveData;
    }


}

