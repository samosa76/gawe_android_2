package karya.imb.gawe.view.er.intro;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.util.CustomFontCache;
import com.google.common.base.Strings;

import karya.imb.gawe.GlideApp;
import karya.imb.gawe.R;
import gawe.imb.karya.model.objects.intro.Intro;

/**
 * Created by korneliussendy on 1/25/18.
 */

public class FragmentErIntro1 extends Fragment {

    protected static final String ARG_TITLE = "title";
    protected static final String ARG_TITLE_TYPEFACE = "title_typeface";
    protected static final String ARG_DESC = "desc";
    protected static final String ARG_DESC_TYPEFACE = "desc_typeface";
    protected static final String ARG_DRAWABLE = "drawable";
    protected static final String ARG_BG_COLOR = "bg_color";
    protected static final String ARG_TITLE_COLOR = "title_color";
    protected static final String ARG_DESC_COLOR = "desc_color";

    protected static final String ARG_IMAGE_URL = "image_url";

    private int drawable, bgColor, titleColor, descColor, layoutId;
    private String title, titleTypeface, description, descTypeface, imageUrl;

    private LinearLayout mainLayout;

    Intro intro;

    public AppIntroFragment newInstance(Intro intro) {
        this.intro = intro;
        AppIntroFragment slide = new AppIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, intro.getTitle());
        args.putString(ARG_TITLE_TYPEFACE, null);
        args.putString(ARG_DESC, intro.getDescription());
        args.putString(ARG_DESC_TYPEFACE, null);
//        args.putInt(ARG_DRAWABLE, imageDrawable);
        args.putString(ARG_IMAGE_URL, intro.getImageUrl());
        args.putInt(ARG_BG_COLOR, Strings.isNullOrEmpty(intro.getBackground()) ? 0 : Color.parseColor(intro.getBackground()));
        args.putInt(ARG_TITLE_COLOR, 0);
        args.putInt(ARG_DESC_COLOR, 0);
        slide.setArguments(args);

        return slide;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_intro_1, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        TextView t = (TextView) v.findViewById(R.id.title);
        TextView d = (TextView) v.findViewById(R.id.description);
        ImageView i = (ImageView) v.findViewById(R.id.image);
        mainLayout = (LinearLayout) v.findViewById(R.id.main);

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

        super.onViewCreated(v, savedInstanceState);
    }

    public Intro getIntro() {
        return intro;
    }
}
