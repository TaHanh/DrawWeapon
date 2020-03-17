package com.draw.weapons.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.draw.weapons.R;
import com.draw.weapons.WeaponDetal;
import com.draw.weapons.modal.Weapon;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<Weapon> weapons;
    private Context context;
    private static AdapterView.OnItemClickListener listener;

    public DataAdapter(Context context, ArrayList<Weapon> weapons) {
        this.weapons = weapons;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, year;
        ImageView imgView;
        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            name = view.findViewById(R.id.name);
            imgView = view.findViewById(R.id.avatar);
            year = view.findViewById(R.id.code);
        }
    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(weapons.get(i).getName());
        viewHolder.imgView.setImageBitmap(getBitmapFromAsset(viewHolder.itemView.getContext(), weapons.get(i).getAvatar()));
        viewHolder.year.setText(weapons.get(i).getYear());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activitymoi = new Intent(context, WeaponDetal.class);
//                activitymoi.putExtra("INDEX", i);
//                activitymoi.putExtra("TOTAL", getItemCount());
                activitymoi.putExtra("data", weapons.get(i));
                context.startActivity(activitymoi);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weapons.size();
    }


    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }
        return bitmap;
    }
}