package tw.assignment.githubapidemo.ui.repositorydetails;

import tw.assignment.githubapidemo.R;
import tw.assignment.githubapidemo.ui.BaseActivity;
import tw.assignment.githubapidemo.ui.model.RepositoryDetailsModel;
import tw.assignment.githubapidemo.utils.Constants;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepositoryDetailsActivity extends BaseActivity
        implements RepositoryDetailsContract.RepositoryDetailsView {

    @BindView(R.id.authorImageView) ImageView mAuthorImageView;
    @BindView(R.id.authorNameTextView) TextView mAuthorNameTextView;
    @BindView(R.id.repositoryNameTextView) TextView mRepositoryNameTextView;
    @BindView(R.id.descriptionTextView) TextView mDescriptionTextView;
    @BindView(R.id.stargazersTextView) TextView mstarGazersTextView;
    @BindView(R.id.forksTextView) TextView mForksTextView;
    @BindView(R.id.watchersTextView) TextView mWatchersTextView;
    @BindView(R.id.openIssuesTextView) TextView mOpenIssuesTextView;
    @Inject protected RepositoryDetailsPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getControllerComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);
        ButterKnife.bind(this);
        mPresenter.attachView(this);
        final String repositoryFullName = getIntent().getStringExtra(Constants.REPOSITORY_NAME);
        mPresenter.getRepositoryDetails(repositoryFullName);
    }

    @Override
    public void showRepositoryDetails(RepositoryDetailsModel repositoryDetailsModel) {
        Glide.with(this)
                .load(repositoryDetailsModel.getAuthorImageUrl())
                .into(mAuthorImageView);
        mAuthorNameTextView.setText(repositoryDetailsModel.getAuthorName());
        mRepositoryNameTextView.setText(repositoryDetailsModel.getRepositoryName());
        mDescriptionTextView.setText(repositoryDetailsModel.getRepositoryDescription());
        mstarGazersTextView.setText(getResources().getString(R.string.stargazers_text,repositoryDetailsModel.getRepositoryStargazers()));
        mForksTextView.setText(getResources().getString(R.string.forks_text,repositoryDetailsModel.getRepositoryForks()));
        mWatchersTextView.setText(getResources().getString(R.string.watchers_text,repositoryDetailsModel.getRepositoryWatchers()));
        mOpenIssuesTextView.setText(getResources().getString(R.string.open_issues_text,repositoryDetailsModel.getRepositoryOpenIssues()));
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

}
