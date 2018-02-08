package gawe.imb.karya.presenter.er.urgent;

import java.util.List;

import gawe.imb.karya.presenter.base.BaseView;

/**
 * Created by korneliussendy on 1/27/18.
 */

public interface ERUrgentView extends BaseView {

    void showActivePage(List<String> strings);

    void showPassivePage();


}
