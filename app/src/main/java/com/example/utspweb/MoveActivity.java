package com.example.utspweb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_registration, R.id.navigation_tabel)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.setting) {
            Intent moveIntent = new Intent(MoveActivity.this, SettingActivity.class);
            startActivity(moveIntent);
            return true;
        } else if (itemId == R.id.logout) {
            // Menampilkan dialog logout warning
            showLogoutWarningDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showLogoutWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate layout XML
        View dialogView = LayoutInflater.from(this).inflate(R.layout.logoutwarning, null);
        builder.setView(dialogView);

        // Mengambil referensi elemen dalam layout XML
        TextView textView = dialogView.findViewById(R.id.tv_text);
        Button positiveButton = dialogView.findViewById(R.id.positive_Button);
        Button negativeButton = dialogView.findViewById(R.id.negative_button);

        // Set teks pesan dan listener untuk tombol-tombol
        textView.setText("Kamu yakin ingin Logout?");
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proses logout disini
                // Misalnya, panggil method untuk melakukan logout
                performLogout();
            }
        });
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tutup dialog
                Dialog dialog = null;
                dialog.dismiss();
            }
        });

        // Buat dan tampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void performLogout() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}