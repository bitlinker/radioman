package com.github.bitlinker.radioman.ui.main.radiolist;

import com.github.bitlinker.radioman.domain.Radio;

import androidx.recyclerview.widget.DiffUtil;

public class RadioItemDiffCallback extends DiffUtil.ItemCallback<Radio> {
    @Override
    public boolean areItemsTheSame(Radio oldItem, Radio newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(Radio oldItem, Radio newItem) {
        return oldItem.getId().equals(newItem.getId());
    }
}
