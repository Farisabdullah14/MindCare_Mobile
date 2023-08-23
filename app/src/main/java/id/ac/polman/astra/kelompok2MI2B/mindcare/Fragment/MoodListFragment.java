package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.annotation.SuppressLint;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;

import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.MoodRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.RawRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.MoodListViewModel;


public class MoodListFragment extends Fragment {


    private static final String TAG = "MoodListFragment";
    private MoodListViewModel mMoodListViewModel;
    private RecyclerView mMoodRecyclerView;
    private MoodAdapter mAdapter;
    private List<Mood> mMoods;
 //  private RawRepository mRawRepository;

    private PenggunaRepository mPenggunaRepository;
    private SharedPreferences pref;
  //  private MoodRepository mMoodRepository;

    public interface Callbacks {
        public void onMoodSelected(int moodId);
        public void onMoodDetailSelected(int moodId);
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



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "MoodListFragment.onCreate() called");
        setHasOptionsMenu(true);
        mMoodListViewModel = new ViewModelProvider(this)
                .get(MoodListViewModel.class);
        mAdapter = new MoodAdapter(Collections.<Mood>emptyList());


        pref = requireContext().getSharedPreferences("session", Context.MODE_PRIVATE);

       mPenggunaRepository = PenggunaRepository.get();


        //Log.d(TAG, "Total Mood: " + mMoodListViewModel.getMoods().size());
    }

    private void updateUI(List<Mood> moods){
        //List<User> users = mUserListViewModel.getUsers();
        Log.i(TAG, "updateUI called");
        mAdapter = new MoodAdapter(moods);
        mMoodRecyclerView.setAdapter(mAdapter);
        mMoods = moods;
    }

    public static MoodListFragment newInstance(){
        return new MoodListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "UserListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_mood_list,
                container, false);


        mMoodRecyclerView = (RecyclerView)
                view.findViewById(R.id.mood_recycler_view);
        mMoodRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        //updateUI();
        mMoodRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "UserListFragment.onViewCreated() called");
        int idPengguna = pref.getInt("id_user", 0);
        System.out.println("moodList"+idPengguna);
//        double idUser = Double.parseDouble(nama);
//        int idPengguna = (int) idUser;
        mMoodListViewModel.getMoodsById(idPengguna).observe(
                getViewLifecycleOwner(),
                new Observer<List<Mood>>() {
                    @Override
                    public void onChanged(List<Mood> moods) {
                        //Update the cached copy of
                        updateUI(moods);
                        Log.i(TAG, "Got Moods: " + moods.size());
                    }
                }
        );
    }

    private class MoodHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mNilaiTextView;

        private TextView mTanggalTextView;

        private TextView mKontenTextView;

        private ImageView mImageView;

        private ColorDrawable mColorDrawable;

        private RelativeLayout mRelativeLayout;
        private Mood mMood;

        public MoodHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_mood_item, parent, false));
            this.itemView.setOnClickListener(this);
            //buat ubah
            mNilaiTextView = this.itemView.findViewById(R.id.nilai_mood);
            mTanggalTextView = this.itemView.findViewById(R.id.tanggal_mood);
            mKontenTextView = this.itemView.findViewById(R.id.konten_mood);
            mImageView = this.itemView.findViewById(R.id.icon_mood);
            mColorDrawable = new ColorDrawable();
            mColorDrawable.setBounds(0, 0, 0, 0);
            mRelativeLayout = itemView.findViewById(R.id.Button_id_mood_01);
        }

        public void bind(Mood mood){
            mMood = mood;

            String tglMulai = mMood.getTanggal();
            SimpleDateFormat formatLama = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatBaru = new SimpleDateFormat("EEEE,dd MMMM yyyy", new Locale("id", "ID"));
         //   Date date = null;
            try {
                Date  date = formatLama.parse(tglMulai);
                String tanggalBaru = formatBaru.format(date);
               // date = formatLama.parse(tanggalBaru);
                mTanggalTextView.setText(tanggalBaru);

            } catch (ParseException e) {
                e.printStackTrace();
            }





            int colorResId = getBackgroundColor(mMood.getNilai());
            mRelativeLayout.setBackgroundColor(itemView.getContext().getResources().getColor(colorResId));

            //  mTanggalTextView.setText(String.valueOf(mMood.getTanggal_mood()));

            int nilai = mMood.getNilai();

            if (nilai==1){
                mNilaiTextView.setText("Mood anda : Sangat Buruk");
                mImageView.setImageResource(R.drawable.emoji5);

            }
            else if (nilai==2){
                mNilaiTextView.setText("Mood anda : Buruk");
                mImageView.setImageResource(R.drawable.emoji4);
            }
            else if (nilai==3){
                mNilaiTextView.setText("Mood anda : Normal");
                mImageView.setImageResource(R.drawable.emoji3);
            }
            else if (nilai==4){
                mNilaiTextView.setText("Mood anda : Baik");
                mImageView.setImageResource(R.drawable.emoji2);
            }
            else if (nilai==5){
                mNilaiTextView.setText("Mood anda : Sangat Baik");
                mImageView.setImageResource(R.drawable.emoji1);
            }


            mKontenTextView.setText(String.valueOf(mMood.getPerasaan()));
        }

        private int getBackgroundColor(int nilai) {
            switch (nilai) {
                case 1:
                    return R.color.bg_emoji_merah; // Replace with the color resource for "Sangat Buruk"
                case 2:
                    return R.color.bg_emoji_kuning; // Replace with the color resource for "Buruk"
                case 3:
                    return R.color.bg_emoji_abu_abu; // Replace with the color resource for "Normal"
                case 4:
                    return R.color.bg_emoji_hijau; // Replace with the color resource for "Baik"
                case 5:
                    return R.color.bg_emoji_biru; // Replace with the color resource for "Bersemangat"
                default:
                    return R.color.black; // Replace with the default color resource
            }

        }

        @Override
        public void onClick(View v) {


            Toast.makeText(getActivity(),
                            mMood.getNilai() + " clicked!",
                            Toast.LENGTH_SHORT)
                    .show();
            mCallbacks.onMoodDetailSelected(mMood.getId_mood());
        }
    }

    private class MoodAdapter extends RecyclerView.Adapter<MoodHolder>{

        private List<Mood> mMoodList;

        public MoodAdapter(List<Mood> moods){
            mMoodList = moods;
        }

        @Override
        public MoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            return new MoodHolder(layoutinflater, parent);
        }

        @Override
        public void onBindViewHolder(MoodHolder holder, int position) {
            Mood mood = mMoodList.get(position);
            holder.bind(mood);
        }

        @Override
        public int getItemCount() {
            return mMoodList.size();
        }


    }

}
