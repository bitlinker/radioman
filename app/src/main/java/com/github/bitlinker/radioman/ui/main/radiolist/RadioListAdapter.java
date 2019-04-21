package com.github.bitlinker.radioman.ui.main.radiolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.domain.Radio;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RadioListAdapter extends ListAdapter<Radio, RadioListAdapter.RadioItemViewHolder> {
    class RadioItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private TextView tvTitle;

        public RadioItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bind(Radio item) {
            // TODO: picasso?
            //ivIcon.setImageDrawable(item.getImgUrl());
            tvTitle.setText(item.getName());
        }
    }

    private final LayoutInflater inflater;

    public RadioListAdapter(LayoutInflater inflater) {
        super(new RadioItemDiffCallback());
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public RadioItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RadioItemViewHolder(inflater.inflate(R.layout.radiolist_fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RadioItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
