package acm.event.codetocreate18.View.Authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import acm.event.codetocreate18.Model.RealmModels.TeamMember;
import acm.event.codetocreate18.Model.RealmModels.User;
import acm.event.codetocreate18.Model.RetroAPI.RetroAPI;
import acm.event.codetocreate18.R;
import acm.event.codetocreate18.Utility.Miscellaneous.Constants;
import acm.event.codetocreate18.View.Main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.login_username)
    EditText usernameEditText;
    @BindView(R.id.login_password)
    EditText passwordEditText;
    @BindView(R.id.login_username_layout)
    TextInputLayout usernameLayout;
    @BindView(R.id.login_password_layout)
    TextInputLayout passwordLayout;
    @BindView(R.id.login_root_layout)
    ConstraintLayout loginContainer;

    String key;
    int i;
    int usn=0;
    private static final String TAG = "LoginActivity";
    RetroAPI retroAPI;
    SharedPreferences.Editor sharedPreferencesEditor;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
/*
        retroAPI = new RetroAPI();
        sharedPreferencesEditor = this.getSharedPreferences(Constants.sharedPreferenceName, Context.MODE_PRIVATE).edit();
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();*/
        loginButton.setText("SIGN IN");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                loginButton.setText("SIGNING IN...");
                final String eusername=usernameEditText.getText().toString();
                final String epassword=passwordEditText.getText().toString();
                if(!eusername.equals("") && !epassword.equals(""))
                {
                    DatabaseReference familyListReference = FirebaseDatabase.getInstance().getReference().child("Users");

                    familyListReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()) {

                                key = (String) ds.getKey();

                                if(key.equals(eusername)) {
                                    usn=1;

                                    DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("Users").child(eusername);
                                    keyReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            //String emailid = dataSnapshot.child("username").getValue(String.class);
                                            String password = dataSnapshot.child("password").getValue(String.class);
                                            //String nm = dataSnapshot.child("name").getValue(String.class);

                                            if (password.equals(epassword)) {
                                                SharedPreferences sharedPreferences = getSharedPreferences(Constants.sharedPreferenceName, MODE_PRIVATE);
                                                sharedPreferencesEditor= sharedPreferences.edit();
                                                sharedPreferencesEditor.putBoolean("loggedin",true);
                                                sharedPreferencesEditor.commit();
                                                SharedPreferences sharedPreferencesU = getSharedPreferences("acm.event.codetocreate18.View.Authentication",Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editorU= sharedPreferencesU.edit();
                                                editorU.putString("user",eusername);
                                                editorU.commit();


                                                //Toast.makeText(LoginActivity.this, "Signedup Successfully", Toast.LENGTH_SHORT).show();
                                                Constants.isGuest = false;
                                                String loginstatus = dataSnapshot.child("loginStatus").getValue(String.class);
                                                dataSnapshot.child("loginStatus").getRef().setValue("true");
                                                Intent logininetent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(logininetent);
                                                finish();


                                            }
                                            else
                                            {
                                                Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                                loginButton.setText("SIGN IN");

                                            }

                                        }



                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            Log.d(TAG, "Read failed");
                                        }
                                    });
                                }

                                //if(!key.equals(eusername))
                                //{
                                 //   Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                                //}

                            }
                            if(usn!=1)
                            {

                                //Toast.makeText(LoginActivity.this, "Username is Incorrect", Toast.LENGTH_SHORT).show();
                                loginButton.setText("INCORRECT USERNAME, TRY AGAIN");


                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Read failed");
                        }
                    });

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    loginButton.setText("SIGN IN");
                }


            }
        });

    }

    @OnClick(R.id.link_guest_login)
    public void onGuestLogin(View v) {
        Constants.isGuest = true;
        loadMain();
    }

    @OnClick(R.id.login_button)
    public void onUserLogin(View v) {
        Constants.isGuest = false;
        signin();
    }

    public void signin(){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(username.equals("") || password.equals("")) {
            usernameLayout.setError("Enter valid username");
            passwordLayout.setError("Enter valid password");
            return;
        }
        loginButton.setText("SIGNING IN...");
        loginButton.setClickable(false);
        retroAPI.observableAPIService.signIn(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        loginButton.setText("SIGN IN");
                        loginButton.setClickable(true);
                        Snackbar snackbar = Snackbar
                                .make(loginContainer, "Could not connect to server", Snackbar.LENGTH_LONG)
                                .setAction("RETRY", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        signin();
                                    }
                                });

                        snackbar.show();
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if(jsonObject.get("success").getAsBoolean()){
                            sharedPreferencesEditor.putBoolean("loggedin",true);
                            sharedPreferencesEditor.putString("authtoken",jsonObject.get("token").getAsString());
                            sharedPreferencesEditor.putString("userid", jsonObject.getAsJsonObject("user").get("id").getAsString());
                            sharedPreferencesEditor.commit();
                            Constants.accessToken = jsonObject.get("token").getAsString();
                            User user = new User();
                            realm.beginTransaction();
                            user.name = jsonObject.getAsJsonObject("user").get("name").getAsString();
                            user.email = jsonObject.getAsJsonObject("user").get("email").getAsString();
                            user.gender = jsonObject.getAsJsonObject("user").get("gender").getAsString();
                            realm.copyToRealmOrUpdate(user);
                            realm.commitTransaction();
                            syncProfile();
                        } else {
                            usernameLayout.setError("Enter valid username");
                            passwordLayout.setError("Enter valid password");
                            loginButton.setText("SIGN IN");
                            loginButton.setClickable(true);
                        }
                    }
                });
    }

    public void syncProfile() {
        String accessToken = Constants.accessToken;
        retroAPI.observableAPIService.syncProfile(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {

                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        loginButton.setText("SIGN IN");
                        loginButton.setClickable(true);
                        Snackbar snackbar = Snackbar
                                .make(loginContainer, "Could not connect to server", Snackbar.LENGTH_LONG)
                                .setAction("RETRY", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        signin();
                                    }
                                });
                        snackbar.show();
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if(jsonObject.get("success").getAsBoolean()){
                            User user = realm.where(User.class).findFirst();
                            realm.beginTransaction();
                            user.hasTeam = true;
                            String leader = jsonObject.get("ADMIN").getAsString();
                            user.isLeader = jsonObject.get("admin").getAsBoolean();
                            user.teamName = jsonObject.get("teamName").getAsString();
                            JsonArray teamMembers = jsonObject.getAsJsonArray("teammembers");
                            JsonArray teamMemberEmails = jsonObject.getAsJsonArray("teammembersemail");
                            user.noOfMembers = teamMembers.size() - 1;
                            for(int i = 0; i < teamMembers.size(); i++) {
                                /*TeamMember teamMember = new TeamMember();
                                teamMember.name = teamMembers.get(i).getAsString();
                                if(teamMember.name.equals(user.name))
                                    continue;
                                teamMember.email = teamMemberEmails.get(i).getAsString();
                                if(teamMember.name.equals(leader))
                                    teamMember.isLeader = true;
                                else
                                    teamMember.isLeader = false;
                                if(user.teamMembers == null)
                                    user.teamMembers = new RealmList<>();
                                user.teamMembers.add(teamMember);*/
                            }
                            realm.copyToRealmOrUpdate(user);
                            realm.commitTransaction();
                        } else {
                            User user = realm.where(User.class).findFirst();
                            realm.beginTransaction();
                            user.hasTeam = false;
                            user.isLeader = false;
                            realm.copyToRealmOrUpdate(user);
                            realm.commitTransaction();
                        }
                        loadMain();
                    }
                });
    }

    public void loadMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
