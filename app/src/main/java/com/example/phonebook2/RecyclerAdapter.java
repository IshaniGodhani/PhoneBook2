package com.example.phonebook2;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.mediation.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {
    Activity activity;
    ArrayList<User> userList;
    public RecyclerAdapter(Activity activity, ArrayList<User> userList) {
        this.activity=activity;
        this.userList=userList;

    }

    @NonNull
    @Override
    public RecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(activity).inflate(R.layout.item_category,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.viewHolder holder, int position) {
        User user=userList.get(position);
        int id=user.getId();
        String name=user.getName();
        String contact=user.getContact();
        holder.txt1.setText(""+name);
        holder.txt2.setText(""+contact);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                PopupMenu popupMenu = new PopupMenu(activity,holder.menu);

                activity.getMenuInflater().inflate(R.menu.edit_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.item_update)
                        {
                            DBHelper dbHelper=new DBHelper(activity);
                            Intent intent = new Intent(activity,MainActivity.class );
                            intent.putExtra("Id",id);
                            intent.putExtra("Name",name);
                            intent.putExtra("Contact",contact);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                        else if(item.getItemId()==R.id.item_delete)
                        {
                            DBHelper dbHelper=new DBHelper(activity);
                            dbHelper.deleteData(id);
                            userList.remove(position);
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                popupMenu.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolder extends ViewHolder {
        TextView txt1,txt2;
        ImageView menu;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            TextView txt1=itemView.findViewById(R.id.item_name);
            TextView txt2=itemView.findViewById(R.id.item_contact);
            ImageView menu=itemView.findViewById(R.id.menu);

        }
    }
}
