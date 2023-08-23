package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import static id.ac.polman.astra.kelompok2MI2B.mindcare.R.id.activity_main_fragment_container;
import static id.ac.polman.astra.kelompok2MI2B.mindcare.R.id.frame_layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import id.ac.polman.astra.kelompok2MI2B.mindcare.Activity.MainActivity;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.MoodRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.RawRepository;

public class MoodFragment extends Fragment {
    private static final String TAG = "MoodFragment";


    private EditText mPerasaanField;
    private ImageView mMoodView1;
    private ImageView mMoodView2;
    private ImageView mMoodView3;
    private ImageView mMoodView4;
    private ImageView mMoodView5;
    private Button btnSimpan;

    private Button btnRekomendasiJurnal;
    private Button btnRekomendasiKonten;

    private LinearLayout mLayoutMood;

    private TextView mNotif;


    private SharedPreferences pref;

    private SharedPreferences prefInput;

    private PenggunaRepository mPenggunaRepository;

    private MoodRepository mMoodRepository;
    private int nilaiMood;

    private Pengguna mPengguna;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String dateorder;

    private HomeFragment mHomeFragment;

    private int tombol;

    private Callbacks mCallbacks;


    public interface Callbacks {
        void onBackPressedMoodFragment();
    }

    public void setCallbacks(Callbacks callbacks) {
        mCallbacks = callbacks;
    }

    public static MoodFragment newInstance(){
        return new MoodFragment();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = requireContext().getSharedPreferences("session", Context.MODE_PRIVATE);

        mPenggunaRepository = PenggunaRepository.get();

        prefInput = requireContext().getSharedPreferences("mood_pref", Context.MODE_PRIVATE);
        mMoodRepository = MoodRepository.get();

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        int idPengguna = pref.getInt("id_user", 0);
//        double idUser = Double.parseDouble(nama);
//        int idPengguna = (int) idUser;

        ImageView mBackButton;
        mBackButton = view.findViewById(R.id.left_icon);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   getParentFragmentManager().popBackStack();
                System.out.println("MOOD: Back dipencet");
                onBackPressedMoodFragment();

            }
        });

        String buttonValue= prefInput.getString("tombol", "");
        double buttonMood = Double.parseDouble(buttonValue);
        int tombol = (int) buttonMood;
        System.out.println(idPengguna);
        // Inisialisasi UI

        mPerasaanField = view.findViewById(R.id.editTextPerasaan);
        mMoodView1 = view.findViewById(R.id.icon1);
        mMoodView2 = view.findViewById(R.id.icon2);
        mMoodView3 = view.findViewById(R.id.icon3);
        mMoodView4 = view.findViewById(R.id.icon4);
        mMoodView5 = view.findViewById(R.id.icon5);
        btnSimpan = view.findViewById(R.id.btn_simpanmood);

       mLayoutMood = view.findViewById(R.id.layoutmood);
       mNotif = view.findViewById(R.id.textafter);

        btnRekomendasiJurnal = view.findViewById(R.id.btn_rekomendasijurnal);
        btnRekomendasiKonten = view.findViewById(R.id.btn_rekomendasikonten);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateorder = dateFormat.format(calendar.getTime());



        Drawable originalIcon1 = mMoodView1.getDrawable();
        Drawable originalIcon2 = mMoodView2.getDrawable();
        Drawable originalIcon3= mMoodView3.getDrawable();
        Drawable originalIcon4 = mMoodView4.getDrawable();
        Drawable originalIcon5 = mMoodView5.getDrawable();
        System.out.println(tombol);

        if (tombol == 1){

            mMoodView1.setImageResource(R.drawable.emoji5);

            // Kembalikan gambar pada icon2 ke gambar asli
            mMoodView4.setImageDrawable(originalIcon4);
            mMoodView5.setImageDrawable(originalIcon5);
            mMoodView3.setImageDrawable(originalIcon3);
            mMoodView2.setImageDrawable(originalIcon2);

            Toast.makeText(getContext(), "Mood Anda Sangat Buruk, Semoga Harimu Lebih Baik!", Toast.LENGTH_SHORT).show();
            nilaiMood =1;
        }

        if (tombol == 2){

            mMoodView2.setImageResource(R.drawable.emoji4);

            mMoodView1.setImageDrawable(originalIcon1);
            mMoodView4.setImageDrawable(originalIcon4);
            mMoodView5.setImageDrawable(originalIcon5);
            mMoodView3.setImageDrawable(originalIcon3);

            Toast.makeText(getContext(), "Mood Anda Buruk, Semoga Semuanya berjalan baik!", Toast.LENGTH_SHORT).show();
            nilaiMood =2;
        }
        if (tombol==3){
            mMoodView3.setImageResource(R.drawable.emoji3);

            mMoodView1.setImageDrawable(originalIcon1);
            mMoodView2.setImageDrawable(originalIcon2);
            mMoodView4.setImageDrawable(originalIcon4);
            mMoodView5.setImageDrawable(originalIcon5);

            Toast.makeText(getContext(), "Mood Anda Sangat Normal, Semangat !", Toast.LENGTH_SHORT).show();
            nilaiMood =3;
        }

        if (tombol==4){
            mMoodView4.setImageResource(R.drawable.emoji2);

            mMoodView1.setImageDrawable(originalIcon1);
            mMoodView2.setImageDrawable(originalIcon2);
            mMoodView3.setImageDrawable(originalIcon3);
            mMoodView5.setImageDrawable(originalIcon5);

            Toast.makeText(getContext(), "Mood Anda Baik, Kamu yang terbaik!", Toast.LENGTH_SHORT).show();
            nilaiMood =4;
        }

        if (tombol==5){
            mMoodView5.setImageResource(R.drawable.emoji1);

            mMoodView1.setImageDrawable(originalIcon1);
            mMoodView2.setImageDrawable(originalIcon2);
            mMoodView4.setImageDrawable(originalIcon4);
            mMoodView3.setImageDrawable(originalIcon3);

            Toast.makeText(getContext(), "Mood Anda Sangat Baik, Tetap Jaga ya!", Toast.LENGTH_SHORT).show();
            nilaiMood =5;
        }



        mMoodView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoodView1.setImageResource(R.drawable.emoji5);

                // Kembalikan gambar pada icon2 ke gambar asli
                mMoodView4.setImageDrawable(originalIcon4);
                mMoodView5.setImageDrawable(originalIcon5);
                mMoodView3.setImageDrawable(originalIcon3);
                mMoodView2.setImageDrawable(originalIcon2);

                Toast.makeText(getContext(), "Mood Anda Sangat Buruk, Semoga Harimu Lebih Baik!", Toast.LENGTH_SHORT).show();
                nilaiMood =1;

            }
        });

        mMoodView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoodView2.setImageResource(R.drawable.emoji4);

                mMoodView1.setImageDrawable(originalIcon1);
                mMoodView4.setImageDrawable(originalIcon4);
                mMoodView5.setImageDrawable(originalIcon5);
                mMoodView3.setImageDrawable(originalIcon3);

                Toast.makeText(getContext(), "Mood Anda Buruk, Semoga Semuanya berjalan baik!", Toast.LENGTH_SHORT).show();
                nilaiMood =2;
            }
        });

        mMoodView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoodView3.setImageResource(R.drawable.emoji3);

                mMoodView1.setImageDrawable(originalIcon1);
                mMoodView2.setImageDrawable(originalIcon2);
                mMoodView4.setImageDrawable(originalIcon4);
                mMoodView5.setImageDrawable(originalIcon5);

                Toast.makeText(getContext(), "Mood Anda Sangat Normal, Semangat !", Toast.LENGTH_SHORT).show();
                nilaiMood =3;
            }
        });

        mMoodView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMoodView4.setImageResource(R.drawable.emoji2);

                mMoodView1.setImageDrawable(originalIcon1);
                mMoodView2.setImageDrawable(originalIcon2);
                mMoodView3.setImageDrawable(originalIcon3);
                mMoodView5.setImageDrawable(originalIcon5);

                Toast.makeText(getContext(), "Mood Anda Baik, Kamu yang terbaik!", Toast.LENGTH_SHORT).show();
                nilaiMood =4;

            }
        });



        mMoodView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMoodView5.setImageResource(R.drawable.emoji1);

                mMoodView1.setImageDrawable(originalIcon1);
                mMoodView2.setImageDrawable(originalIcon2);
                mMoodView4.setImageDrawable(originalIcon4);
                mMoodView3.setImageDrawable(originalIcon3);

                Toast.makeText(getContext(), "Mood Anda Sangat Baik, Tetap Jaga ya!", Toast.LENGTH_SHORT).show();
                nilaiMood =5;

            }
        });


        // Inisialisasi Repository
        mMoodRepository = MoodRepository.get();

        // Mengatur aksi klik pada tombol simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat objek pengguna baru
                Mood mood = new Mood();
                mood.setPerasaan(mPerasaanField.getText().toString());
                mood.setId_user(idPengguna);

                System.out.println("mood save :"+idPengguna);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    System.out.println(dateorder);
                    mood.setTanggal(dateorder);

                }

                if (nilaiMood != 0) {
                    SharedPreferences.Editor editor = prefInput.edit();
                    mood.setNilai(nilaiMood);

                    editor.putString("nilaiMood", String.valueOf(nilaiMood));
                    mMoodRepository.addMood(mood);
                    mLayoutMood.setVisibility(View.GONE);
                    mNotif.setVisibility(View.VISIBLE);

                    btnRekomendasiJurnal.setVisibility(View.VISIBLE);

                    btnRekomendasiKonten.setVisibility(View.VISIBLE);



                    editor.putString("tanggalTerakhir", dateorder);
                    editor.apply();
                }
                else {
                    Toast.makeText(getContext(), "Mohon isi nilai mood terlebih dahulu", Toast.LENGTH_SHORT).show();
                }

                //menyimpan data input tanggal

                // Melakukan permintaan registrasi




//                HomeFragment fragmentHome = new HomeFragment();
//                replaceFragment(fragmentHome);
                System.out.println("Mood telah masuk di FRAGMENT " + mPerasaanField.getText().toString());
            }
        });

        btnRekomendasiJurnal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // menampilkan fragment rekomendasikonten

                JurnalListFragmentRekomendasi fragmentjurnallist = new JurnalListFragmentRekomendasi();
                FragmentKosong(fragmentjurnallist);


            }
        });
        btnRekomendasiKonten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // menampilkan fragment rekomendasikonten

                ReminderListFragmentRekomendasi fragmentrekomendasilist = new ReminderListFragmentRekomendasi();
                FragmentKosong(fragmentrekomendasilist);


            }
        });


        return view;

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frame_layout, fragment);
        fragmentTransaction.commit();
    }
    private void FragmentKosong(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frame_layout, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCallbacks = null;
    }

    private void onBackPressedMoodFragment() {
        if (mCallbacks != null) {
            mCallbacks.onBackPressedMoodFragment();
        }else {
            System.out.println("Callback Mood Kosong");
        }
    }
}
