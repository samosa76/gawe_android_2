package gawe.imb.karya.model.manager;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class AccountKitManager {

    public static Single<AccountKitData> getCurrentAccount() {
        Single<AccountKitData> single = Single.create(emitter ->
                AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                    @Override
                    public void onSuccess(final Account account) {
                        PhoneNumber phoneNumber = account.getPhoneNumber();

                        emitter.onSuccess(new AccountKitData(
                                phoneNumber.getCountryCode(),
                                phoneNumber.getCountryCodeIso(),
                                phoneNumber.getRawPhoneNumber(),
                                account.getEmail(),
                                account.getId()
                        ));
                    }

                    @Override
                    public void onError(final AccountKitError error) {
                        emitter.onError(new Exception(error.getUserFacingMessage()));
                    }
                })
        );
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return single;
    }

    public static Single<String> getCurrentAccessToken() {
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            return Single.just(accessToken.getToken());
        } else {
            return Single.just("");
        }
    }

    public static Single<String> getAccessTokenId() {
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            return Single.just(accessToken.getAccountId());
        } else {
            return Single.just("");
        }
    }

    public static class AccountKitData {
        private String countryCode;
        private String countryCodeIso;
        private String phone;
        private String email;
        private String accountId;

        AccountKitData(String countryCode, String countryCodeIso, String phone, String email, String accountId) {
            this.countryCode = countryCode;
            this.countryCodeIso = countryCodeIso;
            this.phone = phone;
            this.email = email;
            this.accountId = accountId;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryCodeIso() {
            return countryCodeIso;
        }

        public void setCountryCodeIso(String countryCodeIso) {
            this.countryCodeIso = countryCodeIso;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }
    }

}
