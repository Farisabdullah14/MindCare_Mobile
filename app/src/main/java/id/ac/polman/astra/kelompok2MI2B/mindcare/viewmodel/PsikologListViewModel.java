package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Psikolog;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PsikologRepository;

public class PsikologListViewModel extends ViewModel {

    private static final String TAG = "PsikologListViewModel";
    private MutableLiveData<List<Psikolog>> mPsikologListMutableLiveData;
    private PsikologRepository mPsikologRepository;

    public PsikologListViewModel(){
        mPsikologRepository = PsikologRepository.get();
        Log.d(TAG, "view model");
    }

    public MutableLiveData<List<Psikolog>> getPsikologs(){
        mPsikologListMutableLiveData = mPsikologRepository.getPsikologs();
        Log.d(TAG, "PsikologListViewModel.getPsikologs() called = " +
                mPsikologListMutableLiveData.toString());
        return mPsikologListMutableLiveData;
    }


}
