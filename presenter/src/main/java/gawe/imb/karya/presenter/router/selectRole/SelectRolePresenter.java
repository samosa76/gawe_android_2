package gawe.imb.karya.presenter.router.selectRole;

import gawe.imb.karya.model.manager.IntroManager;
import gawe.imb.karya.model.manager.UserManager;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/24/18.
 */

public class SelectRolePresenter extends BasePresenter<SelectRoleView> {

    private boolean onProgress = false;

    public SelectRolePresenter(SelectRoleView view) {
        attachView(view);
    }

    public void onEmployeeSelected(String userId) {
        view.toast("Under construction");

//        if (onProgress)
//            return;
//        onProgress = true;
//        saveSelection(userId, UserManager.ROLE_EMPLOYEE);
    }

    public void onEmployerSelected(String userId) {
        if (onProgress)
            return;
        onProgress = true;
        saveSelection(userId, UserManager.ROLE_EMPLOYER);
    }


    private void saveSelection(String userId, String role) {
        UserManager.saveUserRole(userId, role);
        switch (role) {
            case UserManager.ROLE_EMPLOYEE:
                if (UserManager.shouldShowEEIntro()) {
                    loadEmployeeIntro();
                } else {
                    view.toEmployeePage();
                }
                break;
            case UserManager.ROLE_EMPLOYER:
                if (UserManager.shouldShowERIntro()) {
                    loadEmployerIntro();
                } else {
                    view.showLoading();
                    view.toEmployerPage();
                }
                break;
            default:
                view.errorInvalidRole();
                break;
        }
    }

    //demo purpose -> protected
    private void loadEmployerIntro() {
        view.showLoading();
        compositeDisposable.add(IntroManager.getERIntro().subscribe(intros -> {
            if (intros == null || intros.size() <= 0) {
                view.toEmployerPage();
            } else {
                view.toEmployerIntro(intros);
            }
        }, throwable -> view.toEmployerPage()));
    }

    private void loadEmployeeIntro() {
        view.showLoading();
        compositeDisposable.add(IntroManager.getEEIntro().subscribe(intros -> {
            if (intros == null || intros.size() <= 0) {
                view.hideLoading();
                view.toEmployeePage();
            } else {
                view.toEmployeeIntro(intros);
            }
        }, throwable -> view.toEmployeePage()));
    }

    public void done() {
        compositeDisposable.clear();
        dettachView();
    }
}
