package com.wangqing.chilemecilent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener {

    private NavController navController;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.partitionFragment, R.id.messageFragment, R.id.mineFragment
        ).build();

        // 设置顶部actionBar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // 设置底部bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        //设置导航监听器 在某些页面隐藏bottomNavigationView
        navController.addOnDestinationChangedListener(this);

    }

    /**
     * 设置actionBar向上键为返回
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp() || navController.navigateUp();
    }

    /**
     * 目的地改变监听 从top destination跳转后 隐藏底部的导航
     *
     * @param controller
     * @param destination
     * @param arguments
     */
    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        switch (destination.getId()) {
            case R.id.homeFragment:
            case R.id.partitionFragment:
            case R.id.messageFragment:
            case R.id.mineFragment:
                bottomNavigationView.setVisibility(View.VISIBLE);
                break;
            default:
                bottomNavigationView.setVisibility(View.GONE);
                break;
        }
    }
}
