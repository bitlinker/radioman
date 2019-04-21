package com.github.bitlinker.radioman.ui.history;

import com.github.bitlinker.radioman.domain.HistoryItem;

import androidx.recyclerview.widget.DiffUtil;

public class HistoryItemDiffCallback extends DiffUtil.ItemCallback<HistoryItem> {
    @Override
    public boolean areItemsTheSame(HistoryItem oldItem, HistoryItem newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(HistoryItem oldItem, HistoryItem newItem) {
        return oldItem.getId().equals(newItem.getId());
    }
}
