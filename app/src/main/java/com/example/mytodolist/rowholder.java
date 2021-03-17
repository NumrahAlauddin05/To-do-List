package com.example.mytodolist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class rowholder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView text;
    TextView des;

    public rowholder(@NonNull View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.imageview2);
        text=itemView.findViewById(R.id.textview1);
        des=itemView.findViewById(R.id.textview2);
    }
}
