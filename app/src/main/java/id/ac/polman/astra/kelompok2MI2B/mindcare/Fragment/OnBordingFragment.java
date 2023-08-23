package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import id.ac.polman.astra.kelompok2MI2B.mindcare.R;

public class OnBordingFragment extends Fragment {
    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.onbording, container, false);

        backbtn = view.findViewById(R.id.backbtn);
        nextbtn = view.findViewById(R.id.nextbtn);
        skipbtn = view.findViewById(R.id.skipButton);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getitem(0) > 0) {
                    mSLideViewPager.setCurrentItem(getitem(-1), true);
                }
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getitem(0) < 2)
                    mSLideViewPager.setCurrentItem(getitem(1), true);
                else {
                    // Ganti LoginActivity dengan LoginFragment
                    LoginFragment loginFragment = new LoginFragment();
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_main_fragment_container, loginFragment)
                            .commit();
                }
            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ganti LoginActivity dengan LoginFragment
                LoginFragment loginFragment = new LoginFragment();
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_main_fragment_container, loginFragment)
                        .commit();
            }
        });

        mSLideViewPager = view.findViewById(R.id.slideViewPager);
        mDotLayout = view.findViewById(R.id.indicator_layout);
        viewPagerAdapter = new ViewPagerAdapter(requireContext());
        mSLideViewPager.setAdapter(viewPagerAdapter);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpindicator(int position) {
        dots = new TextView[4];
        mDotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(requireContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, requireContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.active, requireContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);

            if (position > 0) {
                backbtn.setVisibility(View.VISIBLE);
            } else {
                backbtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i) {
        return mSLideViewPager.getCurrentItem() + i;
    }

    public static OnBordingFragment newInstance() {
        return new OnBordingFragment();
    }
}
