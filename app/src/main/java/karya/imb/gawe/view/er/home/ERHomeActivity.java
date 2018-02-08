package karya.imb.gawe.view.er.home;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import gawe.imb.karya.presenter.er.home.ERHomePresenter;
import gawe.imb.karya.presenter.er.home.ERHomeView;
import karya.imb.gawe.R;
import karya.imb.gawe.view.er.ERBaseActivity;
import karya.imb.gawe.view.er.history.ERHistoryFragment;
import karya.imb.gawe.view.er.adsMy.ERMyAdsFragment;
import karya.imb.gawe.view.er.partners.ERPartnersFragment;
import karya.imb.gawe.view.er.profile.ERProfileFragment;
import karya.imb.gawe.view.er.urgent.ERUrgentFragment;

/**
 * Created by korneliussendy on 1/26/18.
 */

public class ERHomeActivity extends ERBaseActivity<ERHomePresenter> implements ERHomeView {

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.viewpagertab) SmartTabLayout viewPagerTab;

    @Override
    protected ERHomePresenter createPresenter() {
        return new ERHomePresenter(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_er_home;
    }

    @Override
    protected boolean showTitle() {
        return false;
    }

    @Override
    protected boolean showLogo() {
        return false;
    }

    @Override
    protected boolean showToolbar() {
        return false;
    }

    @Override
    protected boolean showRightIcon() {
        return false;
    }

    @Override
    protected boolean showRightButton() {
        return false;
    }

    @Override
    protected boolean showLeftIcon() {
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private int[] resImages = {
            R.drawable.ic_user,
            R.drawable.ic_flash,
            R.drawable.ic_partner,
            R.drawable.ic_my_ads,
            R.drawable.ic_clock
    };

    private String[] titles = {
            "Profile",
            "Urgent",
            "Partners",
            "My Ads",
            "History"
    };

    private String[] tags = {
            ERProfileFragment.class.getSimpleName(),
            ERUrgentFragment.class.getSimpleName(),
            ERPartnersFragment.class.getSimpleName(),
            ERMyAdsFragment.class.getSimpleName(),
            ERHistoryFragment.class.getSimpleName()
    };

    private int PAGE_COUNT;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(titles[0], ERProfileFragment.class)
                .add(titles[1], ERUrgentFragment.class)
                .add(titles[2], ERPartnersFragment.class)
                .add(titles[3], ERMyAdsFragment.class)
                .add(titles[4], ERHistoryFragment.class)
                .create());

        PAGE_COUNT = adapter.getCount();
        viewPagerTab.setCustomTabView((container, position, adapter1) -> {
            View v = LayoutInflater.from(this).inflate(R.layout.custom_pager_adapter, container, false);
            TextView text = v.findViewById(R.id.tvTitle);
            ImageView icon = v.findViewById(R.id.ivIcon);
//            if (position >= PAGE_COUNT)
//                throw new IllegalStateException("Invalid position: " + position);
            text.setText(titles[position]);
//
            Drawable normalDrawable = ContextCompat.getDrawable(this, resImages[position]);
            Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
            DrawableCompat.setTintList(wrapDrawable, ContextCompat.getColorStateList(this, R.drawable.pager_text_color));

            icon.setImageDrawable(ContextCompat.getDrawable(this, resImages[position]));
            icon.setImageDrawable(wrapDrawable);
            return v;
        });
        viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clearNotif(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPagerTab.setOnTabClickListener(this::clearNotif);

        viewPager.setOffscreenPageLimit(PAGE_COUNT);
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
    }

    private void notifTab(int pos, int qty) {
        String s = qty > 10 ? "9+" : String.valueOf(qty);
        notifTab(pos, s);
    }

    private void notifTab(int pos, String text) {
        View v = viewPagerTab.getTabAt(pos);
        TextView tvNotif = v.findViewById(R.id.tvNotif);
        if (Strings.isNullOrEmpty(text)) {
            text = "*";
        }

        tvNotif.setVisibility(View.VISIBLE);
        tvNotif.setText(text);
    }

    public void notifTab(String tag, String text) {
        for (int i = 0; i < tags.length; i++) {
            if (tags[i].equals(tag)) {
                notifTab(i, text);
                return;
            }
        }
    }

    public void notifTab(String tag, int number) {
        for (int i = 0; i < tags.length; i++) {
            if (tags[i].equals(tag)) {
                notifTab(i, number);
                return;
            }
        }
    }

    private void clearNotif(int pos) {
        View v = viewPagerTab.getTabAt(pos);
        TextView tvNotif = v.findViewById(R.id.tvNotif);
        tvNotif.setVisibility(View.INVISIBLE);
        tvNotif.setText("");
    }
}
