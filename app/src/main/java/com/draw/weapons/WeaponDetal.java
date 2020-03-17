package com.draw.weapons;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.draw.weapons.modal.Weapon;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;
import java.io.InputStream;

public class WeaponDetal extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "WeaponDetal";
    ActionBar actionBar;
    EditText title, content;
    ImageView btnBack, imageWeapon, btnPrev, btnNext, btnTurn;
    TextView index;
    Intent intent;
    Weapon weapon;
    int indexNow = 0;
    boolean isFlip= false;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;
    FrameLayout frmAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weapon_detail);
        getSupportActionBar().hide();
        initAds();

        intent = getIntent();
        weapon = (Weapon) intent.getSerializableExtra("data");
        Log.d(TAG, "onCreate: " + weapon.toString());
        index = findViewById(R.id.index);
        index.setText("1/" + weapon.getImages().size());

        imageWeapon = findViewById(R.id.image_weapon);
        imageWeapon.setImageBitmap(getBitmapFromAsset(this, weapon.getImages().get(indexNow)));


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnTurn = findViewById(R.id.btnTurn);
        btnTurn.setOnClickListener(this);

        btnPrev = findViewById(R.id.arrow_left);
        btnPrev.setOnClickListener(this);
        btnPrev.setVisibility(View.INVISIBLE);

        btnNext = findViewById(R.id.arrow_right);
        btnNext.setOnClickListener(this);
    }

    private void initAds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter_id));
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });



        frmAds = findViewById(R.id.frmAds);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.LARGE_BANNER);
        adView.setAdUnitId(getString(R.string.banner_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        frmAds.addView(adView);


    }


    @Override
    public void onClick(View v) {
        Log.d("", "fhdjhds");
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.arrow_right:

                if (this.indexNow < weapon.getImages().size() - 1) {
                    this.indexNow = this.indexNow + 1;
                    index.setText((this.indexNow + 1) + "/" + weapon.getImages().size());
                    imageWeapon.setImageBitmap(getBitmapFromAsset(this, weapon.getImages().get(indexNow)));
                }
                if (this.indexNow < weapon.getImages().size() - 1) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnPrev.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.arrow_left:

                if (this.indexNow > 0) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnPrev.setVisibility(View.VISIBLE);
                    this.indexNow = this.indexNow - 1;
                    index.setText((this.indexNow + 1) + "/" + weapon.getImages().size());
                    imageWeapon.setImageBitmap(getBitmapFromAsset(this, weapon.getImages().get(indexNow)));
                }
                if (this.indexNow > 0) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnPrev.setVisibility(View.VISIBLE);
//                    btnPrev.setAlpha(255);
                } else {
                    btnPrev.setVisibility(View.INVISIBLE);
//                    btnPrev.setAlpha(50);
                }
                break;
            case R.id.btnTurn :
                this.isFlip = !this.isFlip;
                flipIt(imageWeapon);
                break;
            default:
                break;
        }
    }
    private void flipIt(final View viewToFlip) {
        ObjectAnimator flip;
        if(this.isFlip) {
            flip = ObjectAnimator.ofFloat(viewToFlip, "rotationY", 0f, 180);
        } else {
            flip = ObjectAnimator.ofFloat(viewToFlip, "rotationY", 0f, 0);
        }
        flip.setDuration(1000);
        flip.start();

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
