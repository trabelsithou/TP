package com.tp.myapplication.ui;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.myapplication.R;
import com.tp.myapplication.adapter.StudentsAdapter;
import com.tp.myapplication.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvStudents;
    private ArrayList<User> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        rvStudents = findViewById(R.id.rv_students);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvStudents.setLayoutManager(manager);
        loadStudents();
        StudentsAdapter studentsAdapter = new StudentsAdapter(this, mData);
        Log.d("", "initUi: " + mData.size());
        rvStudents.setAdapter(studentsAdapter);

    }

    private void loadStudents() {
        String json = loadJSONFromAsset();
        try {
            JSONArray arrayUsers = new JSONArray(json);
            for (int i = 0; i < arrayUsers.length(); i++) {
                JSONObject jsonObject = arrayUsers.getJSONObject(i);
                if (jsonObject.getString("type").equals("student")) {
                    User user = new User(jsonObject.getString("fullname"), jsonObject.getBoolean("present"));
                    user.setImage(jsonObject.getString("picture"));
                    mData.add(user);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("users.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
