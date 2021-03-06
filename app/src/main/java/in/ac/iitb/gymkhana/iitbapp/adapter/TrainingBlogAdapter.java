package in.ac.iitb.gymkhana.iitbapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.ac.iitb.gymkhana.iitbapp.ItemClickListener;
import in.ac.iitb.gymkhana.iitbapp.R;
import in.ac.iitb.gymkhana.iitbapp.data.TrainingBlogPost;
import ru.noties.markwon.Markwon;

public class TrainingBlogAdapter extends RecyclerView.Adapter<TrainingBlogAdapter.ViewHolder> {

    private List<TrainingBlogPost> posts;
    private Context context;
    private ItemClickListener itemClickListener;

    public TrainingBlogAdapter(List<TrainingBlogPost> posts, ItemClickListener itemClickListener) {
        this.posts = posts;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.blog_post_card, parent, false);

        final TrainingBlogAdapter.ViewHolder postViewHolder = new TrainingBlogAdapter.ViewHolder(postView);
        postView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, postViewHolder.getAdapterPosition());
            }
        });
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrainingBlogPost post = posts.get(position);
        Markwon.setMarkdown(holder.postTitle, post.getTitle());
        holder.postPublished.setText(post.getPublished());
        Markwon.setMarkdown(holder.postContent, post.getContent());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView postTitle;
        private TextView postPublished;
        private TextView postContent;

        public ViewHolder(View itemView) {
            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.post_title);
            postPublished = (TextView) itemView.findViewById(R.id.post_published);
            postContent = (TextView) itemView.findViewById(R.id.post_content);
        }
    }
}
