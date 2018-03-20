package acm.event.codetocreate18.View.Fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import acm.event.codetocreate18.R;

import static acm.event.codetocreate18.View.Fragments.QuizFragment.returnMessage;

public class scannerMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String message1="Scan the QR Code to get suitable Message!check";
        setContentView(R.layout.activity_scanner_message);
        TextView textView=(TextView) findViewById(R.id.scannermsg);
        textView.setText(message1);
        //Bundle bundle=getIntent().getExtras();
        //if (bundle != null) {
        //message=bundle.getString("success");}
        //message = getIntent().getStringExtra("success");
        //Intent myIntent = getIntent();
        //if (myIntent.hasExtra("success")){
          //  message=myIntent.getStringExtra("success");
        //}
        String message2=returnMessage();
        textView.setText(message2);

    }
}
