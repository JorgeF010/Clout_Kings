package com.example.cloutkings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private ArrayList<Profile> profileArrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        // open profile
//        void onItemClick(int position);
        void onUpVoteClick(int position);
        void onDownVoteClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        // fields for the profiles
        private ImageView mImageView;
        private TextView mTextView1;
        private TextView mTextView2;
        private ImageView upVote;
        private ImageView downVote;


        public ProfileViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageView);
            this.mTextView1 = itemView.findViewById(R.id.textView);
            this.mTextView2 = itemView.findViewById(R.id.textView2);
            this.upVote = itemView.findViewById(R.id.upVote);
            this.downVote = itemView.findViewById(R.id.downVote);

            upVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener  != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onUpVoteClick(position);
                        }
                    }
                }
            });

        }
    }

    public ProfileAdapter(ArrayList <Profile> profiles) {
        this.profileArrayList = profiles;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile, parent, false);
        return new ProfileViewHolder(v, mListener);
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
