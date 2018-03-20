package acm.event.codetocreate18.View.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wenchao.cardstack.CardStack;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.logging.Handler;

import acm.event.codetocreate18.Model.Data.DataGenerator;
import acm.event.codetocreate18.Model.Data.QuizQuestionModel;
import acm.event.codetocreate18.Model.RealmModels.User;
import acm.event.codetocreate18.Model.RetroAPI.RetroAPI;
import acm.event.codetocreate18.R;
import acm.event.codetocreate18.Utility.Adapters.SwipeCardAdapter;
import acm.event.codetocreate18.Utility.Miscellaneous.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static junit.framework.Assert.assertNotNull;

public class QuizFragment extends Fragment implements ScreenShotable, CardStack.CardEventListener {
    @BindView(R.id.quiz_root_layout)
    ConstraintLayout quizContainer;
    @BindView(R.id.quiz_question_stack)
    CardStack questionStack;
    @BindView(R.id.quiz_intro_card)
    CardView quizIntro;
    @BindView(R.id.quiz_end_card)
    CardView quizCompleteCard;
    //@BindView(R.id.quiz_completed_image_overlay)
    //ImageView completedImageOverlay;
    //@BindView(R.id.quiz_completed_message)
    //TextView completedMessage;


    SwipeCardAdapter swipeCardAdapter;
    Realm realm;
    User user;
    RetroAPI retroAPI;
    ProgressDialog progressDialog;

    int cardCount = 15;
    int noOfQuestions = 30;
    int lastQuestion = -1;
    int lastQuestionIndex = -1;
    int marks = 0;
    int[] questionArray;
    private boolean isLeader;
    private boolean finished = false;
    private QuizQuestionModel[] quizDatabase;
    private boolean initialDataReceived = false;
    private boolean updateSent = true;
    private Bitmap bitmap;
    public static final String BARCODE_KEY = "BARCODE";
    private Barcode barcodeResult;
    private TextView result;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor1;
    Button button;
    //private TextView mTextField;
    public int counter;
    public  String name="N/A";
    static String message;
    String scan1="false";
    String scan="false";
    DataSnapshot ds;
    String flag;




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        result = (TextView) getView().findViewById(R.id.barcodeResult);

        //message = "Scan the QR Code to get suitable Message!";
        button=(Button) getView().findViewById(R.id.messagebutton);
        quizIntro=(CardView) getView().findViewById(R.id.quiz_intro_card);
        quizCompleteCard=(CardView) getView().findViewById(R.id.quiz_end_card);
        //mTextField = (TextView) getView().findViewById(R.id.timer_text);
        final FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        SharedPreferences sharedPreferencesU=getActivity().getSharedPreferences("acm.event.codetocreate18.View.Authentication", Context.MODE_PRIVATE);
        name=sharedPreferencesU.getString("user","");
        sharedPreferences1 = getActivity().getSharedPreferences("acm.event.codetocreate18.View.Fragments",Context.MODE_PRIVATE);
        flag=sharedPreferences1.getString("flag","");
        if(flag.equals("1")){
            result.setText("Check your Coupon!");
        }
        if (Constants.isGuest) {
            //fab.setVisibility(View.INVISIBLE);
            //mTextField.setText("Scan Disabled");
            //result.setText("You need to Login first to use the scanner!");
            quizCompleteCard.setVisibility(View.VISIBLE);
            quizIntro.setVisibility(View.INVISIBLE);
        }
        if(Constants.isGuest==false){
            //fab.setVisibility(View.VISIBLE);
            //mTextField.setText("Scan Enabled");
            quizCompleteCard.setVisibility(View.INVISIBLE);
            quizIntro.setVisibility(View.VISIBLE);
        }
        assertNotNull(result);
        assertNotNull(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScan();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                sharedPreferences1 = getActivity().getSharedPreferences("acm.event.codetocreate18.View.Fragments",Context.MODE_PRIVATE);
                flag=sharedPreferences1.getString("flag","");
                if (flag.equals("1"))
                {


                    sharedPreferences1 = getActivity().getSharedPreferences("acm.event.codetocreate18.View.Fragments",Context.MODE_PRIVATE);
                    editor1= sharedPreferences1.edit();
                    editor1.putString("flag","0");
                    editor1.commit();
                    result.setText("Scan the QR Code!");
                    Intent intent = new Intent(getActivity(), scannerMessage.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getActivity(), "Scan the QR code first!", Toast.LENGTH_SHORT).show();

                }


            }
        });
        //assertNotNull(result);
        //assertNotNull(quizStartButton);
        if (savedInstanceState != null) {
            Barcode restoredBarcode = savedInstanceState.getParcelable(BARCODE_KEY);
            if (restoredBarcode != null) {
               result.setText(barcodeResult.rawValue);
               barcodeResult = restoredBarcode;

                //new Handler().post(new Runnable() {
                  //  public void run() {
                       /* CountDownTimer timer =new CountDownTimer(30000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                mTextField.setText("Scan Disabled: " + millisUntilFinished / 1000);
                            }

                            public void onFinish() {
                                mTextField.setText("Scan Enabled");
                            }
                        }.start();*/
                    //}
                /*new CountDownTimer(30000, 1000){
                    public void onTick(long millisUntilFinished){
                        mTextField.setText(String.valueOf(counter));
                        counter++;
                    }
                    public  void onFinish(){
                        mTextField.setText("FINISH!!");
                    }
                }.start();*/

            }//);

        }


    }//}


    @Override
    public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
        }

    private void startScan() {
        /**
         * Build a new MaterialBarcodeScanner
         */


        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(getActivity())
                .withEnableAutoFocus(true)
                .withBleepEnabled(false)
                .withBackfacingCamera()
                .withCenterTracker()
                .withText("Scanning...")
                .withResultListener(new MaterialBarcodeScanner.OnResultListener() {
                    @Override
                    public void onResult(Barcode barcode) {
                        barcodeResult = barcode;


                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference userRef = rootRef.child("Users");
                        ValueEventListener eventListner = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot datasnapshot) {

                                for(DataSnapshot ds : datasnapshot.getChildren() ){
                                    String user = ds.getKey();
                                    if(user.equals(name)) {

                                        if (barcodeResult.rawValue.equals("R!e@f#r$e%s^h&m*e(n)t-s=_C9C2")) {
                                            String scanstatus = ds.child("refreshmentstatus").getValue(String.class);
                                            if (scanstatus.equals("false")) {
                                                result.setText("Check your Coupon!");
                                                //flag=1;
                                                sharedPreferences1 = getActivity().getSharedPreferences("acm.event.codetocreate18.View.Fragments",Context.MODE_PRIVATE);
                                                editor1= sharedPreferences1.edit();
                                                editor1.putString("flag","1");
                                                editor1.commit();
                                                    //mTextField.setText("Scan Disabled");
                                                    //Intent intent = new Intent(getActivity(), scannerMessage.class);
                                                    //intent.putExtra("success", "Proceed to have your meal!");
                                                    //startActivity(intent);
                                                message="Proceed to have your meal!";
                                                    //scanMessage(message);

                                                scan="true";
                                                ds.child("refreshmentstatus").getRef().setValue(scan);


                                            } else {
                                                //result.setText("You already had your meal!");
                                                //mTextField.setText("Scan Disabled");
                                                result.setText("Check your Coupon!");
                                                message="You already had your meal!";
                                                sharedPreferences1 = getActivity().getSharedPreferences("acm.event.codetocreate18.View.Fragments",Context.MODE_PRIVATE);
                                                editor1= sharedPreferences1.edit();
                                                editor1.putString("flag","1");
                                                editor1.commit();
                                                //flag=1;

                                            }
                                        }

                                        if (barcodeResult.rawValue.equals("A!t@t#e$n%d^a&n*c(e)_C9C2")) {
                                            String scanstatus1 = ds.child("checkinstatus").getValue(String.class);
                                            if (scanstatus1.equals("false")) {
                                                result.setText("Check your Coupon!");
                                                //mTextField.setText("Scan Enabled");
                                                message="Welcome and Happy Hacking!";
                                                sharedPreferences1 = getActivity().getSharedPreferences("acm.event.codetocreate18.View.Fragments",Context.MODE_PRIVATE);
                                                editor1= sharedPreferences1.edit();
                                                editor1.putString("flag","1");
                                                editor1.commit();
                                                scan1="true";
                                                //flag=1;
                                                ds.child("checkinstatus").getRef().setValue(scan1);
                                                //Intent intent = new Intent(getActivity(), scannerMessage.class);
                                                //intent.putExtra("success", "Welcome and Happy Hacking!!");
                                                //startActivity(intent);
                                            } else {
                                                result.setText("Check your Coupon!");
                                                //mTextField.setText("Scan Enabled");
                                                message="You're already marked Present!";
                                                sharedPreferences1 = getActivity().getSharedPreferences("acm.event.codetocreate18.View.Fragments",Context.MODE_PRIVATE);
                                                editor1= sharedPreferences1.edit();
                                                editor1.putString("flag","1");
                                                editor1.commit();
                                                //flag=1;
                                            }


                                        }

                                    }


                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        };
                        userRef.addListenerForSingleValueEvent(eventListner);

                    }
                }).build();
        materialBarcodeScanner.startScan();
    }

    public static String returnMessage()
    {
        return message;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BARCODE_KEY, barcodeResult);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != MaterialBarcodeScanner.RC_HANDLE_CAMERA_PERM) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startScan();
            return;
        }
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error")
                .setMessage(R.string.no_camera_permission)
                .setPositiveButton(android.R.string.ok, listener)
                .show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, rootView);

        questionStack.setContentResource(R.layout.fragment_quiz_question);
        questionStack.setStackMargin(18);
        questionStack.setListener(this);

        swipeCardAdapter = new SwipeCardAdapter(getActivity().getApplicationContext(), 0);

        Realm.init(this.getActivity());
        realm = Realm.getDefaultInstance();
        user = realm.where(User.class).findFirst();

        quizDatabase = new DataGenerator().getQuizDatabase();
        retroAPI = new RetroAPI();
        questionArray = new int[15];

        /*if (Constants.isGuest) {
            isLeader = user.isLeader;
            if (Constants.accessToken.equals("Unauthorized")) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.sharedPreferenceName, MODE_PRIVATE);
                Constants.accessToken = sharedPreferences.getString("authtoken", "Unauthorized");
            }
        }

        if (!Constants.isGuest)
            if (isLeader)
                getQuizData();*/

        return rootView;
    }

    @Override
    public boolean swipeEnd(int section, float distance) {
        return (distance > 300) ? true : false;
    }

    @Override
    public boolean swipeStart(int section, float distance) {
        return true;
    }

    @Override
    public boolean swipeContinue(int section, float distanceX, float distanceY) {
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        if (distance > 250) {
            RadioButton choice;
            if (section == 0)
                choice = (RadioButton) questionStack.getTopView().findViewById(R.id.quiz_choice_1);
            else if (section == 1)
                choice = (RadioButton) questionStack.getTopView().findViewById(R.id.quiz_choice_2);
            else if (section == 2)
                choice = (RadioButton) questionStack.getTopView().findViewById(R.id.quiz_choice_3);
            else
                choice = (RadioButton) questionStack.getTopView().findViewById(R.id.quiz_choice_4);
            choice.setChecked(true);
        }
        return true;
    }

    @Override
    public void discarded(int mIndex, int direction) {
        cardCount--;
        lastQuestionIndex++;
        int choice = quizDatabase[questionArray[14 - cardCount] - 1].correctChoice;
        if (choice == direction)
            marks++;
        updateSent = false;
        if (cardCount == 0) {
            quizFinished();
            return;
        }
        lastQuestion = questionArray[14 - cardCount];
    }

    //@OnClick(R.id.quiz_start_button)
    //public void onStartRequest(View v) {
    //}


    public void getQuizData() {
        String accessToken = Constants.accessToken;
        if (!initialDataReceived) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Fetching Quiz Data...");
            progressDialog.show();
            progressDialog.setCancelable(false);
        } else {
            //quizStartButton.setText("Fetching Quiz Data...");
            //quizStartButton.setClickable(false);
        }

        retroAPI.observableAPIService.getQuizData(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!initialDataReceived) {
                            progressDialog.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setMessage("Could not connect to server!")
                                    .setCancelable(false)
                                    .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            getQuizData();
                                        }
                                    })
                                    .setNegativeButton("HOME", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                      //      ((MainActivity) getActivity()).animatedLoadTeamFragment();
                                        }
                                    });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        } else {
                            //quizStartButton.setText("Start Quiz");
                            //quizStartButton.setClickable(true);
                            Snackbar snackbar = Snackbar
                                    .make(quizContainer, "Could not connect to server!", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.get("success").getAsBoolean()) {
                            boolean isLive = jsonObject.get("isLive").getAsBoolean();
                            if (isLive) {
                                boolean started = jsonObject.get("started").getAsBoolean();
                                boolean finished = jsonObject.get("finished").getAsBoolean();
                                if (started && !finished) {
                                    JsonArray qArray = jsonObject.getAsJsonObject("quiz").getAsJsonArray("qArray");
                                    for (int i = 0; i < qArray.size(); i++)
                                        questionArray[i] = qArray.get(i).getAsInt();
                                    lastQuestion = jsonObject.getAsJsonObject("quiz").get("lastQ").getAsInt();
                                    marks = jsonObject.getAsJsonObject("quiz").get("marks").getAsInt();
                                    if (lastQuestion == -1) {
                                        cardCount = 15;
                                        lastQuestionIndex = -1;
                                    } else {
                                        for (int i = 0; i < qArray.size(); i++)
                                            if (questionArray[i] == lastQuestion) {
                                                cardCount = 14 - i;
                                                lastQuestionIndex = i;
                                                break;
                                            }
                                    }
                                    progressDialog.dismiss();
                                    showQuiz();
                                } else if (started && finished) {
                                    progressDialog.dismiss();
                                    showQuizFinishedCard();
                                } else {
                                    if (!initialDataReceived) {
                                        progressDialog.dismiss();
                                    } else {
                                        startQuiz();
                                    }
                                }
                            } else {
                                if (initialDataReceived) {
                                    //quizStartButton.setText("Start Quiz");
                                    //quizStartButton.setClickable(true);
                                    Snackbar snackbar = Snackbar
                                            .make(quizContainer, "Quiz is not live!!", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                } else {
                                    initialDataReceived = true;
                                    progressDialog.dismiss();
                                }
                            }
                            if (!initialDataReceived)
                                initialDataReceived = true;
                        } else {
                        }
                    }
                });
    }

    public void showQuiz() {
        for (int i = lastQuestionIndex + 1; i < 15; i++)
            swipeCardAdapter.add(quizDatabase[questionArray[i] - 1]);
        questionStack.setAdapter(swipeCardAdapter);
        Animation slideOutAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out);
        quizIntro.startAnimation(slideOutAnimation);
        quizIntro.setVisibility(View.INVISIBLE);
        questionStack.setVisibility(View.VISIBLE);
        Animation growAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.grow);
        questionStack.startAnimation(growAnimation);

        Thread quizDataUpdateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!finished) {
                    if ((cardCount % 3) == 0 && updateSent == false && cardCount != 0) {
                        updateQuestionData();
                        updateSent = true;
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        quizDataUpdateThread.start();
    }

    public void startQuiz() {
        String accessToken = Constants.accessToken;
        //quizStartButton.setText("Initializing Quiz...");
        for (int i = 0; i < 15; i++)
            questionArray[i] = -1;
        for (int i = 0; i < 15; i++) {
            int random = (int) (Math.random() * noOfQuestions) + 1;
            outer:
            while (true) {
                for (int j = 0; j < i; j++) {
                    if (questionArray[j] == random) {
                        random++;
                        if (random == noOfQuestions)
                            random = 0;
                        continue outer;
                    }
                }
                break;
            }
            questionArray[i] = random;
        }
        Arrays.sort(questionArray);
        ArrayList<Integer> qArray = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            qArray.add(questionArray[i]);

        long time = System.currentTimeMillis();
        retroAPI.observableAPIService.startQuiz(accessToken, time, qArray)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        //quizStartButton.setText("Start Quiz");
                        //quizStartButton.setClickable(true);
                        Snackbar snackbar = Snackbar
                                .make(quizContainer, "Could not connect to server!", Snackbar.LENGTH_LONG)
                                .setAction("RETRY", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startQuiz();
                                    }
                                });
                        snackbar.show();
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.get("success").getAsBoolean()) {
                            showQuiz();
                        } else {
                        }
                    }
                });
    }

    public void updateQuestionData() {
        String accessToken = Constants.accessToken;
        retroAPI.observableAPIService.updateQuizData(accessToken, lastQuestion, marks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Could not connect to server!")
                                .setCancelable(false)
                                .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        showUpdateProgressDialog();
                                    }
                                })
                                .setNegativeButton("HOME", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                       // ((MainActivity) getActivity()).animatedLoadTeamFragment();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.get("success").getAsBoolean()) {
                            boolean isLive = jsonObject.get("isLive").getAsBoolean();
                            if (isLive) {
                                if (progressDialog.isShowing())
                                    progressDialog.dismiss();
                            } else {
                                quizFinished();
                            }
                        } else {
                        }
                        ;
                    }
                });
    }

    public void showUpdateProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Reconnecting...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        updateQuestionData();
    }

    public void quizFinished() {
        String accessToken = Constants.accessToken;
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading Final Score...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        long time = System.currentTimeMillis();
        retroAPI.observableAPIService.finishQuiz(accessToken, time, marks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Could not connect to server!")
                                .setCancelable(false)
                                .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        quizFinished();
                                    }
                                })
                                .setNegativeButton("HOME", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                   //     ((MainActivity) getActivity()).animatedLoadTeamFragment();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.get("success").getAsBoolean()) {
                            progressDialog.dismiss();
                            showQuizFinishedCard();
                        } else {
                        }
                    }
                });
    }

    public void showQuizFinishedCard() {
        finished = true;
        quizIntro.setVisibility(View.GONE);
        questionStack.setVisibility(View.GONE);
        quizCompleteCard.setVisibility(View.VISIBLE);
        final Animation growAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.grow);
        growAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //completedImageOverlay.setVisibility(View.VISIBLE);
                //completedMessage.setVisibility(View.VISIBLE);
                Animation growAnimation2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.grow);
                //completedImageOverlay.startAnimation(growAnimation2);
                //completedMessage.startAnimation(growAnimation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        quizCompleteCard.startAnimation(growAnimation);
    }

    @Override
    public void topCardTapped() {
    }

    @Override
    public void takeScreenShot() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(quizContainer.getWidth(),
                        quizContainer.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                quizContainer.draw(canvas);
                QuizFragment.this.bitmap = bitmap;
            }
        });
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}



