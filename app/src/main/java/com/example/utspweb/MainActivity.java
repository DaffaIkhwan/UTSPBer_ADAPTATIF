    package com.example.utspweb;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        private Button loginButton;
        private TextView lupaPasswordTextView;
        private TextView signUpTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            loginButton = findViewById(R.id.button);


            loginButton.setOnClickListener(this);
            lupaPasswordTextView = findViewById(R.id.textView31);
            signUpTextView = findViewById(R.id.textView7);

            loginButton.setOnClickListener(this);
            lupaPasswordTextView.setOnClickListener(this);
            signUpTextView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
            } else if (v.getId() == R.id.textView31) {
                Intent moveIntent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                startActivity(moveIntent);
            } else if (v.getId() == R.id.textView7) {
                Intent moveIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(moveIntent);
            }
        }
    }