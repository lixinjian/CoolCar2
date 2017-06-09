package com.xinjian.coolcar2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinjian.coolcar2.R;
import com.xinjian.coolcar2.activity.CarActivity;
import com.xinjian.coolcar2.activity.DetailActivity;
import com.xinjian.coolcar2.model.CarModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    private Context mContext;
    private List<CarModel> mList;

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = (ImageView) itemView.findViewById(R.id.car_image);
            textView = (TextView) itemView.findViewById(R.id.car_name);
        }
    }

    public CarListAdapter(List<CarModel> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.cat_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                CarModel carModel = mList.get(position);
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.CAR_NAME,carModel.name);
                intent.putExtra(DetailActivity.CAR_IMAGE,carModel.logo);
                intent.putExtra(DetailActivity.CAR_PRICE,carModel.list.get(0).price);
                intent.putExtra(DetailActivity.CAR_TYPE,carModel.list.get(0).sizetype);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            CarModel carModel = mList.get(position);
            holder.textView.setText(carModel.name);
            Glide.with(mContext).load(carModel.logo).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
