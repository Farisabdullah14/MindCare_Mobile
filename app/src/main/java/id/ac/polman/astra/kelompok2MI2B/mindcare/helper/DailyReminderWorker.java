package id.ac.polman.astra.kelompok2MI2B.mindcare.helper;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DailyReminderWorker extends Worker {

    public DailyReminderWorker(
            @NonNull Context context,
            @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Tampilkan notifikasi di sini
        AlarmReceiver alarmReceiver = new AlarmReceiver();
        alarmReceiver.showNotification(getApplicationContext());

        // Mengembalikan Result.success() akan mengindikasikan tugas berhasil.
        return Result.success();
    }
}

