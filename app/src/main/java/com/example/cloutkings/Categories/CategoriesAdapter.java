package com.example.cloutkings.Categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloutkings.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private ArrayList<Category> categoryArrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        // open profile
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        // fields for the profiles
        private ImageView mImageView;
        private TextView mTextView1;


        public CategoryViewHolder (@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageCategory);
            this.mTextView1 = itemView.findViewById(R.id.textCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener  != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CategoriesAdapter(ArrayList <Category> categories) {
        this.categoryArrayList = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category, parent, false);
        return new CategoryViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category currentCategory = this.categoryArrayList.get(position);
        holder.mImageView.setImageResource(currentCategory.getmImageResource());
        holder.mTextView1.setText(currentCategory.getmText1());
    }

    @Override
    public int getItemCount() {
        return this.categoryArrayList.size();
    }
}
