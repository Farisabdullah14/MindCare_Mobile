package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.databinding.ActivityMainBinding;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.MoodRepository;

public class DashboardFragment extends Fragment implements JurnalListFragment.Callbacks, DetailJurnalFragment.Callbacks{
    private static final String TAG = "DashboardFragment";
    ActivityMainBinding binding;

    private FloatingActionButton fabButton;
    private MoodFragment mMoodFragment;
    private BottomSheetDialog bottomSheetDialog;

    private MoodRepository mMoodRepository;

    private SharedPreferences prefInput;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String dateorder;

    private String mood;

    public static DashboardFragment newInstance(){
        return new DashboardFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(inflater, container, false);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateorder = dateFormat.format(calendar.getTime());
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefInput = requireContext().getSharedPreferences("mood_pref", Context.MODE_PRIVATE);
        mMoodRepository = MoodRepository.get();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.jurnal:
                    replaceFragment(new JurnalListFragment());
                    break;
                case R.id.Statik:
                    replaceFragment(new MoodListFragment());
                    break;
                case R.id.akun:
                    replaceFragment(new ReminderListFragment());
                    break;
            }
            return true;
        });


        fabButton = view.findViewById(R.id.button_emote);
        fabButton.setOnClickListener(v -> showIconSheetDialog());

        BottomAppBar bottomAppBar = binding.bottomAppBar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(bottomAppBar);
    }


    public void showIconSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.sheet_icon);

        //untuk membatasi input hanya 1 x per hari(dimatiin dulu waktu development)

        String lastInputDate = prefInput.getString("tanggalTerakhir", "");

       if (lastInputDate.equals(dateorder)) {
            // Tampilkan pesan bahwa  input sudah dilakukan hari ini
            TextView teksInput = bottomSheetDialog.findViewById(R.id.harianinput);
            teksInput.setVisibility(View.VISIBLE);
        }else {

        ImageView icon1 = bottomSheetDialog.findViewById(R.id.icon1);
        ImageView icon2 = bottomSheetDialog.findViewById(R.id.icon2);
        ImageView icon3 = bottomSheetDialog.findViewById(R.id.icon3);
        ImageView icon4 = bottomSheetDialog.findViewById(R.id.icon4);
        ImageView icon5 = bottomSheetDialog.findViewById(R.id.icon5);

        icon1.setOnClickListener(v -> {
            mood = "1";
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
            saveIsClicked();

        });

        icon2.setOnClickListener(v -> {
            // Logika yang akan dijalankan saat icon2 diklik
            mood = "2";
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
            saveIsClicked();

        });

        icon3.setOnClickListener(v -> {
            // Logika yang akan dijalankan saat icon3 diklik
            mood = "3";
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
            saveIsClicked();

        });

        icon4.setOnClickListener(v -> {
            // Logika yang akan dijalankan saat icon4 diklik
            mood = "4";
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
            saveIsClicked();

        });

        icon5.setOnClickListener(v -> {
            mood = "5";
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
            saveIsClicked();

        });

    }



        System.out.println(mood);
        bottomSheetDialog.show();
    }

    private void saveIsClicked() {
        SharedPreferences.Editor editor = prefInput.edit();
        editor.putString("tombol", mood);
        editor.apply();

        String buttonValue = prefInput.getString("tombol", "");
        System.out.println("nilai button" + buttonValue);
    }



    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragment2(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager(); // Use getChildFragmentManager() here
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void onJurnalSelected(int jurnalId) {
        Log.i(TAG, "id_section = " + jurnalId);

        Fragment fragment = DetailJurnalFragment.newInstance(jurnalId);
        ((DetailJurnalFragment) fragment).setCallbacks(this);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.activity_main_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackPressedDetailJurnal() {
        replaceFragment(new JurnalListFragment());
    }

    @Override
    public void onBackPressed() {
        replaceFragment(new JurnalListFragment());
    }
}