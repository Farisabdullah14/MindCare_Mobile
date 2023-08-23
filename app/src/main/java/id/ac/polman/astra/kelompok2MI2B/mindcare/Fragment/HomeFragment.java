package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.DetailReminderRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.RawRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.ReminderRepository;


public class HomeFragment extends Fragment {
    private RawRepository mRawRepository;

    private PenggunaRepository mPenggunaRepository;

    private ReminderRepository mReminderRepository;

    private DetailReminderRepository mDetailReminderRepository;

    private SharedPreferences pref;
    private TextView mNama;
    private ImageView logoutCard;

    private ImageView mNotif;


    private TextView mTotalMoodWeekly;
    private TextView mAverageMoodWeekly;

    private TextView mTotalNotif;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //untuk mengambil data dari session
        pref = requireContext().getSharedPreferences("session", Context.MODE_PRIVATE);
        mRawRepository = RawRepository.get();
        mPenggunaRepository = PenggunaRepository.get();
        mReminderRepository = ReminderRepository.get();


        mDetailReminderRepository = DetailReminderRepository.get();

    }

    @Override
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mNama  = (TextView) view.findViewById(R.id.nama_user);

        //untuk total mood

        mTotalMoodWeekly = view.findViewById(R.id.mood_mingguan);

        mTotalNotif = view.findViewById(R.id.textNotif);

        //total mood rata-rata

        mAverageMoodWeekly = view.findViewById(R.id.total_mood_rata);
        String nama = pref.getString("nama", "");

        int idPengguna = pref.getInt("id_user", 0);

        System.out.println("HomeFragment"+idPengguna);

       // System.out.println("main activity"+idPengguna);

      //  int idPengguna = (int) idUser;

        //untuk rata rata
        mRawRepository.getDataTotalWeekly(idPengguna).observe(getViewLifecycleOwner(), totalMood -> {

            if (totalMood == null){
                mAverageMoodWeekly.setText("Belum ada data");
            }else {
                mAverageMoodWeekly.setText(String.valueOf(totalMood));
            }
            // Gunakan nilai totalMood di sini untuk keperluan Anda
            // Contoh: Tampilkan nilai totalMood dalam TextView

        });

        mRawRepository.getDataTotal(idPengguna).observe(getViewLifecycleOwner(), totalMood1 -> {
            // Gunakan nilai totalMood di sini untuk keperluan Anda
            // Contoh: Tampilkan nilai totalMood dalam TextView

            if (totalMood1 == null){
                mTotalMoodWeekly.setText("Belum ada data");
            }else {
                mTotalMoodWeekly.setText(String.valueOf(totalMood1));
            }

        });

        mRawRepository.getDataTotalNotif(idPengguna).observe(getViewLifecycleOwner(), totalNotif1 -> {
            // Gunakan nilai totalMood di sini untuk keperluan Anda
            // Contoh: Tampilkan nilai totalMood dalam TextView

            if (totalNotif1 == null){
                mTotalNotif.setText(00);

            }else {
                if (totalNotif1==0){
                    mTotalNotif.setVisibility(View.GONE);
                }
                else {
                    mTotalNotif.setText(String.valueOf(totalNotif1));
                }


            }

        });



        mNama.setText(nama);

        RelativeLayout relativeLayout = view.findViewById(R.id.button_psikolog);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat instance dari AlamatPsikologFragment
                PsikologListFragment psikologListFragment = new PsikologListFragment();

                // Memperoleh instance FragmentManager
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Membuat transaksi fragment
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Menggantikan fragment_home dengan AlamatPsikologFragment
                fragmentTransaction.replace(R.id.activity_main_fragment_container, psikologListFragment);

                // Menambahkan transaksi ke back stack agar dapat kembali ke fragment_home jika diperlukan
                fragmentTransaction.addToBackStack(null);

                // Melakukan commit transaksi
                fragmentTransaction.commit();
            }
        });

        RelativeLayout relativeLayout1 = view.findViewById(R.id.button_konselor);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat instance dari AlamatPsikologFragment
                KonselorListFragment konselorListFragment = new KonselorListFragment();

                // Memperoleh instance FragmentManager
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Membuat transaksi fragment
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Menggantikan fragment_home dengan AlamatPsikologFragment
                fragmentTransaction.replace(R.id.activity_main_fragment_container, konselorListFragment);

                // Menambahkan transaksi ke back stack agar dapat kembali ke fragment_home jika diperlukan
                fragmentTransaction.addToBackStack(null);

                // Melakukan commit transaksi
                fragmentTransaction.commit();
            }
        });


        mNotif = view.findViewById(R.id.notif);
        mNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pengecekan session
                NotifikasiFragment notifikasiFragment = new NotifikasiFragment();

                // Memperoleh instance FragmentManager
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Membuat transaksi fragment
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Menggantikan fragment_home dengan AlamatPsikologFragment
                fragmentTransaction.replace(R.id.activity_main_fragment_container, notifikasiFragment);

                // Menambahkan transaksi ke back stack agar dapat kembali ke fragment_home jika diperlukan
                fragmentTransaction.addToBackStack(null);

                // Melakukan commit transaksi
                fragmentTransaction.commit();
            }
        });

        logoutCard = view.findViewById(R.id.keluar);
        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pengecekan session
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
                // Menghapus session
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Kembali ke LoginFragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_main_fragment_container, new LoginFragment());
                transaction.commit();
                Toast.makeText(getContext(), "Logout berhasil ", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
}