package com.example.mohamed.simpleconverter.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.simpleconverter.Models.Categories;
import com.example.mohamed.simpleconverter.R;

import java.util.List;

/**
 * Created by Mohamed on 01/02/2017.
 */

public class mRecyclerAdapter extends RecyclerView.Adapter<mRecyclerAdapter.mViewHolder> {
    Context mContext;
    List<Categories> cateList;

    public mRecyclerAdapter(Context mContext, List<Categories> cateList) {
        this.mContext = mContext;
        this.cateList = cateList;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        ImageView Image;
        TextView Title;

        public mViewHolder(View itemView) {
            super(itemView);
            Image = (ImageView) itemView.findViewById(R.id.ivImage);
            Title = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    @Override
    public mRecyclerAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        String layout = prefs.getString(mContext.getString(R.string.pref_layout_key),
                mContext.getString(R.string.pref_layout_grid));

        switch (layout) {
            case "grid":
            default:
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cate_item, parent, false);
                break;
            case "linear":
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cate_item_linear, parent, false);
                break;
        }

        return new mViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(mRecyclerAdapter.mViewHolder holder, int position) {
        Categories cate = cateList.get(position);

        holder.Image.setBackgroundResource(cate.getImage());
        holder.Title.setText(cate.getCategory());

    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }
}
