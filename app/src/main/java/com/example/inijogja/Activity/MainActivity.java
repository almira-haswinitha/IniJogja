package com.example.inijogja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.inijogja.CategoriesFragment;
import com.example.inijogja.Fragment.HomeFragment;
import com.example.inijogja.Fragment.LikeFragment;
import com.example.inijogja.Fragment.LoginFragment;
import com.example.inijogja.Fragment.ProfileFragment;
import com.example.inijogja.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setOnNavigationItemSelectedListener(nav);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

    }



    private BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected_fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    selected_fragment = new HomeFragment();
                    break;
                case R.id.like:
                    selected_fragment = new LikeFragment();
                    break;
                case R.id.kategori:
                    selected_fragment = new CategoriesFragment();
                    break;
                case R.id.profile:
                    selected_fragment = new LoginFragment();
                    break;
            }

            assert selected_fragment != null;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selected_fragment)
                    .commit();
            return true;
        }

    };
}