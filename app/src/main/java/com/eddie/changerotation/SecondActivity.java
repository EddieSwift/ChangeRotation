package com.eddie.changerotation;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "MY_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Configuration conf = getResources().getConfiguration();

        if (conf.orientation == Configuration.ORIENTATION_PORTRAIT) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_container, new ListFragment(), "LIST_FRAGMENT")
                    .commit();
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        Log.e(TAG, "onSaveInstanceState: ");

        if (!isFinishing()) {

            getSupportFragmentManager().popBackStack();

            ListFragment fragment = (ListFragment) getSupportFragmentManager()
                    .findFragmentByTag("LIST_FRAGMENT");

            Log.e(TAG, "onSaveInstanceState: " + fragment);

            if (fragment != null) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(fragment)
                        .commit();
            }

//            int count = getSupportFragmentManager().getBackStackEntryCount();
//
//            for (int i = 0; i < count; i++) {
//
//                getSupportFragmentManager().popBackStack();
//            }
        }

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onStart() {

        Log.e(TAG, "onStart: ");

        super.onStart();
    }

    @Override
    protected void onStop() {

        Log.e(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onPause() {

        Log.e(TAG, "onPause: ");

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        Log.e(TAG, "onDestroy: ");

        super.onDestroy();
    }
}

