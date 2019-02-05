package tw.assignment.githubapidemo.ui.githubrepositories;

import tw.assignment.githubapidemo.R;
import tw.assignment.githubapidemo.ui.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.assignment.githubapidemo.ui.model.RepositoryModel;
import tw.assignment.githubapidemo.ui.repositorydetails.RepositoryDetailsActivity;
import tw.assignment.githubapidemo.utils.Constants;

public class GithubRepositoryActivity extends BaseActivity
        implements GithubRepositoryContract.RepositoryView, GithubRepositoryItemListener {

    @BindView(R.id.repositoriesRecyclerView) RecyclerView mRepositoriesRecyclerView;
    @Inject protected GithubRepositoryPresenter mGithubRepositoryPresenter;
    @Inject protected GithubRepositoryAdapter mGithubRepositoryAdapter;
    private GithubRepositoryItemListener mGithubRepositoryItemListener;
    private List<RepositoryModel> allRepositoriesList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getControllerComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repository);
        ButterKnife.bind(this);
        mGithubRepositoryPresenter.attachView(this);
        mGithubRepositoryItemListener = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGithubRepositoryPresenter.getAllRepositories();
    }

    @Override
    public void showAllRepositories(List<RepositoryModel> repositoryModelList) {
        mRepositoriesRecyclerView.setHasFixedSize(true);
        mRepositoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGithubRepositoryAdapter.setAllRepositories(repositoryModelList);
        mRepositoriesRecyclerView.setAdapter(mGithubRepositoryAdapter);
        mGithubRepositoryAdapter.setOnItemClickListener(mGithubRepositoryItemListener);
        allRepositoriesList = repositoryModelList;
    }

    @Override
    public void showRepositoryDetailsActivity(String repositoryName) {
        final Intent intent = new Intent(this, RepositoryDetailsActivity.class);
        intent.putExtra(Constants.REPOSITORY_NAME, repositoryName);
        startActivity(intent);
    }

    @Override
    public void onItemClickListener(int itemPosition) {
        mGithubRepositoryPresenter.openRepositoryDetailsActivity(allRepositoriesList.get(itemPosition)
                .getRepositoryFullName());
    }

    @Override
    protected void onDestroy() {
        mGithubRepositoryPresenter.detachView();
        super.onDestroy();
    }
}
