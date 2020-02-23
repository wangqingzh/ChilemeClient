package com.wangqing.chilemecilent;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
                R.id.homeFragment,R.id.evaluateFragment, R.id.foodRecFragment, R.id.messageFragment, R.id.mineFragment
        ).build();

        // 设置顶部actionBar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // 设置底部bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        //设置导航监听器 在某些页面隐藏bottomNavigationView
        navController.addOnDestinationChangedListener(this);


        // 动态检测网络变化
        NetworkCallbackImpl networkCallback = new NetworkCallbackImpl();
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        NetworkRequest request = builder.build();
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            connectivityManager.registerNetworkCallback(request, networkCallback);
        }

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
            case R.id.foodRecFragment:
            case R.id.messageFragment:
            case R.id.mineFragment:
            case R.id.signInFragment:
            case R.id.evaluateFragment:
                bottomNavigationView.setVisibility(View.VISIBLE);
                break;
            default:
                bottomNavigationView.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 网络变化回调
     */
    class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            Toast.makeText(MainActivity.this, "网络不可用", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Toast.makeText(MainActivity.this, "您正在使用移动网络", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    /**
     * 增加此方法的重写 避免在fragment中请求权限不回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
