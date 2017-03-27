package khs.study.alc_android.post.view.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import khs.study.alc_android.R;
import khs.study.alc_android.post.domain.Post;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    List<Post> mPosts;

    public PostAdapter(List<Post> mPosts) {
        this.mPosts = mPosts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_post, parent, false);

        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = mPosts.get(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvContext.setText(post.getContent());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvContext;

        public PostViewHolder(View v) {
            super(v);
            this.mView = v;

            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            this.tvContext = (TextView) v.findViewById(R.id.tvContext);
        }
    }
}
