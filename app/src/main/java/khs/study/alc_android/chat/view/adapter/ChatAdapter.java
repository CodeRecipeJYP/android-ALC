package khs.study.alc_android.chat.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import khs.study.alc_android.R;
import khs.study.alc_android.chat.domain.Message;

/**
 * Created by jaeyoung on 2017. 4. 6..
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    List<Message> mMessages;

    public ChatAdapter(List<Message> mMessages) {
        this.mMessages = mMessages;
    }

    @Override
    public khs.study.alc_android.chat.view.adapter.ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public void onBindViewHolder(khs.study.alc_android.chat.view.adapter.ChatAdapter.ViewHolder holder, int position) {
        Message msg = mMessages.get(position);
        holder.tvName.setText(msg.getUser());
        holder.tvMessage.setText(msg.getContent());
    }

    public void add(Message message) {
        this.mMessages.add(message);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvName;
        public final TextView tvMessage;

        public ViewHolder(View v) {
            super(v);
            this.mView = v;

            this.tvName = (TextView) v.findViewById(R.id.tvName);
            this.tvMessage = (TextView) v.findViewById(R.id.tvMessage);
        }
    }
}
