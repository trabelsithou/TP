package com.tp.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tp.myapplication.R;
import com.tp.myapplication.data.Constants;
import com.tp.myapplication.data.Data;
import com.tp.myapplication.model.User;

public class DetailsStudentActivity extends AppCompatActivity {
    TextView tvName;
    Switch swStatus;
    ImageView imgPicture;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_student);
        tvName = findViewById(R.id.tv_name);
        swStatus = findViewById(R.id.sw_status);
        imgPicture = findViewById(R.id.img_picture);
        final int position = getIntent().getIntExtra(Constants.ARG_POSITION, -1);
        if (position >= 0) {
            user = Data.getInstance().getmData().get(position);
        }
        if (user != null) {
            tvName.setText(user.getFullName());
            Glide.with(this).load(user.getImage()).into(imgPicture);
            swStatus.setChecked(user.isPresent());
            swStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Data.getInstance().getmData().get(position).setPresent(swStatus.isChecked());
                }
            });

        }

    }
}
