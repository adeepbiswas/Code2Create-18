package acm.event.codetocreate18.View.Fragments;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;

import acm.event.codetocreate18.Model.Data.DataGenerator;
import acm.event.codetocreate18.R;
import acm.event.codetocreate18.Utility.Adapters.FaqAdapter;
import acm.event.codetocreate18.Model.Data.FaqAnswerModel;
import acm.event.codetocreate18.Model.Data.FaqQuestionModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import yalantis.com.sidemenu.interfaces.ScreenShotable;


public class FaqFragment extends Fragment implements ScreenShotable {

    public FaqAdapter adapter;

    @BindView(R.id.faq_root_view)
    ConstraintLayout faqContainer;

    @BindView(R.id.faq_recycler_view)
    RecyclerView recyclerView;

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
        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        ArrayList<FaqQuestionModel> finalQuestions = new ArrayList<>();
        DataGenerator dataGenerator = new DataGenerator();
        String[] questions = dataGenerator.getQuestions();
        String[] answers = dataGenerator.getAnswers();

        for(int i = 0; i < questions.length; i++) {
            ArrayList<FaqAnswerModel> answer = new ArrayList<>();
            answer.add(new FaqAnswerModel(answers[i]));
            FaqQuestionModel question = new FaqQuestionModel(questions[i], answer);
            finalQuestions.add(question);
        }

        adapter = new FaqAdapter(finalQuestions);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void takeScreenShot() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(faqContainer.getWidth(),
                        faqContainer.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                faqContainer.draw(canvas);
                FaqFragment.this.bitmap = bitmap;
            }
        });
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}