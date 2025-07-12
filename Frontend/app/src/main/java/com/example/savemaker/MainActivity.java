package com.example.savemaker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.savemaker.databinding.ActivityMainBinding;
import com.example.savemaker.utils.ClientUtils;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClientUtils.init();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        try {
            java.lang.reflect.Field mDragger = drawer.getClass().getDeclaredField("mLeftDragger");
            mDragger.setAccessible(true);
            Object draggerObj = mDragger.get(drawer);

            java.lang.reflect.Field mEdgeSize = draggerObj.getClass().getDeclaredField("mEdgeSize");
            mEdgeSize.setAccessible(true);
            int edgeSize = mEdgeSize.getInt(draggerObj);

            int newEdgeSize = (int) (getResources().getDisplayMetrics().widthPixels * 0.2); // 20% of screen width
            mEdgeSize.setInt(draggerObj, newEdgeSize);
            Log.d("DrawerEdge", "Edge size increased from " + edgeSize + " to " + newEdgeSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        EdgeToEdge.enable(this);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home
        ).setOpenableLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.app_bar_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}