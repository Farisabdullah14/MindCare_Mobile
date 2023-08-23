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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.JurnalListViewModel;

public class JurnalListFragment extends Fragment {


    private static final String TAG = "JurnalListFragment";
    private JurnalListViewModel mJurnalListViewModel;
    private RecyclerView mJurnalRecyclerView;
    private JurnalAdapter mAdapter;
    private List<Jurnal> mJurnals;
    private String mSelectedFilter = "Pilih";

    public interface Callbacks {
        public void onJurnalSelected(int jurnalId);
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
        Log.i(TAG, "JurnalListFragment.onCreate() called");
        setHasOptionsMenu(true);
        mJurnalListViewModel = new ViewModelProvider(this)
                .get(JurnalListViewModel.class);
        mAdapter = new JurnalAdapter(Collections.<Jurnal>emptyList());
        //Log.d(TAG, "Total Jurnal: " + mJurnalListViewModel.getJurnals().size());
    }

    private void updateUI(List<Jurnal> jurnals){
        //List<User> users = mUserListViewModel.getUsers();
        Log.i(TAG, "updateUI called");
        mAdapter = new JurnalAdapter(jurnals);
        mJurnalRecyclerView.setAdapter(mAdapter);
        mJurnals = jurnals;
    }

    public static JurnalListFragment newInstance(){
        return new JurnalListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "UserListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_jurnal_list,
                container, false);


        mJurnalRecyclerView = (RecyclerView)
                view.findViewById(R.id.jurnal_recycler_view);
        mJurnalRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        //updateUI();
        mJurnalRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "UserListFragment.onViewCreated() called");
        mJurnalListViewModel.getJurnals().observe(
                getViewLifecycleOwner(),
                new Observer<List<Jurnal>>() {
                    @Override
                    public void onChanged(List<Jurnal> jurnals) {
                        //Update the cached copy of
                        updateUI(jurnals);
                        Log.i(TAG, "Got Jurnals: " + jurnals.size());
                    }
                }
        );

        //untuk dropdown
        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.choosen));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFilter = parent.getItemAtPosition(position).toString();
                mSelectedFilter = selectedFilter;
                updateFilteredData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });



    }

    private void updateFilteredData() {
        List<Jurnal> filteredList = new ArrayList<>();
        if (mJurnals != null) {
            if (mSelectedFilter.equals("Pilih")) {
                // Jika "Select One" dipilih, tampilkan semua data historis
                filteredList.addAll(mJurnals);
            } else {
                // Jika filter lain dipilih, lakukan pemfilteran berdasarkan jenis jurnal
                for (Jurnal jurnal : mJurnals) {
                    if (mSelectedFilter.equals("Kesehatan Mental") && jurnal.getJenis_jurnal().equals("Kesehatan Mental") ) {
                        filteredList.add(jurnal);
                    } else if (mSelectedFilter.equals("Kesehatan Hati") && jurnal.getJenis_jurnal().equals( "Kesehatan Hati")) {
                        filteredList.add(jurnal);
                    } else if (mSelectedFilter.equals("Kasih Sayang") && jurnal.getJenis_jurnal() .equals("Kasih Sayang") ){
                        filteredList.add(jurnal);
                    } else if (mSelectedFilter.equals("Kesehatan Fisik") && jurnal.getJenis_jurnal() .equals( "Kesehatan Fisik")) {
                        filteredList.add(jurnal);
                    } else if (mSelectedFilter.equals("Semangat Berprestasi") && jurnal.getJenis_jurnal().equals( "Semangat Berprestasi")) {
                        filteredList.add(jurnal);
                    }
                }
            }
        }
        mAdapter.setData(filteredList);

    }

    private class JurnalHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mNamaTextView;

        private TextView mTanggalTextView;

        private TextView mJenisTextView;

        private ImageView mImageJurnal;


        private Jurnal mJurnal;

        public JurnalHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_jurnal_item, parent, false));
            this.itemView.setOnClickListener(this);
            //buat ubah
            mNamaTextView = this.itemView.findViewById(R.id.nama_jurnal);
            mTanggalTextView = this.itemView.findViewById(R.id.konten_jurnal);
            mJenisTextView = this.itemView.findViewById(R.id.jenis_jurnal);

            mImageJurnal = this.itemView.findViewById(R.id.imageJurnal);

        }

        public void bind(Jurnal jurnal){
            mJurnal = jurnal;

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



            mTanggalTextView.setText(formattedDate);


            String gambar = mJurnal.getGambar_jurnal();



            //String imageUrl = "http://192.168.100.60:8080/uploads/foto/" + gambar;

         //  String imageUrl = "http://10.8.1.151:8080/uploads/gambar/" + gambar;
         //   String imageUrl = "http://10.8.5.72:8080/uploads/foto/" + gambar;
            String imageUrl = "http://192.168.0.160:8080/uploads/foto/" + gambar;

            Picasso.get().load(imageUrl).into(mImageJurnal);


            //  mTanggalTextView.setText(String.valueOf(mJurnal.getTanggal_jurnal()));
            mNamaTextView.setText(String.valueOf(mJurnal.getNama_jurnal()));

            mJenisTextView.setText(String.valueOf(mJurnal.getJenis_jurnal()));
        }

        @Override
        public void onClick(View v) {


            Toast.makeText(getActivity(),
                            mJurnal.getNama_jurnal() + " clicked!",
                            Toast.LENGTH_SHORT)
                    .show();
            mCallbacks.onJurnalSelected(mJurnal.getId_jurnal());
/*
            mCallbacks.onBackPressed();
*/

        }
    }

    private class JurnalAdapter extends RecyclerView.Adapter<JurnalHolder>{

        private List<Jurnal> mJurnalList;

        public JurnalAdapter(List<Jurnal> jurnals){
            mJurnalList = jurnals;
        }

        public void setData(List<Jurnal> jurnals) {
            mJurnalList = jurnals;
            notifyDataSetChanged();
        }





        @Override
        public JurnalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            return new JurnalHolder(layoutinflater, parent);
        }

        @Override
        public void onBindViewHolder(JurnalHolder holder, int position) {
            Jurnal jurnal = mJurnalList.get(position);
            holder.bind(jurnal);
        }

        @Override
        public int getItemCount() {
            return mJurnalList.size();
        }


    }

}