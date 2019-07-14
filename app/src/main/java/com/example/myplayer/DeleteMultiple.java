package com.example.myplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeleteMultiple extends AppCompatActivity {

    LinearLayout linearlayout;
    Button btn_Delete;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_multiple);

        linearlayout=findViewById(R.id.del_mul_ll);
        btn_Delete=findViewById(R.id.del_mul_btn);
        check=findViewById(R.id.del_mul_cb);

        for(int i=0;i<=50;i++)
        {
            CheckBox cb =new CheckBox(this);
            cb.setText("Song "+i);
            linearlayout.addView(cb);
        }

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<linearlayout.getChildCount();i++)
                {
                    CheckBox cb=(CheckBox)linearlayout.getChildAt(i);
                    if(check.isChecked()==false)
                    {
                        if(cb.isChecked())
                        {
                            linearlayout.removeViewAt(i);
                        }
                    }
                    else
                    {
                        linearlayout.removeAllViews();
                    }
                }
            }
        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(check.isChecked())
                {
                    for(int i=0;i<linearlayout.getChildCount();i++)
                    {
                        CheckBox cb=(CheckBox)linearlayout.getChildAt(i);
                        cb.setChecked(true);
                    }
                }
                else
                {
                    for(int i=0;i<linearlayout.getChildCount();i++)
                    {
                        CheckBox cb=(CheckBox)linearlayout.getChildAt(i);
                        cb.setChecked(false);
                    }
                }
            }
        });
    }
}
