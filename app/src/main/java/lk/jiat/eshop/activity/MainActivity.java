package lk.jiat.eshop.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import lk.jiat.eshop.R;
import lk.jiat.eshop.fragment.HomeFragment;
import lk.jiat.eshop.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NavigationBarView.OnItemSelectedListener {

    private MaterialToolbar toolbar;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.side_navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        setSupportActionBar(toolbar);

        // Drawer Toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Back button behavior
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finish();
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);

        // Load default fragment
        loadFragment(new HomeFragment());
        navigationView.setCheckedItem(R.id.side_nav_home);
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);
    }

    // 🔹 Fragment Loader Method
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    // SIDE + BOTTOM NAVIGATION HANDLER
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        // HOME
        if (id == R.id.side_nav_home || id == R.id.bottom_nav_home) {
            loadFragment(new HomeFragment());
            navigationView.setCheckedItem(R.id.side_nav_home);
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);
        }

        // PROFILE
        else if (id == R.id.side_nav_profile || id == R.id.bottom_nav_profile) {
            loadFragment(new ProfileFragment());
            navigationView.setCheckedItem(R.id.side_nav_profile);
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_profile);
        }

        // ORDERS
        else if (id == R.id.side_nav_orders) {
            // loadFragment(new OrdersFragment());
        }

        // WISHLIST
        else if (id == R.id.side_nav_wishlist) {
        }

        // CART
        else if (id == R.id.side_nav_cart || id == R.id.bottom_nav_cart) {
        }

        // MESSAGE
        else if (id == R.id.side_nav_message) {
        }

        // SETTINGS
        else if (id == R.id.side_nav_setting) {
        }

        // CATEGORY (Bottom)
        else if (id == R.id.bottom_nav_category) {
        }

        // LOGIN
        else if (id == R.id.side_nav_login) {
        }

        // LOGOUT
        else if (id == R.id.side_nav_logout) {
        }

        // Close drawer after click
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
