package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Reminder;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.helper.LinkifiedTextView;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.ReminderListViewModel;

public class ReminderListFragmentRekomendasi extends Fragment {


    private static final String TAG = "ReminderListFragment";
    private ReminderListViewModel mReminderListViewModel;
    private RecyclerView mReminderRecyclerView;
    private ReminderAdapter mAdapter;
    private List<Reminder> mReminders;
    private String mSelectedFilter = "Pilih";

    public interface Callbacks {
        public void onReminderSelectedRekomendasi(int reminderId);
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
        Log.i(TAG, "ReminderListFragment.onCreate() called");
        setHasOptionsMenu(true);
        mReminderListViewModel = new ViewModelProvider(this)
                .get(ReminderListViewModel.class);
        mAdapter = new ReminderAdapter(Collections.<Reminder>emptyList());
        //Log.d(TAG, "Total Reminder: " + mReminderListViewModel.getReminders().size());
    }

    private void updateUI(List<Reminder> reminders){
        //List<User> users = mUserListViewModel.getUsers();
        Log.i(TAG, "updateUI called");
        mAdapter = new ReminderAdapter(reminders);
        mReminderRecyclerView.setAdapter(mAdapter);
        mReminders = reminders;
    }

    public static ReminderListFragmentRekomendasi newInstance(){
        return new ReminderListFragmentRekomendasi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "UserListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_reminder_list,
                container, false);


        mReminderRecyclerView = (RecyclerView)
                view.findViewById(R.id.reminder_recycler_view);
        mReminderRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        //updateUI();
        mReminderRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "UserListFragment.onViewCreated() called");
        mReminderListViewModel.getReminders().observe(
                getViewLifecycleOwner(),
                new Observer<List<Reminder>>() {
                    @Override
                    public void onChanged(List<Reminder> reminders) {
                        //Update the cached copy of
                        updateUI(reminders);
                        Log.i(TAG, "Got Reminders: " + reminders.size());
                    }
                }
        );

        //untuk dropdown
        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFilter = parent.getItemAtPosition(position).toString();
                mSelectedFilter = selectedFilter;

                selectRandomItem();
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });



    }

    private void selectRandomItem() {
        int itemCount = getResources().getStringArray(R.array.type).length;
        int randomPosition = new Random().nextInt(itemCount);
        Spinner spinner = getView().findViewById(R.id.spinner);
        spinner.setSelection(randomPosition);
        updateFilteredData();
    }


    private void updateFilteredData() {
        List<Reminder> filteredList = new ArrayList<>();
        if (mReminders != null) {
            if (mSelectedFilter.equals("Pilih")) {
                // Jika "Select One" dipilih, tampilkan semua data historis
                filteredList.addAll(mReminders);
            } else {
                // Jika filter lain dipilih, lakukan pemfilteran berdasarkan jenis reminder
                for (Reminder reminder : mReminders) {
                    if (mSelectedFilter.equals("Audio") && reminder.getReminder_tipe().equals("Audio") ) {
                        filteredList.add(reminder);
                    } else if (mSelectedFilter.equals("Video") && reminder.getReminder_tipe().equals( "Kesehatan Hati")) {
                        filteredList.add(reminder);
                    } else if (mSelectedFilter.equals("Video") && reminder.getReminder_tipe() .equals("Video") ){
                        filteredList.add(reminder);
                    } else if (mSelectedFilter.equals("Web") && reminder.getReminder_tipe() .equals( "Web")) {
                        filteredList.add(reminder);
                    }
                }
            }
        }
        mAdapter.setData(filteredList);

    }

    private class ReminderHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mNamaTextView;

        private LinkifiedTextView mKontenTextView;

        private TextView mJenisTextView;

        private TextView mDeskripsiTextView;
        private ImageView mImageReminder;


        private Reminder mReminder;

        public ReminderHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_reminder_item, parent, false));
            this.itemView.setOnClickListener(this);
            //buat ubah
            mNamaTextView = this.itemView.findViewById(R.id.nama_reminder);
            mKontenTextView = this.itemView.findViewById(R.id.konten_reminder);
            mJenisTextView = this.itemView.findViewById(R.id.jenis_reminder);
            mDeskripsiTextView = this.itemView.findViewById(R.id.deskripsi_reminder);
            mImageReminder = this.itemView.findViewById(R.id.image_reminder);
        }

        public void bind(Reminder reminder){
            mReminder = reminder;
            mNamaTextView.setText(String.valueOf(mReminder.getReminder_judul()));
            mKontenTextView.setText(String.valueOf(mReminder.getReminder_konten()));
            mJenisTextView.setText(String.valueOf(mReminder.getReminder_tipe()));
            mDeskripsiTextView.setText(String.valueOf(mReminder.getReminder_deskripsi()));

            String gambar = mReminder.getReminder_gambar();


            //String imageUrl = "http://192.168.100.60:8080/uploads/gambar/" + gambar;

            String imageUrl = "http://192.168.0.160:8080/uploads/gambar/" + gambar;
            Picasso.get().load(imageUrl).into(mImageReminder);

        }

        @Override
        public void onClick(View v) {


            Toast.makeText(getActivity(),
                            mReminder.getReminder_judul() + " clicked!",
                            Toast.LENGTH_SHORT)
                    .show();
            mCallbacks.onReminderSelectedRekomendasi(mReminder.getId_reminder());
        }
    }

    private class ReminderAdapter extends RecyclerView.Adapter<ReminderHolder>{

        private List<Reminder> mReminderList;

        public ReminderAdapter(List<Reminder> reminders){
            mReminderList = reminders;
        }

        public void setData(List<Reminder> reminders) {
            mReminderList = reminders;
            notifyDataSetChanged();
        }





        @Override
        public ReminderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            return new ReminderHolder(layoutinflater, parent);
        }

        @Override
        public void onBindViewHolder(ReminderHolder holder, int position) {
            Reminder reminder = mReminderList.get(position);
            holder.bind(reminder);
        }

        @Override
        public int getItemCount() {
            return mReminderList.size();
        }


    }

}