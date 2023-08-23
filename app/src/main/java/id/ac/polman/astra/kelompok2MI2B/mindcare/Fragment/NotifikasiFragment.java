package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.DetailReminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Reminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;

import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.DetailReminderRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.DetailReminderListViewModel;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.DetailReminderViewModel;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.ReminderListViewModel;

public class NotifikasiFragment extends Fragment {


    private static final String TAG = "NotifikasiFragment";
    private DetailReminderListViewModel mDetailReminderListViewModel;
    private RecyclerView mDetailReminderRecyclerView;
    private DetailReminderAdapter mAdapter;
    private List<DetailReminder> mDetailReminders;

    private Reminder mReminder;

    private ReminderListViewModel mReminderListViewModel;

    private DetailReminderViewModel mDetailReminderViewModel;

    //  private RawRepository mRawRepository;

    private PenggunaRepository mPenggunaRepository;

    private DetailReminderRepository mDetailReminderRepository;
    private SharedPreferences pref;
    //  private DetailReminderRepository mDetailReminderRepository;

    public interface Callbacks {
        public void onDetailReminderSelectedNotif(int reminderId);
        public void onBackPressed();
    }

    private Callbacks mCallbacks = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach called");
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach called");
        mCallbacks = null;
    }

    private DetailReminderViewModel getReminderDetailViewModel(){
        if (mDetailReminderViewModel == null){
            mDetailReminderViewModel = new ViewModelProvider(this)
                    .get(DetailReminderViewModel.class);
        }
        return mDetailReminderViewModel;
    }


    private DetailReminderListViewModel getReminderDetailListViewModel(){
        if (mDetailReminderListViewModel == null){
            mDetailReminderListViewModel = new ViewModelProvider(this)
                    .get(DetailReminderListViewModel.class);
        }
        return mDetailReminderListViewModel;
    }



    private ReminderListViewModel getReminderListViewModel(){
        if (mReminderListViewModel == null){
            mReminderListViewModel = new ViewModelProvider(this)
                    .get(ReminderListViewModel.class);
        }
        return mReminderListViewModel;
    }




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "DetailReminderListFragment.onCreate() called");
        setHasOptionsMenu(true);


        mAdapter = new DetailReminderAdapter(Collections.<DetailReminder>emptyList());


        pref = requireContext().getSharedPreferences("session", Context.MODE_PRIVATE);

        mPenggunaRepository = PenggunaRepository.get();
        mDetailReminderRepository = DetailReminderRepository.get();

        mDetailReminderViewModel = getReminderDetailViewModel();
        mReminderListViewModel = getReminderListViewModel();
        mDetailReminderListViewModel = getReminderDetailListViewModel();



        //Log.d(TAG, "Total DetailReminder: " + mDetailReminderListViewModel.getDetailReminders().size());
    }

    private void updateUI(List<DetailReminder> detailReminders){
        //List<User> users = mUserListViewModel.getUsers();
        Log.i(TAG, "updateUI called");
        if (mDetailReminderRecyclerView != null) {
            mAdapter = new DetailReminderAdapter(detailReminders);
            mDetailReminderRecyclerView.setAdapter(mAdapter);
            mDetailReminders = detailReminders;
        }
    }

    public static NotifikasiFragment newInstance(){
        return new NotifikasiFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "UserListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_notifikasi_list,
                container, false);


        mDetailReminderRecyclerView = (RecyclerView)
                view.findViewById(R.id.notifikasi_recycler_view);
        mDetailReminderRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        //updateUI();
        mDetailReminderRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void loadDetailReminderData() {
        int idPengguna = pref.getInt("id_user", 0);
        mDetailReminderListViewModel.getDetailReminderByIdPengguna(idPengguna).observe(
                getViewLifecycleOwner(),
                new Observer<List<DetailReminder>>() {
                    @Override
                    public void onChanged(List<DetailReminder> detailReminders) {
                        // Periksa apakah data tidak null sebelum diberikan ke updateUI
                        if (detailReminders != null) {
                            updateUI(detailReminders);
                            Log.i(TAG, "Got DetailReminders: " + detailReminders.size());
                        } else {
                            Log.e(TAG, "DetailReminders is null");
                        }
                    }
                }
        );
    }
    private void onBackPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "UserListFragment.onViewCreated() called");
        int idPengguna = pref.getInt("id_user", 0);
        System.out.println("detailReminderList"+idPengguna);
//        double idUser = Double.parseDouble(nama);
//        int idPengguna = (int) idUser;


        ImageView leftIconImageView = view.findViewById(R.id.left_icon);
        leftIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mDetailReminderListViewModel.getDetailReminderByIdPengguna(idPengguna).observe(
                getViewLifecycleOwner(),
                new Observer<List<DetailReminder>>() {
                    @Override
                    public void onChanged(List<DetailReminder> detailReminders) {
                        //Update the cached copy of
                        updateUI(detailReminders);
                        Log.i(TAG, "Got DetailReminders: " + detailReminders.size());
                    }
                }
        );
        loadDetailReminderData();
       // mDetailReminderViewModel.loadPendaftaran(mPendaftaranId);
    }

    private class DetailReminderHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mJudulTextView;

        private TextView mPesanTextView;

        private TextView mKontenTextView;

        private ImageView mImageView;

        private ColorDrawable mColorDrawable;

        private RelativeLayout mRelativeLayout;
        private DetailReminder mDetailReminder;

       // private ReminderListFragment.Callbacks mCallbacks1 = null;

        public DetailReminderHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_notifikasi_item, parent, false));
            this.itemView.setOnClickListener(this);
            //buat ubah
       //     mCallbacks1 = (ReminderListFragment.Callbacks) getActivity();
            mJudulTextView = this.itemView.findViewById(R.id.NotifikasiJudul);
            mPesanTextView = this.itemView.findViewById(R.id.KontenNotifikasi);
            mKontenTextView = this.itemView.findViewById(R.id.JudulKontenNotifikasi);


            mRelativeLayout = itemView.findViewById(R.id.Button_id_mood_01);
        }

        public void bind(DetailReminder detailReminder) {
            mDetailReminder = detailReminder;
            mPesanTextView.setText(String.valueOf("Biar lebih semangat, yuk lihat "+detailReminder.getReminder().getReminder_tipe()+" ini ya !"));
            mKontenTextView.setText(String.valueOf("Ada " + detailReminder.getReminder().getReminder_tipe()
                    + " khusus buat kamu, judulnya " + detailReminder.getReminder().getReminder_judul()));
        }

        @Override
        public void onClick(View v) {


            Toast.makeText(getActivity(),
                            mDetailReminder.getReminder().getReminder_tipe() + " clicked!",
                            Toast.LENGTH_SHORT)
                    .show();
            System.out.println("id detail notif "+mDetailReminder.getId_detail());
            mDetailReminderRepository.deleteNotif(mDetailReminder.getId_detail());
            mCallbacks.onDetailReminderSelectedNotif(mDetailReminder.getReminder().getId_reminder());






        }
    }

    private class DetailReminderAdapter extends RecyclerView.Adapter<DetailReminderHolder>{

        private List<DetailReminder> mDetailReminderList;

        public DetailReminderAdapter(List<DetailReminder> detailReminders){
            mDetailReminderList = detailReminders;
        }

        @Override
        public DetailReminderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            return new DetailReminderHolder(layoutinflater, parent);
        }

        @Override
        public void onBindViewHolder(DetailReminderHolder holder, int position) {
            DetailReminder detailReminder = mDetailReminderList.get(position);
            holder.bind(detailReminder);
        }

        @Override
        public int getItemCount() {
            return mDetailReminderList.size();
        }


    }

}
