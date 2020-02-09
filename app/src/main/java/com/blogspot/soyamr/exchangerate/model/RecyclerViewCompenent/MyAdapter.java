package com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.soyamr.exchangerate.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<MoneyRate> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView sellingRate;
        public TextView buyingRate;
        public TextView curencyName;
        public TextView currencyCode;
        public ImageView sellingRatePhoto, buyingRatePhoto, currencyIcone;

        public MyViewHolder(View v) {
            super(v);
            sellingRate = v.findViewById(R.id.sellingRate);
            buyingRate = v.findViewById(R.id.buyingRate);
            curencyName = v.findViewById(R.id.curency_name);
            currencyCode = v.findViewById(R.id.currency_code);

            sellingRatePhoto = v.findViewById(R.id.sellingRatePhoto);
            buyingRatePhoto = v.findViewById(R.id.byingRatePhoto);
            currencyIcone = v.findViewById(R.id.currencyIcon);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<MoneyRate> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.sellingRate.setText(mDataset.get(position).getConvertedRateToday());
        holder.currencyCode.setText(mDataset.get(position).getCurrencyCode());
        holder.curencyName.setText(mDataset.get(position).getCurrencyName()+ "");
        holder.currencyIcone.setImageResource(mDataset.get(position).getImageRecourseId());
        if (mDataset.get(position).isIncreasing())
            holder.sellingRatePhoto.setImageResource(R.drawable.up_arrow);
        else
            holder.sellingRatePhoto.setImageResource(R.drawable.down_arrow);



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}