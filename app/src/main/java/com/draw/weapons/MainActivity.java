package com.draw.weapons;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.draw.weapons.adapter.DataAdapter;
import com.draw.weapons.adapter.NavDataAdapter;
import com.draw.weapons.modal.Nav;
import com.draw.weapons.modal.Nav2;
import com.draw.weapons.modal.Weapon;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Weapon> weaponsList;
    ActionBar actionBar;
    ImageView image;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView textView;
    RecyclerView recyclerView;
    FrameLayout frmAds;
    ImageView drawer;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;
    RecyclerView recyclerViewNav;
    private static String TAG = "MainActivity";
    ArrayList<Weapon> listData;
    DataAdapter dataAdapter;
    Weapon itemSelected;
    RelativeLayout relativeLoaing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.hide();
        drawer = findViewById(R.id.drawer);
        drawer.setOnClickListener(this);

        relativeLoaing = findViewById(R.id.relativeLoaing);
        relativeLoaing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: loading");
            }
        });
        initAds();
        initListView();
        initDrawerView();

    }

    private void initAds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                intentActivity();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                intentActivity();
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

void intentActivity() {
    Intent activitymoi = new Intent(MainActivity.this, WeaponDetal.class);
    activitymoi.putExtra("data", itemSelected);
    startActivity(activitymoi);
    relativeLoaing.setVisibility(View.GONE);
}
    private void initListView() {
        recyclerView = findViewById(R.id.listWeapons);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        listData = createData("");
        dataAdapter = new DataAdapter(getApplicationContext(), listData);
        DataAdapter.OnItemClick adapterClick = new DataAdapter.OnItemClick() {
            @Override
            public void onClickItem(int position) {
                relativeLoaing.setVisibility(View.VISIBLE);
                itemSelected = listData.get(position);
                int random = new Random().nextInt(10);
                if (true) {
                    AdRequest adRequestss = new AdRequest.Builder().build();
                    mInterstitialAd.loadAd(adRequestss);
                } else {
                    intentActivity();
                }

            }
        };
        dataAdapter.setOnItemClick(adapterClick);
        recyclerView.setAdapter(dataAdapter);
    }

    private void initDrawerView() {
        drawerLayout = findViewById(R.id.my_drawer);
        drawerLayout.setOnClickListener(this);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnClickListener(this);
        recyclerViewNav = findViewById(R.id.listNav);
        recyclerViewNav.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerViewNav.setLayoutManager(layoutManager);

        final ArrayList<Nav2> list = new ArrayList<>();
        list.add(new Nav2("", "icon/0.png"));
        list.add(new Nav2("AS", "icon/1.png"));
        list.add(new Nav2("Revolver", "icon/2.png"));
        list.add(new Nav2("Pistols", "icon/3.png"));
        list.add(new Nav2("SMG", "icon/4.png"));
        list.add(new Nav2("AntiTank", "icon/5.png"));
        list.add(new Nav2("LMG", "icon/6.png"));
        list.add(new Nav2("SR", "icon/7.png"));
        list.add(new Nav2("Melee", "icon/8.png"));
        list.add(new Nav2("SG", "icon/9.png"));
        list.add(new Nav2("Grenade", "icon/10.png"));
        list.add(new Nav2("Other", "icon/11.png"));

        final NavDataAdapter adapter = new NavDataAdapter(getApplicationContext(), list);
        NavDataAdapter.OnItemClick adapterItemClick = new NavDataAdapter.OnItemClick() {
            @Override
            public void onClickItem(int position) {
                setData(list.get(position));
                drawerLayout.closeDrawers();
                for (Nav2 n : list) {
                    n.setSelected(false);
                }
                list.get(0).setImage("icon/0.png");
                list.get(position).setSelected(true);
                if(position == 0) {
                    list.get(position).setImage("icon/00.png");
                }
                adapter.notifyDataSetChanged();
            }
        };
        adapter.setOnItemClick(adapterItemClick);
        recyclerViewNav.setAdapter(adapter);

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Log.d(TAG, "onNavigationItemSelected: ");
//                menuItem.setChecked(true);
//                return true;
//            }
//        });


    }

    private void setData(Nav nav) {
        listData = createData(nav.getName());
        dataAdapter = new DataAdapter(getApplicationContext(), listData);
        DataAdapter.OnItemClick adapterClick = new DataAdapter.OnItemClick() {
            @Override
            public void onClickItem(int position) {
                relativeLoaing.setVisibility(View.VISIBLE);
                itemSelected = listData.get(position);
                int random = new Random().nextInt(10);
                if (true) {
                    AdRequest adRequestss = new AdRequest.Builder().build();
                    mInterstitialAd.loadAd(adRequestss);
                } else {
                    intentActivity();
                }

            }
        };
        dataAdapter.setOnItemClick(adapterClick);
        recyclerView.setAdapter(dataAdapter);
    }

    ArrayList createData(String category) {
        weaponsList = new ArrayList<Weapon>();
        ArrayList list = new ArrayList<Weapon>();

        Type type = new TypeToken<ArrayList<Weapon>>() {
        }.getType();
//        Weapon2 demo = new Gson().fromJson("data", Weapon2.class);
        ArrayList<Weapon> data = new Gson().fromJson(getStringFromJson(), type);
        Log.d("demo", "createData: " + data.toString());
        if (category != "") {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).equals(new Weapon(category))) {
                    list.add(data.get(i));
                }
            }
        } else {
            list = data;
        }

        return list;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawer: {
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            }

        }
//        return super.onOptionsItemSelected(v);
    }

    public String getStringFromJson() {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.data)));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
