package com.example.akmaral.otest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;

import com.example.akmaral.otest.fragments.AlbumsFragment;
import com.example.akmaral.otest.fragments.PostsFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        switch (item.getItemId()) {
                            case R.id.action_albums:

                                AlbumsFragment albumsFragment = new AlbumsFragment();
                                transaction.replace(R.id.container, albumsFragment);
                                transaction.commit();
                                break;

                            case R.id.action_posts:
                                PostsFragment postsFragment = new PostsFragment();
                                transaction.replace(R.id.container, postsFragment);
                                transaction.commit();
                            break;

                        }
                        return true;
                    }
                });

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                AlbumsFragment fragment = new AlbumsFragment();
                transaction.replace(R.id.container, fragment);
                transaction.commit();


    }



}
