package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.Collections;
import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Konselor;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.helper.LinkifiedTextView;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.KonselorListViewModel;

public class KonselorListFragment extends Fragment {


    private static final String TAG = "KonselorListFragment";
    private KonselorListViewModel mKonselorListViewModel;
    private RecyclerView mKonselorRecyclerView;
    private KonselorAdapter mAdapter;
    private List<Konselor> mKonselors;

    public interface Callbacks {
        public void onKonselorSelected(int konselorId);
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
        Log.i(TAG, "KonselorListFragment.onCreate() called");
        setHasOptionsMenu(true);
        mKonselorListViewModel = new ViewModelProvider(this)
                .get(KonselorListViewModel.class);
        mAdapter = new KonselorAdapter(Collections.<Konselor>emptyList());
        //Log.d(TAG, "Total Konselor: " + mKonselorListViewModel.getKonselors().size());
    }

    private void updateUI(List<Konselor> konselors){
        //List<User> users = mUserListViewModel.getUsers();
        Log.i(TAG, "updateUI called");
        mAdapter = new KonselorAdapter(konselors);
        mKonselorRecyclerView.setAdapter(mAdapter);
        mKonselors = konselors;
    }

    public static KonselorListFragment newInstance(){
        return new KonselorListFragment();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView mBackButton;

        Log.i(TAG, "UserListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_konselor_list, container, false);

        Activity itemView = null;
       /* Button buttonKonselor = itemView.findViewById(R.id.Button_id_konselor_01);

      *//*  buttonKonselor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = mLokasiTextView.getText().toString();
                // Here you can launch the link using an Intent or a WebView
                // For example, launching the link in a browser:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });*/
        
        mBackButton = view.findViewById(R.id.left_icon);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kode untuk mengarahkan pengguna ke HomeFragment
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        mKonselorRecyclerView = view.findViewById(R.id.konselor_recycler_view);
        mKonselorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //updateUI();

        mKonselorRecyclerView.setAdapter(mAdapter);
        return view;
    }


    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "UserListFragment.onViewCreated() called");
        mKonselorListViewModel.getKonselors().observe(
                getViewLifecycleOwner(),
                new Observer<List<Konselor>>() {
                    @Override
                    public void onChanged(List<Konselor> konselors) {
                        //Update the cached copy of
                        updateUI(konselors);
                        Log.i(TAG, "Got Konselors: " + konselors.size());
                    }
                }
        );
    }

    private class KonselorHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{


        private TextView mNamaTextView;

        private TextView mAlamatTextView;

        private LinkifiedTextView mLokasiTextView;
        private Konselor mKonselor;

        public KonselorHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_konselor_item, parent, false));
            this.itemView.setOnClickListener(this);
            //buat ubah
            mNamaTextView = this.itemView.findViewById(R.id.nama_konselor);
            mAlamatTextView = this.itemView.findViewById(R.id.kontak_konselor);
            mLokasiTextView = this.itemView.findViewById(R.id.link_kontak);
        }

        public void bind(Konselor konselor){
            mKonselor = konselor;
            mAlamatTextView.setText("Nomor Telepon : "+String.valueOf(mKonselor.getKontak_konselor()));
            mNamaTextView.setText(""+String.valueOf(mKonselor.getNama_konselor()));
            mLokasiTextView.setText("Tautan Chat :"+ String.valueOf(mKonselor.getLink_kontak()));
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                            mKonselor.getNama_konselor() + " clicked!",
                            Toast.LENGTH_SHORT)
                    .show();
            // mCallbacks.onKonselorSelected(mKonselor.getId_konselor());
        }
    }

    private class KonselorAdapter extends RecyclerView.Adapter<KonselorHolder>{

        private List<Konselor> mKonselorList;

        public KonselorAdapter(List<Konselor> konselors){
            mKonselorList = konselors;
        }

        @Override
        public KonselorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            return new KonselorHolder(layoutinflater, parent);
        }

        @Override
        public void onBindViewHolder(KonselorHolder holder, int position) {
            Konselor konselor = mKonselorList.get(position);
            holder.bind(konselor);
        }

        @Override
        public int getItemCount() {
            return mKonselorList.size();
        }


    }

}
