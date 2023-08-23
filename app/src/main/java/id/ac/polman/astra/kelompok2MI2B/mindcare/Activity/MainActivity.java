package id.ac.polman.astra.kelompok2MI2B.mindcare.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

/*import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.HomeFragment;*/
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.DashboardFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.DetailJurnalFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.DetailMoodFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.DetailReminderFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.HomeFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.JurnalListFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.JurnalListFragmentRekomendasi;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.KonselorListFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.LoginFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.MoodFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.MoodListFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.NotifikasiFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.OnBordingFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.PsikologListFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.RegisterFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.ReminderListFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.ReminderListFragmentRekomendasi;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Psikolog;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;

import id.ac.polman.astra.kelompok2MI2B.mindcare.helper.DailyReminderScheduler;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.DetailReminderRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.JurnalRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.KonselorRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.MoodRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PsikologRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.RawRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.ReminderRepository;

public class MainActivity extends AppCompatActivity implements JurnalListFragment.Callbacks, PsikologListFragment.Callbacks, KonselorListFragment.Callbacks,
        DetailJurnalFragment.Callbacks, MoodListFragment.Callbacks, ReminderListFragment.Callbacks,
        ReminderListFragmentRekomendasi.Callbacks, JurnalListFragmentRekomendasi.Callbacks,
        NotifikasiFragment.Callbacks , DetailReminderFragment.Callbacks, DetailMoodFragment.Callbacks, MoodFragment.Callbacks {
    private static final String TAG = "MainActivity";

    private PenggunaRepository mPenggunaRepository;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kosong);

        JurnalRepository.initialize(this);
        PsikologRepository.initialize(this);
        PenggunaRepository.initialize(this);
        KonselorRepository.initialize(this);
        MoodRepository.initialize(this);
        RawRepository.initialize(this);
        ReminderRepository.initialize(this);

        DetailReminderRepository.initialize(this);
        DailyReminderScheduler.scheduleDailyReminder(this);
        MoodRepository.initialize(this);


        initComponents();
    }

    private void initComponents() {
        pref = getSharedPreferences("session", MODE_PRIVATE);
        mPenggunaRepository = PenggunaRepository.get();

        System.out.println("session di main activity"+pref);

        if (pref.contains("npk") ) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.activity_main_fragment_container);
            fragment = new DashboardFragment().newInstance();

            if (fragment == null) {
                fm.beginTransaction().add(R.id.activity_main_fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                fm.beginTransaction()
                        .replace(R.id.activity_main_fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        } else {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.activity_main_fragment_container);
            fragment = new OnBordingFragment().newInstance();
            fm.beginTransaction().replace(R.id.activity_main_fragment_container, fragment).commit();
        }
    }

    @Override
    public void onJurnalSelected(int jurnalId) {
        Log.i(TAG, "id_section = " + jurnalId);
        Fragment fragment = DetailJurnalFragment.newInstance(jurnalId);
        getSupportFragmentManager().beginTransaction()

                .replace(R.id.activity_main_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onPsikologSelected(int psikologId) {
        Log.i(TAG,"id_psikolog = " + psikologId);
        Fragment fragment = PsikologListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_home, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onKonselorSelected(int konselorId) {
        Log.i(TAG,"id_psikolog = " + konselorId);
        Fragment fragment = KonselorListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_home, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onMoodSelected(int moodId) {
        Log.i(TAG,"id_psikolog = " + moodId);
        Fragment fragment = MoodListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_home, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onReminderSelected(int reminderId) {
        Log.i(TAG,"id_reminder = " + reminderId);
        Fragment fragment = ReminderListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_home, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onJurnalSelectedRekomendasi(int jurnalId) {

        Log.i(TAG, "id_section = " + jurnalId);
        Fragment fragment = JurnalListFragmentRekomendasi.newInstance();
        getSupportFragmentManager().beginTransaction()

                .replace(R.id.fragment_mood, fragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onReminderSelectedRekomendasi(int reminderId) {
        Log.i(TAG,"id_reminder = " + reminderId);
        Fragment fragment = ReminderListFragmentRekomendasi.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_mood, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressedDetailJurnal() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressedMoodFragment() {
   //     getSupportFragmentManager().popBackStack();

//
        Fragment fragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressedDetailReminder() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressedDetailMood() {
        getSupportFragmentManager().popBackStack();
    }

    public void onBackPressedPsikolog() {
        getSupportFragmentManager().popBackStack();
    }


    @Override
    public void onDetailReminderSelected(int reminderId) {

        Log.i(TAG,"id_reminder = " + reminderId);

        Fragment fragment = DetailReminderFragment.newInstance(reminderId);
        // Fragment fragment = NotifikasiFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onDetailReminderSelectedNotif(int reminderId) {
       Fragment fragment = DetailReminderFragment.newInstance(reminderId);
     //   Fragment fragment = NotifikasiFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onMoodDetailSelected(int moodId) {
        Fragment fragment = DetailMoodFragment.newInstance(moodId);
        //   Fragment fragment = NotifikasiFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
