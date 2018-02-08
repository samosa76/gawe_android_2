package gawe.imb.karya.model.manager;

import com.google.common.base.Strings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import gawe.imb.karya.model.utils.Helper;
import io.reactivex.Completable;

/**
 * Created by korneliussendy on 2/7/18.
 */

public class CreditManager {

    private static String BASE_DB = "db_karya/";
    private static String BASE_EMPLOYEE_DB = BASE_DB + "db_employee/";
    private static String BASE_EMPLOYER_DB = BASE_DB + "db_employer/";
    private static String BASE_UNIVERSAL_DB = BASE_DB + "user_all/";

    private static String REF_USER_EMPLOYEE = BASE_EMPLOYEE_DB + "user";
    private static String REF_USER_EMPLOYER = BASE_EMPLOYER_DB + "user";
    private static String REF_USER_UNIVERSAL = BASE_UNIVERSAL_DB;
    private static String POINTS = "point";

    public static Completable consumeCredits(final Long howMuch) {
        if (UserManager.getUserPreference() == null || Strings.isNullOrEmpty(UserManager.getUserPreference().getId())) {
            return Completable.error(new NullPointerException("empty user"));
        }

        return Completable.create(emitter -> {
            DatabaseReference userRef = Helper.getDatabase()
                    .getReference(REF_USER_EMPLOYER)
                    .child(UserManager.getUserPreference().getId())
                    .child(POINTS);
            userRef.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData currentData) {
                    if (currentData.getValue() == null || ((Long) currentData.getValue()) == 0) {
//                            return Transaction.abort();
                        currentData.setValue(0);
                    }
                    currentData.setValue((Long) currentData.getValue() - howMuch);
                    return Transaction.success(currentData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                    if (databaseError != null) {
                        emitter.onError(databaseError.toException());
                        return;
                    }
                    if (!b) {
                        emitter.onError(new Exception("Not Commited"));
                        return;
                    }
                    emitter.onComplete();
                }
            });
        });
    }
}
