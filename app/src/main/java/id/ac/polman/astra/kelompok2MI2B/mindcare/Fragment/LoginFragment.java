package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.json.JSONException;
import org.json.JSONObject;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Activity.MainActivity;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.PenggunaSSO;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.helper.DailyReminderScheduler;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.PenggunaViewModel;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginFragment extends Fragment {

    private static final String TAG = "PenggunaFragment";
    private EditText mTxtEmail;
    private EditText mTxtPassword;
    private Button mBtnLogin;
    private PenggunaRepository mPenggunaRepository;
    private PenggunaViewModel mPenggunaViewModel;


    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    public LoginFragment() {
        mPenggunaRepository = PenggunaRepository.get();

//        mIdMutableLiveData = new MutableLiveData<Integer>();
//        mLombaLiveData = Transformations.switchMap(mIdMutableLiveData, lombaId -> mLombaRepository.getLomba(lombaId));
    }

    private PenggunaViewModel getPenggunaViewModel(){
        if (mPenggunaViewModel == null){
            mPenggunaViewModel = new ViewModelProvider(this)
                    .get(PenggunaViewModel.class);
        }
        return mPenggunaViewModel;
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//         return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPenggunaViewModel = getPenggunaViewModel();


    }

//    @JavascriptInterface
//    public void printNama(String nama) {
//        Log.d("Nama", nama);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);

        // Pengecekan session
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("result", "");

        if (result.equals("TRUE")) {
            // Menyiapkan intent untuk memulai DashboardActivity
            Intent intent = new Intent(getContext(), MainActivity.class);

            // Menyiapkan data pengguna sebagai ekstra intent
            intent.putExtra("npk", sharedPreferences.getString("npk", ""));
            intent.putExtra("username", sharedPreferences.getString("username", ""));
            intent.putExtra("nama", sharedPreferences.getString("nama", ""));

            // Memulai DashboardActivity
            startActivity(intent);

            // Mengakhiri Activity saat ini (jika diperlukan)
            getActivity().finish();
        }




        mTxtEmail = (EditText) view.findViewById(R.id.login_username);
        mTxtPassword = (EditText) view.findViewById(R.id.login_password);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamePengguna = mTxtEmail.getText().toString();
                String pass = mTxtPassword.getText().toString();

                new LoginTask().execute(usernamePengguna, pass);

                if (usernamePengguna.isEmpty()) {
                    showErrorDialog("Error", "Harap Isi Username.");
                } else {
                    new LoginTask().execute(usernamePengguna, pass);
                }

            }
        });


        return view;
    }


    private void showErrorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.layout_error_dailog, null);

        TextView textTitle = dialogView.findViewById(R.id.textTitle);
        TextView textMessage = dialogView.findViewById(R.id.textMessage);
        Button buttonAction = dialogView.findViewById(R.id.buttonAction);

        textTitle.setText(title);
        textMessage.setText(message);
        buttonAction.setText("OK");

        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private class LoginTask extends AsyncTask<String, Void, String> {
        private OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            String usernamePengguna = params[0];
            String pass = params[1];
            String response = "";

            try {
                // Mendapatkan access token
                String accessTokenUrl = "https://api.polytechnic.astra.ac.id:2906/api_dev/AccessToken/Get";
                FormBody requestBody = new FormBody.Builder()
                        .add("username", usernamePengguna)
                        .add("password", pass)
                        .add("grant_type", "password")
                        .build();

                Request accessTokenRequest = new Request.Builder()
                        .url(accessTokenUrl)
                        .post(requestBody)
                        .build();

                Response accessTokenResponse = client.newCall(accessTokenRequest).execute();
                String accessTokenJson = accessTokenResponse.body().string();
                JSONObject accessTokenObj = new JSONObject(accessTokenJson);
                String accessToken = accessTokenObj.getString("access_token");
                accessTokenResponse.close();

                // Login dengan access token
                String loginUrl = "https://api.polytechnic.astra.ac.id:2906/api_dev/efcc359990d14328fda74beb65088ef9660ca17e/SIA/LoginSIA?username="
                        + usernamePengguna + "&password=" + pass;
                Request loginRequest = new Request.Builder()
                        .url(loginUrl)
                        .post(RequestBody.create(null, new byte[0])) // Tidak ada body request
                        .header("Authorization", "Bearer " + accessToken)
                        .build();

                Response loginResponse = client.newCall(loginRequest).execute();
                response = loginResponse.body().string();
                loginResponse.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            try {
                // Tindakan setelah berhasil login
                JSONObject resultObj = new JSONObject(response);

                // Mengambil data dari JSON response
                String result = resultObj.getString("result");
                String npk = resultObj.getString("npk");
                String username = resultObj.getString("username");
                String nama = resultObj.getString("nama");

                System.out.println(nama);

                mPenggunaRepository.savePengguna(npk, nama);

                // Membuat objek PenggunaAPIModel
                PenggunaSSO userSSO = new PenggunaSSO(result, npk, username, nama);


                if (result.equals("TRUE")) {
                    AlertDialog successDialog = showSuccessDialog(nama);
                    successDialog.show();

                    // Membuat intent untuk aktivitas Dashboard
                    Intent intent = new Intent(getContext(), MainActivity.class);

                    // Mengisi data pengguna sebagai ekstra intent
                    intent.putExtra("npk", userSSO.getNpk());
                    intent.putExtra("username", userSSO.getUsername());
                    intent.putExtra("nama", userSSO.getNama());


                    // Menyimpan data pengguna ke SharedPreferences
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("result", result);
                    editor.putString("npk", npk);
                    editor.putString("username", username);
                    editor.putString("nama", nama);
                    System.out.println("npk"+npk);
                    //     String nama =
                    mPenggunaViewModel.getPenggunaByNim(npk).observe(
                            getViewLifecycleOwner(),
                            new Observer<Pengguna>() {
                                @Override
                                public void onChanged(Pengguna user) {
                                    editor.putInt("id_user", user.getMid_user());
                                    System.out.println("login id_user"+user.getMid_user());
                                    editor.apply();
                                }
                            }
                    );




                    // Memulai aktivitas Dashboard
                    startActivity(intent);
                } else {
                    showErrorDialog("Error", "Username Tidak Ditemukan !");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private AlertDialog showSuccessDialog(String nama) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.layout_success_dailog, null);
        builder.setView(dialogView);

        TextView textTitle = dialogView.findViewById(R.id.textTitle);
        TextView textMessage = dialogView.findViewById(R.id.textMessage);
        Button buttonAction = dialogView.findViewById(R.id.buttonAction);

        textTitle.setText("Login Success");
        textMessage.setText("Selamat Datang " + nama);

        AlertDialog successDialog = builder.create();
        successDialog.show();

        // Optional: Customize the dialog appearance or add functionality to the buttonAction
        // buttonAction.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         successDialog.dismiss();
        //     }
        // });

        return successDialog; // Return the AlertDialog object
    }




}
