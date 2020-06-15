package com.sin.lifesim.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sin.lifesim.R;
import com.sin.lifesim.work.smlouva.Smlouva;

import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("ALL")
public class hashMapAdapterSmlouvaBoolean extends BaseAdapter {
    private final ArrayList mData;

    public hashMapAdapterSmlouvaBoolean(Map<Smlouva, Boolean> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<Smlouva, Boolean> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO implement you own logic with ID
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.hash_map_list_item, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<Smlouva, Boolean> item = getItem(position);
        Smlouva show = item.getKey();
        String sh = show.getTitle() + show.getPodminky() + show.getZkusenost();
        // TODO replace findViewById by ViewHolder
        ((TextView) result.findViewById(android.R.id.text1)).setText(String.valueOf(sh));
        ((TextView) result.findViewById(android.R.id.text2)).setText(String.valueOf(item.getValue()));

        return result;
    }
}
