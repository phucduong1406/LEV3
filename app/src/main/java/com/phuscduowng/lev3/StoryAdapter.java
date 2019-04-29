package com.phuscduowng.lev3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phuscduowng.lev3.listener.DictionaryAdapterListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private DictionaryAdapterListener listener;

    private List<Story> storyList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public void setListener(DictionaryAdapterListener listener) {
        this.listener = listener;
    }

    public StoryAdapter(Context context, List<Story> storyList) {
        this.storyList = storyList;
        this.mContext = context;

        mLayoutInflater = LayoutInflater.from(context);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtStoryNameE, txtStoryNameV, id;
        ImageView imgStory;

        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStoryNameE = itemView.findViewById(R.id.txtStoryNameE);
            txtStoryNameV = itemView.findViewById(R.id.txtStoryNameV);
            imgStory = itemView.findViewById(R.id.imgStory);
        }

        @Override
        public void onClick(View v) {

        }
    }


    // Định nghĩa các Item layout và khởi tạo Holder
    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = mLayoutInflater.inflate(R.layout.story_item, viewGroup,false);
        return new ViewHolder(item);
    }

    // Thiết lập các thuộc tính của View và dữ liệu.
    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder viewHolder, int position) {
        final Story story = storyList.get(position);

        viewHolder.txtStoryNameE.setText(story.name_e);
        viewHolder.txtStoryNameV.setText(story.name_v);

        if (story.id.equals(""))
            story.id = "story000";
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lev3-usuow.appspot.com/o/stories%2F" + story.id + ".jpg?alt=media").into(viewHolder.imgStory);




//        viewHolder.bind(dictionary);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onItemClick(story.id);
                }
            }
        });
    }

    // Đếm số Item trong List Data.
    @Override
    public int getItemCount() {
        return storyList.size();
    }

}
