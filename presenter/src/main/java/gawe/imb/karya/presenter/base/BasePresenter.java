package gawe.imb.karya.presenter.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by korneliussendy on 1/22/18.
 */

public abstract class BasePresenter<V> {
    protected V view;
    protected CompositeDisposable compositeDisposable;

    protected void attachView(V view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    protected void dettachView() {
        this.view = null;
        compositeDisposable.clear();
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void clearDisposable() {
        compositeDisposable.clear();
    }

    public void done() {
        dettachView();
        clearDisposable();
    }

}