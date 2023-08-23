package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.JurnalRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.JurnalDetailViewModel;

public class DetailJurnalFragment extends Fragment { //implements JurnalListFragment.Callbacks{
    private static final String ARG_JURNAL_ID = "jurnal_id";
    private static final String TAG = "DetailJurnalFragment";

    private Jurnal mJurnal;
    private TextView mJurnalKonten;
    private TextView mJurnalName;
    private TextView mJurnalTanggal;

    private TextView mJurnalSumber;

    private TextView mJenisJurnal;

    private ImageView mImageJurnal;


    private JurnalDetailViewModel mJurnalDetailViewModel;
    private int mJurnalId;

    private JurnalRepository mJurnalRepository;

    private Callbacks mCallbacks;


    public interface Callbacks {
        void onBackPressedDetailJurnal();

    }

    public void setCallbacks(Callbacks callbacks) {
        mCallbacks = callbacks;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach called");
        try {
            mCallbacks = (Callbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Callbacks");
        }
    }

    // private JurnalListFragment.Callbacks mCallbacks = null;



    public JurnalDetailViewModel getJurnalDetailViewModel() {
        if (mJurnalDetailViewModel == null) {
            mJurnalDetailViewModel = new ViewModelProvider(this).get(JurnalDetailViewModel.class);
            System.out.println("data di get kosong");
        }
        return mJurnalDetailViewModel;
    }

    public static DetailJurnalFragment newInstance(int jurnalId) {
        Bundle args = new Bundle();
        args.putInt(ARG_JURNAL_ID, jurnalId);
        DetailJurnalFragment fragment = new DetailJurnalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "DetailJurnalFragment.onCreate() called");
        mJurnalId = getArguments().getInt(ARG_JURNAL_ID);
        mJurnalDetailViewModel = getJurnalDetailViewModel();
        mJurnalRepository = JurnalRepository.get();

    }

    private void updateUI() {
        Log.i(TAG, "DetailJurnalFragment.updateUI() called");
        if (mJurnal != null) {
            Log.d(TAG, "Data tidak kosong");
            mJurnalName.setText(mJurnal.getNama_jurnal());
            mJurnalKonten.setText(mJurnal.getKonten());

            // Mengubah java.util.Date menjadi java.time.LocalDate
            LocalDate localDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                localDate = mJurnal.getTanggal_jurnal().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }

            DateTimeFormatter dateFormatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
            }
            String formattedDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDate = localDate.format(dateFormatter);
            }
            mJurnalTanggal.setText(formattedDate);
            mJenisJurnal.setText(mJurnal.getJenis_jurnal());
            mJurnalSumber.setText(mJurnal.getSumber_jurnal());
            String gambar = mJurnal.getGambar_jurnal();
         //   String imageUrl = "http://10.8.5.72:8080/uploads/foto/" + gambar;
          //  String imageUrl = "http://10.8.1.151:8080/uploads/foto/" + gambar;
            String imageUrl = "http://192.168.0.160:8080/uploads/foto/" + gambar;
            Picasso.get().load(imageUrl).into(mImageJurnal);


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_jurnal, container, false);
        mJurnalName = v.findViewById(R.id.jurnal_judul);
        mJurnalKonten = v.findViewById(R.id.jurnal_konten);
        mJurnalTanggal = v.findViewById(R.id.jurnal_tanggal);
        mJenisJurnal = v.findViewById(R.id.jurnal_jenis);
        mJurnalSumber = v.findViewById(R.id.jurnal_sumber);
        mImageJurnal = v.findViewById(R.id.jurnal_gambar);

        ImageView mBackButton;

        mBackButton = v.findViewById(R.id.left_icon);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   getParentFragmentManager().popBackStack();
                requireActivity().getSupportFragmentManager().popBackStack();

            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "DetailJurnalFragment.onViewCreated() called");

        // Menambahkan callback ketika tombol back ditekan


        mJurnalDetailViewModel.getJurnalLiveData().observe(getViewLifecycleOwner(), new Observer<Jurnal>() {
            @Override
            public void onChanged(Jurnal jurnal) {
                mJurnal = jurnal;
                updateUI();
            }
        });
        mJurnalDetailViewModel.loadJurnal(mJurnalId);
    }



    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "DetailJurnalFragment.onStop() called");
        // mJurnalDetailViewModel.saveJurnal(mJurnal);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCallbacks = null;
    }
    private void onBackPressedDetailJurnal() {
        if (mCallbacks != null) {
            mCallbacks.onBackPressedDetailJurnal();
        }
        // Navigate back to the previous fragment (fragment_jurnal_list)
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}
