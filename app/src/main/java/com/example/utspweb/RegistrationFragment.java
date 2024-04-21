package com.example.utspweb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class RegistrationFragment extends Fragment {

    private ImageButton imageButton;
    private static final int FILE_REQUEST_CODE = 123;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Call method to set up AutoCompleteTextViews
        setupAutoCompleteTextViews(view);

        // Initialize ImageButton
        imageButton = view.findViewById(R.id.imagebutton);

        // Set click listener to ImageButton
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to open file picker
                openFilePicker();
            }
        });

        // Initialize the "Serahkan" button
        View serahkanButton = view.findViewById(R.id.serahkan);

        // Set click listener to the "Serahkan" button
        serahkanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to display notification
                showSubmissionNotification();
            }
        });
    }

    // Method to set up AutoCompleteTextViews
    private void setupAutoCompleteTextViews(View view) {
        // Initialize AutoCompleteTextViews
        AutoCompleteTextView autoCompleteTextView1 = view.findViewById(R.id.auto_complete_txt);
        AutoCompleteTextView autoCompleteTextView2 = view.findViewById(R.id.auto_complete_txt2);
        AutoCompleteTextView autoCompleteTextView3 = view.findViewById(R.id.auto_complete_txt3);
        AutoCompleteTextView autoCompleteTextView4 = view.findViewById(R.id.auto_complete_txt4);

        // Initialize ArrayAdapter for each AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line);

        // Add items to the ArrayAdapter for each AutoCompleteTextView
        adapter.add("Iwan Iskandar, S.T., M.T.");
        adapter.add("Reski Mai Candra, S.T., M.Sc.");
        adapter.add("Muhammad Affandes, S.T., M.T.");
        adapter.add("Fadhilah Syafria, S.T., M.Kom.");

        // Set the adapter to each AutoCompleteTextView
        autoCompleteTextView1.setAdapter(adapter);
        autoCompleteTextView2.setAdapter(adapter);
        autoCompleteTextView3.setAdapter(adapter);
        autoCompleteTextView4.setAdapter(adapter);
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

        // Check if the request code matches the file request code
        if (requestCode == FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Get the URI of the selected file
            Uri fileUri = data.getData();

            // Example: Display a notification
            showNotification("Berkas dipilih", "Berhasil memilih berkas!");

            // Example: Display the file name in a TextView
            View rootView = getView();
            if (rootView != null) {
                TextView fileNameTextView = rootView.findViewById(R.id.fileNameTextView);
                if (fileNameTextView != null) {
                    fileNameTextView.setText(getFileName(fileUri));
                }
            }
        }
    }

    // Method to show notification
    private void showNotification(String title, String message) {
        // You can customize the notification based on your requirements
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    // Method to show submission notification
    private void showSubmissionNotification() {
        // Create and show an AlertDialog with the submission success message
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Status Penyerahan")
                .setMessage("Tugas Akhir berhasil diserahkan!")
                .setPositiveButton("OK", null)
                .show();
    }

    // Method to get file name from URI
    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = requireContext().getContentResolver().query(uri, null, null, null, null);
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
