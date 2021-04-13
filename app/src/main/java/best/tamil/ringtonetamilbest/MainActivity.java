package best.tamil.ringtonetamilbest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import best.tamil.ringtonetamilbest.ui.Latest.LatestFragment;
import best.tamil.ringtonetamilbest.ui.Latest.PapularFragment;
import best.tamil.ringtonetamilbest.ui.categories.CategoriesFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_latest:
                        selectedFragment = new LatestFragment();
                        break;
                    case R.id.nav_popular:
                        selectedFragment = new PapularFragment();
                        Toast.makeText(getApplicationContext(), "Popular Fragment", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_categories:
                        selectedFragment = new CategoriesFragment();
                        Toast.makeText(getApplicationContext(), "Categories Fragment", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_favorites:
                        selectedFragment = new LatestFragment();
                        Toast.makeText(getApplicationContext(), "Favourites Fragment", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_downloads:
                        selectedFragment = new LatestFragment();
                        Toast.makeText(getApplicationContext(), "Downloads Fragment", Toast.LENGTH_SHORT).show();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            }
        });


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Latest");
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new LatestFragment()).commit();
                        break;
                    case R.id.nav_login_register:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new LatestFragment()).commit();

//                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getApplicationContext());
//                        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.login_register_bottomsheet, holder.linearLayout);
//                        bottomSheetDialog.setContentView(view);
//                        bottomSheetDialog.show();
//
//                        /*initilize xml object and then do oprations on that */
//
//                        Button submit = view.findViewById(R.id.submit);
//
//                        //for dismis bottomsheet
//                        bottomSheetDialog.dismiss();


                        Toast.makeText(getApplicationContext(), "Login Register", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_full_menu:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new LatestFragment()).commit();
                        Toast.makeText(getApplicationContext(), "Full menu Fragment", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_rate:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new LatestFragment()).commit();
                        Toast.makeText(getApplicationContext(), "Rate Action", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_share:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new LatestFragment()).commit();
                        Toast.makeText(getApplicationContext(), "Share Action", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_contact_us:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new LatestFragment()).commit();
                        Toast.makeText(getApplicationContext(), "Contact Us Fragment", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_privacy:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new LatestFragment()).commit();
                        Toast.makeText(getApplicationContext(), "Privacy Fragment", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_exit:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new LatestFragment()).commit();
                        Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });
        //navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new LatestFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}