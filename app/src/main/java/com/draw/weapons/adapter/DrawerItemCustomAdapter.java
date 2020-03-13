package com.draw.weapons.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.draw.weapons.R;
import com.draw.weapons.modal.Nav;

import java.io.IOException;
import java.io.InputStream;

public class DrawerItemCustomAdapter extends ArrayAdapter<Nav> {

    Context mContext;
    int layoutResourceId;
    Nav data[] = null;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, Nav[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

//        ImageView imageViewIcon =  listItem.findViewById(R.id.imageViewIcon);
//        TextView textViewName = listItem.findViewById(R.id.textViewName);
//
//        Nav folder = data[position];
//
//
//        imageViewIcon.setImageBitmap(getBitmapFromAsset(viewHolder.itemView.getContext(),"images/"+(i+1)+"/0.png"));
//        textViewName.setText(folder.name);

        return listItem;
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
