package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Psikolog;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.helper.LinkifiedTextView;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.PsikologListViewModel;

public class PsikologListFragment extends Fragment {


    private static final String TAG = "PsikologListFragment";
    private PsikologListViewModel mPsikologListViewModel;
    private RecyclerView mPsikologRecyclerView;
    private PsikologAdapter mAdapter;
    private List<Psikolog> mPsikologs;

    public interface Callbacks {
        public void onPsikologSelected(int psikologId);
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
        Log.i(TAG, "PsikologListFragment.onCreate() called");
        setHasOptionsMenu(true);
        mPsikologListViewModel = new ViewModelProvider(this)
                .get(PsikologListViewModel.class);
        mAdapter = new PsikologAdapter(Collections.<Psikolog>emptyList());
        //Log.d(TAG, "Total Psikolog: " + mPsikologListViewModel.getPsikologs().size());
    }

    private void updateUI(List<Psikolog> psikologs){
        //List<User> users = mUserListViewModel.getUsers();
        Log.i(TAG, "updateUI called");
        mAdapter = new PsikologAdapter(psikologs);
        mPsikologRecyclerView.setAdapter(mAdapter);
        mPsikologs = psikologs;
    }

    public static PsikologListFragment newInstance(){
        return new PsikologListFragment();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ImageView mBackButton;
        Log.i(TAG, "UserListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_psikolog_list,
                container, false);

        mBackButton = view.findViewById(R.id.left_icon);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kode untuk mengarahkan pengguna ke HomeFragment
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });
        mPsikologRecyclerView = (RecyclerView)
                view.findViewById(R.id.psikolog_recycler_view);
        mPsikologRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        //updateUI();
        mPsikologRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "UserListFragment.onViewCreated() called");
        mPsikologListViewModel.getPsikologs().observe(
                getViewLifecycleOwner(),
                new Observer<List<Psikolog>>() {
                    @Override
                    public void onChanged(List<Psikolog> psikologs) {
                        //Update the cached copy of
                        updateUI(psikologs);
                        Log.i(TAG, "Got Psikologs: " + psikologs.size());
                    }
                }
        );
    }

    private class PsikologHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mNamaTextView;

        private TextView mAlamatTextView;

        private LinkifiedTextView mLokasiTextView;
        private Psikolog mPsikolog;

        public PsikologHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_psikolog_item, parent, false));
            this.itemView.setOnClickListener(this);
            //buat ubah
            mNamaTextView = this.itemView.findViewById(R.id.nama_psikolog);
            mAlamatTextView = this.itemView.findViewById(R.id.alamat_psikolog);
            mLokasiTextView = this.itemView.findViewById(R.id.lokasi_psikolog);


        }

        public void bind(Psikolog psikolog){
            mPsikolog = psikolog;
            mAlamatTextView.setText(String.valueOf(mPsikolog.getAlamat_psi()));
            mNamaTextView.setText(String.valueOf(mPsikolog.getNama_psi()));
            mLokasiTextView.setText(String.valueOf(mPsikolog.getLokasi()));
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                            mPsikolog.getNama_psi() + " clicked!",
                            Toast.LENGTH_SHORT)
                    .show();
           // mCallbacks.onPsikologSelected(mPsikolog.getId_psikolog());
        }
    }

    private class PsikologAdapter extends RecyclerView.Adapter<PsikologHolder>{

        private List<Psikolog> mPsikologList;

        public PsikologAdapter(List<Psikolog> psikologs){
            mPsikologList = psikologs;
        }

        @Override
        public PsikologHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            return new PsikologHolder(layoutinflater, parent);
        }

        @Override
        public void onBindViewHolder(PsikologHolder holder, int position) {
            Psikolog psikolog = mPsikologList.get(position);
            holder.bind(psikolog);
        }

        @Override
        public int getItemCount() {
            return mPsikologList.size();
        }


    }

}
