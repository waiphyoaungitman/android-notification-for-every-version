package ec.waiphyoaungitman.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String CH_ID = "ruby";
    private String CH_NA = "ruby";
    private String CH_DEC = "ruby";
    private  NotificationCompat.Builder builder;
    private  NotificationManagerCompat managerCompat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CH_ID,CH_NA,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CH_DEC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification(0);
                for(int i=0;i<=100;i++){
                    builder.setProgress(100,i,false)
                            .setContentTitle("" + i)
                            .setContentText("" + 100);
                    managerCompat.notify(1,builder.build());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i==100){
                        Toast.makeText(getApplicationContext(), "haha", Toast.LENGTH_SHORT).show();
                        builder.setContentText("Finished")
                               .setOngoing(false);
                        managerCompat.notify(1,builder.build());
                    }
                }






            }

            public void createNotification(int progress){
                builder =
                        new NotificationCompat.Builder(getApplicationContext(),CH_ID)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setProgress(100,progress,false)
                                .setContentTitle("Content Title")
                                .setOnlyAlertOnce(true)
                                .setOngoing(true)
                                .setContentText("Content Text")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                managerCompat =  NotificationManagerCompat.from(getApplicationContext());
                managerCompat.notify(1,builder.build());
            }
        });
    }
}
