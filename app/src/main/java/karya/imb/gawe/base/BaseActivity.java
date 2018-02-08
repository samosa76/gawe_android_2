package karya.imb.gawe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by korneliussendy on 1/22/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static int TAG_EMPLOYER = 1;
    public static int TAG_EMPLOYEE = 2;
    public static int TAG_ROUTER = 3;

    protected String TAG = "@@@@";
    protected int setTag(){
        return TAG_EMPLOYER;
    }

    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected View getRootView() {
        return ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

    }
}
