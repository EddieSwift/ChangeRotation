package com.eddie.changerotation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button clickBtn, editBtn;
    EditText inputData;
    TextView myTxt;
    private MyTask myTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("MY_TAG", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputData = findViewById(R.id.input_data);
        myTxt = findViewById(R.id.text_view);
        clickBtn = findViewById(R.id.next_activity_btn);
        clickBtn.setOnClickListener(this);
        editBtn = findViewById(R.id.change_txt_btn);
        editBtn.setOnClickListener(this);

        if (savedInstanceState != null) {

            myTxt.setText(savedInstanceState.getString("TEXT", ""));
        }

        myTask = (MyTask) getLastCustomNonConfigurationInstance();

        if (myTask != null) {

            myTask.attach(this);
        }


    }

    @Override
    protected void onDestroy() {

        Log.d("MY_TAG", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.change_txt_btn) {

//            myTxt.setText("New text");
//            new MyTask().execute();
            myTask = new MyTask(this);
            myTask.execute();

        } else if (v.getId() == R.id.next_activity_btn) {

            startActivity(new Intent(this, SecondActivity.class));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("TEXT", myTxt.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {


        if (myTask != null) {

            return myTask;
        }
        return super.onRetainCustomNonConfigurationInstance();
    }

    class MyTask extends AsyncTask<Void, Integer, Void> {

        private MainActivity activity;

        public MyTask(MainActivity activity) {

            this.activity = activity;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            myTxt.setText(String.valueOf(values[0]));

            super.onProgressUpdate(values);
        }

        public void detach() {

            activity = null;
        }

        public void attach(MainActivity activity) {

            this.activity = activity;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i = 0; i < 100; i++) {

                Log.d("MY_TAG", "doInBackground: " + i);

                try {

                    Thread.sleep(50);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                publishProgress(i);
            }


            return null;
        }
    }
}