package tw.assignment.githubapidemo.ui;

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
