package gawe.imb.karya.presenter.er.urgent;

import java.util.ArrayList;
import java.util.List;

import gawe.imb.karya.model.manager.JobManager;
import gawe.imb.karya.presenter.base.BasePresenter;

/**
 * Created by korneliussendy on 1/27/18.
 */

public class ERUrgentPresenter extends BasePresenter<ERUrgentView> {

    private List<String> listActiveJob;

    public ERUrgentPresenter(ERUrgentView view) {
        attachView(view);
        listActiveJob = new ArrayList<>();
    }

    public void listenPage() {
//        addDisposable(JobManager.hasActiveJobs()
//                .subscribe(hasActiveJob -> {
//                    if (hasActiveJob) {
//                        view.showActivePage();
//                    } else {
//                        view.showPassivePage();
//                    }
//                }, throwable -> view.errorListenPage(throwable))
//        );

        //dummy test N

//        listActiveJob.clear();
//        listActiveJob.add("aa");
//        listActiveJob.add("bb");
//        listActiveJob.add("cc");
//        view.showActivePage(listActiveJob);


        addDisposable(JobManager.getActiveJobs().subscribe(strings -> {
            if (strings == null || strings.size() <= 0) {
                view.showPassivePage();
            } else {
                view.showActivePage(strings);
            }
        }));
    }
}
