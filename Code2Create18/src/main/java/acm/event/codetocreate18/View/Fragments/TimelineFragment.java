package acm.event.codetocreate18.View.Fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import acm.event.codetocreate18.Model.Data.DataGenerator;
import acm.event.codetocreate18.Model.Data.TimeLineModel;
import acm.event.codetocreate18.R;
import acm.event.codetocreate18.Utility.Adapters.TimeLineAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static acm.event.codetocreate18.Model.Data.DataGenerator.getTimelineDate;
import static acm.event.codetocreate18.Model.Data.DataGenerator.getTimelineEvent;

public class TimelineFragment extends Fragment implements ScreenShotable {
    final String time[] = new String[18];
    final String event[] = new String[18];
    private static final String TAG = "TimelineFragment";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.timeline_root_layout)
    RelativeLayout timelineContainer;

    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private DataGenerator.Orientation mOrientation;
    private boolean mWithLinePadding;
    private Bitmap bitmap;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        ButterKnife.bind(this, rootView);

        mOrientation = DataGenerator.Orientation.VERTICAL;
        mWithLinePadding = false;

        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        initView();
        return rootView;
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }


    private void initView() {
        setDataListItems();
        mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mTimeLineAdapter);
        alphaAdapter.setDuration(1000);
        mRecyclerView.setAdapter(alphaAdapter);
    }

    public void setDataListItems(){



     /*   DatabaseReference timeReference = FirebaseDatabase.getInstance().getReference().child("Time");
        timeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = (String) ds.getKey();

                    DatabaseReference timekeyReference = FirebaseDatabase.getInstance().getReference().child("Time").child(key);
                    timekeyReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            time[0] = dataSnapshot.child("Time1").getValue(String.class);
                            time[1] = dataSnapshot.child("Time2").getValue(String.class);
                            time[2]= dataSnapshot.child("Time3").getValue(String.class);
                            time[3]= dataSnapshot.child("Time4").getValue(String.class);
                            time[4]= dataSnapshot.child("Time5").getValue(String.class);
                            time[5]= dataSnapshot.child("Time6").getValue(String.class);
                            time[6]= dataSnapshot.child("Time7").getValue(String.class);
                            time[7]= dataSnapshot.child("Time8").getValue(String.class);
                            time[8]= dataSnapshot.child("Time9").getValue(String.class);
                            time[9]= dataSnapshot.child("Time10").getValue(String.class);
                            time[10]= dataSnapshot.child("Time11").getValue(String.class);
                            time[11]= dataSnapshot.child("Time12").getValue(String.class);
                            time[12]= dataSnapshot.child("Time13").getValue(String.class);
                            time[13]= dataSnapshot.child("Time14").getValue(String.class);
                            time[14]= dataSnapshot.child("Time15").getValue(String.class);
                            time[15]= dataSnapshot.child("Time16").getValue(String.class);
                            time[16]= dataSnapshot.child("Time17").getValue(String.class);
                            time[17]= dataSnapshot.child("Time18").getValue(String.class);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Read failed");
                        }
                    });
                }                       }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Read failed");
            }
        });


        DatabaseReference timelineReference = FirebaseDatabase.getInstance().getReference().child("Timeline");
        timelineReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = (String) ds.getKey();

                    DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("Timeline").child(key);
                    keyReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            event[0] = dataSnapshot.child("Event1").getValue(String.class);
                            event[1] = dataSnapshot.child("Event2").getValue(String.class);
                            event[2]= dataSnapshot.child("Event3").getValue(String.class);
                            event[3]= dataSnapshot.child("Event4").getValue(String.class);
                            event[4]= dataSnapshot.child("Event5").getValue(String.class);
                            event[5]= dataSnapshot.child("Event6").getValue(String.class);
                            event[6]= dataSnapshot.child("Event7").getValue(String.class);
                            event[7]= dataSnapshot.child("Event8").getValue(String.class);
                            event[8]= dataSnapshot.child("Event9").getValue(String.class);
                            event[9]= dataSnapshot.child("Event10").getValue(String.class);
                            event[10]= dataSnapshot.child("Event11").getValue(String.class);
                            event[11]= dataSnapshot.child("Event12").getValue(String.class);
                            event[12]= dataSnapshot.child("Event13").getValue(String.class);
                            event[13]= dataSnapshot.child("Event14").getValue(String.class);
                            event[14]= dataSnapshot.child("Event15").getValue(String.class);
                            event[15]= dataSnapshot.child("Event16").getValue(String.class);
                            event[16]= dataSnapshot.child("Event17").getValue(String.class);
                            event[17]= dataSnapshot.child("Event18").getValue(String.class);

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Read failed");
                        }
                    });
                }                       }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Read failed");
            }
        });

*/

        for(int i=0; i<29 ; i++)
        {
           // mDataList.add(new TimeLineModel(event[i],time[i],DataGenerator.checkOderStatus(time[i],time[i+1])));
           mDataList.add(new TimeLineModel(getTimelineEvent(i),getTimelineDate(i),DataGenerator.checkOderStatus(getTimelineDate(i),getTimelineDate(i+1))));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void takeScreenShot() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(timelineContainer.getWidth(),
                        timelineContainer.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                timelineContainer.draw(canvas);
                TimelineFragment.this.bitmap = bitmap;
            }
        });
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }


}


