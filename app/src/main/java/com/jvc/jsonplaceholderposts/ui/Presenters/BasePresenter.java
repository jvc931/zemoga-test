package com.jvc.jsonplaceholderposts.ui.Presenters;

import com.jvc.jsonplaceholderposts.ui.BaseView;

/**
 * Contains the common methods for the presenters.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class BasePresenter<V extends BaseView> {
    protected V view;

    /**
     * Assigns the view to the presenter.
     *
     * @param view instance of the view.
     */
    public void attachView(V view) {
        this.view = view;
    }

    /**
     * Detach the view to the presenter.
     */
    public void detachView() {
        view = null;
    }

    /**
     * Returns if the view is attached or not.
     *
     * @return true if the view is attached the the presenter.
     */
    public boolean isViewAttached() {
        return view != null;
    }
}
