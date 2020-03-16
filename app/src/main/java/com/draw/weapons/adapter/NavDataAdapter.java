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

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.draw.weapons.R;
import com.draw.weapons.WeaponDetal;
import com.draw.weapons.modal.Nav;
import com.draw.weapons.modal.Weapon;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class NavDataAdapter extends RecyclerView.Adapter<NavDataAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<Nav> list;
    private Context context;
    private static AdapterView.OnItemClickListener listener;

    public NavDataAdapter(Context context, ArrayList<Nav> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNav;
        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            imageNav = view.findViewById(R.id.imageNav);
        }
    }


    @Override
    public NavDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nav, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NavDataAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.imageNav.setImageBitmap(getBitmapFromAsset(viewHolder.itemView.getContext(), list.get(i).getImage()));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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