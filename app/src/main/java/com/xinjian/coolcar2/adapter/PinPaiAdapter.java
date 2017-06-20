package com.xinjian.coolcar2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinjian.coolcar2.R;
import com.xinjian.coolcar2.model.BrandModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class PinPaiAdapter extends BaseAdapter implements SectionIndexer {

    Context context;
    List<BrandModel> list;

    public PinPaiAdapter(Context context, List<BrandModel> list) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.choose_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.pinPai);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.pinPaiLogo);
//            viewHolder.showLetter = (TextView) convertView.findViewById(R.id.lixinji);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BrandModel brandModel = list.get(position);

        viewHolder.mTextView.setText(brandModel.name);
        Glide.with(context).load(brandModel.logo).into(viewHolder.mImageView);

        //获得当前position是属于哪个分组
        int sectionForPosition = getSectionForPosition(position);
        //获得该分组第一项的position
        int positionForSection = getPositionForSection(sectionForPosition);
        //查看当前position是不是当前item所在分组的第一个item
        //如果是，则显示showLetter，否则隐藏
//        if (position == positionForSection) {
//            viewHolder.showLetter.setVisibility(View.VISIBLE);
//            viewHolder.showLetter.setText(brandModel.initial);
//        } else {
//            viewHolder.showLetter.setVisibility(View.GONE);
//        }

        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    //传入一个分组值[A....Z],获得该分组的第一项的position
    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).initial.charAt(0) == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    //传入一个position，获得该position所在的分组
    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).initial.charAt(0);
    }

    private final class ViewHolder {
        TextView mTextView;
        ImageView mImageView;
//        TextView showLetter;
    }
}
