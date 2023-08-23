package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;

public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    private EditText mNimField;
    private EditText mNamaField;
    private EditText mAlamatField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;
    private RadioButton mJenisKelaminLaki;
    private RadioButton mJenisKelaminPerempuan;
    private Button btnRegister;
    private PenggunaRepository penggunaRepository;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Inisialisasi UI
        mNimField = view.findViewById(R.id.editTextNIM);
        mNamaField = view.findViewById(R.id.editTextNama);
        mAlamatField = view.findViewById(R.id.editTextAlamat);
        mPasswordField = view.findViewById(R.id.editTextPassword);
        mConfirmPasswordField = view.findViewById(R.id.editTextConfirmPassword);
        mJenisKelaminLaki = view.findViewById(R.id.rbLakiLaki);
        mJenisKelaminPerempuan = view.findViewById(R.id.rbPerempuan);
        btnRegister = view.findViewById(R.id.btn_register);

        // Inisialisasi Repository
        penggunaRepository = PenggunaRepository.get();

        // Mengatur aksi klik pada tombol register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat objek pengguna baru
                Pengguna pengguna = new Pengguna();

                pengguna.setNama(mNamaField.getText().toString());
                if (mJenisKelaminLaki.isChecked()) {
                    pengguna.setMjenis_kelamin("Laki-Laki");
                } else if (mJenisKelaminPerempuan.isChecked()) {
                    pengguna.setMjenis_kelamin("Perempuan");
                }
                pengguna.setAlamat(mAlamatField.getText().toString());
                pengguna.setPassword(mPasswordField.getText().toString());
                pengguna.setNim(mNimField.getText().toString());
                System.out.println("NIM DI REGISTER FRAGMENT " + mNimField.getText().toString());
                System.out.println("Rian ganteng "+pengguna.getNim().toString());
                // Melakukan permintaan registrasi
            //    penggunaRepository.savepengguna(pengguna);


                System.out.println("kirana babi"+pengguna.getNim().toString());
            }
        });

        return view;
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }
}

