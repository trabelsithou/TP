package com.tp.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tp.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPass;
    Button btnLogin;
    String ema;
    String pass;
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        initUI();

    }

    private void initUI() {
        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });
    }

    private void checkFields() {
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Merci d'entrer votre email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Merci d'entrer un email valide", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            Toast.makeText(this, "Votre mot de passe doit contenir plus que 6 caractères", Toast.LENGTH_SHORT).show();
            return;
        }
        checkUserInfosValid(email, password);

    }

    private void checkUserInfosValid(String email, String password) {
        String json = loadJSONFromAsset();
        try {
            JSONArray arrayUsers = new JSONArray(json);
            for (int i = 0; i < arrayUsers.length(); i++) {
                JSONObject jsonObject = arrayUsers.getJSONObject(i);
                if (jsonObject.getString("type").equals("teacher")) {
                    ema = jsonObject.getString("email");
                    pass = jsonObject.getString("password");
                    break;
                }
            }
            if (email.equals(ema) && password.equals(pass)) {
                saveSession();
                startMainActivity();
            } else {
                Toast.makeText(this, "Votre email ou bien votre mot de passe est incorrect", Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void saveSession() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("IS_CONNECTED", true);
        editor.apply();
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
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
