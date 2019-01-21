package com.shaheda.assignment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.shaheda.assignment.network.ApiResponseObj;
import com.shaheda.assignment.tabs.Screen1Fragment;
import com.shaheda.assignment.tabs.Screen2Fragment;
import com.shaheda.assignment.tabs.TabFragment;
import com.shaheda.assignment.tabs.adapters.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Screen1Fragment.Screen1Callback {

    private static final int SCREEN1 = 0;
    private static final int SCREEN2 = 1;

    private ViewPager viewPager;
    private List<String> imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagesList = new ArrayList<>();
        viewPager = findViewById(R.id.view_pager);
        setTabs();
    }

    private void setTabs() {

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        final TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), new TabFragment[] {Screen1Fragment.newInstance(), Screen2Fragment.newInstance()});

        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                TabFragment currentTab = (TabFragment) tabPagerAdapter.getItem(position);
                if (currentTab instanceof Screen2Fragment) {
                    ((Screen2Fragment) currentTab).refresh();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setCurrentItem(SCREEN1);
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    private void goToScreen2() {
        viewPager.setCurrentItem(SCREEN2, true);
    }


    // Screen1Callbacks

    @Override
    public void onClickButton1() {
        final ProgressBar loadingProgressbar = findViewById(R.id.loadingProgress);
        ((MainApp) getApplication())
                .getApiInterface()
                .getImage()
                .enqueue(new Callback<ApiResponseObj>() {
                    @Override
                    public void onResponse(Call<ApiResponseObj> call, Response<ApiResponseObj> response) {
                        ApiResponseObj responseObj;
                        if (response != null && (responseObj = response.body()) != null) {
                            switch (responseObj.getStatus()) {
                                case "success":
                                    imagesList.add(0, responseObj.getMessage());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            goToScreen2();
                                        }
                                    });
                                    break;
                            }
                        }
                        loadingProgressbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<ApiResponseObj> call, Throwable t) {
                        Log.e(".Main", "onFailure " + t.getMessage());
                        loadingProgressbar.setVisibility(View.GONE);
                    }
                });
        loadingProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickButton2() {
        imagesList.clear();
    }
}
