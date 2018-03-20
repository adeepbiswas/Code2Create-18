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
import acm.event.codetocreate18.Utility.Adapters.PrizeAdapter;
import acm.event.codetocreate18.Utility.Adapters.SponsorAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import yalantis.com.sidemenu.interfaces.ScreenShotable;


public class prizeFragment extends Fragment implements ScreenShotable {
    @BindView(R.id.prize_root_view)
    ConstraintLayout prizeLayout;

    @BindView(R.id.sponsor_recycler_view)
    RecyclerView recyclerView;

    private Bitmap bitmap;
    private RecyclerView.Adapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prize, container, false);
        ButterKnife.bind(this, rootView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new PrizeAdapter(getDataSet());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        return rootView;
    }

    private ArrayList<DataGenerator> getDataSet() {
        ArrayList<DataGenerator> results = new ArrayList<>();
        for (int index = 0; index < 8; index++) {
            DataGenerator obj = new DataGenerator("Title " + index,
                    "Image" + index, "Amount "+ index, "Details" +index);
            results.add(index, obj);
        }
        return results;
    }

    @Override
    public void takeScreenShot() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(prizeLayout.getWidth(),
                        prizeLayout.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                prizeLayout.draw(canvas);
                prizeFragment.this.bitmap = bitmap;
            }
        });
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}