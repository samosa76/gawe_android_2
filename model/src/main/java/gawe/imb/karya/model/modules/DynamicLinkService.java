package gawe.imb.karya.model.modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

import gawe.imb.karya.model.BuildConfig;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class DynamicLinkService {
    private Context ctx;
    private Activity act;
    private Intent intent;
    private static String keyShared = "keyshared_utkdyn";
    private static String keyRef = "ref_code_dyn";
    private boolean debug = false;

    public void setDebug(boolean val) {
        debug = val;
    }

    public static void checkCode(final GaweDynamicListener gaweDynamicListener, Activity activity) {
        checkCode(gaweDynamicListener, activity, BuildConfig.DEBUG);
    }

    public static void checkCode(final GaweDynamicListener gaweDynamicListener, Activity activity, boolean debug) {
        Context context = activity;
        Intent intent = activity.getIntent();

        if (debug)
            Toast.makeText(activity, "Getting refferals", Toast.LENGTH_SHORT).show();

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(intent)
                .addOnSuccessListener(activity, pendingDynamicLinkData -> {
                    // Get deep link from result (may be null if no link is found)
                    Uri deepLink = null;
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.getLink();
                    }

                    if (deepLink != null
                            && deepLink.getBooleanQueryParameter("invitedby", false)) {
                        String referrerUid = deepLink.getQueryParameter("invitedby");

                        if (debug)
                            Toast.makeText(context, "Invited By " + referrerUid, Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = context.getSharedPreferences(keyShared, Context.MODE_PRIVATE).edit();
                        editor.putString(keyRef, referrerUid);
                        editor.commit();

                        if (gaweDynamicListener != null) {
                            gaweDynamicListener.onSuccess(referrerUid);
                        }

                    } else {
                        if (gaweDynamicListener != null) {
                            gaweDynamicListener.onSuccess(""); //kalau tidak ketemu ref code nya return empty string
                        }
                    }
                }).addOnFailureListener(e -> {
            if (gaweDynamicListener != null) {
                gaweDynamicListener.onFailed(e); //kalau tidak ketemu ref code nya return empty string
            }
        });
    }

    public String getCode(Activity activity) {
        act = activity;
        SharedPreferences prefs = act.getSharedPreferences(
                keyShared, Context.MODE_PRIVATE);

        String set = prefs.getString(keyRef, "");

        return set;
    }

    public void getInviteLink(final GaweInviteLinkListener gaweInviteLinkListener, Activity activity, String myRefCode) {
        getInviteLink(gaweInviteLinkListener, activity, myRefCode, "karya.imb.gawe", "imb.Gawe", "1305512852", 16, "1.0.1");
    }

    public void getInviteLink(final GaweInviteLinkListener gaweInviteLinkListener, Activity active, String myRefCode, String androidPackage, String iosPackage, String appStoreID, int androidMinimumVersion, String iosMinimumVersion) {

        act = active;
        intent = act.getIntent();
        ctx = act.getApplicationContext();

        if (!myRefCode.equals("")) {
            if (debug)
                Toast.makeText(ctx, "Getting invitelinks", Toast.LENGTH_SHORT).show();
            String link = "https://gawe.asia/index.html?invitedby=" + myRefCode;
            FirebaseDynamicLinks.getInstance().createDynamicLink()
                    .setLink(Uri.parse(link))
                    .setDynamicLinkDomain("hru5a.app.goo.gl")
                    .setAndroidParameters(
                            new DynamicLink.AndroidParameters.Builder(androidPackage)
                                    .setMinimumVersion(androidMinimumVersion)
                                    .build())
                    .setIosParameters(
                            new DynamicLink.IosParameters.Builder(iosPackage)
                                    .setAppStoreId(appStoreID)
                                    .setMinimumVersion(iosMinimumVersion)
                                    .build())
                    .buildShortDynamicLink()
                    .addOnSuccessListener(shortDynamicLink -> {
                        Uri mInvitationUrl = shortDynamicLink.getShortLink();
                        if (gaweInviteLinkListener != null) {
                            gaweInviteLinkListener.onSuccess(mInvitationUrl.toString());
                            if (debug)
                                Toast.makeText(ctx, "Invite Link OK", Toast.LENGTH_SHORT).show();
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (gaweInviteLinkListener != null) {
                        gaweInviteLinkListener.onFailed(e);
                    }
                }
            });
        } else {
            if (gaweInviteLinkListener != null) {
                gaweInviteLinkListener.onSuccess("");
            }
        }

    }

    public interface GaweDynamicListener {
        void onSuccess(String referral);
        void onFailed(Exception failed);
    }

    public interface GaweInviteLinkListener {
        void onSuccess(String inviteLink);
        void onFailed(Exception failed);
    }
}
