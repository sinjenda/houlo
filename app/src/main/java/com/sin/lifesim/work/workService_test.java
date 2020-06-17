package com.sin.lifesim.work;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.sin.lifesim.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class workService_test extends Service {
    public workService_test() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int dat5 = 15;
        while (dat5 != 0) {
            Calendar calendar = Calendar.getInstance();
            DateFormat formatData = new SimpleDateFormat("H");
            String dat4 = formatData.format(calendar.getTime());
            dat5 = Integer.parseInt(dat4);
        }
        String[] strings = intent.getStringArrayExtra("data");
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("test", true);
        i.putExtra("data", strings);
        startActivity(intent);
        return START_REDELIVER_INTENT;
    }
}
