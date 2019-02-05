package tw.assignment.githubapidemo.ui.repositorydetails;

import tw.assignment.githubapidemo.ui.BasePresenter;
import tw.assignment.githubapidemo.ui.BaseView;
import tw.assignment.githubapidemo.ui.model.RepositoryDetailsModel;


public class RepositoryDetailsContract {

    interface RepositoryDetailsView extends BaseView {
        void showRepositoryDetails(RepositoryDetailsModel repositoryDetailsModel);
    }

    interface RepositoryDetailsActionListener<T extends BaseView> extends BasePresenter<T> {
        void getRepositoryDetails(String repositoryFullName);
    }
}
