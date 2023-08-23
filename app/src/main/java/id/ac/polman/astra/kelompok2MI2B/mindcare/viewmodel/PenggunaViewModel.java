package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;


public class PenggunaViewModel extends ViewModel {
    private MutableLiveData<Pengguna> mPenggunaListMutableLiveData;
    private PenggunaRepository mPenggunaRepository;

    public PenggunaViewModel(){
        Log.d(TAG, "PenggunaViewModel constructor called");
        mPenggunaRepository = PenggunaRepository.get();
    }

    public MutableLiveData<Pengguna> getPenggunaByNim(String nim){
        mPenggunaListMutableLiveData = mPenggunaRepository.getPenggunaByNim(nim);
        Log.d(TAG, "PenggunaViewModel.getLombas() called = " +
                mPenggunaListMutableLiveData.toString());
        return mPenggunaListMutableLiveData;
    }

    public void savePengguna(Pengguna user){
        mPenggunaRepository.updatePengguna(user);
    }


}
