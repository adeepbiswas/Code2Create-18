package acm.event.codetocreate18.View.Fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import acm.event.codetocreate18.Model.Data.DataGenerator;
import acm.event.codetocreate18.R;
import acm.event.codetocreate18.Utility.Adapters.SponsorAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import yalantis.com.sidemenu.interfaces.ScreenShotable;



public class SponsorFragment extends Fragment implements ScreenShotable {
    @BindView(R.id.sponsor_root_view)
    ConstraintLayout sponsorLayout;

    @BindView(R.id.sponsor_recycler_view)
    RecyclerView recyclerView;

    private Bitmap bitmap;
    private RecyclerView.Adapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sponsors, container, false);
        ButterKnife.bind(this, rootView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SponsorAdapter(getDataSet());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        return rootView;
    }

    private ArrayList<DataGenerator> getDataSet() {
        ArrayList<DataGenerator> results = new ArrayList<>();
        for (int index = 0; index < 28; index++) {
            DataGenerator obj = new DataGenerator("Title " + index,
                    "Image" + index);
            results.add(index, obj);
        }
        return results;
    }

    @Override
    public void takeScreenShot() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(sponsorLayout.getWidth(),
                        sponsorLayout.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                sponsorLayout.draw(canvas);
                SponsorFragment.this.bitmap = bitmap;
            }
        });
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}