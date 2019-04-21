package com.github.bitlinker.radioman.ui.history;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.domain.HistoryItem;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryItemAdapter extends ListAdapter<HistoryItem, HistoryItemAdapter.HistoryItemViewHolder> {
    class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvRadioTitle;
        private TextView tvDate;

        public HistoryItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRadioTitle = itemView.findViewById(R.id.tvRadioTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
        }

        public void bind(HistoryItem item) {
            tvTitle.setText(item.getTitle());
            tvRadioTitle.setText(item.getRadioTitle());
            tvDate.setText(dateFormatter.format(item.getDate()));
        }
    }

    private final SimpleDateFormat dateFormatter;
    private final LayoutInflater inflater;

    public HistoryItemAdapter(LayoutInflater inflater) {
        super(new HistoryItemDiffCallback());
        this.inflater = inflater;
        // TODO: proper pattern
        dateFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryItemViewHolder(inflater.inflate(R.layout.history_fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
