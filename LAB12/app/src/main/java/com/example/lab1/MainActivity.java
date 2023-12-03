package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private Button b3;
    MediaPlayer mediaPlayer;
    private int size = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        b3 = (Button)findViewById(R.id.button3);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    public void ChangeSize(View view) {
        switch (size)
        {
            case 2:{
                b3.setText("3x3");
                size++;
            }; break;
            case 3:{
                b3.setText("4x4");
                size++;
            }; break;
            case 4:{
                b3.setText("5x5");
                size++;
            }; break;
            case 5:{
                b3.setText("2x2");
                size = 2;
            }; break;
        }
    }

    public void StartGame(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("size", size);
        startActivity(intent);
    }

    public void EndGame(View view) {
        finish();
    }

    public void ShowRecordTable(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Таблица рекордов");
        builder.setIcon(android.R.drawable.btn_star);
        String recordString = "Рекорды для каждого режима игры:\n\n2x2 - ";
        SharedPreferences RecordTime;
        RecordTime = getSharedPreferences("RecordTime2x2", MODE_PRIVATE);
        recordString += RecordTime.getString("RecordTime2x2", "00:00");
        recordString += "\n\n3x3 - ";
        RecordTime = getSharedPreferences("RecordTime3x3", MODE_PRIVATE);
        recordString += RecordTime.getString("RecordTime3x3", "00:00");
        recordString += "\n\n4x4 - ";
        RecordTime = getSharedPreferences("RecordTime4x4", MODE_PRIVATE);
        recordString += RecordTime.getString("RecordTime4x4", "00:00");
        recordString += "\n\n5x5 - ";
        RecordTime = getSharedPreferences("RecordTime5x5", MODE_PRIVATE);
        recordString += RecordTime.getString("RecordTime5x5", "00:00");
        builder.setMessage(recordString);
        builder.setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}