package com.draw.weapons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.draw.weapons.adapter.DataAdapter;
import com.draw.weapons.adapter.NavDataAdapter;
import com.draw.weapons.modal.Nav;
import com.draw.weapons.modal.Weapon;
import com.draw.weapons.modal.Weapon2;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Weapon> weaponsList;
    DrawerLayout mDrawerLayout;
    ActionBar actionBar;
    ImageView image;
    ActionBarDrawerToggle abdt;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.weapon_detail);
        actionBar = getSupportActionBar();
        actionBar.hide();
//
        initListView();
        initDrawerView();

    }


    private void initListView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listWeapons);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        DataAdapter adapter = new DataAdapter(getApplicationContext(), createData("a"));
        recyclerView.setAdapter(adapter);
    }

    private void initDrawerView() {
        drawerLayout = findViewById(R.id.my_drawer);
        navigationView = findViewById(R.id.nav_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listNav);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Nav> list = new ArrayList<Nav>();
        list.add(new Nav("1","icon/1.png"));
        list.add(new Nav("1","icon/2.png"));
        list.add(new Nav("1","icon/3.png"));
        list.add(new Nav("1","icon/4.png"));
        list.add(new Nav("1","icon/5.png"));
        list.add(new Nav("1","icon/6.png"));
        list.add(new Nav("1","icon/7.png"));
        list.add(new Nav("1","icon/8.png"));
        list.add(new Nav("1","icon/10.png"));

        NavDataAdapter adapter = new NavDataAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
//                textView.setText(menuItem.getTitle());
                drawerLayout.closeDrawers();
                return true;
            }
        });


    }

    ArrayList createData(String category) {
        weaponsList = new ArrayList<Weapon>();
        ArrayList list = new ArrayList<Weapon>();

        Type type = new  TypeToken<ArrayList<Weapon>>(){}.getType();
//        Weapon2 demo = new Gson().fromJson("data", Weapon2.class);
        ArrayList<Weapon> data = new Gson().fromJson(getStringFromJson(), type);
        Log.d("demo", "createData: " +data.toString());

//        ArrayList<Weapon> list = new ArrayList<Weapon>();
//        list = data;
        switch (category){
            case "a" :
                list = data;
                break;
            case "b" :
//                list = data.subList(0, 1);
                break;
        }
        return list;
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
//        image.setImageResource(R.drawable.ima0);
            image.setImageBitmap(bitmap);
        } catch (IOException e) {
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawer:
                Log.d("asssss", "sdasdasda");
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

        }
//        return super.onOptionsItemSelected(v);
    }
}
