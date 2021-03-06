package com.winthan.joketeller.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winthan.joketeller.R;
import com.winthan.joketeller.clicklistener.ItemClickListener;
import com.winthan.joketeller.data.vos.JokeVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by winthanhtike on 3/26/17.
 */

public class JokeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<JokeVO> jokeVOList;

    private ItemClickListener itemClickListener;

    public JokeRvAdapter(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        jokeVOList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_joke, parent, false);
        return new JokeViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof JokeViewHolder) {
            ((JokeViewHolder) holder).bindData(jokeVOList.get(position));
        }

    }

    public void addJokes(List<JokeVO> jokeVOList) {

        for (JokeVO joke : jokeVOList) {
            if (!this.jokeVOList.contains(joke)) {
                this.jokeVOList.add(joke);
            }
        }
        notifyItemRangeInserted(getItemCount(), jokeVOList.size());
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return jokeVOList.size();
    }

    public class JokeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_joke_name)
        TextView mTvJokeName;

        @BindView(R.id.tv_joke_preview)
        TextView mTvJokePreview;

        private JokeVO jokeVO;

        private ItemClickListener itemClickListener;

        public JokeViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);

        }

        public void bindData(JokeVO jokeVO) {
            this.jokeVO = jokeVO;

            mTvJokeName.setText(jokeVO.getName());
            mTvJokePreview.setText(jokeVO.getPreview());

        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onTapJoke(jokeVO, getAdapterPosition());
        }
    }

}
