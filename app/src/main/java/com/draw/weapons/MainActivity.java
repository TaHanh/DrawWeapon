package com.draw.weapons;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.draw.weapons.adapter.DataAdapter;
import com.draw.weapons.adapter.NavDataAdapter;
import com.draw.weapons.modal.Nav;
import com.draw.weapons.modal.Weapon;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Weapon> weaponsList;
    DrawerLayout mDrawerLayout;
    ActionBar actionBar;
    ImageView image;
    ActionBarDrawerToggle abdt;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView textView;
    RecyclerView recyclerView;
    ImageView drawer;
    //    ArrayList<Weapon>
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.hide();

        drawer  = findViewById(R.id.drawer);
        drawer.setOnClickListener(this);

        initListView();
        initDrawerView();

    }


    private void initListView() {
        recyclerView = (RecyclerView) findViewById(R.id.listWeapons);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        DataAdapter adapter = new DataAdapter(getApplicationContext(), createData(""));
        recyclerView.setAdapter(adapter);


    }

    private void initDrawerView() {
        drawerLayout = findViewById(R.id.my_drawer);
        drawerLayout.setOnClickListener(this);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.listNav);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<Nav> list = new ArrayList<Nav>();
        list.add(new Nav("", "icon/0.png"));
        list.add(new Nav("AS", "icon/1.png"));
        list.add(new Nav("Revolver", "icon/2.png"));
        list.add(new Nav("Pistols", "icon/3.png"));
        list.add(new Nav("SMG", "icon/4.png"));
        list.add(new Nav("AntiTank", "icon/5.png"));
        list.add(new Nav("LMG", "icon/6.png"));
        list.add(new Nav("SR", "icon/7.png"));
        list.add(new Nav("Melee", "icon/8.png"));
        list.add(new Nav("SG", "icon/9.png"));
        list.add(new Nav("Grenade", "icon/10.png"));
        list.add(new Nav("Other", "icon/11.png"));

        NavDataAdapter adapter = new NavDataAdapter(getApplicationContext(), list);
        NavDataAdapter.OnItemClick adapterItemClick = new NavDataAdapter.OnItemClick() {
            @Override
            public void onClickItem(int position) {
//                list.get(position).setBackgroundColor("#ffffff");
                setData(list.get(position));
                drawerLayout.closeDrawers();

            }
        };
        adapter.setOnItemClick(adapterItemClick);
        recyclerView.setAdapter(adapter);

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
        DataAdapter adapter = new DataAdapter(getApplicationContext(), createData(nav.getName()));
        recyclerView.setAdapter(adapter);
    }

    ArrayList createData(String category) {
        weaponsList = new ArrayList<Weapon>();
        ArrayList list = new ArrayList<Weapon>();

        Type type = new TypeToken<ArrayList<Weapon>>() {
        }.getType();
//        Weapon2 demo = new Gson().fromJson("data", Weapon2.class);
        ArrayList<Weapon> data = new Gson().fromJson(getStringFromJson(), type);
        Log.d("demo", "createData: " + data.toString());
        if(category != "" ) {
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
            case R.id.drawer:
            {
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

    public void addListenerOnButton() {
        try {
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open("images/1/0.png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            image = (ImageView) findViewById(R.id.avatar);
            image.setImageBitmap(bitmap);
        } catch (IOException e) {
        }

    }
}
