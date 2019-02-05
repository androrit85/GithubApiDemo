package tw.assignment.githubapidemo.ui.githubrepositories;

import tw.assignment.githubapidemo.ui.BasePresenter;
import tw.assignment.githubapidemo.ui.BaseView;
import tw.assignment.githubapidemo.ui.model.RepositoryModel;

import java.util.List;

public interface GithubRepositoryContract {
    interface RepositoryView extends BaseView {
        void showAllRepositories(List<RepositoryModel> repositoryModelList);

        void showRepositoryDetailsActivity(String repositoryName);
    }

    interface RepositoryActionListener<T extends BaseView> extends BasePresenter<T> {
        void getAllRepositories();

        void openRepositoryDetailsActivity(String repositoryName);
    }
}
