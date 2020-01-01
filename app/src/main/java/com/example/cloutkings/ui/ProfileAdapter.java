package com.example.cloutkings.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloutkings.Profile;
import com.example.cloutkings.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private ArrayList<Profile> profileArrayList;

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        // fields for the profiles
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageView);
            this.mTextView1 = itemView.findViewById(R.id.textView);
            this.mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public ProfileAdapter(ArrayList <Profile> profiles) {
        this.profileArrayList = profiles;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile, parent, false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile currentProfile = this.profileArrayList.get(position);
        holder.mImageView.setImageResource(currentProfile.getmImageResource());
        holder.mTextView1.setText(currentProfile.getmText1());
        holder.mTextView2.setText(currentProfile.getmText2());
    }

    @Override
    public int getItemCount() {
        return this.profileArrayList.size();
    }
}
