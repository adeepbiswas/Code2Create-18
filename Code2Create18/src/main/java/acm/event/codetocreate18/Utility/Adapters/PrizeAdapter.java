package acm.event.codetocreate18.Utility.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import acm.event.codetocreate18.Model.Data.DataGenerator;
import acm.event.codetocreate18.R;

public class PrizeAdapter extends RecyclerView.Adapter<PrizeAdapter.MyViewHolder> {

    private List<DataGenerator> sponsorsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView sponsor;
        public  TextView text;
        public  TextView text2;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.sponsor_title);
            sponsor = (ImageView) view.findViewById(R.id.sponsor_image);
            text = (TextView) view.findViewById(R.id.prize_amount);
            text2 =(TextView) view.findViewById(R.id.prize_detail);

        }
    }


    public PrizeAdapter(List<DataGenerator> sponsorsList) {
        this.sponsorsList = sponsorsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prize_details, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(sponsorsList.get(position).getPrizeTitle(position));
        holder.sponsor.setImageResource(sponsorsList.get(position).Prizepathtoimage(position));
        holder.text.setText(sponsorsList.get(position).getprzieAmount(position));
        holder.text2.setText(sponsorsList.get(position).getprizeDetail(position));
    }

    @Override
    public int getItemCount() {
        return sponsorsList.size();
    }
}