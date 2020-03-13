package com.draw.weapons;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.draw.weapons.modal.Weapon;

import java.io.IOException;
import java.io.InputStream;

public class WeaponDetal extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "WeaponDetal";
    ActionBar actionBar;
    EditText title, content;
    ImageView btnBack, imageWeapon, btnPrev, btnNext;
    TextView index;
    Intent intent;
    Weapon weapon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("", "value value");
        setContentView(R.layout.weapon_detail);
        getSupportActionBar().hide();
        intent = getIntent();
//        String idx = getIntent().getStringExtra("INDEX");
//        String total = getIntent().getStringExtra("TOTAL");
        weapon = (Weapon) intent.getSerializableExtra("data");
        Log.d(TAG, "onCreate: " + weapon.toString());
        index = findViewById(R.id.index);
//        index.setText(idx + "/" + total);

        imageWeapon = findViewById(R.id.image_weapon);
        imageWeapon.setImageBitmap(getBitmapFromAsset(this, "images/1/1.png"));


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnPrev = findViewById(R.id.arrow_left);
        btnPrev.setOnClickListener(this);

        btnNext = findViewById(R.id.arrow_right);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("", "fhdjhds");
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.arrow_right:
                imageWeapon.setImageBitmap(getBitmapFromAsset(this, "images/1/2.png"));
                break;
            case R.id.arrow_left:
                imageWeapon.setImageBitmap(getBitmapFromAsset(this, "images/1/0.png"));
                break;

            default:
                break;
        }
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
