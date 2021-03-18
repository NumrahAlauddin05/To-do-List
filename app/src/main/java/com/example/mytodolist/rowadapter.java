package com.example.mytodolist;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class rowadapter extends RecyclerView.Adapter<rowholder> {
    Activity context;
    ArrayList<Model> models;
    ActionMode actionMode;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    Model model;

    public rowadapter(Activity context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public rowholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rowlistlayout, parent, false);
        rowholder rowholder = new rowholder(view);
        return rowholder;


    }

    @Override
    public void onBindViewHolder(@NonNull rowholder holder, final int position) {

        auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User").child(uid).child("Task");

        model = models.get(position);
        //  holder.image.setImageResource(models.get(position).getImage());
        holder.text.setText(models.get(position).getText());
        holder.des.setText(models.get(position).getDescription());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (actionMode != null) {
                    return false;
                }

                actionMode = context.startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        mode.getMenuInflater().inflate(R.menu.rec, menu);
                        mode.setTitle("Delete Task");
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.delete: {
                                reference.child(models.get(position).getId()).removeValue();
                                models.remove(position);
                                notifyItemRemoved(position);
                                mode.finish();
                                return true;
                            }
                            default:
                                return false;
                        }

                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        actionMode=null;

                    }
                });
                return true;

            }
        });
//

    }


    @Override
    public int getItemCount() {
        return models.size();
    }
}
