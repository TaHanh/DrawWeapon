package com.draw.weapons.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.draw.weapons.R;
import com.draw.weapons.modal.Nav;
import com.draw.weapons.modal.Nav2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class NavDataAdapter extends RecyclerView.Adapter<NavDataAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<Nav2> list;
    private Context context;
    private OnItemClick onItemClick;
    private String TAG = "NavDataAdapter";
    ImageView imgNav;

    public NavDataAdapter(Context context, ArrayList<Nav2> list) {
        this.list = list;
        this.context = context;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public void onClick(View v) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNav;
        LinearLayout layoutImageNav;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            imageNav = view.findViewById(R.id.imageNav);
            layoutImageNav = view.findViewById(R.id.layout_image_nav);
        }
    }


    @Override
    public NavDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nav, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NavDataAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.imageNav.setImageBitmap(getBitmapFromAsset(viewHolder.itemView.getContext(), list.get(i).getImage()));
        if (list.get(i).isSelected()) {
            viewHolder.layoutImageNav.setBackgroundResource(R.drawable.item_selected_nav_style);
        } else {
            viewHolder.layoutImageNav.setBackgroundResource(R.drawable.item_nav_style);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onClickItem(i);

                }
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

    public interface OnItemClick {
        void onClickItem(int position);
    }
}