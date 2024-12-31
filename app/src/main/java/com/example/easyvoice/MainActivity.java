package com.example.easyvoice;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Set up the adapter for ViewPager
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), getLifecycle()));

        // Attach TabLayout with ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("To Text");
            } else if (position == 1) {
                tab.setText("From Text");
            } else if (position == 2) {
                tab.setText("Text to Sign");
            }
        }).attach();
    }

    private static class FragmentAdapter extends FragmentStateAdapter {

        public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new ToTextFragment();
            } else if (position == 1) {
                return new FromTextFragment();
            } else if (position == 2) {
                return new TextToSignFragment();  // Add this fragment for Text to Sign conversion
            }
            return new Fragment();  // Default case
        }


        @Override
        public int getItemCount() {
            return 3; // Three tabs
        }

        private class TextToSignFragment extends Fragment {
        }

        private class ToTextFragment extends Fragment {
        }
    }
}
