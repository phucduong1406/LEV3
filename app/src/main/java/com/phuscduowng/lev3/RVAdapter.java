package com.phuscduowng.lev3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.phuscduowng.lev3.listener.DictionaryAdapterListener;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private DictionaryAdapterListener listener;

    private List<Dictionary> dictionaryList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public void setListener(DictionaryAdapterListener listener) {
        this.listener = listener;
    }

    public RVAdapter(Context context, List<Dictionary> dictionaryList) {
        this.dictionaryList = dictionaryList;
        this.mContext = context;

        mLayoutInflater = LayoutInflater.from(context);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView word, mean;
        public ImageView pronun, isFavorite;

        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            word = itemView.findViewById(R.id.txtWord);
            mean = itemView.findViewById(R.id.txtMean);
            pronun = itemView.findViewById(R.id.imgPronun);
            isFavorite = itemView.findViewById(R.id.imgFavorite);


            pronun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, word.getText(),Toast.LENGTH_SHORT).show();
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }


    // Định nghĩa các Item layout và khởi tạo Holder
    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = mLayoutInflater.inflate(R.layout.dictionary_item, viewGroup,false);
        return new ViewHolder(item);
    }

    // Thiết lập các thuộc tính của View và dữ liệu.
    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder viewHolder, int position) {
        final Dictionary dictionary = dictionaryList.get(position);
        viewHolder.word.setText(dictionary.getWord());
        viewHolder.mean.setText(dictionary.getMean());


//        viewHolder.bind(dictionary);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onItemClick(dictionary.getWord());
                }
            }
        });
    }

    // Đếm số Item trong List Data.
    @Override
    public int getItemCount() {
        return dictionaryList.size();
    }

}
