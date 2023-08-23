package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Reminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.ReminderRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.DetailReminderViewModel;

public class DetailReminderFragment extends Fragment { //implements ReminderListFragment.Callbacks{
    private static final String ARG_JURNAL_ID = "reminder_id";
    private static final String TAG = "DetailReminderFragment";
    private Reminder mReminder;
    private TextView mReminderKonten;
    private TextView mReminderName;


    private TextView mReminderSumber;

    private TextView mJenisReminder;

    private ImageView mImageReminder;


    private DetailReminderViewModel mDetailReminderViewModel;
    private int mReminderId;

    private ReminderRepository mReminderRepository;

    private Callbacks mCallbacks;


    public interface Callbacks {
        void onBackPressedDetailReminder();
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

    // private ReminderListFragment.Callbacks mCallbacks = null;



    public DetailReminderViewModel getDetailReminderViewModel() {
        if (mDetailReminderViewModel == null) {
            mDetailReminderViewModel = new ViewModelProvider(this).get(DetailReminderViewModel.class);
            System.out.println("data di get kosong");
        }
        return mDetailReminderViewModel;
    }

    public static DetailReminderFragment newInstance(int reminderId) {
        Bundle args = new Bundle();
        args.putInt(ARG_JURNAL_ID, reminderId);
        DetailReminderFragment fragment = new DetailReminderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "DetailReminderFragment.onCreate() called");
        mReminderId = getArguments().getInt(ARG_JURNAL_ID);
        mDetailReminderViewModel = getDetailReminderViewModel();
        mReminderRepository = ReminderRepository.get();

    }

    private void updateUI() {
        Log.i(TAG, "DetailReminderFragment.updateUI() called");
        if (mReminder != null) {
            Log.d(TAG, "Data tidak kosong");
            mReminderName.setText(mReminder.getReminder_judul());
            mReminderKonten.setText(mReminder.getReminder_deskripsi());



            mJenisReminder.setText(mReminder.getReminder_tipe());
            mReminderSumber.setText(mReminder.getReminder_konten());
            String gambar = mReminder.getReminder_gambar();
            //String imageUrl = "http://192.168.100.60:8080/uploads/gambar/" + gambar;
            String imageUrl = "http://192.168.0.160:8080/uploads/gambar/" + gambar;

           // String imageUrl = "http://10.8.1.151:8080/uploads/gambar/" + gambar;
            if (imageUrl != null){
                Picasso.get().load(imageUrl).into(mImageReminder);
            }else {
                Toast.makeText(getActivity(),
                                "Gambar ERROR",
                                Toast.LENGTH_SHORT)
                        .show();
            }



        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_reminder, container, false);
        mReminderName = v.findViewById(R.id.reminder_judul);
        mReminderKonten = v.findViewById(R.id.reminder_konten);

        mJenisReminder = v.findViewById(R.id.reminder_jenis);
        mReminderSumber = v.findViewById(R.id.reminder_sumber);
        mImageReminder = v.findViewById(R.id.reminder_gambar);

        ImageView mBackButton;

        mBackButton = v.findViewById(R.id.left_icon);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   getParentFragmentManager().popBackStack();
                onBackPressedDetailReminder();

            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "DetailReminderFragment.onViewCreated() called");

        // Menambahkan callback ketika tombol back ditekan


        mDetailReminderViewModel.getReminderLiveData().observe(getViewLifecycleOwner(), new Observer<Reminder>() {
            @Override
            public void onChanged(Reminder reminder) {
                mReminder = reminder;
                updateUI();
            }
        });
        mDetailReminderViewModel.loadReminder(mReminderId);
    }



    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "DetailReminderFragment.onStop() called");
        // mDetailReminderViewModel.saveReminder(mReminder);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCallbacks = null;
    }
    private void onBackPressedDetailReminder() {
        if (mCallbacks != null) {
            mCallbacks.onBackPressedDetailReminder();
        }
    }
}
