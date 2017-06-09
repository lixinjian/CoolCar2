package com.xinjian.coolcar2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinjian.coolcar2.R;
import com.xinjian.coolcar2.model.BrandModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class PinPaiAdapter extends BaseAdapter{

    Context context;
    List<BrandModel> list;

    public PinPaiAdapter(Context context,List<BrandModel> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =  null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.choose_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.pinPai);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.pinPaiLogo);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.mTextView.setText(list.get(position).name);

        Glide.with(context).load(list.get(position).logo).into(viewHolder.mImageView);

//        viewHolder.mImageView.set




        return convertView;
    }

    private final class ViewHolder
    {
        TextView mTextView;
        ImageView mImageView;
    }

}
