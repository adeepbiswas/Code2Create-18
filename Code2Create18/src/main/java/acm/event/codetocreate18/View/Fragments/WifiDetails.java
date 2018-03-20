package acm.event.codetocreate18.View.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import acm.event.codetocreate18.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import yalantis.com.sidemenu.interfaces.ScreenShotable;


public class WifiDetails extends Fragment implements ScreenShotable {
    @BindView(R.id.wifi_root_view)
    ConstraintLayout wifiContainer;
    @BindView(R.id.wifiusername)
    TextView usernamelabel;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.wifipassword)
    TextView passwordlabel;
    @BindView(R.id.password)
    TextView password;

    public  String curr_us="N/A";

    private Bitmap bitmap;
    String key;
    private static final String TAG = "WifiActivity";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        usernamelabel=(TextView) getView().findViewById(R.id.wifiusername);
        username=(TextView) getView().findViewById(R.id.username);
        passwordlabel=(TextView) getView().findViewById(R.id.wifipassword);
        password=(TextView) getView().findViewById(R.id.password);
        DatabaseReference familyListReference= FirebaseDatabase.getInstance().getReference().child("Users");

        familyListReference.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            key = (String) ds.getKey();
                            SharedPreferences sharedPreferencesU=getActivity().getSharedPreferences("acm.event.codetocreate18.View.Authentication", Context.MODE_PRIVATE);
                            curr_us=sharedPreferencesU.getString("user","");

                            if (key.equals(curr_us)) {
                                DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("Users").child(curr_us);
                                keyReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot){
                                        String us= dataSnapshot.child("wifiUsername").getValue(String.class);
                                        String pass = dataSnapshot.child("wifiPassword").getValue(String.class);
                                        username.setText(us);
                                        password.setText(pass);
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.d(TAG, "Read failed");
                                    }
                                });
                            }
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(TAG, "Read failed");
                    }
                });


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_wifi_details, container, false);
        //if (container != null) {
          //  container.removeAllViews();
        //}

        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void takeScreenShot() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(wifiContainer.getWidth(),
                        wifiContainer.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                wifiContainer.draw(canvas);
                WifiDetails.this.bitmap = bitmap;
            }
        });
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }


}
