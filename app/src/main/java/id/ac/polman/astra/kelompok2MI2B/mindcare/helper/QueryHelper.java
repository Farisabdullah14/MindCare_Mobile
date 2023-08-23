package id.ac.polman.astra.kelompok2MI2B.mindcare.helper;

import android.util.Log;

public class QueryHelper {
    private static final String TAG = "QueryHelper";
    public static String login(String NIM , String password){
        String query = "SELECT * FROM mahasiswa WHERE NIM = '"+NIM+"' AND password = '"+password+"'";
        Log.d(TAG, NIM);
        Log.d(TAG, password);
        return query;
    }
}
