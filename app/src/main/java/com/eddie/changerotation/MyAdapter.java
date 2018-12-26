package com.eddie.changerotation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> titles;
    private MyCallback callback;

    public MyAdapter() {

        titles = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            titles.add("Title " + i);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_row, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.titleTxt.setText(titles.get(i));
    }

    @Override
    public int getItemCount() {

        return titles.size();
    }

    public void setListener(MyCallback callback) {

        this.callback = callback;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTxt;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            titleTxt = itemView.findViewById(R.id.titile_txt);

            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    if (callback != null) {

                        callback.onRowClick(titles.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    interface MyCallback {

        void onRowClick(String title);
    }
}
