package karya.imb.gawe.view.er.intro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.paolorotolo.appintro.AppIntroBaseFragment;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;
import com.github.paolorotolo.appintro.ISlideSelectionListener;
import com.github.paolorotolo.appintro.util.CustomFontCache;
import com.github.paolorotolo.appintro.util.LogHelper;
import com.google.common.base.Strings;

import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import gawe.imb.karya.model.objects.intro.Intro;

/**
 * Created by korneliussendy on 1/26/18.
 */

public final class BaseFragmentIntro extends AppIntroBaseFragment implements ISlideSelectionListener,
        ISlideBackgroundColorHolder {
    protected static final String ARG_TITLE = "title";
    protected static final String ARG_TITLE_TYPEFACE = "title_typeface";
    protected static final String ARG_DESC = "desc";
    protected static final String ARG_DESC_TYPEFACE = "desc_typeface";
    protected static final String ARG_DRAWABLE = "drawable";
    protected static final String ARG_BG_COLOR = "bg_color";
    protected static final String ARG_TITLE_COLOR = "title_color";
    protected static final String ARG_DESC_COLOR = "desc_color";

    protected static final String ARG_IMAGE_URL = "image_url";

    private static final String TAG = LogHelper.makeLogTag(AppIntroBaseFragment.class);

    private int drawable, bgColor, titleColor, descColor, layoutId;
    private String title, titleTypeface, description, descTypeface, imageUrl;

    private LinearLayout mainLayout;

    public static AppIntroFragment newInstance(Intro intro) {
        AppIntroFragment slide = new AppIntroFragment();

        int resBackground, resTitleColor, resDescColor;

        try {
            resBackground = Color.parseColor(intro.getBackground());
        } catch (Exception e) {
            resBackground = 0;
        }

        try {
            resTitleColor = Color.parseColor(intro.getTitleColor());
        } catch (Exception e) {
            resTitleColor = 0;
        }

        try {
            resDescColor = Color.parseColor(intro.getDescriptionColor());
        } catch (Exception e) {
            resDescColor = 0;
        }

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, intro.getTitle());
        args.putString(ARG_TITLE_TYPEFACE, null);
        args.putString(ARG_DESC, intro.getDescription());
        args.putString(ARG_DESC_TYPEFACE, null);
//        args.putInt(ARG_DRAWABLE, imageDrawable);
        args.putString(ARG_IMAGE_URL, intro.getImageUrl());
        args.putInt(ARG_BG_COLOR, resBackground);
        args.putInt(ARG_TITLE_COLOR, resTitleColor);
        args.putInt(ARG_DESC_COLOR, resDescColor);
        slide.setArguments(args);

        return slide;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        if (getArguments() != null && getArguments().size() != 0) {
            drawable = getArguments().getInt(ARG_DRAWABLE);
            title = getArguments().getString(ARG_TITLE);
            imageUrl = getArguments().containsKey(ARG_IMAGE_URL) ?
                    getArguments().getString(ARG_IMAGE_URL) : "";
            titleTypeface = getArguments().containsKey(ARG_TITLE_TYPEFACE) ?
                    getArguments().getString(ARG_TITLE_TYPEFACE) : "";
            description = getArguments().getString(ARG_DESC);
            descTypeface = getArguments().containsKey(ARG_DESC_TYPEFACE) ?
                    getArguments().getString(ARG_DESC_TYPEFACE) : "";
            bgColor = getArguments().getInt(ARG_BG_COLOR);
            titleColor = getArguments().containsKey(ARG_TITLE_COLOR) ?
                    getArguments().getInt(ARG_TITLE_COLOR) : 0;
            descColor = getArguments().containsKey(ARG_DESC_COLOR) ?
                    getArguments().getInt(ARG_DESC_COLOR) : 0;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            drawable = savedInstanceState.getInt(ARG_DRAWABLE);
            title = savedInstanceState.getString(ARG_TITLE);
            imageUrl = savedInstanceState.getString(ARG_IMAGE_URL);
            titleTypeface = savedInstanceState.getString(ARG_TITLE_TYPEFACE);
            description = savedInstanceState.getString(ARG_DESC);
            descTypeface = savedInstanceState.getString(ARG_DESC_TYPEFACE);
            bgColor = savedInstanceState.getInt(ARG_BG_COLOR);
            titleColor = savedInstanceState.getInt(ARG_TITLE_COLOR);
            descColor = savedInstanceState.getInt(ARG_DESC_COLOR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(com.github.paolorotolo.appintro.R.layout.fragment_intro2, container, false);
        TextView t = v.findViewById(com.github.paolorotolo.appintro.R.id.title);
        TextView d = v.findViewById(com.github.paolorotolo.appintro.R.id.description);
        ImageView i = v.findViewById(com.github.paolorotolo.appintro.R.id.image);
        mainLayout = v.findViewById(com.github.paolorotolo.appintro.R.id.main);

        t.setText(title);
        if (titleColor != 0) {
            t.setTextColor(titleColor);
        }
        if (titleTypeface != null) {
            if (CustomFontCache.get(titleTypeface, getContext()) != null) {
                t.setTypeface(CustomFontCache.get(titleTypeface, getContext()));
            }
        }
        d.setText(description);
        if (descColor != 0) {
            d.setTextColor(descColor);
        }
        if (descTypeface != null) {
            if (CustomFontCache.get(descTypeface, getContext()) != null) {
                d.setTypeface(CustomFontCache.get(descTypeface, getContext()));
            }
        }
        if (Strings.isNullOrEmpty(imageUrl)) {
            GlideApp.with(this).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(i);
        }
//            i.setImageResource(drawable);
        mainLayout.setBackgroundColor(bgColor);

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_DRAWABLE, drawable);
        outState.putString(ARG_TITLE, title);
        outState.putString(ARG_IMAGE_URL, imageUrl);
        outState.putString(ARG_DESC, description);
        outState.putInt(ARG_BG_COLOR, bgColor);
        outState.putInt(ARG_TITLE_COLOR, titleColor);
        outState.putInt(ARG_DESC_COLOR, descColor);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSlideDeselected() {
        LogHelper.d(TAG, String.format("Slide %s has been deselected.", title));
    }

    @Override
    public void onSlideSelected() {
        LogHelper.d(TAG, String.format("Slide %s has been selected.", title));
    }

    @Override
    public int getDefaultBackgroundColor() {
        return bgColor;
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        mainLayout.setBackgroundColor(backgroundColor);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_intro2;
    }
}