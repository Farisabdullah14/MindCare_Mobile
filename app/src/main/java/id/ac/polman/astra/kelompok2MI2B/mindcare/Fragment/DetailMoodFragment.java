package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.MoodRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.MoodDetailViewModel;

public class DetailMoodFragment extends Fragment { //implements MoodListFragment.Callbacks{
    private static final String ARG_JURNAL_ID = "mood_id";
    private static final String TAG = "DetailMoodFragment";

    private Mood mMood;
    private TextView mMoodKonten;
    private TextView mMoodName;
    private TextView mMoodTanggal;

    private TextView mMoodSumber;

    private TextView mJenisMood;

    private ImageView mImageMood;


    private MoodDetailViewModel mMoodDetailViewModel;
    private int mMoodId;

    private MoodRepository mMoodRepository;

    private Callbacks mCallbacks;


    public interface Callbacks {
        void onBackPressedDetailMood();
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

    // private MoodListFragment.Callbacks mCallbacks = null;



    public MoodDetailViewModel getMoodDetailViewModel() {
        if (mMoodDetailViewModel == null) {
            mMoodDetailViewModel = new ViewModelProvider(this).get(MoodDetailViewModel.class);
            System.out.println("data di get kosong");
        }
        return mMoodDetailViewModel;
    }

    public static DetailMoodFragment newInstance(int moodId) {
        Bundle args = new Bundle();
        args.putInt(ARG_JURNAL_ID, moodId);
        DetailMoodFragment fragment = new DetailMoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "DetailMoodFragment.onCreate() called");
        mMoodId = getArguments().getInt(ARG_JURNAL_ID);
        mMoodDetailViewModel = getMoodDetailViewModel();
        mMoodRepository = MoodRepository.get();

    }

    private void updateUI() {
        Log.i(TAG, "DetailMoodFragment.updateUI() called");
        if (mMood != null) {
            Log.d(TAG, "Data tidak kosong");
            //mMoodName.setText(mMood.getNilai());


            int nilai = mMood.getNilai();

            if (nilai==1){
                mMoodName.setText("Sangat Buruk");
                mImageMood.setImageResource(R.drawable.emoji5);

            }
            else if (nilai==2){
                mMoodName.setText("Buruk");
                mImageMood.setImageResource(R.drawable.emoji4);
            }
            else if (nilai==3){
                mMoodName.setText("Normal");
                mImageMood.setImageResource(R.drawable.emoji3);
            }
            else if (nilai==4){
                mMoodName.setText("Baik");
                mImageMood.setImageResource(R.drawable.emoji2);
            }
            else if (nilai==5){
                mMoodName.setText("Sangat Baik");
                mImageMood.setImageResource(R.drawable.emoji1);
            }

            mMoodKonten.setText(String.valueOf(mMood.getPerasaan()));

            String tglMulai = mMood.getTanggal();
            SimpleDateFormat formatLama = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatBaru = new SimpleDateFormat("EEEE,dd MMMM yyyy", new Locale("id", "ID"));
            //   Date date = null;
            try {
                Date date = formatLama.parse(tglMulai);
                String tanggalBaru = formatBaru.format(date);
                // date = formatLama.parse(tanggalBaru);
                mMoodTanggal.setText(tanggalBaru);

            } catch (ParseException e) {
                e.printStackTrace();
            }






        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_mood, container, false);
        mMoodName = v.findViewById(R.id.mood_judul);
        mMoodKonten = v.findViewById(R.id.mood_konten);
        mMoodTanggal = v.findViewById(R.id.mood_tanggal);


        mImageMood = v.findViewById(R.id.mood_gambar);

        ImageView mBackButton;

        mBackButton = v.findViewById(R.id.left_icon);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   getParentFragmentManager().popBackStack();
                onBackPressedDetailMood();

            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "DetailMoodFragment.onViewCreated() called");

        // Menambahkan callback ketika tombol back ditekan


        mMoodDetailViewModel.getMoodLiveData().observe(getViewLifecycleOwner(), new Observer<Mood>() {
            @Override
            public void onChanged(Mood mood) {
                mMood = mood;
                updateUI();
            }
        });
        mMoodDetailViewModel.loadMood(mMoodId);
    }



    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "DetailMoodFragment.onStop() called");
        // mMoodDetailViewModel.saveMood(mMood);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCallbacks = null;
    }
    private void onBackPressedDetailMood() {
        if (mCallbacks != null) {
            mCallbacks.onBackPressedDetailMood();
        }
    }
}
