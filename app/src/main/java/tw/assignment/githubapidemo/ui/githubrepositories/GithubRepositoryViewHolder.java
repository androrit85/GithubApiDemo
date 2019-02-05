package tw.assignment.githubapidemo.ui.githubrepositories;

import tw.assignment.githubapidemo.R;
import tw.assignment.githubapidemo.ui.model.RepositoryModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubRepositoryViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    @BindView(R.id.repositoryCardView) CardView mRepositoryCardView;
    @BindView(R.id.authorImageView) ImageView mAuthorImageView;
    @BindView(R.id.titleTextView) TextView mTitleTextView;
    @BindView(R.id.repositoryNameTextView) TextView mRepositoryName;
    @BindView(R.id.descriptionTextView) TextView mDescriptionTextView;

    private final GithubRepositoryItemListener mGithubRepositoryItemListener;

    public GithubRepositoryViewHolder(@NonNull View itemView, GithubRepositoryItemListener mGithubRepositoryItemListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mGithubRepositoryItemListener = mGithubRepositoryItemListener;
        mRepositoryCardView.setOnClickListener(this);
    }

    public void bindItems(List<RepositoryModel> repositoryModelList) {
        final RepositoryModel repositoryModel = repositoryModelList.get(getAdapterPosition());
        Glide.with(itemView.getContext())
                .load(repositoryModel.getAuthorImageUrl())
                .into(mAuthorImageView);
        mRepositoryName.setText(repositoryModel.getRepositoryName());
        mTitleTextView.setText(repositoryModel.getAuthorName());
        mDescriptionTextView.setText(splitDescription(repositoryModel.getRepositoryDescription()));
    }

    private String splitDescription(String description) {
        if (description != null && description.split("\\s+").length > 15) {
            final StringBuilder builder = new StringBuilder();
            for (String word : description.split("\\s+")) {
                if(builder.toString().split("\\s+").length < 15) {
                    builder.append(word).append(" ");
                }
            }
            return builder.toString().replaceAll("\\s+$", "") + "...";
        }
        return description;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mRepositoryCardView.getId()) {
            mGithubRepositoryItemListener.onItemClickListener(getAdapterPosition());
        }
    }
}
