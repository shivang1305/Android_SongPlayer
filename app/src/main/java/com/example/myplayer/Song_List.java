package com.example.myplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Song_List extends AppCompatActivity {
    LinearLayout ll;
    Intent in_1;
    View id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song__list);

        ll=findViewById(R.id.songlist_llayout);
        for(int i=1;i<=50;i++)
        {
            TextView tv = new TextView(Song_List.this);
            tv.setText("Song "+i);
            registerForContextMenu(tv);
            ll.addView(tv);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.contextlist_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.contextmenu,menu);
        id=v;

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.context_delete){
            ll.removeView(id);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.del_mul)
        {
          in_1=new Intent(Song_List.this,DeleteMultiple.class);
          startActivity(in_1);
        }
        return super.onOptionsItemSelected(item);
    }
}
