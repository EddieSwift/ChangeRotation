package com.eddie.changerotation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    private TextView myTxt;
    private String title;

    public static InfoFragment of(String title) {

        InfoFragment fragment = new InfoFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {

            title = savedInstanceState.getString("TITLE", "no title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        myTxt = view.findViewById(R.id.myTxt);
        myTxt.setText(title);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString("TITLE", title);
        super.onSaveInstanceState(outState);
    }
}
