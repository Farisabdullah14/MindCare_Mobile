package id.ac.polman.astra.kelompok2MI2B.mindcare.helper;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DailyReminderScheduler {

    public static void scheduleDailyReminder(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build();

        // Atur waktu 17.30
        Calendar now = Calendar.getInstance();
        Calendar targetTime = Calendar.getInstance();
        targetTime.set(Calendar.HOUR_OF_DAY, 17);
        targetTime.set(Calendar.MINUTE, 30);
        targetTime.set(Calendar.SECOND, 0);



        if (now.after(targetTime)) {
            // Jika waktu saat ini sudah melewati waktu target, atur ulang ke esok hari
            targetTime.add(Calendar.DATE, 1);
            System.out.println("nOTIFIKASI HARIAN");
        }

        long initialDelay = targetTime.getTimeInMillis() - now.getTimeInMillis();
        System.out.println("ini delay"+initialDelay);

        // Jadwalkan OneTimeWorkRequest dengan initial delay agar berjalan pada pukul 17.30 setiap hari
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DailyReminderWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);
    }
}
