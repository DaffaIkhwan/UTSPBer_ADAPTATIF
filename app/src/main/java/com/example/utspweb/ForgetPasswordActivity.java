package com.example.utspweb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        // Initialize the button
        Button resetButton = findViewById(R.id.button);

        // Set click listener to the button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to display reset link sent notification
                displayResetLinkSentNotification();
            }
        });
    }

    // Method to display reset link sent notification using AlertDialog
    private void displayResetLinkSentNotification() {
        // Create AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset Link Sent")
                .setMessage("Tautan reset telah dikirim")
                .setPositiveButton("OK", null)
                .show();
    }
}
