package com.nicolas.vwarwj2.volkswagenagenda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import com.nicolas.vwarwj2.volkswagenagenda.R;
import com.nicolas.vwarwj2.volkswagenagenda.pojo.SimpleModel;

import java.util.List;


public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder> {
    private List<SimpleModel> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView itemView;

        public MyViewHolder(View v) {
            super(v);
            itemView = (TextView) v.findViewById(R.id.simple_text);
        }

        public void bindData(final SimpleModel viewModel) {
            itemView.setText(viewModel.getSimpleText());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRecyclerView(List<SimpleModel> myDataset) {
        mDataset = myDataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public AdapterRecyclerView.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view, parent, false);
        return new MyViewHolder(layoutView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ((MyViewHolder) holder).bindData(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}