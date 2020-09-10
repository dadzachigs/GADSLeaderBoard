package com.darlington.chigumbu.SkillsIQ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.darlington.chigumbu.Leaders.Leaders;
import com.darlington.chigumbu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SkillsIQAdapter extends RecyclerView.Adapter<SkillsIQAdapter.SkillsViewHolder>   {



        ArrayList<Skills> mSkills;
        private LayoutInflater mLayoutInflater;
        //private List<Question> question = new ArrayList();
        private final Context mContext;

    /*public LeadersAdapter(ArrayList<Leaders> leaders) {
        this.mLeaders = leaders;
    }*/

        public SkillsIQAdapter (Context context) {
            mContext = context;
            this.mLayoutInflater = LayoutInflater.from(mContext);
            mSkills = new ArrayList<>();
        }

        public void setData(ArrayList<Skills> skills){
            this.mSkills = skills;
        }


        @Override
        public SkillsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_list2, parent, false);
            return new SkillsViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SkillsViewHolder holder, int position) {

            final Skills skills = mSkills.get(position);
            if (mSkills != null){
                Picasso.get()
                        .load(skills.badgeUrl)
                        .into(holder.badgeUrl);
                holder.name.setText(skills.name);
                holder.score.setText(skills.score);
                holder.country.setText(skills.country);}

        }

        @Override
        public int getItemCount() {
            return mSkills.size();
        }

        public static  class SkillsViewHolder extends RecyclerView.ViewHolder {

            ImageView badgeUrl;
            TextView name;
            TextView score;
            TextView country;


            public SkillsViewHolder(View itemView) {
                super(itemView);
                name =  itemView.findViewById(R.id.country_of_leader);
                score= itemView.findViewById(R.id.hours);
                country = itemView.findViewById(R.id.name_of_leader);
                badgeUrl= itemView.findViewById(R.id.imageView);
                //tvPublisher =(TextView) itemView.findViewById(R.id.tvPublisher);

            }
        /*public void bind (Leaders leader) {
            name.setText(leader.name);
            hours.setText(leader.hours);
            country.setText(leader.country);

        }*/


        }
}
