package com.example.myplayer;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button start_btn,pause_btn,play_btn;
    SeekBar seek_bar;
    Intent intent_1,intent_2,intent_3;
    MediaPlayer mp;
    CountDownTimer ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_btn=findViewById(R.id.btn_start);
        pause_btn=findViewById(R.id.btn_pause);
        play_btn=findViewById(R.id.btn_play);
        seek_bar=findViewById(R.id.seek_bar);
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b==true)
                    mp.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intent_1==null) {
                    intent_1 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent_1.setType("audio/*");
                    startActivityForResult(intent_1, 1);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Song is already playing",Toast.LENGTH_SHORT).show();
                }
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()==true)
                    Toast.makeText(MainActivity.this, "Song is playing", Toast.LENGTH_SHORT).show();
                else
                    mp.start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== Activity.RESULT_OK)
        {
            mp=new MediaPlayer();
            try {
                mp.setDataSource(MainActivity.this,data.getData());
                mp.prepare();
                mp.start();
                seek_bar.setMax(mp.getDuration());
                ct=new CountDownTimer(mp.getDuration(),1000) {
                    @Override
                    public void onTick(long l) {
                        seek_bar.setProgress(mp.getCurrentPosition());
                    }
                    @Override
                    public void onFinish() {
                    }
                };

                ct.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(requestCode==2 && resultCode==Activity.RESULT_OK)
        {
            mp=new MediaPlayer();
            try {
                mp.setDataSource(MainActivity.this,data.getData());
                mp.prepare();
                mp.start();
                seek_bar.setMax(mp.getDuration());
                ct=new CountDownTimer(mp.getDuration(),1000) {
                    @Override
                    public void onTick(long l) {
                        seek_bar.setProgress(mp.getCurrentPosition());
                    }
                    @Override
                    public void onFinish() {
                    }
                };

                ct.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m1)
        {
            intent_2=new Intent(Intent.ACTION_GET_CONTENT);
            intent_2.setType("audio/*");
            startActivityForResult(intent_2,2);
            mp.stop();
        }
        else if(item.getItemId()==R.id.m2)
        {
            intent_3=new Intent(MainActivity.this,Song_List.class);
            startActivity(intent_3);
        }
        else if(item.getItemId()==R.id.m3)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
        b.setMessage("Do you want to exit?");
        b.setTitle("Confirm...");
        b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onPause();
            }
        });
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        b.show();
    }
}
