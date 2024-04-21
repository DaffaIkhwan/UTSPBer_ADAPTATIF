package com.example.utspweb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SettingActivity extends AppCompatActivity {

    private static final int FILE_REQUEST_CODE = 123;
    private TextView fileNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Pengaturan");

        // Initialize TextView for file name
        fileNameTextView = findViewById(R.id.fileNameTextView);

        // Initialize the button for uploading photo
        Button uploadButton = findViewById(R.id.button2);

        // Set click listener to the upload button
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check for permission to read external storage
                if (ContextCompat.checkSelfPermission(SettingActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request permission if not granted
                    ActivityCompat.requestPermissions(SettingActivity.this,
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, FILE_REQUEST_CODE);
                } else {
                    // Permission already granted, open file picker
                    openFilePicker();
                }
            }
        });

        // Initialize TextView 28 for deleting file name
        TextView deleteTextView = findViewById(R.id.textView28);

        // Set click listener to delete TextView
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the file name TextView
                fileNameTextView.setText("");
            }
        });

        // Initialize Button3
        Button button3 = findViewById(R.id.button3);

        // Set click listener to Button3
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to show AlertDialog
                showAlertDialog("Perubahan Disimpan");
            }
        });
    }

    // Method to open file picker
    private void openFilePicker() {
        // Create an intent to select file
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Set MIME type to all files
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Start the activity for result
        startActivityForResult(intent, FILE_REQUEST_CODE);
    }

    // Override onActivityResult method to handle file selection result
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the request code matches the file request code and result is OK
        if (requestCode == FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Get the URI of the selected file
            Uri fileUri = data.getData();

            // Example: Display the file name in a TextView
            if (fileUri != null) {
                fileNameTextView.setText(getFileName(fileUri));
            }
        }
    }

    // Method to show AlertDialog
    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Info")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    // Method to get file name from URI
    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme() != null && uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
