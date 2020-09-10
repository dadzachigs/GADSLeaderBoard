package com.darlington.chigumbu.Leaders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.darlington.chigumbu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LeadersAdapter extends RecyclerView.Adapter<LeadersAdapter.LeadersViewHolder> {

    ArrayList<Leaders> mLeaders;
    private LayoutInflater mLayoutInflater;
    //private List<Question> question = new ArrayList();
    private final Context mContext;

    /*public LeadersAdapter(ArrayList<Leaders> leaders) {
        this.mLeaders = leaders;
    }*/

    public LeadersAdapter(Context context) {
        mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        mLeaders = new ArrayList<>();
    }

    public void setData(ArrayList<Leaders> leaders){
        this.mLeaders = leaders;
    }


    @Override
    public LeadersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_list, parent, false);
        return new LeadersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LeadersViewHolder holder, int position) {

        final Leaders leaders = mLeaders.get(position);
        if (mLeaders != null){
        Picasso.get()
                .load(leaders.badgeUrl)
                .into(holder.badgeUrl);
        holder.name.setText(leaders.name);
        holder.hours.setText(leaders.hours);
        holder.country.setText(leaders.country);}

    }

    @Override
    public int getItemCount() {
        return mLeaders.size();
    }

  public static  class LeadersViewHolder extends RecyclerView.ViewHolder {

        ImageView badgeUrl;
        TextView name;
        TextView hours;
        TextView country;


        public LeadersViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.country_of_leader);
            hours= itemView.findViewById(R.id.hours);
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
