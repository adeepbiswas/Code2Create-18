package acm.event.codetocreate18.View.Main;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by adeep on 20/2/18.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN = "REG_TOKEN";
    @Override
    public void onTokenRefresh() {
        // String recent_token = FirebaseInstanceId.getInstance().getToken();
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Log.d(REG_TOKEN,recent_token);
        Log.d("My  FirebaseId", "Refreshed token: " + refreshedToken);



    }

    
}
