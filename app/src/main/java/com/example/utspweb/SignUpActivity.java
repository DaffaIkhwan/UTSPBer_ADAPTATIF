package com.example.utspweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button SignupButton;
    private TextView LoginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        LoginTextView = findViewById(R.id.textView7);
        SignupButton = findViewById(R.id.button);

        LoginTextView.setOnClickListener(this);
        SignupButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            Intent moveIntent = new Intent(SignUpActivity.this, MoveActivity.class);
            startActivity(moveIntent);
        } else if (v.getId() == R.id.textView7) {
            Intent moveIntent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(moveIntent);
        }
    }
}