package tw.assignment.githubapidemo.ui.githubrepositories;

import tw.assignment.githubapidemo.R;
import tw.assignment.githubapidemo.ui.model.RepositoryModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class GithubRepositoryAdapter extends RecyclerView.Adapter<GithubRepositoryViewHolder> {

    private final static int MAX_LIST_SIZE = 25;
    private List<RepositoryModel> mGithubRepositoryList;
    private GithubRepositoryItemListener mGithubRepositoryItemListener;

    public GithubRepositoryAdapter() {
        mGithubRepositoryList = new ArrayList<>();
    }

    public void setAllRepositories(List<RepositoryModel> repositoryModelList) {
        mGithubRepositoryList = repositoryModelList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(GithubRepositoryItemListener githubRepositoryItemListener) {
        mGithubRepositoryItemListener = githubRepositoryItemListener;
    }

    @NonNull
    @Override
    public GithubRepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GithubRepositoryViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_repository_details_item, viewGroup, false),
                mGithubRepositoryItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubRepositoryViewHolder githubRepositoryViewHolder, int itemPosition) {
        githubRepositoryViewHolder.bindItems(mGithubRepositoryList);
    }

    @Override
    public int getItemCount() {
        return MAX_LIST_SIZE;
    }
}
