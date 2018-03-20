package acm.event.codetocreate18.Utility.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;

import acm.event.codetocreate18.Model.Data.AboutGroupModel;
import acm.event.codetocreate18.Model.Data.AboutModel;
import acm.event.codetocreate18.Model.Holders.AboutChildHolder;
import acm.event.codetocreate18.Model.Holders.AboutParentHolder;
import acm.event.codetocreate18.R;
import acm.event.codetocreate18.View.Fragments.contactWeb;




public class AboutAdapter extends ExpandableRecyclerAdapter<AboutParentHolder, AboutChildHolder> {

    private LayoutInflater layoutInflater;

    public AboutAdapter(Context context, @NonNull ArrayList<AboutGroupModel> parentItemList) {
        super(parentItemList);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public AboutParentHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View groupView = layoutInflater.inflate(R.layout.fragment_about_group, parentViewGroup, false);
        return new AboutParentHolder(groupView);
    }

    @Override
    public AboutChildHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View itemView = layoutInflater.inflate(R.layout.fragment_about_group_item, childViewGroup, false);
        return new AboutChildHolder(itemView);
    }


    @Override
    public void onBindParentViewHolder(AboutParentHolder parentViewHolder, int position, ParentListItem parentListItem) {
        AboutGroupModel group = (AboutGroupModel) parentListItem;
        parentViewHolder.groupTextView.setText(group.getName());
    }

    @Override
    public void onBindChildViewHolder(AboutChildHolder childViewHolder, final int position, Object childListItem) {
        //SharedPreferences sharedPreferences=this.getSharedPreferences("acm.event.codetocreate18.View.Authentication", Context.MODE_PRIVATE);
        //String pos=sharedPreferences.getString("childpos","");
        final AboutModel child = (AboutModel) childListItem;
        if (child.getContact() == false) {
            childViewHolder.mItemTextView.setText(child.getName());
            childViewHolder.desgination.setVisibility(View.VISIBLE);
            childViewHolder.desgination.setText(child.getDesignation());
            childViewHolder.mImageView.setImageResource(child.getImageResource());
            childViewHolder.holder.setClickable(false);
        } else {
            childViewHolder.mItemTextView.setText(child.getName());
            childViewHolder.desgination.setVisibility(View.GONE);
            childViewHolder.mImageView.setImageResource(child.getImageResource());
            childViewHolder.holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(position == 1){
                        Intent intent = new Intent(v.getContext(), contactWeb.class);
                        intent.putExtra("url", "http://c2c.acmvit.in");
                        v.getContext().startActivity(intent);
                    }
                    else{
                        //String open=Uri.parse(child.getDesignation()).toString();
                        //Intent intent = new Intent(v.getContext(), contactWeb.class);
                        //intent.putExtra("url", open);
                        //v.getContext().startActivity(intent);

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(child.getDesignation()));
                        if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                            v.getContext().startActivity(intent);
                        }
                    }


                    //}
                }

            });
        }
    }
}
