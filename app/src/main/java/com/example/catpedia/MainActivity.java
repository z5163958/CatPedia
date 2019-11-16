package com.example.catpedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new CatRecyclerFragment();

        swapFragment(fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_fave) {
                    Fragment fragment = new FaveRecyclerFragment();
                    swapFragment(fragment);
                    return true;
                }
                else if (menuItem.getItemId() == R.id.nav_search){
                    Fragment fragment = new CatRecyclerFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });

        SearchView searchView = findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sendMessage(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }
        });
    }



    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }
    private void sendMessage(CharSequence query){
        Intent intent = new Intent(this, SearchRecycler.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }


}
